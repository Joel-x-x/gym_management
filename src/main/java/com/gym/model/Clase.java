package com.gym.model;

public class Clase {
    int id;
    String clase;
    String descripcion;
    int entrenador_id;
    int administrador_id;

	public Clase(int id, String clase, String descripcion, int entrenador_id, int administrador_id) {
        this.id = id;
        this.clase = clase;
        this.descripcion = descripcion;
        this.entrenador_id = entrenador_id;
        this.administrador_id= administrador_id;
    }
	public Clase(String nombre) {
		this.clase = nombre;
	}
	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEntrenador_id() {
        return entrenador_id;
    }

    public void setEntrenador_id(int entrenador_id) {
        this.entrenador_id = entrenador_id;
    }
    
    public int getAdministrador_id() {
		return administrador_id;
	}

	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
    
}
