package com.gym.controller;

import java.util.List;

import com.gym.dao.PlanesDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Plan;

public class PlanController {
	PlanesDAO planesDAO;
	public PlanController() {
		planesDAO = new PlanesDAO(new ConnectionFactory().conectar());
	}
	public boolean agregar(Plan plan) {
		return planesDAO.agregar(plan);
	}
	public Object[][] consulta(Plan plan) {
		return planesDAO.consultar(plan);

	}
	public String[] consultar_(Plan plan) {
		return planesDAO.consultar_(plan);
	}
	public boolean consult(Plan plan) {
		return planesDAO.modificar(plan);
	}
	public boolean eliminar(Plan plan) {
		return planesDAO.eliminar(plan);
	}

}
