package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gym.model.Plan;
import com.gym.model.Registro;
import com.gym.model.Usuario;

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
public boolean modificar(Plan plan) {
	try {
		String sentencia = "UPDATE plan SET  nombre= ?, precio = ?, descripcion = ?, duracion= ?  WHERE id = ?";
				
		final PreparedStatement statement = con.prepareStatement(sentencia);
		try(statement){
			statement.setString(1, plan.getNombre());
			statement.setFloat(2, plan.getPrecio());
			statement.setString(3, plan.getDescripcion());
			statement.setString(4, plan.getDuracion());
			statement.setString(5, String.valueOf(plan.getId()));
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
public Object[][] consultar(Plan plan) {
	Object obj[][]= null;
	String sentencia="";

	try {
		if(plan.getNombre().equals("")) {
			sentencia = "SELECT * FROM plan ";
			
		}else {
			sentencia = "SELECT * FROM plan where nombre = "+plan.getNombre();
			}
	    
	    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    
	    try (ResultSet resultSet = statement.executeQuery(sentencia)) {
	    	resultSet.last();
	    	int nf=resultSet.getRow();
			obj =new Object[nf][5];
			resultSet.beforeFirst();
			int f=0;
	        while (resultSet.next()) {
	            obj[f][0] = resultSet.getObject(1);
	            obj[f][1] = resultSet.getObject(2);
	            obj[f][2] = resultSet.getObject(3);
	            obj[f][3] = resultSet.getObject(4);
	            obj[f][4] = resultSet.getObject(5);
	            
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
public String[] consultar_(Plan plan) {
	String obj[]= new String[4];
	String sentencia="";
	
	try {
			sentencia = "SELECT * FROM plan where id =  "+plan.getId();
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
