package com.gym.controller;

import com.gym.dao.UsuarioDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Membresia;
import com.gym.model.Usuario;
import com.gym.utilidades.ArrayUtilidades;

public class UsuarioController {
	UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO( ConnectionFactory.conectar());
	}
	
	public boolean guardar(Usuario usuario) {
		return usuarioDAO.guardar(usuario);
	}

	public Object[][] listar(int administrador_id) {
		var listaUsuarios = usuarioDAO.listar(administrador_id);
		
		return new ArrayUtilidades().toMatrizUsuario(listaUsuarios);
	}

	public boolean modificar(Usuario usuario) {
		return usuarioDAO.modificar(usuario);
	}

	public Usuario consulta(int idSeleccionado, int administrador_id) {
		return usuarioDAO.consulta(idSeleccionado, administrador_id);
	}
	
	public Membresia consultaMembresia(int usuario_id) {
		return usuarioDAO.consultaMembresia(usuario_id);
	}

	public boolean eliminar(int idSeleccionado) {
		return usuarioDAO.eliminar(idSeleccionado);
	}

	public Object[][] consultar(int administrador_id, String cedula) {
		var listaUsuarios = usuarioDAO.consultar(administrador_id, cedula);
		
		return new ArrayUtilidades().toMatrizUsuario(listaUsuarios);
	}
	
}
