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
@WebServlet("register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 2289146343305887782L;
	private UserDao userDao=new UserDao();
	

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String username=request.getParameter("username").toString();
		String password=request.getParameter("password").toString();
		if(userDao.findByUsername(username)){
				request.setAttribute("msg", "用户名已存在");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		else{
			User user=userDao.addUser(username, password);
			if(user!=null){
				request.setAttribute("msg2", "注册成功");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
		}
	}



}
