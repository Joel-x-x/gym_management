package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Clase;
import com.gym.model.Plan;
import com.gym.model.Usuario;

public class MembresiaDAO {
	Connection con;
	
	public MembresiaDAO(Connection con) {
		this.con = con;
	}

	public List<Plan> listarPlan() {

		try {
			String sentencia = "select * from plan";
			List<Plan> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
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
	
	public List<Clase> listarClase() {

		try {
			String sentencia = "select * from clase";
			List<Clase> resultado = new ArrayList<>();
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				final ResultSet resultSet = statement.executeQuery();
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Clase(
								resultSet.getInt("id"),
								resultSet.getString("clase"),
								resultSet.getString("descripcion"),
								resultSet.getInt("entrenador_id")));
					}
					
					return resultado;
				}
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
