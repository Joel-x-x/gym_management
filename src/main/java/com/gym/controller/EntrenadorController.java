package com.gym.controller;

import java.util.List;

import com.gym.dao.EntrenadorDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Entrenador;
import com.gym.utilidades.ArrayUtilidades;

public class EntrenadorController {
	EntrenadorDAO entrenadorDAO;
	public EntrenadorController() {
		entrenadorDAO = new EntrenadorDAO(ConnectionFactory.conectar());
	}
	
	public boolean guardar(Entrenador entrenador) {
		return entrenadorDAO.guardar(entrenador);
	}
	
	public boolean modificar(Entrenador entrenador) {
		return entrenadorDAO.modificar(entrenador);
	}
	
	public Object[][] consultar(String nombre, int administrador_id) {
		var listaEntrenadores =  entrenadorDAO.consultar(nombre, administrador_id);
		
		return new ArrayUtilidades().toMatrizEntrenador(listaEntrenadores);
	}
	
	public List<Entrenador> consultarLista(String cedula, int administrador_id) {
		
		var entrenadores = entrenadorDAO.consultar(cedula, administrador_id);
		
		entrenadores.add(0, new Entrenador("-- Selecciona un Entrenador --"));
		
		return entrenadores;
	}
	
	public int eliminar(int id) {
		return entrenadorDAO.eliminar(id);
	}
	
	public Entrenador consulta(int id) {
		return entrenadorDAO.consulta(id);
	}

}