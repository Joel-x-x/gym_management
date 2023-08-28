package com.gym.controller;

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
}
