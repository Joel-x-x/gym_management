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
		for(Reporte reporte : lista_reporte) {
			reporte.cambiarActivoMembresia();
			System.out.println(reporte.getFecha_fin());
		}
		return new ArrayUtilidades().toMatrizMembresiaReporte(lista_reporte);
		}
}

