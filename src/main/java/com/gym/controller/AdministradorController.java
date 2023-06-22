package com.gym.controller;

import com.gym.dao.AdministradorDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Administrador;

public class AdministradorController {
	AdministradorDAO administradorDAO;
	
	public AdministradorController() {
		administradorDAO = new AdministradorDAO(new ConnectionFactory().conectar());
	}
	
	public Integer guardar(Administrador administrador) {
			
		return administradorDAO.guardar(administrador); 
	}

	public boolean login(String usuario, String contra) {
		
		return administradorDAO.login(usuario, contra);
	}
	public Object[][] consulta(Administrador administrador) {
		return administradorDAO.consulta(administrador);
	}
}
