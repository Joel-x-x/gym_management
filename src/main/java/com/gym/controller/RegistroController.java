package com.gym.controller;
import java.util.List;

import com.gym.dao.RegistroDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Registro;
import com.gym.utilidades.ArrayUtilidades;
public class RegistroController {
	RegistroDAO registroDAO;
	public RegistroController() {
		registroDAO = new RegistroDAO( ConnectionFactory.conectar());
	}
	public boolean registrar(int usuario_id) {
		return registroDAO.registrar(usuario_id);
	}
	
	public Object[][] consultar(int usuario_id) {
		var listaRegistros = registroDAO.consultar(usuario_id);
		
		return new ArrayUtilidades().toMatrizRegistro(listaRegistros);
	}
	
	public List<Registro> consultarLista(int usuario_id) {
		return registroDAO.consultar(usuario_id);
	}
	
	public boolean registrarSalida(int id) {
		return registroDAO.registrarSalida(id);
	}

}
