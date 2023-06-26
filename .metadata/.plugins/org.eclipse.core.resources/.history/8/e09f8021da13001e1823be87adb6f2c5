package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gym.model.Plan;

public class PlanesDAO {
	Connection con;
	
	public PlanesDAO(Connection con) {
		this.con = con;
	}
public boolean agregar(Plan plan) {
	try {
		String sentencia = "insert into plan (nombre, precio, descripcion, duracion) "
				+ " values(?,?,?,?);";
		final PreparedStatement statement = con.prepareStatement(sentencia);
		try(statement){
			statement.setString(1, plan.getNombre());
			statement.setFloat(2, plan.getPrecio());
			statement.setString(3, plan.getDescripcion());
			statement.setString(4, plan.getDuracion());
			int i = statement.executeUpdate();
			if(i>0) {
				return true;
			}else {
				return false;
			}
		}
	}catch(SQLException e) {
		throw new RuntimeException(e);
	}
	
}
}
