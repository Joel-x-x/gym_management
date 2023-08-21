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
	
	public Object[][] listarFactura(int administrador_id) {
		var listaFactura = facturaDAO.listarFactura(administrador_id);
		
		return new ArrayUtilidades().toMatrizFactura(listaFactura);
	}
	
	public boolean crearFactura(int administrador_id) {
		return facturaDAO.crearFactura(administrador_id);
	}
	
	public Factura consultarUltimaFactura(int administrador_id) {
		return facturaDAO.consultarUltimaFactura(administrador_id);
	}
	
	public boolean actualizarFactura(Factura factura) {
		return facturaDAO.actualizarFactura(factura);
	}
	
	public int eliminarFactura(int id) {
		return  facturaDAO.eliminarFactura(id);
	}
}
