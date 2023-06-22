package com.gym.pruebas;

import java.sql.Connection;

import com.gym.controller.AdministradorController;
import com.gym.factory.ConnectionFactory;

public class Pruebas {
	public static void main(String[] args) {
		Connection con = new ConnectionFactory().conectar();
		System.out.println("xd");
		System.out.println("listos");
		AdministradorController administradorController = new AdministradorController();
		
		
	}
}
