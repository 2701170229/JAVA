package zsp_2701170229.servlet;

import zsp_2701170229.bean.SettleInfo;
import zsp_2701170229.bean.User;
import zsp_2701170229.dao.CartDao;
import zsp_2701170229.dao.SettleDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 购物车结算
 */
@WebServlet("/settleList")
public class SettleServlet extends HttpServlet {
	private CartDao cartDao = new CartDao();
	private SettleDao settleDao = new SettleDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String action=request.getParameter("action");
		String cartIds=request.getParameter("cartIds");	//获取到前端传递过来的购物车id
		User user=(User)request.getSession().getAttribute("loginUser");
		if(user==null){
			response.getOutputStream().print(-1);//没有登录
			return;
		}
		//添加结算信息
		if("settle".equalsIgnoreCase(action)){
			String price=request.getParameter("price");
			String num=request.getParameter("num");
			SettleInfo settleInfo=new SettleInfo();
			settleInfo.setGoodsIds(cartIds);
			settleInfo.setPrice(price);
			settleInfo.setUserId(user.getId());
			settleInfo.setCreateTime(getNow());
			settleInfo.setNum(Integer.valueOf(num));
			settleDao.addSettle(settleInfo);
			for(String str:cartIds.split(",")){
				cartDao.delCart(Integer.valueOf(str));
			}
			response.getOutputStream().print(0);//添加成功
		}else{
			List<SettleInfo> settleList=settleDao.getSettleList(user.getId());
			request.setAttribute("settleList",settleList);
			request.getRequestDispatcher("settleList.jsp").forward(request, response);
			return;
		}
	}

	//获取当前时间
	private static String getNow(){
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return simpleDateFormat.format(new Date());
	}
}
