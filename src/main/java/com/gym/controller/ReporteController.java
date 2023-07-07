package com.gym.controller;

import com.gym.dao.ReporteDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Reporte;
import com.gym.utilidades.ArrayUtilidades;

public class ReporteController{
	private ReporteDAO reporteDAO;
	public ReporteController() {
		reporteDAO = new ReporteDAO(ConnectionFactory.conectar()); 
	}
	
	public Object[][] listar_reporte(int usuario_id) {
		var lista_reporte = reporteDAO.listar_reporte(usuario_id);
		
		return new ArrayUtilidades().toMatrizMembresiaReporte(lista_reporte);
		}
}

