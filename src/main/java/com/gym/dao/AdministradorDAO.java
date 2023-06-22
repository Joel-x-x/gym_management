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
				statement.setString(2, administrador.getApellido());
				statement.setString(3, administrador.getEmail());
				statement.setString(4, administrador.getPassword());
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

	public Object[][] consulta(Administrador administrador) {
		
try {
			Object obj[][]=null;
			String sentencia = "select from administrador";
					
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				final ResultSet resultSet = statement.executeQuery();
				int filas=0;
				while(resultSet.next()) {
					obj[filas][0]=resultSet.getObject(1);
					obj[filas][1]=resultSet.getObject(2);
					obj[filas][2]=resultSet.getObject(3);
					obj[filas][3]=resultSet.getObject(4);
					obj[filas][4]=resultSet.getObject(5);
					obj[filas][5]=resultSet.getObject(6);
					obj[filas][6]=resultSet.getObject(7);
					obj[filas][7]=resultSet.getObject(8);
					filas++;
					
				}
			
				
				
			}
				
				
			return obj;	
			
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
