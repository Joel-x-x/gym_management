package com.gym.controller;

import com.gym.dao.FisicoDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Fisico;
import com.gym.utilidades.ArrayUtilidades;

public class FisicoController {
	private FisicoDAO fisicoDAO;
	
	public FisicoController() {
		fisicoDAO = new FisicoDAO(new ConnectionFactory().conectar());
	}

	public Object[][] listar(int usuario_id) {
		var listaUsuarios = fisicoDAO.listar(usuario_id);
		
		return new ArrayUtilidades().toMatrizFisico(listaUsuarios);
	}

	public Fisico consulta(int id, int usuario_id) {
		return fisicoDAO.consulta(id, usuario_id);
	}

	public boolean guardar(Fisico fisico) {
		return fisicoDAO.guardar(fisico);
	}

	public boolean eliminar(int id) {
		return fisicoDAO.eliminar(id);
	}

}
