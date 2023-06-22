package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gym.model.Administrador;

public class AdministradorDAO {
	Connection con;
	
	public AdministradorDAO(Connection con) {
		this.con = con;
	}

	public Integer guardar(Administrador administrador) {
		try {
			
			String sentencia = "insert into administrador(nombre, apellido, email, password, sesion_iniciada, super_admin) "
					+ "values(?,?,?,?,?,?);";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, administrador.getNombre());
				statement.setString(2, administrador.getNombre());
				statement.setString(3, administrador.getNombre());
				statement.setString(4, administrador.getNombre());
				statement.setString(5, administrador.getNombre());
				statement.setString(6, administrador.getNombre());
				
				
			}
				
				
			return 0;	
			
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean login(String usuario, String contra) {
		try {
			
			String sentencia = "select email, password from administrador where email = ? and password = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, usuario);
				statement.setString(2, contra);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					return resultSet.next();	
				}
				
			}
				
				
			
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
