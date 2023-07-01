package com.gym.controller;

import java.util.List;

import com.gym.dao.MembresiaDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Clase;
import com.gym.model.Membresia;
import com.gym.model.Plan;
import com.gym.utilidades.ArrayUtilidades;

public class MembresiaController {
	private MembresiaDAO membresiaDAO;
	
	public MembresiaController() {
		membresiaDAO = new MembresiaDAO( ConnectionFactory.conectar());
	}
	
	public List<Plan> listarPlan(int administrador_id) {
		return membresiaDAO.listarPlan(administrador_id);
	}

	public List<Clase> listarClase(int administrador_id) {
		return membresiaDAO.listarClase(administrador_id);
	}

	public Object[][] listar(int usuario_id) {
		var listaMembresia = membresiaDAO.listar(usuario_id);
		
		return new ArrayUtilidades().toMatrizMembresia(listaMembresia);
	}

	public boolean guardar(Membresia membresia) {
		return membresiaDAO.guardar(membresia);
	}

	public Membresia consulta(int id, int usuario_id) {
		return membresiaDAO.consulta(id, usuario_id);
	}
	
	public Membresia consultaUltimaMembresia(int usuario_id) {
		return membresiaDAO.consultaUltimaMembresia(usuario_id);
	}
	
	// Consulta si ese usuario ya tiene una membresia activa
	public boolean consultaActivo(int usuario_id) {
		return membresiaDAO.consultaActivo(usuario_id);
	}

	public boolean modificar(Membresia membresia) {
		return membresiaDAO.modificar(membresia);
	}
	
	public boolean modificarActivo(int id, int activo) {
		return membresiaDAO.modificarActivo(id, activo);
	}

	public boolean eliminar(int id) {
		return membresiaDAO.eliminar(id);
	}
}
