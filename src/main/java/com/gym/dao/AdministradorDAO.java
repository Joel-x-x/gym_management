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
				
				statement.execute();
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet) {
					return resultSet.next();
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	private boolean eliminarClave(String clave) {
		try {
			String sentencia = "update administrador set clave = null where clave = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, clave);
				
				int item = statement.executeUpdate();
				
				return toBoolean(item);
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean toBoolean(int numero) {
		if(numero != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean iniciarSesion(Administrador administrador) {

		try {
			
			String sentencia = "SELECT * FROM administrador where email = ? and password = ? ";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, administrador.getEmail());
				statement.setString(2, administrador.getPassword());
				
				statement.execute();
				
				
				final ResultSet resultSet = statement.getResultSet();
				
				try(resultSet) {
					return resultSet.next();
				}
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
			
			String sentencia = "insert into administrador(nombre, email, password, sesion_iniciada, super_admin) "
					+ "values(?,?,?,?,?);";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, administrador.getNombre());
				statement.setString(2, administrador.getEmail());
				statement.setString(3, administrador.getPassword());
				statement.setInt(4, administrador.getSesion_iniciada());
				statement.setInt(5, administrador.isSuper_admin());
				
				int item = statement.executeUpdate();
				
				if(toBoolean(item)) {
					eliminarClave(administrador.getClave());
				}
				
				return toBoolean(item);
			}
				
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean sesion(Administrador administrador) {
		return true;
	}
}
