package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn = null;
	// 절대 경로로 지정, 다른 컴퓨터에서 프로젝트 실행시 경로 변경 필요
	private static String DBURL = "C:\\Users\\MJ_J\\Mission1\\Mission\\src\\main\\java\\Databases\\PublicWifi.db";
	
	public static Connection getConnection() {
		if (conn == null) {
			try {
				Class.forName("org.sqlite.JDBC");
				conn = DriverManager.getConnection("jdbc:sqlite:" + DBURL);
				return conn;
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
