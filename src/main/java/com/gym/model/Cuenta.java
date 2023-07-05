package com.gym.model;

public class Cuenta {
    int id;
    String nombre_empresa;
    byte[] logo_empresa;
    byte[] imagen_perfil;
    int administrador_id;
    
	public Cuenta(int id, String nombre_empresa, byte[] logo_empresa, byte[] imagen_perfil, int administrador_id) {
		super();
		this.id = id;
		this.nombre_empresa = nombre_empresa;
		this.logo_empresa = logo_empresa;
		this.imagen_perfil = imagen_perfil;
		this.administrador_id = administrador_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public byte[] getLogo_empresa() {
		return logo_empresa;
	}

	public void setLogo_empresa(byte[] logo_empresa) {
		this.logo_empresa = logo_empresa;
	}

	public byte[] getImagen_perfil() {
		return imagen_perfil;
	}

	public void setImagen_perfil(byte[] imagen_perfil) {
		this.imagen_perfil = imagen_perfil;
	}

	public int getAdministrador_id() {
		return administrador_id;
	}

	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
    
}