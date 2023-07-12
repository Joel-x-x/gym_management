package com.gym.model;

public class RecuperacionCuenta {
	private int id;
	private String email;
	private String nombre_amigo;
	private String nombre_mascota;
	private String color_favorito;
	private int administrador_id;
	
	public RecuperacionCuenta(String nombre_amigo, String nombre_mascota, String color_favorito,
			int administrador_id) {
		this.nombre_amigo = nombre_amigo;
		this.nombre_mascota = nombre_mascota;
		this.color_favorito = color_favorito;
		this.administrador_id = administrador_id;
	}
	
	public RecuperacionCuenta(String email, String nombre_amigo, String nombre_mascota, String color_favorito,
			int administrador_id) {
		this.email = email;
		this.nombre_amigo = nombre_amigo;
		this.nombre_mascota = nombre_mascota;
		this.color_favorito = color_favorito;
		this.administrador_id = administrador_id;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre_amigo() {
		return nombre_amigo;
	}
	public void setNombre_amigo(String nombre_amigo) {
		this.nombre_amigo = nombre_amigo;
	}
	public String getNombre_mascota() {
		return nombre_mascota;
	}
	public void setNombre_mascota(String nombre_mascota) {
		this.nombre_mascota = nombre_mascota;
	}
	public String getColor_favorito() {
		return color_favorito;
	}
	public void setColor_favorito(String color_favorito) {
		this.color_favorito = color_favorito;
	}
	public int getAdministrador_id() {
		return administrador_id;
	}
	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	
	
}
