package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
