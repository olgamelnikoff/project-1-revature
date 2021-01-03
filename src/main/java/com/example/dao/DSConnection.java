package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DSConnection {
	private static String url = "jdbc:mariadb://revature-db.c1jnfhxqwao5.us-east-1.rds.amazonaws.com:3306/Project1";
	private static String username = "project1user";
	private static String password = "mypassword";
	
	public DSConnection() {
		// TODO Auto-generated constructor stub
	}

	  public Connection getDBConnection() throws SQLException {
	    return DriverManager.getConnection(url, username, password);
	  }

}
