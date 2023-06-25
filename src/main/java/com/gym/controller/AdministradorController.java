package com.gym.controller;

import com.gym.dao.AdministradorDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Administrador;

public class AdministradorController {
	AdministradorDAO administradorDAO;
	
	public AdministradorController() {
		administradorDAO = new AdministradorDAO(new ConnectionFactory().conectar());
	}
	
	public boolean registrar(Administrador administrador) {
			
		return administradorDAO.registrar(administrador); 
	}

	public boolean sesion(Administrador administrador) {
		
		return administradorDAO.iniciarSesion(administrador);
	}

}
