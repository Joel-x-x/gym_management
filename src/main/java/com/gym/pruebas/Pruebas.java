package com.gym.pruebas;

import com.gym.model.Membresia;
import com.gym.utilidades.PasswordUtilidades;

public class Pruebas {
	public static void main(String[] args) {
		
//		String passwordHash = PasswordUtilidades.hashearPassword("1234");
//		String hashSalt = PasswordUtilidades.getSaltHash();
//		
//		System.out.println(PasswordUtilidades.validarPassword("1234", passwordHash, hashSalt));
		
		Membresia membresia = new Membresia();
		
		membresia.setFecha_fin("2023-09-01 22:38:57");
		
		System.out.println(membresia.caducaDias());
		
	}
}
