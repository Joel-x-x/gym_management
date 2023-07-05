package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gym.model.Cuenta;
import com.gym.utilidades.Utilidades;

public class CuentaDAO {
	private Connection con;
	
	public CuentaDAO(Connection con) {
		this.con =  con;
	}
	
	public boolean modificarEmpresa(Cuenta cuenta) {
		int item = 0;		
		
		try {
			String sentencia = "update cuenta set nombre_empresa = ?, logo_empresa = ? where administrador_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, cuenta.getNombre_empresa());
				statement.setBytes(2, cuenta.getLogo_empresa());
				statement.setInt(3, cuenta.getAdministrador_id());
				
				item = statement.executeUpdate();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
	}
	
	public boolean modificarPerfil(byte[] perfil, int administrador_id) {
		int item = 0;		
		
		try {
			String sentencia = "update cuenta set imagen_perfil = ? where administrador_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setBytes(1, perfil);
				statement.setInt(2, administrador_id);
				
				item = statement.executeUpdate();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
	}
	
	public byte[] getLogo(int administrador_id) {
		byte[] logo = null;		
		
		try {
			String sentencia = "select logo_empresa from cuenta where administrador_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					resultSet.next();
					
					logo = resultSet.getBytes("logo_empresa");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return logo;
	}
	
	public String getNombreEmpresa(int administrador_id) {
		String nombre = "";		
		
		try {
			String sentencia = "select nombre_empresa from cuenta where administrador_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					resultSet.next();
					
					nombre = resultSet.getString("nombre_empresa");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return nombre;
	}
	
	public byte[] getPerfil(int administrador_id) {
		byte[] perfil = null;		
		
		try {
			String sentencia = "select imagen_perfil from cuenta where administrador_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					resultSet.next();
					
					perfil = resultSet.getBytes("imagen_perfil");
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return perfil;
	}
	
}
