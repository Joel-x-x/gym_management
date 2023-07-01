package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Clase;
import com.gym.model.Membresia;
import com.gym.model.Plan;
import com.gym.model.Usuario;

public class MembresiaDAO {
	Connection con;
	
	public MembresiaDAO(Connection con) {
		this.con = con;
	}
	
	public boolean toBoolean(int numero) {
		if(numero != 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Plan> listarPlan(int administrador_id) {

		try {
			String sentencia = "select * from plan where administrador_id = ?";
			List<Plan> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					resultado.add(new Plan("-- Selecciona un Plan --"));
					
					while(resultSet.next()) {
						resultado.add(new Plan(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getFloat("precio"),
								resultSet.getString("descripcion"),
								resultSet.getString("duracion")));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Clase> listarClase(int administrador_id) {

		try {
			String sentencia = "select * from clase where administrador_id = ?";
			List<Clase> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					resultado.add(new Clase("-- Selecciona una Clase --"));
					
					while(resultSet.next()) {
						resultado.add(new Clase(
								resultSet.getInt("id"),
								resultSet.getString("clase"),
								resultSet.getString("descripcion"),
								resultSet.getInt("entrenador_id"),
								resultSet.getInt("administrador_id")));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Membresia> listar(int usuario_id) {

		try {
			String sentencia = "select m.*, p.nombre, c.clase from membresia m"
					+ " join plan p on p.id = m.plan_id"
					+ " join clase c on c.id = m.clase_id"
					+ " where usuario_id = ?";
			
			List<Membresia> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Membresia(
								resultSet.getInt("m.id"),
								resultSet.getString("m.fecha_inicio"),
								resultSet.getString("m.fecha_fin"),
								resultSet.getInt("m.usuario_id"),
								resultSet.getInt("m.plan_id"),
								resultSet.getInt("m.clase_id"),
								resultSet.getFloat("m.valor_extra"),
								resultSet.getFloat("m.valor_total"),
								resultSet.getInt("m.administrador_id"),
								resultSet.getString("p.nombre"),
								resultSet.getString("c.clase"),
								resultSet.getInt("m.activo"),
								resultSet.getInt("m.anticipacion")
								));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean guardar(Membresia membresia) {
		
		try {
			String sentencia = "insert into membresia(fecha_fin, usuario_id, plan_id, clase_id, valor_extra, valor_total, activo, anticipacion, administrador_id) "
					+ "values(?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, membresia.getFecha_fin());
				statement.setInt(2, membresia.getUsuario_id());
				statement.setInt(3, membresia.getPlan_id());
				statement.setInt(4, membresia.getClase_id());
				statement.setFloat(5, membresia.getValor_extra());
				statement.setFloat(6, membresia.getValor_total());
				statement.setInt(7, membresia.getActivo());
				statement.setInt(8, membresia.getAnticipacion());
				statement.setInt(9, membresia.getAdministrador_id());
					
					return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public Membresia consulta(int id, int usuario_id) {

		try {
			String sentencia = "select m.*, p.nombre, c.clase from membresia m"
					+ " join plan p on p.id = m.plan_id"
					+ " join clase c on c.id = m.clase_id"
					+ " where m.id = ? and m.usuario_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, id);
				statement.setInt(2, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
						return new Membresia(
								resultSet.getInt("m.id"),
								resultSet.getString("m.fecha_inicio"),
								resultSet.getString("m.fecha_fin"),
								resultSet.getInt("m.usuario_id"),
								resultSet.getInt("m.plan_id"),
								resultSet.getInt("m.clase_id"),
								resultSet.getFloat("m.valor_extra"),
								resultSet.getFloat("m.valor_total"),
								resultSet.getInt("m.administrador_id"),
								resultSet.getString("p.nombre"),
								resultSet.getString("c.clase"),
								resultSet.getInt("m.activo"),
								resultSet.getInt("m.anticipacion")
								);
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Membresia consultaUltimaMembresia(int usuario_id) {

		try {
			
			System.out.println(usuario_id);
			
			String sentencia = "select m.*, p.nombre, c.clase"
					+ " from membresia m"
					+ " join plan p ON p.id = m.plan_id"
					+ " join clase c ON c.id = m.clase_id"
					+ " where m.id = ("
					+ "    select max(id) from membresia where usuario_id = ?"
					+ ")"
					+ " and m.usuario_id = ?"
					+ " group by m.id, p.nombre, c.clase;";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				statement.setInt(2, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
						return new Membresia(
								resultSet.getInt("m.id"),
								resultSet.getString("m.fecha_inicio"),
								resultSet.getString("m.fecha_fin"),
								resultSet.getInt("m.usuario_id"),
								resultSet.getInt("m.plan_id"),
								resultSet.getInt("m.clase_id"),
								resultSet.getFloat("m.valor_extra"),
								resultSet.getFloat("m.valor_total"),
								resultSet.getInt("m.administrador_id"),
								resultSet.getString("p.nombre"),
								resultSet.getString("c.clase"),
								resultSet.getInt("m.activo"),
								resultSet.getInt("m.anticipacion")
								);
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean modificar(Membresia membresia) {

		try {
			String sentencia = "update membresia set fecha_fin = ?, plan_id = ?, clase_id = ?, valor_extra = ?, valor_total = ?, activo = ?, anticipacion = ?"
					+ " where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, membresia.getFecha_fin());
				statement.setInt(2, membresia.getPlan_id());
				statement.setInt(3, membresia.getClase_id());
				statement.setFloat(4, membresia.getValor_extra());
				statement.setFloat(5, membresia.getValor_total());
				statement.setFloat(6, membresia.getActivo());
				statement.setFloat(7, membresia.getAnticipacion());
				statement.setInt(8, membresia.getId());
					
					return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean eliminar(int id) {

		try {
			String sentencia = "delete from membresia where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, id);
				
				return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean modificarActivo(int id, int activo) {

		try {
			String sentencia = "update membresia set activo = ?"
					+ " where id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, activo);
				statement.setFloat(2, id);
					
					return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public boolean consultaActivo(int usuario_id) {

		try {
			String sentencia = "select activo from membresia where usuario_id = ? and activo = 1";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
						return resultSet.next();
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
