package com.gym.controller;

import java.util.List;

import com.gym.dao.FacturaDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Factura;
import com.gym.utilidades.ArrayUtilidades;

public class FacturaController {
	public FacturaDAO facturaDAO;
	
	public FacturaController() {
		facturaDAO = new FacturaDAO(ConnectionFactory.conectar());
	}
	
	public Object[][] listarFactura(int administrador_id, String nombre) {
		var listaFactura = facturaDAO.listarFactura(administrador_id, nombre);
		
		return new ArrayUtilidades().toMatrizFactura(listaFactura);
	}
	
	public Factura consultarFactura(int id) {
		return facturaDAO.consultarFactura(id);
	}
	
	public boolean crearFactura(int administrador_id) {
		return facturaDAO.crearFactura(administrador_id);
	}
	
	public Factura consultarUltimaFactura(int administrador_id) {
		return facturaDAO.consultarUltimaFactura(administrador_id);
	}
	
	public boolean ultimaFacturaIncompleta(int administrador_id) {
		return facturaDAO.ultimaFacturaIncompleta(administrador_id);
	}
	
	public boolean actualizarFactura(Factura factura) {
		return facturaDAO.actualizarFactura(factura);
	}
	
	public int eliminarFactura(int id) {
		return  facturaDAO.eliminarFactura(id);
	}
	
	public int obtenerIva(int administrador_id) {
		return facturaDAO.obtenerIva(administrador_id);
	}
	
	public boolean actulizarIva(double iva, int administrador_id) {
		return facturaDAO.actualizarIva(iva, administrador_id);
	}
	
	public List<Factura> listarIvas(int administrador_id) {
		return facturaDAO.listarIvas(administrador_id);
	}
	
	public boolean agregarFormaPago(String forma_pago, Double monto, String factura_numero) {
		return facturaDAO.agregarFormaPago(forma_pago, monto, factura_numero);
	}
	
	public boolean borrarFormaPago(int id) {
		return facturaDAO.borrarFormaPago(id);
	}
	
	public Object[][] listarFormaPago(String factura_numero) {
		var listaFormaPago = facturaDAO.listarFormaPago(factura_numero);
		
		return new ArrayUtilidades().toMatrizFormaPago(listaFormaPago);
	}
}
