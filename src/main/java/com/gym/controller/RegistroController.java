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
	public boolean registrar(int usuario_id, int membresia_id) {
		return registroDAO.registrar(usuario_id, membresia_id);
	}
	
	public Object[][] consultar(int administrador_id) {
		var listaRegistros = registroDAO.consultar(administrador_id);
		
		return new ArrayUtilidades().toMatrizMembresiaRegistro(listaRegistros);
	}
	
	public List<Registro> consultarLista(int usuario_id) {
		return registroDAO.consultarRegistro(usuario_id);
	}
	
	public boolean registrarSalida(int id) {
		return registroDAO.registrarSalida(id);
	}
	
	public Object[][] consultarFecha(int administrador_id, Date fechaInicioSQL, Date fechaFinSQL) {
		var listaRegistros = registroDAO.consultarFecha(administrador_id, fechaInicioSQL, fechaFinSQL);
		
		return new ArrayUtilidades().toMatrizMembresiaRegistro(listaRegistros);
	}

}
