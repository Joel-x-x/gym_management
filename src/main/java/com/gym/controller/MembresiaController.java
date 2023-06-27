package com.gym.controller;

import java.util.Collection;
import java.util.List;

import com.gym.dao.MembresiaDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Clase;
import com.gym.model.Plan;

public class MembresiaController {
	private MembresiaDAO membresiaDAO;
	
	public MembresiaController() {
		membresiaDAO = new MembresiaDAO(new ConnectionFactory().conectar());
	}
	
	public List<Plan> listarPlan() {
		return membresiaDAO.listarPlan();
	}

	public List<Clase> listarClase() {
		return membresiaDAO.listarClase();
	}
}
