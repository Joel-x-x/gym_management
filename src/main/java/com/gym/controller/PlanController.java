package com.gym.controller;

import com.gym.dao.PlanesDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Plan;
import com.gym.utilidades.ArrayUtilidades;

public class PlanController {
	PlanesDAO planesDAO;
	public PlanController() {
		planesDAO = new PlanesDAO( ConnectionFactory.conectar());
	}
	public boolean guardar(Plan plan) {
		return planesDAO.guardar(plan);
	}
	public Object[][] consultar(String plan, int administrador_id) {
		var listaPlanes = planesDAO.consultar(plan, administrador_id);
		
		return new ArrayUtilidades().toMatrizPlan(listaPlanes);
	}

	public boolean eliminar(int id) {
		return planesDAO.eliminar(id);
	}
	public Plan consulta(int id) {
		return planesDAO.consulta(id);
	}
	public boolean modificar(Plan plan) {
		return planesDAO.modificar(plan);
	}

}
