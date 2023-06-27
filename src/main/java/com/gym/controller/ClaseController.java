package com.gym.controller;

import com.gym.dao.ClaseDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Clase;

public class ClaseController {
	ClaseDAO claseDAO;
	public ClaseController() {
		claseDAO = new ClaseDAO(new ConnectionFactory().conectar());
	}
	public boolean agregar(Clase clase) {
		return claseDAO.agregar(clase);
	}
	public Object[][] consulta(Clase clase) {
		return claseDAO.consultar(clase);

	}
	public String[] consultar_(Clase clase) {
		return claseDAO.consultar_(clase);
	}
	public boolean consult(Clase clase) {
		return claseDAO.modificar(clase);
	}
	public boolean eliminar(Clase clase) {
		return claseDAO.eliminar(clase);
	}
	

}
