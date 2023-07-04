package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Plan;
import com.gym.utilidades.Utilidades;

public class PlanesDAO {
	Connection con;
	
	public PlanesDAO(Connection con) {
		this.con = con;
	}
	public boolean guardar(Plan plan) {
		
		int item = 0;
		
		try {
			
			String sentencia = "insert into plan (nombre, precio, descripcion, duracion, administrador_id) "
					+ " values(?,?,?,?,?);";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setString(1, plan.getNombre());
				statement.setFloat(2, plan.getPrecio());
				statement.setString(3, plan.getDescripcion());
				statement.setString(4, plan.getDuracion());
				statement.setInt(5, plan.getAdministrador_id());
				
				item = statement.executeUpdate();
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	public boolean modificar(Plan plan) {
		
		int item = 0;
		
		try {
			String sentencia = "update plan set nombre= ?, precio = ?, descripcion = ?, duracion= ?  where id = ?";
					
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setString(1, plan.getNombre());
				statement.setFloat(2, plan.getPrecio());
				statement.setString(3, plan.getDescripcion());
				statement.setString(4, plan.getDuracion());
				statement.setInt(5, plan.getId());
				
				item = statement.executeUpdate();
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	public boolean eliminar(int id) {
		
		int item = 0;
		
		try {
			String sentencia = "delete from plan where id = ?";
					
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setInt(1, id);
				
				item = statement.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
		public List<Plan> consultar(String plan, int administrador_id) {
			
			List<Plan> resultado = new ArrayList<>();
			
			try {
				
				String sentencia = "select * from plan where administrador_id = ?";
				
				if(!plan.equals("")) {
					sentencia = "select * from plan where nombre like ? and administrador_id = ?";
				}
				
				
				final PreparedStatement statement = con.prepareStatement(sentencia);
				
				try(statement) {
					statement.setInt(1, administrador_id);
					
					if(!plan.equals("")) {
						statement.setString(1, plan + "%");
						statement.setInt(2, administrador_id);
					}
					
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
					}
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			return resultado;
			
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
	            obj[3]=resultSet.getObject(5).toString();
	            
	            
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
	public Plan consulta(int id) {
	
		Plan plan = null;
		
		try {
			
			String sentencia = "select * from plan where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					plan = new Plan(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getFloat("precio"),
							resultSet.getString("descripcion"),
							resultSet.getString("duracion"));
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return plan;
		
	}

}
