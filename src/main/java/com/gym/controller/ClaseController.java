package com.gym.controller;

import com.gym.dao.ClaseDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Clase;
import com.gym.utilidades.ArrayUtilidades;

public class ClaseController {
	ClaseDAO claseDAO;
	public ClaseController() {
		claseDAO = new ClaseDAO(ConnectionFactory.conectar());
	}
	public boolean guardar(Clase clase) {
		return claseDAO.guardar(clase);
	}
	
	public boolean modificar(Clase clase) {
		return claseDAO.modificar(clase);
	}
	
	public Object[][] consultar(String clase, int administrador_id) {
		var listaConsultas = claseDAO.consultar(clase, administrador_id);
		
		return new ArrayUtilidades().toMatrizClase(listaConsultas);
	}
	
	public boolean eliminar(int id) {
		return claseDAO.eliminar(id);
	}
	public Clase consulta(int id) {
		return claseDAO.consulta(id);
	}
	

}
