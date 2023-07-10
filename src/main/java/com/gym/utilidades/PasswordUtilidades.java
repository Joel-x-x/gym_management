package com.gym.utilidades;

import com.password4j.Hash;
import com.password4j.Password;

public class PasswordUtilidades {
	private static Hash hash;
	
	public static String hashearPassword(String password) {
		hash = Password.hash(password).addRandomSalt().withPBKDF2();
		
		return hash.getResult();
	}
	
	public static String getSaltHash() {
		return hash.getSalt();
	}
	
	public static boolean validarPassword(String passwordUser, String passwordHash, String hashSalt) {
		return Password.check(passwordUser, passwordHash).addSalt(hashSalt).withPBKDF2();
	}
}
