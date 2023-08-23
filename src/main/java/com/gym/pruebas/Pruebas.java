package com.gym.pruebas;

import com.gym.utilidades.PasswordUtilidades;
import com.gym.utilidades.Utilidades;

public class Pruebas {
	public static void main(String[] args) {
		
		String passwordHash = PasswordUtilidades.hashearPassword("1234");
		String hashSalt = PasswordUtilidades.getSaltHash();
		
		System.out.println(PasswordUtilidades.validarPassword("1234", passwordHash, hashSalt));
		
	}
}
