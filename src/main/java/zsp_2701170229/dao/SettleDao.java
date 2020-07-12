package zsp_2701170229.dao;

import zsp_2701170229.bean.SettleInfo;
import zsp_2701170229.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettleDao {

	private DBUtils db = DBUtils.getInstance();

	//获取所有结算信息
	public List<SettleInfo> getSettleList(int userId) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<SettleInfo> list=new ArrayList<>();
		try {
			ps = conn.prepareStatement("select * from t_settle where userId=? ");
			ps.setInt(1,userId);
			rs = ps.executeQuery();
			while (rs.next()) {
				SettleInfo settleInfo = new SettleInfo();
				settleInfo.setId(rs.getInt(1));
				settleInfo.setCreateTime(rs.getString("createTime"));
				settleInfo.setUserId(rs.getInt("userId"));
				settleInfo.setGoodsIds(rs.getString("goodsIds"));
				settleInfo.setPrice(rs.getString("price"));
				settleInfo.setNum(rs.getInt("num"));
				list.add(settleInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseDB(conn,ps,rs);
		}
		return list;
	}

	//添加结算信息
	public int addSettle(SettleInfo settleInfo) {
		Connection conn = db.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("insert into t_settle(id,price,createTime,userId,goodsIds,Num) values(null,?,?,?,?,?)");
			ps.setString(1, settleInfo.getPrice());
			ps.setString(2, settleInfo.getCreateTime());
			ps.setInt(3, settleInfo.getUserId());
			ps.setString(4, settleInfo.getGoodsIds());
			ps.setInt(5, settleInfo.getNum());
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

}
