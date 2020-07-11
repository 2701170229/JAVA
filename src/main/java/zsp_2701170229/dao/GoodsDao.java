package zsp_2701170229.dao;

import zsp_2701170229.bean.Goods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GoodsDao {

	private DBInfo db = DBInfo.getInstance();

	//根据id查询商品信息
	public Goods findGoodsById(int id) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Goods goods = null;
		try {
			ps = conn.prepareStatement("select * from t_goods where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				goods = new Goods();
				goods.setId(rs.getLong(1));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setNum(rs.getInt("num"));
				goods.setPicture(rs.getString("picture"));
				goods.setPrice(rs.getString("price"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseDB(conn,ps,rs);
		}
		return goods;
	}

	//查询找所有商品列表
	public List<Goods> getGoodsList() {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list=new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from t_goods ");
			rs = ps.executeQuery();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setId(rs.getLong(1));
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setNum(rs.getInt("num"));
				goods.setPicture(rs.getString("picture"));
				goods.setPrice(rs.getString("price"));
				list.add(goods);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseDB(conn,ps,rs);
		}
		return list;
	}


	public int addUser(String username, String password) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = conn.prepareStatement("insert into t_user values(null,?,?)");

			ps.setString(1, username);
			ps.setString(2, password);
			ps.execute();

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



	public void deleteUserByIds(int[] ids) {
		if (ids == null || ids.length <= 0) {
			return;
		}
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ids.length; i++) {
				if (i == ids.length - 1) {
					sb.append("?");
				} else {
					sb.append("?,");
				}
			}
			String in = sb.toString();
			ps = conn.prepareStatement("delete from user where id in (" + in+ ")");
			for(int i = 0 ;i<ids.length;i++){
				ps.setInt(i+1, ids[i]);
			}
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
