package com.gym.controller;
import com.gym.dao.RegistroDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Registro;
public class RegistroController {
	RegistroDAO registroDAO;
	public RegistroController() {
		registroDAO = new RegistroDAO(new ConnectionFactory().conectar());
	}
	public Integer guardar(Registro registro) {
		return registroDAO.guardar(registro);
	}
	public Object[][]  consulta(Registro registro) {
		return registroDAO.consultar(registro);
	}
	public Integer guardar_salida(Registro registro) {
		return registroDAO.guardar_salida(registro);
	}

}
