package practice.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/test";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";
	
	private static Connection conn = null;
	
	static {
		
		try {
			// 1.加载驱动
			Class.forName("com.mysql.jdbc.Driver");
	
			// 2获得数据库的连接
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static void main (String[] args) throws Exception {
		
		//1.加载驱动(反射)
		Class.forName("com.mysql.jdbc.Driver");
		//2获得数据库的连接
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		//3操作数据库
		Statement state = conn.createStatement();
		ResultSet resultSet = state.executeQuery("select practice.username,practice.sex,"
				+ "practice.age from practice");
		
		while (resultSet.next()){
			System.out.println("我服了");
			System.out.println("姓名"+resultSet.getString("username")+"性别"+resultSet.getString(2)+"年龄"+resultSet.getInt(3));
		}
		
	}
}
