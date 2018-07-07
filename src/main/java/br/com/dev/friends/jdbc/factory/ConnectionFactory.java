package br.com.dev.friends.jdbc.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static Connection connection;

	private ConnectionFactory() {

	}

	public static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");

			if (connection == null) {
				connection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/jbpm-db;MVCC=TRUE", "sa", "");
			}
			return connection;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

	}

}
