package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gym.model.Fisico;

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
}
