package com.wide.task.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLSelectTest {

	public static void main(String[] args) {
			// TODO Auto-generated method stub
			String username = "root";
			String password = "";
			String port = "3306";
			String jdbcUrl = "jdbc:mysql://localhost:3306/db1";
			
			try {
//				Load Driver
				Class.forName("com.mysql.jdbc.Driver");
				
//				get db connection
				Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
				
				String sqlQuery = "SELECT * FROM customer";
				
				Statement stm = conn.createStatement();
				
				ResultSet rs = stm.executeQuery(sqlQuery);
				
//				Read result
				while(rs.next()) {
					System.out.println(rs.getInt(1));
					System.out.println(rs.getString("name"));
					System.out.println(rs.getString("address"));
					System.out.println(rs.getInt("dependent"));
					System.out.println("====================");
				}
				
				if(conn != null) {
					System.out.println("DB Connected!");
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}


