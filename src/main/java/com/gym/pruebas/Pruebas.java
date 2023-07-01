package com.gym.pruebas;

import com.gym.model.Membresia;

public class Pruebas {
	public static void main(String[] args) {
		var membresia = new Membresia();
		
		membresia.setFecha_fin("2023-07-10 03:51:31");
		
		membresia.setAnticipacion(9);
		
		System.out.println(membresia.notificarMembresia());
	}
}
