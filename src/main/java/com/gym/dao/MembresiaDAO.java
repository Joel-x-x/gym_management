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
			String sentencia = "select * from membresia where usuario_id = ?";
			List<Membresia> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setInt(1, usuario_id);
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Membresia(
								resultSet.getInt("id"),
								resultSet.getString("fecha_inicio"),
								resultSet.getString("fecha_fin"),
								resultSet.getInt("usuario_id"),
								resultSet.getInt("plan_id"),
								resultSet.getInt("clase_id"),
								resultSet.getFloat("valor_extra"),
								resultSet.getFloat("valor_total"),
								resultSet.getInt("administrador_id")
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
			String sentencia = "insert into membresia(fecha_fin, usuario_id, plan_id, clase_id, valor_extra, valor_total, administrador_id) "
					+ "values(?,?,?,?,?,?,?)";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, membresia.getFecha_fin());
				statement.setInt(2, membresia.getUsuario_id());
				statement.setInt(3, membresia.getPlan_id());
				statement.setInt(4, membresia.getClase_id());
				statement.setFloat(5, membresia.getValor_extra());
				statement.setFloat(6, membresia.getValor_total());
				statement.setInt(7, membresia.getAdministrador_id());
					
					return toBoolean(statement.executeUpdate());
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
