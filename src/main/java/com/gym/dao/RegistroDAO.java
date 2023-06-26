package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gym.model.Registro;
import com.gym.model.Usuario;

public class RegistroDAO {
	Connection con;
	
		public RegistroDAO(Connection con) {
			this.con  = con;
		}
	
		public Integer guardar(Registro registro) {
			
			try {
				String sentencia = "insert into registro(usuario_id) values(?);";
				
				PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, registro.getUsuario());
					
					return statement.executeUpdate();
				}
				
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
		public Integer guardar_salida(Registro registro) {
			
			try {
				String sentencia = "update registro set fecha_salida = current_timestamp where id = ?;";
				
				PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, registro.getId());
					
					return statement.executeUpdate();
				}
				
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
		public Object[][] consultar(Registro registro) {
			Object obj[][]= null;
			String sentencia="";
			try {
					sentencia = "SELECT * FROM registro where usuario_id =  "+registro.getUsuario();
			    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			    try (ResultSet resultSet = statement.executeQuery(sentencia)) {
			    	resultSet.last();
			    	int nf=resultSet.getRow();
					obj =new Object[nf][3];
					resultSet.beforeFirst();
					int f=0;
			        while (resultSet.next()) {
			            obj[f][0] = resultSet.getObject(1);
			            obj[f][1] = resultSet.getObject(2);
			            obj[f][2] = resultSet.getObject(3);
			            
			            System.out.println("JHJKUH " + obj[0][1]);
			            System.out.println(obj[0][0]);
			            f++;
			        }
			    }
			} catch (SQLException e) {
			    e.printStackTrace();
			}

			return obj;

				
				
		}
		
		
}
