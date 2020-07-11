package zsp_2701170229.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import zsp_2701170229.bean.User;
import zsp_2701170229.dao.UserDao;

/**
 * 注册操作
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 2289146343305887782L;
	private UserDao userDao=new UserDao();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action=request.getParameter("action");
		if(action!=null&&"register".equalsIgnoreCase(action)){//执行注册操作
			String username=request.getParameter("username");
			String password=request.getParameter("pwd");
			if(userDao.findByUsername(username)){
				response.getOutputStream().print("2");//用户名已存在
			}
			else{
				User user=userDao.addUser(username, password);
				if(user!=null){
					response.getOutputStream().print("0");//注册成功
				}
			}
		}else{
			request.getRequestDispatcher("/register.jsp").forward(request, response);//页面跳转
			return;
		}
	}



}
