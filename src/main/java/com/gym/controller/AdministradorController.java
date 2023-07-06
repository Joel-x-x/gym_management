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

	public Object[][] listar(String nombre) {
		var listarAdministradores = administradorDAO.listar(nombre);
		
		return new ArrayUtilidades().toMatrizAdministrador(listarAdministradores);
	}

	public boolean modificarClave(String clave, int administrador_id) {
		return administradorDAO.modificarClave(clave, administrador_id);
	}

	public boolean eliminarClave(String clave) {
		return administradorDAO.eliminarClave(clave);
	}
	
	public int getId() {
		return administradorDAO.getId();
	}

	public boolean crearCuenta(int id) {
		return administradorDAO.crearCuenta(id);
	}
	
	public boolean crearRecuperacionCuenta(int id) {
		return administradorDAO.crearRecuperacionCuenta(id);
	}
	
	public boolean cambiarPassword(String password, int id) {
		return administradorDAO.cambiarPassword(password, id);
	}

	public String getNombreUsuario(int administrador_id) {
		return administradorDAO.getNombreUsuario(administrador_id);
	}

}
