package zsp_2701170229.servlet;

import zsp_2701170229.bean.User;
import zsp_2701170229.dao.UserDao;
import zsp_2701170229.utils.EmailModel;
import zsp_2701170229.utils.EmailUtils;
import zsp_2701170229.utils.RandomUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * 登录操作
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserDao userDao = new UserDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
		} else if (action != null && action.equals("login")) {
			String username = request.getParameter("username").trim();
			String password = request.getParameter("pwd");
			User user = userDao.login(username, password);
			if (user != null) {
				request.getSession().setAttribute("loginUser", user);
				response.getOutputStream().print(0);
			} else {
				response.getOutputStream().print(1);//用户名或密码错误
				return;
			}
		} else if (action != null && action.equals("findPwd")) {//跳转到找回密码页面
			request.getRequestDispatcher("findPwd.jsp").forward(request, response);
			return;
		}else if (action != null && action.equals("sendCode")) {//发送验证码
			String username = request.getParameter("username").trim();
			String email = request.getParameter("email").trim();
			User result=userDao.findByUsername(username);
			if(result==null){
				response.getOutputStream().print(-1);//用户不存在
			}else{
				int authCode=RandomUtil.getIntRandom(4);//验证码生成
				request.getSession().setAttribute("sessionCode",authCode);
				EmailModel emailModel=new EmailModel();
				emailModel.setReceiverEmail(email);
				emailModel.setText(EmailModel.register+authCode);
				EmailUtils.sendEmail(emailModel);
				response.getOutputStream().print(0);//邮件发送成功
			}
		}else if (action != null && action.equals("checkCodePage")) {//跳转验证邮箱页面
			String username=request.getParameter("username");
			request.setAttribute("username",username);
			request.getRequestDispatcher("checkCode.jsp").forward(request, response);
			return;
		} else if (action != null && action.equals("checkCode")) {//效验验证码
			String username = request.getParameter("username").trim();
			String newPwd = request.getParameter("newPwd").trim();
			String authCode = request.getParameter("authCode").trim();
			if(request.getSession().getAttribute("sessionCode")==null){
				response.getOutputStream().print(-2);//验证码已经过期
				return;
			}else{
				String sessionCode=String.valueOf(request.getSession().getAttribute("sessionCode"));
				User user=userDao.findByUsername(username);
				if(sessionCode!=null&&authCode!=null&&sessionCode.equals(authCode)){
					user.setPwd(newPwd);
					userDao.update(user);
					response.getOutputStream().print(0);
				}else{
					response.getOutputStream().print(-1);
				}
			}
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
	}



}
