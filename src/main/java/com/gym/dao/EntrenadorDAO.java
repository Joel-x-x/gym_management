package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Entrenador;
import com.gym.utilidades.Utilidades;

public class EntrenadorDAO {
	Connection con;
		
		public EntrenadorDAO(Connection con) {
			this.con = con;
		}
	public boolean guardar(Entrenador entrenador) {
		
		int item = 0;
		
		try {
			String sentencia = "insert into entrenador (nombre, apellido, sexo, correo, telefono, cedula, administrador_id) "
					+ " values(?,?,?,?,?,?,?);";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			try(statement){
				
				statement.setString(1, entrenador.getNombre());
				statement.setString(2, entrenador.getApellido());
				statement.setString(3, entrenador.getSexo());
				statement.setString(4, entrenador.getCorreo());
				statement.setString(5, entrenador.getTelefono());
				statement.setString(6, entrenador.getCedula());
				statement.setInt(7, entrenador.getAdministrador_id());
				
				item = statement.executeUpdate();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	public boolean modificar(Entrenador entrenador) {
		int item = 0;
		
		try {
			String sentencia = "update entrenador set nombre = ?, apellido = ?, sexo = ?, correo = ?, telefono = ?, cedula = ?  WHERE id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			try(statement){
				
				statement.setString(1, entrenador.getNombre());
				statement.setString(2, entrenador.getApellido());
				statement.setString(3, entrenador.getSexo());
				statement.setString(4, entrenador.getCorreo());
				statement.setString(5, entrenador.getTelefono());
				statement.setString(6, entrenador.getCedula());
				statement.setInt(7, entrenador.getId());
				
				item = statement.executeUpdate();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	public boolean eliminar(int id) {
		int item = 0;
		
		if(!this.eliminarClase(id)) {
			System.out.println("No se puedo eliminar las clases");
		}
		
		try {
			String sentencia = "delete from entrenador where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			try(statement){
				
				statement.setInt(1, id);
				
				item = statement.executeUpdate();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	public boolean eliminarClase(int entrenador_id) {
		int item = 0;
		
		List<Integer> clase_id = this.consultarClaseId(entrenador_id);
		
		for(int i : clase_id) {
			if(!this.eliminarMembresia(i)) {
				System.out.println("No se pudo eliminar las membresias");
			}	
			
		}
		
		
		try {
			String sentencia = "delete from clase where entrenador_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			try(statement){
				
				statement.setInt(1, entrenador_id);
				
				item = statement.executeUpdate();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}	
	
	public boolean eliminarMembresia(int clase_id) {
		int item = 0;
		
		List<Integer> membresia_id = this.consultarMembresiaId(clase_id);
		
		for(int i : membresia_id) {
			
			if(!this.eliminarRegistro(i)) {
				System.out.println("No se puedo eliminar los registros");
			}	
			
		}
		
		try {
			String sentencia = "delete from membresia where clase_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			try(statement){
				
				statement.setInt(1, clase_id);
				
				item = statement.executeUpdate();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	public boolean eliminarRegistro(int membresia_id) {
		int item = 0;
		
		try {
			String sentencia = "delete from registro where membresia_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			try(statement){
				
				statement.setInt(1, membresia_id);
				
				item = statement.executeUpdate();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	// Número de clase_id que esten relacionandas al entrenador
	public List<Integer> consultarClaseId(int entrenador_id) {

		List<Integer> clase_id = new ArrayList<>();
		
		try {
			
			String sentencia = "select * from clase where entrenador_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, entrenador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						
						clase_id.add(resultSet.getInt("id"));
						
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return clase_id;
		
	}
	
	// Número de membresia_id que esten relacionandas al registro
	public List<Integer> consultarMembresiaId(int clase_id) {

		List<Integer> membresia_id = new ArrayList<>();
		
		try {
			
			String sentencia = "select * from membresia where clase_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, clase_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						membresia_id.add(resultSet.getInt("id"));
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return membresia_id;
		
	}
	
	public List<Entrenador> consultar(String cedula, int administrador_id) {
	
		List<Entrenador> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "select * from entrenador where administrador_id = ?";
			
			if(!cedula.equals("")) {
				sentencia = "select * from entrenador where administrador_id = ? and nombre = ?";
			}
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, administrador_id);
				
				if(!cedula.equals("")) {
					statement.setString(2, cedula);
				}
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
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
	
	public Entrenador consulta(int id) {

		Entrenador entrenador = null;
		
		try {
			
			String sentencia = "select * from entrenador where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
						entrenador = new Entrenador(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getString("apellido"),
							resultSet.getString("sexo"),
							resultSet.getString("correo"),
							resultSet.getString("telefono"),
							resultSet.getString("cedula"));
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return entrenador;
		
	}

}
