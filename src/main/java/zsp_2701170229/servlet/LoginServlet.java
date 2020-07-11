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
 * 登录操作
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UserDao userDao = new UserDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("logout")) {
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
		} else if (action != null && action.equals("login")) {
			String username = request.getParameter("username").trim();
			String password = request.getParameter("password");
			User user = userDao.login(username, password);
			if (user != null) {
				request.getSession().setAttribute("loginUser", user);
				response.sendRedirect("user");
			} else {
				request.setAttribute("msg", "用户名或密码错误！！");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				return;
			}
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}


	}


}
