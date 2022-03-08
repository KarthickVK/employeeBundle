package com.launchclub.connectDataBase;

import com.launchclub.Exception.AccessfailedException;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 * ConnectDataBase
 * 
 * @author KarthickV
 */

public class ConnectDataBase {

	private static final String DB_HOST = "jdbc:mysql://localhost:3306/databasedb";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "Karthi33";

	/**
	 * Create the mySql database connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			final Connection connection = DriverManager.getConnection(DB_HOST, USER_NAME, PASSWORD);
			return connection;
		} catch (Exception exception) {
			throw new AccessfailedException("FAILED TO CONNECTION");
		}
	}
}
