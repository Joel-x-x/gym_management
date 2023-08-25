package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.TipoMembresia;
import com.gym.utilidades.Utilidades;

public class TipoMembresiaDAO {
	Connection con;
	
	public TipoMembresiaDAO(Connection con) {
		this.con = con;
	}
	
	public boolean guardar(TipoMembresia tipoMembresia) {
		
		int item = 0;
		
		try {
			String sentencia = "insert into tipo_membresia(nombre, descripcion, precio, duracion, tipo_duracion, clase_id, administrador_id) "
					+ " values(?,?,?,?,?,?,?);";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setString(1, tipoMembresia.getNombre());
				statement.setString(2, tipoMembresia.getDescripcion());
				statement.setFloat(3, tipoMembresia.getPrecio());
				statement.setInt(4, tipoMembresia.getDuracion());
				statement.setString(5, tipoMembresia.getTipoDuracion());
				statement.setInt(6, tipoMembresia.getClase_id());
				statement.setInt(7, tipoMembresia.getAdministrador_id());
				
				item = statement.executeUpdate();
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	
	public boolean modificar(TipoMembresia tipoMembresia) {
		
		int item = 0;
		
		try {
			String sentencia = "update tipo_membresia set nombre= ?, descripcion = ?, precio = ?, duracion= ?, tipo_duracion = ?, clase_id = ?  where id = ?";
					
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setString(1, tipoMembresia.getNombre());
				statement.setString(2, tipoMembresia.getDescripcion());
				statement.setFloat(3, tipoMembresia.getPrecio());
				statement.setInt(4, tipoMembresia.getDuracion());
				statement.setString(5, tipoMembresia.getTipoDuracion());
				statement.setInt(6, tipoMembresia.getClase_id());
				statement.setInt(7, tipoMembresia.getId());
				
				item = statement.executeUpdate();
				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
		
	}
	public int eliminar(int id) {
		
		try {
			String sentencia = "delete from tipo_membresia where id = ?";
					
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setInt(1, id);
				
				return statement.executeUpdate();
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return e.getErrorCode();
		}
		
	}
	public List<TipoMembresia> consultar(String nombre, int administrador_id) {
		
		List<TipoMembresia> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "select t.*, c.clase from tipo_membresia t, clase c"
					+ " where t.clase_id = c.id and t.administrador_id = ?";
			
			if(!nombre.equals("")) {
				sentencia = "select t.*, c.clase from tipo_membresia t, clase c"
						+ " where t.clase_id = c.id and t.administrador_id = ? and nombre like ?";
			}
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, administrador_id);
				
				if(!nombre.equals("")) {
					statement.setString(2, nombre + "%");
				}
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new TipoMembresia(
								resultSet.getInt("t.id"),
								resultSet.getString("t.nombre"),
								resultSet.getString("t.descripcion"),
								resultSet.getFloat("t.precio"),
								resultSet.getInt("t.duracion"),
								resultSet.getString("t.tipo_duracion"),
								resultSet.getInt("t.clase_id"),
								resultSet.getString("c.clase")));
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
		
	}
	
	public TipoMembresia consulta(int id) {
	
		TipoMembresia tipoMembresia = null;
		
		try {
			
			String sentencia = "select * from tipo_membresia where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					tipoMembresia = new TipoMembresia(
							resultSet.getInt("id"),
							resultSet.getString("nombre"),
							resultSet.getString("descripcion"),
							resultSet.getFloat("precio"),
							resultSet.getInt("duracion"),
							resultSet.getString("tipo_duracion"),
							resultSet.getInt("clase_id"));
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return tipoMembresia;
		
	}

}
