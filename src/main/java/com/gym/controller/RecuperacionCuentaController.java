package com.gym.controller;

import com.gym.dao.RecuperacionCuentaDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.RecuperacionCuenta;

public class RecuperacionCuentaController {
	RecuperacionCuentaDAO recuperacionCuentaDAO;
	
	public RecuperacionCuentaController() {
		recuperacionCuentaDAO = new RecuperacionCuentaDAO(ConnectionFactory.conectar());
	}

	public boolean modificar(RecuperacionCuenta recuperacionCuenta) {
		return recuperacionCuentaDAO.modificar(recuperacionCuenta);
	}
	
}
