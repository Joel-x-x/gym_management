package com.gym.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection conectar() {
		try {
			System.out.println("Conexion exitosa");
			return DriverManager.getConnection("jdbc:mysql://localhost/bdd_gym?serverTimezone=UTC", "root", "root");
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
