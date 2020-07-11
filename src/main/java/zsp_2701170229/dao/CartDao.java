package zsp_2701170229.dao;

import zsp_2701170229.bean.Cart;
import zsp_2701170229.bean.Goods;
import zsp_2701170229.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

	private DBInfo db = DBInfo.getInstance();

	public Cart findCartById(int id,int userId) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Cart cart = null;
		try {
			ps = conn.prepareStatement("select * from t_cart where goodsId=? and userId=?");
			ps.setInt(1, id);
			ps.setInt(2, userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				cart = new Cart();
				cart.setId(rs.getLong(1));
				cart.setGoodsName(rs.getString("goodsName"));
				cart.setNum(rs.getInt("num"));
				cart.setPicture(rs.getString("picture"));
				cart.setPrice(rs.getString("price"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseDB(conn,ps,rs);
		}
		return cart;
	}


	//查询找所有购物车列表
	public List<Cart> getCartList(User user) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Cart> list=new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from t_cart where userId=? ");
			ps.setInt(1, user.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Cart cart = new Cart();
				cart.setId(rs.getLong(1));
				cart.setGoodsName(rs.getString("goodsName"));
				cart.setNum(rs.getInt("num"));
				cart.setPicture(rs.getString("picture"));
				cart.setPrice(rs.getString("price"));
				list.add(cart);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseDB(conn,ps,rs);
		}
		return list;
	}


	//添加购物车
	public int addCart(Cart cart) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Cart isAdd=findCartById(cart.getGoodsId(),cart.getUserId());
			if(null!=isAdd){//判断是否添加过购物车
				ps = conn.prepareStatement("update t_cart set num = ? where id = ?");
				ps.setInt(1, isAdd.getNum()+1);
				ps.execute();
			}else{
				ps = conn.prepareStatement("insert into t_cart values(null,?,?,?,?,?,?)");
				ps.setString(1, cart.getGoodsName());
				ps.setInt(2, cart.getNum());
				ps.setString(3, cart.getPrice());
				ps.setString(4, cart.getPicture());
				ps.setInt(5, cart.getUserId());
				ps.setInt(6, cart.getGoodsId());
				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseDB(conn,ps,rs);
		}
		return 1;

	}


	//关闭数据库操作资源
	private  void colseDB(Connection conn, PreparedStatement ps, ResultSet rs){
		try {
			if (conn != null){
				conn.close();
			}
			if (ps != null){
				ps.close();
			}
			if (rs != null){
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	//移除购物车
	public void delCart(int id) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();

			String in = sb.toString();
			ps = conn.prepareStatement("delete from user where id =?");
			ps.setInt(1,id);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseDB(conn,ps,rs);
		}
	}

	public void update(Goods goods) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("update t_goods set num = ? where id = ?");
			ps.setInt(1, goods.getNum());
			ps.setLong(2, goods.getId());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseDB(conn,ps,rs);
		}
	}

}
