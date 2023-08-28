package com.gym.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gym.model.Factura;
import com.gym.model.TipoMembresia;
import com.gym.utilidades.Utilidades;

public class FacturaDAO {
	Connection con;
	
	public FacturaDAO(Connection con) {
		this.con = con;
	}
	

	public Factura consultarFactura(int id){
		Factura factura = null;
		
		try {
			
			String sentencia = "select * from factura f, usuario u where f.usuario_id = u.id and f.id = ?";
			
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					factura = new Factura(
	                    resultSet.getInt("f.id"), 
	                    resultSet.getString("f.numero_factura"),
	                    resultSet.getDouble("f.descuento_porcentaje"),
	                    resultSet.getDouble("f.descuento"),
	                    resultSet.getDouble("f.subtotal"),
	                    resultSet.getDouble("f.iva"),
	                    resultSet.getDouble("f.total"),
	                    resultSet.getString("f.forma_pago"),
	                    resultSet.getDate("f.fecha"),
	                    resultSet.getString("f.establecimiento"),
	                    resultSet.getString("f.punto_emision"),
	                    resultSet.getInt("f.usuario_id"),
	                    resultSet.getInt("f.administrador_id"),
	                    resultSet.getString("u.nombre"),
	                    resultSet.getString("u.apellido"),
	                    resultSet.getString("u.cedula")
							);
					
					return factura;
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return factura;
		}
	}

	
	public List<Factura> listarFactura(int administrador_id, String nombre) {
		
		List<Factura> resultado = new ArrayList<>();
//		int elementosPorPagina = 10; 
//		int paginaActual = 1;
		
		try {
			
			String sentencia = "select * from factura f, usuario u where f.administrador_id = ? and f.usuario_id = u.id and total <> 0.0";
			
			if(!nombre.equals("")) {
				sentencia = "select * from factura f, usuario u where f.administrador_id = ? and f.usuario_id = u.id and nombre like ? and total <> 0.0";
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
						resultado.add(new Factura(
			                    resultSet.getInt("f.id"), 
			                    resultSet.getString("f.numero_factura"),
			                    resultSet.getDouble("f.descuento_porcentaje"),
			                    resultSet.getDouble("f.descuento"),
			                    resultSet.getDouble("f.subtotal"),
			                    resultSet.getDouble("f.iva"),
			                    resultSet.getDouble("f.total"),
			                    resultSet.getString("f.forma_pago"),
			                    resultSet.getDate("f.fecha"),
			                    resultSet.getString("f.establecimiento"),
			                    resultSet.getString("f.punto_emision"),
			                    resultSet.getInt("f.usuario_id"),
			                    resultSet.getInt("f.administrador_id"),
			                    resultSet.getString("u.nombre")
								));
					}
					
					return resultado;
					
				}
				
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return resultado;
		}
	}

	public boolean crearFactura(int administrador_id) {
		int item = 0;
		
		try {
			String sentencia = "call insertarFactura(?)";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setInt(1, administrador_id);
				
				item = statement.executeUpdate();
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
	}
	
	public Factura consultarUltimaFactura(int administrador_id) {
		Factura factura = null;
		
		try {
			String sentencia = "select id, numero_factura, descuento_porcentaje, descuento, subtotal,"
					+ " iva, total, forma_pago, date(fecha) as fecha, establecimiento, punto_emision, usuario_id, administrador_id"
					+ " from factura where id = maxIdFactura() and administrador_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					return factura = new Factura(
							resultSet.getInt("id"),
							resultSet.getString("numero_factura"), 
							resultSet.getString("forma_pago"), 
							resultSet.getDate("fecha"), 
							resultSet.getString("establecimiento"), 
							resultSet.getString("punto_emision") 
							);
				}
				
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return factura;
		}
	}
	
	public int consultarUsuarioUltimaFactura(int administrador_id) {
		
		try {
			String sentencia = "select usuario_id from factura where id = maxIdFactura() and administrador_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					resultSet.next();
					
					if(Utilidades.isNumber(resultSet.getInt("usuario_id") + "")) {
						return resultSet.getInt("usuario_id");
					} else {
						return 0;
					}
				}
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	// Verificamos si la ultima factura creada tiene productos para saber si tenemos que crear otra o podemos usar la misma
	public boolean ultimaFacturaIncompleta(int administrador_id) {
		
		try {
			String sentencia = "select * from factura where id = maxIdFactura() and administrador_id = ? and total = 0.00";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					if(resultSet.next()) {
						return true;
					} else {
						return false;
					}
					
				}
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean actualizarFactura(Factura factura) {
		int item = 0;
		
		try {
			String sentencia = "call actualizarFactura(?,?,?,?,?,?,?,?,?)";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setInt(1, factura.getId());
				statement.setDouble(2, factura.getDescuento_porcentaje());
				statement.setDouble(3, factura.getDescuento());
				statement.setDouble(4, factura.getSubtotal());
				statement.setDouble(5, factura.getIva());
				statement.setDouble(6, factura.getTotal());
				statement.setString(7, factura.getForma_pago());
				statement.setDate(8, factura.getFecha());
				statement.setInt(9, factura.getUsuario_id());
				
				item = statement.executeUpdate();
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
	}
	
	public int eliminarFactura(int id) {
		
		if(!eliminarMembresiasFactura(id)) {
			return 0;
		}
		
		try {
			String sentencia = "delete from factura where id = ?";
			
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
	
	public boolean eliminarMembresiasFactura(int factura_id) {
		
		try {
			String sentencia = "delete from membresia where factura_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setInt(1, factura_id);
				
				return new Utilidades().toBoolean(statement.executeUpdate());
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
