package com.sale.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

		private final static String userName = "root";
		private final static String password = "";
		private final static String jdbcUrl = "jdbc:mysql://localhost:3306/db1";
		private final static String jdbcDriver = "com.mysql.jdbc.Driver";
		
		public static Connection getDataSource() throws ClassNotFoundException, SQLException {
		
			Class.forName(jdbcDriver);
			return DriverManager.getConnection(jdbcUrl,userName,password);
		
		}
}
