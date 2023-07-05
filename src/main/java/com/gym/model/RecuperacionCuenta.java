package com.gym.model;

public class RecuperacionCuenta {
	private int id;
	private String nombre_amigo;
	private String nombre_mascota;
	private String color_favorito;
	private String cedula;
	private int administrador_id;
	
	public RecuperacionCuenta(int id, String nombre_amigo, String nombre_mascota, String color_favorito, String cedula,
			int administrador_id) {
		super();
		this.id = id;
		this.nombre_amigo = nombre_amigo;
		this.nombre_mascota = nombre_mascota;
		this.color_favorito = color_favorito;
		this.cedula = cedula;
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
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public int getAdministrador_id() {
		return administrador_id;
	}
	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	
	
}
