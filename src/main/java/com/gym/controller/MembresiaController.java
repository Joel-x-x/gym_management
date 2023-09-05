package com.gym.controller;

import java.sql.Date;
import java.util.List;

import com.gym.dao.MembresiaDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Clase;
import com.gym.model.Membresia;
import com.gym.model.TipoMembresia;
import com.gym.utilidades.ArrayUtilidades;

public class MembresiaController {
	private MembresiaDAO membresiaDAO;
	
	public MembresiaController() {
		membresiaDAO = new MembresiaDAO( ConnectionFactory.conectar());
	}
	
	public List<Clase> listarClase(int administrador_id) {
		return membresiaDAO.listarClase(administrador_id);
	}

	public Object[][] listar(int administrador_id, String buscar) {
		var listaMembresias = membresiaDAO.listar(administrador_id, buscar);
		
		for(Membresia membresia : listaMembresias) {
			membresia.cambiarActivoMembresia();
			
		    Thread notificarThread = new Thread(() -> {
		        membresia.membresiaCaducada();
		    });

		    notificarThread.start();
		}
		
		return new ArrayUtilidades().toMatrizMembresia(listaMembresias);
	}
	
	public List<Membresia> listarMembresiasUsuario(int usuario_id) {
		var listaMembresias = membresiaDAO.listarMembresiasUsuario(usuario_id);
		
		for(Membresia membresia : listaMembresias) {
			membresia.cambiarActivoMembresia();
		}
		
		return listaMembresias;
	}
	
	public Object[][] listarMembresiaFactura(int administrador_id, int factura_id) {
		var listaMembresia = membresiaDAO.listarMembresiaFactura(administrador_id, factura_id);
		
		return new ArrayUtilidades().toMatrizMembresiaFactura(listaMembresia);
	}
	
	public List<Membresia> listarMembresiaFacturaList(int administrador_id, int factura_id) {
		return membresiaDAO.listarMembresiaFactura(administrador_id, factura_id);
	}

	public boolean crearMembresia(int administrador_id, int usuario_id, int factura_id, int tipo_membresia_id) {
		return membresiaDAO.crearMembresia(administrador_id, usuario_id, factura_id, tipo_membresia_id);
	}
	
	public Membresia consulta(int id) {
		return membresiaDAO.consulta(id);
	}
	
	// hasta aqui vale
	
	// Consulta si ese usuario ya tiene una membresia activa
	public boolean consultaActivo(int usuario_id, int id) {
		return membresiaDAO.consultaActivo(usuario_id, id);
	}
	
	public boolean modificarActivo(int id, int activo) {
		return membresiaDAO.modificarActivo(id, activo);
	}
	
	public boolean modificarCaducando(int id, int caducando) {
		return membresiaDAO.modificarCaducando(id, caducando);
	}
	
	public boolean modificarCaducada(int id, int caducado) {
		return membresiaDAO.modificarCaducada(id, caducado);
	}

	public boolean eliminar(int id) {
		return membresiaDAO.eliminar(id);
	}

	public boolean existeMembresia(int usuario_id) {
		return membresiaDAO.existeMembresia(usuario_id);
	}

	public List<Integer> consultarClases(int usuario_id) {
		return membresiaDAO.consultarClases(usuario_id);
	}
	
	public Membresia consultaMembresia(int usuario_id, int id) {
		return membresiaDAO.consultaMembresia(usuario_id, id);
	}

	public List<Membresia> consultarFecha(int administrador_id, Date fechaInicioSQL, Date fechaFinSQL) {
		return membresiaDAO.consultarFecha(administrador_id, fechaInicioSQL, fechaFinSQL);
	}
}
