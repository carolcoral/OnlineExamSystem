package cn.xdl.exam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class DBUtils {
	private static String DBCONFIGPATH = "cn.xdl.exam.util.db_config";// 配置文件的位置
	private static String USERNAME;
	private static String PWD;
	private static String URL;
	private static String DRIVER;
	// private static String MAXACTIVE;
	// private static String MAXIDLE;
	// private static String MAXWAIT;
	// private static String MINIDLE;

	// 加载数据
	static {
		// 1.创建操作资源配置文件的对象
		ResourceBundle rs = ResourceBundle.getBundle(DBCONFIGPATH);
		// Properties properties = new Properties();
		// properties.getProperty(DBCONFIGPATH);
		// 2.从此对象中获取配置文件中的数据
		USERNAME = rs.getString("username");
		PWD = rs.getString("pwd");
		URL = rs.getString("url");
		DRIVER = rs.getString("driver");
		// MAXACTIVE = properties.getProperty(key);
		// 3. 注册数据库驱动
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PWD);
	}

	/**
	 * 释放数据库连接资源
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void close(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
