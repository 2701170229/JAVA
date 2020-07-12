package zsp_2701170229.utils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 数据库操作工具类
 */
public class DBUtils {
	String url = null;			//连接地址
	String username = null;		//数据库名
	String password = null;		//数据库密码
	String driverClass = null;	//连接驱动
	private static DBUtils db = new DBUtils();
	/**构建数据库连接参数*/
	private DBUtils() {
		try {
			url = "jdbc:mysql://localhost:3306/shopCartDb?useUnicode=true&characterEncoding=utf8";
			username = "root";
			password = "root123";
			driverClass = "com.mysql.jdbc.Driver";
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**构建数据库连接对象*/
	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static DBUtils getInstance(){
		return db;
	}
}
