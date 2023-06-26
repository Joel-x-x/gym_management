package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Fisico;
import com.gym.model.Usuario;

public class FisicoDAO {
	Connection con;
	
	public FisicoDAO(Connection con) {
		this.con = con;
	}
	
	public boolean toBoolean(int numero) {
		if(numero != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean guardar(Fisico fisico) {
		
		try {
			String sentencia = "insert into fisico(peso, altura, usuario_id) "
					+ "values(?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setDouble(1, fisico.getPeso());
				statement.setDouble(2, fisico.getAltura());
				statement.setInt(3, fisico.getUsuario_id());
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean modificar(Fisico fisico) {
		
		try {
			String sentencia = "update fisico set peso = ?, altura = ? where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setDouble(1, fisico.getPeso());
				statement.setDouble(2, fisico.getAltura());
				statement.setInt(3, fisico.getId());
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean eliminar(int id) {
		
		try {
			String sentencia = "delete from fisico where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Fisico> listar(int usuario_id) {
		
		try {
			String sentencia = "select * from fisico where usuario_id = ?";
			List<Fisico> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Fisico(
								resultSet.getInt("id"),
								resultSet.getDouble("altura"),
								resultSet.getDouble("peso"),
								resultSet.getString("fecha"),
								resultSet.getInt("usuario_id")));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}