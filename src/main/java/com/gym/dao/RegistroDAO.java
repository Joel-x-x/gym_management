package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Membresia;
import com.gym.model.Registro;
import com.gym.utilidades.Utilidades;

public class RegistroDAO {
	Connection con;
	
		public RegistroDAO(Connection con) {
			this.con  = con;
		}
	
		public boolean registrar(int usuario_id) {
			
			var membresia = this.consultaMembresia(usuario_id);
			
			try {
				String sentencia = "insert into registro(usuario_id, membresia_id) values(?, ?)";
				
				PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, usuario_id);
					statement.setInt(2, membresia.getId());
					
					// Transforma el 0 o 1 a booleano
					return  new Utilidades().toBoolean(statement.executeUpdate());
				}
				
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
		
		public Membresia consultaMembresia(int usuario_id) {

			try {
				String sentencia = "select * from membresia where usuario_id = ? and activo = 1";
				
				PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, usuario_id);
					
					final ResultSet resultSet = statement.executeQuery();
					
					try(resultSet) {
						
						resultSet.next();
						
						return  new Membresia(
								resultSet.getInt("id"),
								resultSet.getString("fecha_inicio"),
								resultSet.getString("fecha_fin"),
								resultSet.getInt("usuario_id"),
								resultSet.getInt("activo"),
								resultSet.getInt("anticipacion")
								);
					}
				}
				
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
		
		public boolean registrarSalida(int id) {
			
			try {
				String sentencia = "update registro set fecha_salida = current_timestamp where id = ?";
				
				PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, id);
					
					return new Utilidades().toBoolean(statement.executeUpdate());
				}
				
			} catch(SQLException e) {
				throw new RuntimeException(e);
			}
			
		}
		
		public List<Registro> consultar(int usuario_id) {
			
			List<Registro> resultado = new ArrayList<>();
			
			try {
			    String sentencia = "SELECT r.*, u.id, u.nombre, p.nombre, c.clase, m.* from membresia m"
			        + " join usuario u on u.id = m.usuario_id"
			        + " join plan p on p.id = m.plan_id"
			        + " join clase c on c.id = m.clase_id"
			        + " join registro r on r.usuario_id = u.id where r.usuario_id = ? and u.id = m.usuario_id"
			        + " order by r.id desc";

			    final PreparedStatement statement = con.prepareStatement(sentencia);

			    try (statement) {
			        statement.setInt(1, usuario_id);

			        final ResultSet resultSet = statement.executeQuery();

			        try (resultSet) {
			            while (resultSet.next()) {
			            	
			                resultado.add(new Registro(
			                    resultSet.getInt("r.id"),
			                    resultSet.getString("r.fecha_entrada"),
			                    resultSet.getString("r.fecha_salida"),
			                    usuario_id,
			                    resultSet.getString("u.nombre"),
			                    resultSet.getString("p.nombre"),
			                    resultSet.getString("c.clase"),
			                    resultSet.getInt("m.activo")
			                ));
			            }
			        }
			    }

			} catch (SQLException e) {
			    throw new RuntimeException(e);
			}

			return resultado;


		}
		
		
}
