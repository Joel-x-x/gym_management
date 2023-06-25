package com.gym.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	public Object[][] consultar(Usuario usuario) {
		Object obj[][]= null;
		//obj= new Object[1][6];
		String sentencia="";

		try {
			if(usuario.getCedula().equals("")) {
				sentencia = "SELECT * FROM usuario  ";
				
			}else {
				sentencia = "SELECT * FROM usuario where cedula =  "+usuario.getCedula();
				}
		    
		    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    //statement.setString(1, usuario.getCedula().toString());
		    
		    try (ResultSet resultSet = statement.executeQuery(sentencia)) {
		    	resultSet.last();
		    	int nf=resultSet.getRow();
				obj =new Object[nf][7];
				resultSet.beforeFirst();
				int f=0;
		        while (resultSet.next()) {
		            obj[f][0] = resultSet.getObject(1);
		            obj[f][1] = resultSet.getObject(2);
		            obj[f][2] = resultSet.getObject(3);
		            obj[f][3] = resultSet.getObject(4);
		            obj[f][4] = resultSet.getObject(5);
		            obj[f][5] = resultSet.getObject(6);
		            obj[f][6] = resultSet.getObject(7);
		            System.out.println("JHJKUH " + obj[0][1]);
		            System.out.println(obj[0][0]);
		            f++;
		        }
		    }
		} catch (SQLException e) {
		    e.printStackTrace();
		}

		return obj;

			
			
	}
	
}
