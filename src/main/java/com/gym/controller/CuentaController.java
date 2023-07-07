package com.gym.controller;

import com.gym.dao.CuentaDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Cuenta;

public class CuentaController {
	private CuentaDAO cuentaDAO;
	
	public CuentaController() {
		cuentaDAO = new CuentaDAO(ConnectionFactory.conectar());
	}

	public boolean modificarEmpresa(Cuenta cuenta) {
		return cuentaDAO.modificarEmpresa(cuenta);
	}

	public boolean modificarPerfil(byte[] perfil, int administrador_id) {
		return cuentaDAO.modificarPerfil(perfil, administrador_id);
	}
	
	public byte[] getLogo(int administrador_id) {
		return cuentaDAO.getLogo(administrador_id);
	}
	
	public String getNombreEmpresa(int administrador_id) {
		return cuentaDAO.getNombreEmpresa(administrador_id);
	}
	
	public byte[] getPerfil(int administrador_id) {
		return cuentaDAO.getPerfil(administrador_id);
	}
}
