package com.gym.controller;

import com.gym.dao.UsuarioDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Usuario;

public class UsuarioController {
	UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		this.usuarioDAO = new UsuarioDAO(new ConnectionFactory().conectar());
	}
	
	public int guardar(Usuario usuario) {
		return usuarioDAO.guardar(usuario);
	}
}
