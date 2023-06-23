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
	
	private boolean validarClave(String clave) {
		try {
			String sentencia = "select clave from administrador where clave = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, clave);
				
				return statement.execute();
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean registrar(Administrador administrador) {
		
		if(!validarClave(administrador.getClave())) {
			return false;
		}
		
		try {
			
			String sentencia = "insert into administrador(nombre, apellido, email, password, sesion_iniciada, super_admin) "
					+ "values(?,?,?,?,?,?);";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, administrador.getNombre());
				statement.setString(2, administrador.getApellido());
				statement.setString(3, administrador.getEmail());
				statement.setString(4, administrador.getPassword());
				statement.setBoolean(5, administrador.getSesion_iniciada());
				statement.setBoolean(6, administrador.isSuper_admin());
				
				return statement.execute();
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean sesion(Administrador administrador) {
		return true;
	}
}
