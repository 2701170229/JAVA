package zsp_2701170229.servlet;

import zsp_2701170229.bean.Cart;
import zsp_2701170229.bean.Goods;
import zsp_2701170229.bean.User;
import zsp_2701170229.dao.CartDao;
import zsp_2701170229.dao.GoodsDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 购物车
 */
@WebServlet("/shopCart")
public class ShopCartServlet extends HttpServlet {
	private CartDao cartDao = new CartDao();
	private GoodsDao goodsDao = new GoodsDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String action=request.getParameter("action");
		String goodsId=request.getParameter("goodsId");
		User user=(User)request.getSession().getAttribute("loginUser");
		if(user==null){
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//添加购物车
		if("addCart".equalsIgnoreCase(action)){
			Goods goods=goodsDao.findGoodsById(Integer.valueOf(goodsId));
			Cart cart=new Cart();
			cart.setGoodsId(goods.getId());
			cart.setPicture(goods.getPicture());
			cart.setPrice(goods.getPrice());
			cart.setGoodsName(goods.getGoodsName());
			cart.setNum(1);
			cart.setUserId(user.getId());
			cartDao.addCart(cart);
			response.getOutputStream().print(0);//添加成功
		//移除购物车
		}else if ("delCart".equalsIgnoreCase(action)){
			Integer cartId=Integer.valueOf(request.getParameter("cartId"));
			cartDao.delCart(cartId);
			response.getOutputStream().print(0);//移除成功
		}else{
			List<Cart> cartList=cartDao.getCartList(user);
			request.setAttribute("cartList",cartList);
			request.getRequestDispatcher("cartList.jsp").forward(request, response);
			return;
		}



	}


}
