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
	public List<Factura> consultarFactura(){
		List<Factura> resultado = new ArrayList<>();
//		int elementosPorPagina = 10; 
//		int paginaActual = 1;
		
		try {
			
			String sentencia = "select * from factura f, usuario u where f.administrador_id = ? and f.usuario_id = u.id";
			
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement) {
				
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
			                    resultSet.getString("f.fecha"),
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
	
	public List<Factura> listarFactura(int administrador_id, String nombre) {
		
		List<Factura> resultado = new ArrayList<>();
//		int elementosPorPagina = 10; 
//		int paginaActual = 1;
		
		try {
			
			String sentencia = "select * from factura f, usuario u where f.administrador_id = ? and f.usuario_id = u.id";
			
			if(!nombre.equals("")) {
				sentencia = "select * from factura f, usuario u where f.administrador_id = ? and f.usuario_id = u.id and nombre like ?";
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
			                    resultSet.getString("f.fecha"),
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
			String sentencia = "select * from factura where id = last_insert_id() and administrador_id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				statement.setInt(1, administrador_id);
				
				final ResultSet resultSet = statement.executeQuery();
				
				try(resultSet) {
					return factura = new Factura(
							resultSet.getInt("id"),
							resultSet.getString("numero_factura"), 
							resultSet.getString("forma_pago"), 
							resultSet.getString("fecha"), 
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
	
	public boolean actualizarFactura(Factura factura) {
		int item = 0;
		
		try {
			String sentencia = "update factura set descuento_porcentaje = ?, descuento = ?, subtotal = ?, iva = ?, total = ?,"
					+ " forma_pago = ?, fecha = ?, usuario_id = ? where id = ?";
			
			final PreparedStatement statement = con.prepareStatement(sentencia);
			
			try(statement){
				
				statement.setDouble(1, factura.getDescuento_porcentaje());
				statement.setDouble(1, factura.getDescuento());
				statement.setDouble(1, factura.getSubtotal());
				statement.setDouble(1, factura.getIva());
				statement.setDouble(1, factura.getTotal());
				statement.setString(1, factura.getForma_pago());
				statement.setString(1, factura.getFecha());
				statement.setInt(1, factura.getUsuario_id());
				statement.setInt(1, factura.getId());
				
				item = statement.executeUpdate();
	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return new Utilidades().toBoolean(item);
	}
	
	public int eliminarFactura(int id) {
		
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
}
