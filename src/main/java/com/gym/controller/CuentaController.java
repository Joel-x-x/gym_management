package com.gym.controller;

import com.gym.dao.CuentaDAO;
import com.gym.factory.ConnectionFactory;

public class CuentaController {
	private CuentaDAO cuentaDAO;
	
	public CuentaController() {
		cuentaDAO = new CuentaDAO(ConnectionFactory.conectar());
	}
}
