package com.gym.controller;

import com.gym.dao.PlanesDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Plan;

public class PlanController {
	PlanesDAO planesDAO;
	public PlanController() {
		planesDAO = new PlanesDAO(new ConnectionFactory().conectar());
	}
	public boolean registrar(Plan plan) {
		return planesDAO.agregar(plan);
	}

}
