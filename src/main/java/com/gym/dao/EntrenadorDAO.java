package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gym.model.Entrenador;

public class EntrenadorDAO {
Connection con;
	
	public EntrenadorDAO(Connection con) {
		this.con = con;
	}
public boolean agregar(Entrenador entrenador) {
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
public boolean modificar(Entrenador entrenador) {
	try {
		String sentencia = "UPDATE entrenador SET  nombre= ?, apellido = ?, sexo = ?, correo= ?, telefono =?, cedula = ?  WHERE id = ?";
				
		final PreparedStatement statement = con.prepareStatement(sentencia);
		try(statement){
			statement.setString(1, entrenador.getNombre());
			statement.setString(2, entrenador.getApellido());
			statement.setString(3, entrenador.getSexo());
			statement.setString(4, entrenador.getCorreo());
			statement.setString(5, entrenador.getTelefono());
			statement.setString(6, entrenador.getCedula());
			statement.setString(7, String.valueOf(entrenador.getId()));
			
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
public boolean eliminar(Entrenador entrenador) {
	try {
		String sentencia = "DELETE FROM entrenador WHERE id = ?";
				
		final PreparedStatement statement = con.prepareStatement(sentencia);
		try(statement){
			
			statement.setString(1, String.valueOf(entrenador.getId()));
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
public Object[][] consultar(Entrenador entrenador) {
	Object obj[][]= null;
	String sentencia="";

	try {
		if(entrenador.getNombre().equals("")) {
			sentencia = "SELECT * FROM entrenador ";
			
		}else {
			sentencia = "SELECT * FROM entrenador where nombre = '"+entrenador.getNombre()+"'";
			}
	    
	    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    
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
public String[][] consulta_id_nombres_entrenador(Entrenador entrenador) {
	String obj[][]= null;
	String sentencia="";

	try {
		
			sentencia = "SELECT * FROM entrenador ";
			
		
	    
	    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    
	    try (ResultSet resultSet = statement.executeQuery(sentencia)) {
	    	resultSet.last();
	    	int nf=resultSet.getRow();
			obj =new String[nf][2];
			resultSet.beforeFirst();
			int f=0;
	        while (resultSet.next()) {
	            obj[f][0] = resultSet.getObject(1).toString();
	            obj[f][1] = resultSet.getObject(2).toString();
	            
	            
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
public String[] consultar_(Entrenador entrenador) {
	String obj[]= new String[6];
	String sentencia="";
	
	try {
			sentencia = "SELECT * FROM entrenador where id =  "+entrenador.getId();
	    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    try (ResultSet resultSet = statement.executeQuery(sentencia)) {
	    	
		if(resultSet.next()) {
			obj[0] = resultSet.getObject(2).toString();
            obj[1] = resultSet.getObject(3).toString();
            obj[2] = resultSet.getObject(4).toString();
            obj[3]=resultSet.getObject(5).toString();
            obj[4]=resultSet.getObject(6).toString();
            obj[5]=resultSet.getObject(7).toString();
          
            
            
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
