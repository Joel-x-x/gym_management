package com.gym.dao;

import java.sql.Connection;
import java.sql.Date;
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
	
		public boolean registrar(int usuario_id, int membresia_id) {
			
			try {
				String sentencia = "insert into registro(usuario_id, membresia_id) values(?, ?)";
				
				PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, usuario_id);
					statement.setInt(2, membresia_id);
					
					// Transforma el 0 o 1 a booleano
					return  new Utilidades().toBoolean(statement.executeUpdate());
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
				return false;
			}
			
		}
		
		public Membresia consultaMembresia(int usuario_id, int id) {

			try {
				String sentencia = "select * from membresia where usuario_id = ? and activo = 1 and id = ?";
				
				PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, usuario_id);
					statement.setInt(2, id);
					
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
		
		// Lista todos los registros
		public List<Membresia> consultar(int administrador_id) {
			
			List<Membresia> resultado = new ArrayList<>();
			
			try {
			    String sentencia = "select r.id, u.nombre, u.cedula, u.apellido, r.fecha_entrada, r.fecha_salida, m.*, t.nombre from registro r"
			    		+ " join usuario u on u.id = r.usuario_id"
			    		+ " join membresia m on m.id = r.membresia_id"
			    		+ " join tipo_membresia t on t.id = m.tipo_membresia_id"
			    		+ " where u.administrador_id = ?"
			    		+ " order by r.id desc"
			    		+ " limit 30";

			    final PreparedStatement statement = con.prepareStatement(sentencia);

			    try (statement) {
			        statement.setInt(1, administrador_id);

			        final ResultSet resultSet = statement.executeQuery();

			        try (resultSet) {
			        	
			            while (resultSet.next()) {
			            	
			                resultado.add(new Membresia(
			                    resultSet.getInt("r.id"),
			                    resultSet.getString("u.nombre"),
			                    resultSet.getString("u.apellido"),
			                    resultSet.getString("u.cedula"),
			                    resultSet.getString("r.fecha_entrada"),
			                    resultSet.getString("r.fecha_salida"),
			                    resultSet.getString("t.nombre")));
			            }
			        }
			    }

			} catch (SQLException e) {
			    throw new RuntimeException(e);
			}

			return resultado;


		}
		
		public List<Membresia> consultarFecha(int  administrador_id, Date fechaInicio, Date fechaFin) {
			
			List<Membresia> resultado = new ArrayList<>();
			
			try {
			    String sentencia = "select r.id, u.nombre, u.apellido, u.cedula, r.fecha_entrada, r.fecha_salida, m.*, t.nombre from registro r"
			    		+ " join usuario u on u.id = r.usuario_id"
			    		+ " join membresia m on m.id = r.membresia_id"
			    		+ " join tipo_membresia t on t.id = m.tipo_membresia_id"
			    		+ " where u.administrador_id = ? and r.fecha_entrada between ? and ?"
			    		+ " order by r.id desc";

			    final PreparedStatement statement = con.prepareStatement(sentencia);

			    try (statement) {
			        statement.setInt(1, administrador_id);
			        statement.setDate(2, fechaInicio);
			        statement.setDate(3, fechaFin);

			        final ResultSet resultSet = statement.executeQuery();

			        try (resultSet) {
			        	
			            while (resultSet.next()) {
			            	
			                resultado.add(new Membresia(
				                    resultSet.getInt("r.id"),
				                    resultSet.getString("u.nombre"),
				                    resultSet.getString("u.apellido"),
				                    resultSet.getString("u.cedula"),
				                    resultSet.getString("r.fecha_entrada"),
				                    resultSet.getString("r.fecha_salida"),
				                    resultSet.getString("t.nombre")));
			            }
			        }
			    }

			} catch (SQLException e) {
			    throw new RuntimeException(e);
			}

			return resultado;
		}
		
		
}
