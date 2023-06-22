package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.gym.model.Usuario;

public class UsuarioDAO {
	Connection con;
	
	public UsuarioDAO(Connection con) {
		this.con  = con;
	}
	
	public Integer guardar(Usuario usuario) {
		
		try {
			String sentencia = "insert into usuario(nombre, apellido, fecha_nacimiento, sexo, correo, cedula, direccion, telefono, cuenta_id) "
					+ "values(?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setString(1, usuario.getNombre());
				statement.setString(2, usuario.getApellido());
				statement.setString(3, usuario.getFecha_nacimiento());
				statement.setString(4, usuario.getSexo());
				statement.setString(5, usuario.getCorreo());
				statement.setString(6, usuario.getCedula());
				statement.setString(7, usuario.getDireccion());
				statement.setString(8, usuario.getTelefono());
				statement.setString(9, usuario.getCuenta_id());
				
				return statement.executeUpdate();
			}
			
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
