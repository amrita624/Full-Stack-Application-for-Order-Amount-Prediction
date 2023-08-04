package com.highradius.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

	public static Connection getConnection() {
		Connection connection = null;
		String url = "jdbc:mysql://localhost/highradius_db";
		String user = "root";
		String password = "AbcXyz@123";
		
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url,user,password);
			System.out.println("Connection Successful");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}