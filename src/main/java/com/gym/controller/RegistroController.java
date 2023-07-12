package com.gym.controller;
import java.sql.Date;
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
	public boolean registrar(int usuario_id, int id) {
		return registroDAO.registrar(usuario_id, id);
	}
	
	public Object[][] consultar(int usuario_id) {
		var listaRegistros = registroDAO.consultar(usuario_id);
		
		return new ArrayUtilidades().toMatrizMembresiaRegistro(listaRegistros);
	}
	
	public List<Registro> consultarLista(int usuario_id) {
		return registroDAO.consultarRegistro(usuario_id);
	}
	
	public boolean registrarSalida(int id) {
		return registroDAO.registrarSalida(id);
	}
	public Object[][] consultarFecha(int usuario_id, Date fechaInicioSQL, Date fechaFinSQL) {
		var listaRegistros = registroDAO.consultarFecha(usuario_id, fechaInicioSQL, fechaFinSQL);
		
		return new ArrayUtilidades().toMatrizMembresiaRegistro(listaRegistros);
	}

}
