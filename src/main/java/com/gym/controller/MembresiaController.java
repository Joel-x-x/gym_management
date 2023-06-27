package com.gym.controller;

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
	
	public List<Plan> listarPlan(int administrador_id) {
		return membresiaDAO.listarPlan(administrador_id);
	}

	public List<Clase> listarClase(int administrador_id) {
		return membresiaDAO.listarClase(administrador_id);
	}
}
