package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gym.model.Clase;
import com.gym.model.Entrenador;

public class ClaseDAO {
Connection con;
	
	public ClaseDAO(Connection con) {
		this.con = con;
	}
public boolean agregar(Clase clase) {
	try {
		String sentencia = "insert into clase (clase, descripcion, entrenador_id, administrador_id) "
				+ " values(?,?,?,?);";
		final PreparedStatement statement = con.prepareStatement(sentencia);
		try(statement){
		
			statement.setString(1, clase.getClase());
			statement.setString(2, clase.getDescripcion());
			statement.setString(3, String.valueOf(clase.getEntrenador_id()));
			System.out.println("clase entrenador id "+clase.getAdministrador_id());
			statement.setString(4, String.valueOf(clase.getAdministrador_id()));
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
	
public boolean modificar(Clase clase) {
	try {
		String sentencia = "UPDATE clase SET  clase= ?, descripcion = ?, entrenador_id = ? where id =?";
				
		final PreparedStatement statement = con.prepareStatement(sentencia);
		try(statement){
			statement.setString(1, clase.getClase());
			statement.setString(2, clase.getDescripcion());
			statement.setString(3, String.valueOf(clase.getEntrenador_id()));
			statement.setString(4, String.valueOf(clase.getId()));
			
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
public boolean eliminar(Clase clase) {
	try {
		String sentencia = "DELETE FROM clase WHERE id = ?";
				
		final PreparedStatement statement = con.prepareStatement(sentencia);
		try(statement){
			
			statement.setString(1, String.valueOf(clase.getId()));
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
public Object[][] consultar(Clase clase) {
	Object obj[][]= null;
	String sentencia="";

	try {
		if(clase.getClase().equals("")) {
			sentencia = "SELECT * FROM clase ";
			
		}else {
			sentencia = "SELECT * FROM clase where clase = '"+clase.getClase()+"'";
			}
	    
	    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    
	    try (ResultSet resultSet = statement.executeQuery(sentencia)) {
	    	resultSet.last();
	    	int nf=resultSet.getRow();
			obj =new Object[nf][4];
			resultSet.beforeFirst();
			int f=0;
	        while (resultSet.next()) {
	            obj[f][0] = resultSet.getObject(1);
	            obj[f][1] = resultSet.getObject(2);
	            obj[f][2] = resultSet.getObject(3);
	            obj[f][3] = resultSet.getObject(4);
	            
	            
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
public String[] consultar_(Clase clase) {
	String obj[]= new String[3];
	String sentencia="";
	
	try {
			sentencia = "SELECT * FROM clase where id =  "+clase.getId();
	    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    try (ResultSet resultSet = statement.executeQuery(sentencia)) {
	    	
		if(resultSet.next()) {
			obj[0] = resultSet.getObject(2).toString();
            obj[1] = resultSet.getObject(3).toString();
            obj[2] = resultSet.getObject(4).toString();
            
          
            
            
            System.out.println("JHJKUH " + obj[0]);
            System.out.println(obj[1]);
            
		}else {
			System.out.println("no se ecndfg");
		}
	        
	            
	        
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}

	return obj;

		
		
}

}
