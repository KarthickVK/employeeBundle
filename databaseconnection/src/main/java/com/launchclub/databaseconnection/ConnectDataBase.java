package com.launchclub.databaseconnection;

import com.launchclub.exception.AccessfailedException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * ConnectDataBase
 * 
 * @author KarthickV
 */

public class ConnectDataBase {

	public static Connection getConnection() {

		try {
			InputStream fileInputStream = new FileInputStream("C:/karaf/etc/system.properties");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			
			final String url = properties.getProperty("karaf.jdbc.url");
			final String username = properties.getProperty("karaf.jdbc.user");
			final String password = properties.getProperty("karaf.jdbc.password");

			Class.forName("com.mysql.cj.jdbc.Driver");
			final Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (Exception exception) {
			throw new AccessfailedException(exception.getMessage());
		}
	}
}
