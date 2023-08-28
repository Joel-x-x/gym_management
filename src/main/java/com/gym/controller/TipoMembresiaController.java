package com.gym.controller;

import java.util.List;

import com.gym.dao.TipoMembresiaDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.TipoMembresia;
import com.gym.utilidades.ArrayUtilidades;

public class TipoMembresiaController {
	TipoMembresiaDAO tipoMembresiaDAO;
	
	public TipoMembresiaController() {
		tipoMembresiaDAO = new TipoMembresiaDAO( ConnectionFactory.conectar());
	}
	
	public boolean guardar(TipoMembresia tipoMembresia) {
		return tipoMembresiaDAO.guardar(tipoMembresia);
	}
	
	public Object[][] consultar(String nombre, int administrador_id) {
		var listaPlanes = tipoMembresiaDAO.consultar(nombre, administrador_id);
		
		return new ArrayUtilidades().toMatrizTipoMembresia(listaPlanes);
	}

	public int eliminar(int id) {
		return tipoMembresiaDAO.eliminar(id);
	}
	public TipoMembresia consulta(int id) {
		return tipoMembresiaDAO.consulta(id);
	}
	public boolean modificar(TipoMembresia tipoMembresia) {
		return tipoMembresiaDAO.modificar(tipoMembresia);
	}
	
	public List<TipoMembresia> listarPrecios(int tipo_membresia_id) {
		return tipoMembresiaDAO.listarPrecios(tipo_membresia_id);
	}

}
