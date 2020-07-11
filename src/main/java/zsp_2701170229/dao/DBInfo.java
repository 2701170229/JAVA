package zsp_2701170229.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBInfo {

	String url = null;
	String username = null;
	String password = null;
	String driverClass = null;
	
	private static DBInfo db = new DBInfo();

	public static DBInfo getInstance(){
		return db;
	}


	private DBInfo() {
		try {
//			url = "jdbc:mysql://localhost:3306/shopCartDb?useUnicode=true&characterEncoding=utf8";
			url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8";
			username = "root";
			password = "root123";
			driverClass = "com.mysql.jdbc.Driver";
			
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection(){
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
