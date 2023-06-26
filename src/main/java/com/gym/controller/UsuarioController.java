package com.gym.controller;

import com.gym.dao.UsuarioDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Usuario;
import com.gym.utilidades.ArrayUtilidades;

public class UsuarioController {
	UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().conectar());
	}
	
	public boolean guardar(Usuario usuario) {
		return usuarioDAO.guardar(usuario);
	}
	
	public Object[][]  consulta(Usuario usuario) {
		return usuarioDAO.consultar(usuario);
	}

	public Object[][] listar(int administrador_id) {
		var listaUsuarios = usuarioDAO.listar(administrador_id);
		
		return new ArrayUtilidades().toMatrizUsuario(listaUsuarios);
	}
	
}
