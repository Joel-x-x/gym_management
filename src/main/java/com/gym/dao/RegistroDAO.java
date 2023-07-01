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
			
			System.out.println(membresia.getId());
			
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
		
		public List<Registro> consultarRegistro(int usuario_id) {
			
			List<Registro> resultado = new ArrayList<>();
			
			try {
			    String sentencia = "select r.id, u.nombre, r.fecha_entrada, r.fecha_salida, p.nombre as plan, c.clase, m.* from registro r"
			    		+ " join usuario u on u.id = r.usuario_id"
			    		+ " join membresia m on m.id = r.membresia_id"
			    		+ " join plan p on p.id = m.plan_id"
			    		+ " join clase c on c.id = m.clase_id"
			    		+ " where r.usuario_id = ?"
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
			                    resultSet.getString("p.plan"),
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
		
		public List<Membresia> consultar(int usuario_id) {
			
			List<Membresia> resultado = new ArrayList<>();
			
			try {
			    String sentencia = "select r.id, u.nombre, r.fecha_entrada, r.fecha_salida, p.nombre as plan, c.clase, m.* from registro r"
			    		+ " join usuario u on u.id = r.usuario_id"
			    		+ " join membresia m on m.id = r.membresia_id"
			    		+ " join plan p on p.id = m.plan_id"
			    		+ " join clase c on c.id = m.clase_id"
			    		+ " where r.usuario_id = ?"
			    		+ " order by r.id desc";

			    final PreparedStatement statement = con.prepareStatement(sentencia);

			    try (statement) {
			        statement.setInt(1, usuario_id);

			        final ResultSet resultSet = statement.executeQuery();

			        try (resultSet) {
			        	
			            while (resultSet.next()) {
			            	
			                resultado.add(new Membresia(
			                    resultSet.getInt("r.id"),
			                    resultSet.getString("u.nombre"),
			                    resultSet.getString("r.fecha_entrada"),
			                    resultSet.getString("r.fecha_salida"),
			                    resultSet.getString("p.plan"),
			                    resultSet.getString("c.clase"),
			                    resultSet.getInt("m.id"),
			                    resultSet.getString("m.fecha_inicio"),
			                    resultSet.getString("m.fecha_fin"),
			                    resultSet.getInt("m.activo"),
			                    resultSet.getInt("m.anticipacion")));
			            }
			        }
			    }

			} catch (SQLException e) {
			    throw new RuntimeException(e);
			}

			return resultado;


		}
		
		
}
