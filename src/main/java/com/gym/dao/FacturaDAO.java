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

	
	public List<Factura> listarFactura(int administrador_id, String buscar) {
		
		List<Factura> resultado = new ArrayList<>();
//		int elementosPorPagina = 10; 
//		int paginaActual = 1;
		
		try {
			
			String sentencia = "select * from factura f, usuario u where f.administrador_id = ? and f.usuario_id = u.id and total <> 0.0";
			
			// Cedula
			if(Utilidades.isNumber(buscar)) sentencia = "select * from factura f, usuario u where f.administrador_id = ? and f.usuario_id = u.id and cedula like ? and total <> 0.0";
			
			// Nombre
			if(!Utilidades.isNumber(buscar) && !buscar.equals("")) sentencia = "select * from factura f, usuario u where f.administrador_id = ? and f.usuario_id = u.id and nombre like ? and total <> 0.0";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, administrador_id);
				
				// Consultar
				if(!buscar.equals("")) statement.setString(2, buscar + "%");
				
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
			                    resultSet.getString("u.nombre"),
			                    resultSet.getString("u.cedula")
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
	
	public int obtenerIva(int administrador_id) {
			
		try {
			String sentencia = "select iva from iva where id = (select obtenerIdIva(?))";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					resultSet.next();
					
					return resultSet.getInt("iva");
				}
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public boolean actualizarIva(double iva, int administrador_id) {
		
		try {
			String sentencia = "insert into iva(iva, administrador_id) values(?, ?)";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setDouble(1, iva);
				statement.setInt(2, administrador_id);
				
				return new Utilidades().toBoolean(statement.executeUpdate());
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Factura> listarIvas(int administrador_id) {
		
		List<Factura> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "call listarIvas(?)";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Factura(
								resultSet.getDouble("iva"),
								resultSet.getInt("administrador_id"),
								resultSet.getString("fecha")
								));
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
		
	}
	
	public boolean agregarFormaPago(String forma_pago, Double monto_pagado, int usuario_id, int factura_id) {
		
		try {
			String sentencia = "insert into forma_pago(forma_pago, monto_pagado, usuario_id, factura_id) values(?,?,?,?)";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setString(1, forma_pago);
				statement.setDouble(2, monto_pagado);
				statement.setInt(3, usuario_id);
				statement.setInt(4, factura_id);
				
				return new Utilidades().toBoolean(statement.executeUpdate());
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean borrarFormaPago(int id) {
		
		try {
			String sentencia = "delete from forma_pago where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setInt(1, id);
				
				return new Utilidades().toBoolean(statement.executeUpdate());
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Factura> listarFormaPago(int factura_id) {
		
		List<Factura> resultado = new ArrayList<>();
		
		try {
			
			String sentencia = "select * from forma_pago where factura_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				statement.setInt(1, factura_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					
					while(resultSet.next()) {
						resultado.add(new Factura(
								resultSet.getInt("id"),
								resultSet.getString("forma_pago"),
								resultSet.getDouble("monto_pagado"),
								resultSet.getDate("fecha")
								));
					}
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return resultado;
		
	}
	
}
