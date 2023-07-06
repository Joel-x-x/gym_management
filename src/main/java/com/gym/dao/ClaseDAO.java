package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Clase;
import com.gym.model.Entrenador;
import com.gym.utilidades.Utilidades;

public class ClaseDAO {
	Connection con;
		
		public ClaseDAO(Connection con) {
			this.con = con;
		}
	public boolean guardar(Clase clase) {
		int item = 0;
		
		try {
			String sentencia = "insert into clase (clase, descripcion, entrenador_id, administrador_id) "
					+ " values(?,?,?,?);";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
			
				statement.setString(1, clase.getClase());
				statement.setString(2, clase.getDescripcion());
				statement.setInt(3, clase.getEntrenador_id());
				statement.setInt(4, clase.getAdministrador_id());
				
				item = statement.executeUpdate();
			}
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return new Utilidades().toBoolean(item);
		
	}
		
	public boolean modificar(Clase clase) {
		int item = 0;
		
		try {
			String sentencia = "update clase set clase = ?, descripcion = ?, entrenador_id = ? where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setString(1, clase.getClase());
				statement.setString(2, clase.getDescripcion());
				statement.setInt(3, clase.getEntrenador_id());
				statement.setInt(4, clase.getId());
				
				item = statement.executeUpdate();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	public int eliminar(int id) {
		
		int item = 0;
		
		try {
			String sentencia = "delete from clase where id = ?";
					
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setInt(1, id);
				
				item = statement.executeUpdate();
			}
		}catch(SQLException e) {
			item = e.getErrorCode();
		}
		
		return item;
		
	}
	
	public List<Clase> consultar(String clase, int administrador_id) {
		
		List<Clase> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "select * from clase c"
					+ " join entrenador e on e.id = c.entrenador_id"
					+ " where c.administrador_id = ?";
			
			if(!clase.equals("")) {
				sentencia = "select * from clase c"
						+ " join entrenador e on e.id = c.entrenador_id"
						+ " where c.administrador_id = ? and c.clase like ?";
			}
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, administrador_id);
				
				if(!clase.equals("")) {
					statement.setString(2, clase + "%");
				}
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Clase(
								resultSet.getInt("c.id"),
								resultSet.getString("c.clase"),
								resultSet.getString("c.descripcion"),
								resultSet.getString("e.nombre")));
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	public List<Entrenador> consultarEntrenadores(int administrador_id) {
		
		List<Entrenador> resultado = new ArrayList<>();
		
		try {
			String sentencia = "select * from entrenador where administrador_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultado.add(new Entrenador("-- Selecciona un Entrenador --"));
					
					while(resultSet.next()) {
						resultado.add(new Entrenador(
								resultSet.getInt("id"),
								resultSet.getString("nombre"),
								resultSet.getString("apellido"),
								resultSet.getString("sexo"),
								resultSet.getString("correo"),
								resultSet.getString("telefono"),
								resultSet.getString("cedula")));
					}				
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
		
	}
	public Clase consulta(int id) {
		
		Clase clase = null;
		
		try {
			
			String sentencia = "select * from clase where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					clase = new Clase(
							resultSet.getInt("id"),
							resultSet.getString("clase"),
							resultSet.getString("descripcion"),
							resultSet.getInt("entrenador_id"));
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return clase;
		
	}

}
