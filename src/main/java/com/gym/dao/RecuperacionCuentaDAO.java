package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gym.model.RecuperacionCuenta;
import com.gym.utilidades.Utilidades;

public class RecuperacionCuentaDAO {
	Connection con;
	
	public RecuperacionCuentaDAO(Connection con) {
		this.con = con;
	}
	
	public boolean modificar(RecuperacionCuenta recuperacionCuenta) {
		int item = 0;		
		
		try {
			String sentencia = "update recuperacion_cuenta set nombre_amigo = ?, nombre_mascota = ?, color_favorito = ? where administrador_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
				statement.setString(1, recuperacionCuenta.getNombre_amigo());
				statement.setString(2, recuperacionCuenta.getNombre_mascota());
				statement.setString(3, recuperacionCuenta.getColor_favorito());
				statement.setInt(4, recuperacionCuenta.getAdministrador_id());
				
				item = statement.executeUpdate();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
	}
	
	public boolean validarDatos(RecuperacionCuenta recuperacionCuenta) {
		boolean item = false;		
		
		try {
			String sentencia = "select nombre_amigo, nombre_mascota, color_favorito from recuperacion_cuenta"
					+ " where nombre_amigo = ? and nombre_mascota = ? and color_favorito = ? and administrador_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, recuperacionCuenta.getNombre_amigo());
				statement.setString(2, recuperacionCuenta.getNombre_mascota());
				statement.setString(3, recuperacionCuenta.getColor_favorito());
				statement.setInt(4, recuperacionCuenta.getAdministrador_id());
				
				final ResultSet resultSet = statement.executeQuery();
				
				item = resultSet.next();
			
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return item;
	}
}
