package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gym.model.Registro;

public class RegistroDAO {
	Connection con;
	
		public RegistroDAO(Connection con) {
			this.con  = con;
		}
	
		public Integer guardar(Registro registro) {
			
			try {
				String sentencia = "insert into registro(usuario_id) values(1);";
				
				PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, registro.getUsuario());
					
					return statement.executeUpdate();
				}
				
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
}
