package com.gym.view;

import com.gym.model.Usuario;

public interface GenerarFrameInterfaz {
	public void listarUsuarios();
	public void usuarioSeleccionado(Usuario usuario);
	public void listarTipoMembresias();
	public void tipoMembresiaSeleccionada(int id);
}
