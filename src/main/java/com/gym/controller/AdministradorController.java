package com.gym.controller;

import com.gym.dao.AdministradorDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Administrador;
import com.gym.utilidades.ArrayUtilidades;

public class AdministradorController {
	AdministradorDAO administradorDAO;
	
	public AdministradorController() {
		administradorDAO = new AdministradorDAO(ConnectionFactory.conectar());
	}
	
	public boolean registrar(Administrador administrador) {
			
		return administradorDAO.registrar(administrador); 
	}

	public boolean sesion(Administrador administrador) {
		
		return administradorDAO.iniciarSesion(administrador);
	}
	
	public int consultarId(String email) {
		return administradorDAO.consultarId(email);
	}

	public boolean superUsuario(int administrador_id) {
		return administradorDAO.superUsuario(administrador_id);
	}

	public Object[][] listar() {
		var listarAdministradores = administradorDAO.listar();
		
		return new ArrayUtilidades().toMatrizAdministrador(listarAdministradores);
	}

	public boolean modificarClave(String clave, int administrador_id) {
		return administradorDAO.modificarClave(clave, administrador_id);
	}

	public boolean eliminarClave(String clave) {
		return administradorDAO.eliminarClave(clave);
	}

}
