package com.gym.controller;

import com.gym.dao.EntrenadorDAO;
import com.gym.dao.PlanesDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Entrenador;
import com.gym.model.Plan;

public class EntrenadorController {
	EntrenadorDAO entrenadorDAO;
	public EntrenadorController() {
		entrenadorDAO = new EntrenadorDAO(new ConnectionFactory().conectar());
	}
	public boolean registrar(Entrenador entrenador) {
		return entrenadorDAO.agregar(entrenador);
	}
	public Object[][] consulta(Entrenador entrenador) {
		return entrenadorDAO.consultar(entrenador);

	}
	public String[] consultar_(Entrenador entrenador) {
		return entrenadorDAO.consultar_(entrenador);
	}
	public boolean consult(Entrenador entrenador) {
		return entrenadorDAO.modificar(entrenador);
	}
	public boolean eliminar(Entrenador entrenador) {
		return entrenadorDAO.eliminar(entrenador);
	}

}