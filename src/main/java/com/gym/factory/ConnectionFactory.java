package com.gym.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection conectar() {
		try {
			System.out.println("Sesion abierta");
			return DriverManager.getConnection("jdbc:mysql://root:Hni8Udp74e6OJ1Fcacmd@containers-us-west-152.railway.app:6199/railway", "root", "Hni8Udp74e6OJ1Fcacmd");
//			return DriverManager.getConnection("jdbc:mysql://localhost/bdd_gym?serverTimezone=UTC", "root", "");
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void desconectar() {
		try {
			conectar().close();
			System.out.println("Sesion Cerrada");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
