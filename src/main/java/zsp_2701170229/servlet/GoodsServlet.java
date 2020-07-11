package zsp_2701170229.servlet;

import zsp_2701170229.bean.Goods;
import zsp_2701170229.bean.User;
import zsp_2701170229.dao.GoodsDao;
import zsp_2701170229.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 商品列表
 */
@WebServlet("/goods")
public class GoodsServlet extends HttpServlet {
	private GoodsDao goodsDao = new GoodsDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List<Goods> goodsList=goodsDao.getGoodsList();
		request.setAttribute("goodsList",goodsList);
		request.getRequestDispatcher("goodsList.jsp").forward(request, response);
		return;


	}


}
