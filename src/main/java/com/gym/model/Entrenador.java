package com.gym.model;

public class Entrenador {
    int id;
    String nombre;
    String apellido;
    String sexo;
    String correo;
    String telefono;
    String cedula;
    int administrador_id;
    
    // Consultar
    public Entrenador(int id, String nombre, String apellido, String sexo, String correo, String telefono, String cedula) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.correo = correo;
        this.telefono = telefono;
        this.cedula = cedula;
    }
    
    // Guardar
    public Entrenador(int id, String nombre, String apellido, String sexo, String correo, String telefono, String cedula, int administrador_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.correo = correo;
        this.telefono = telefono;
        this.cedula = cedula;
        this.administrador_id = administrador_id;
    }
    
    public Entrenador(String nombre) {
    	this.nombre=nombre;
    }
    

    public int getAdministrador_id() {
		return administrador_id;
	}


	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}


	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	@Override
	public String toString() {
		return this.nombre;
	}
    

}
