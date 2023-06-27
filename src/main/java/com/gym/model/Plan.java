package com.gym.model;

public class Plan {
    int id;
    String nombre;
    Float precio;
    String descripcion;
    String duracion;
    public Plan(int id, String nombre, Float precio, String descripcion, String duracion) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }
    public Plan(String nombre, Float precio, String descripcion, String duracion) {
    	this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.duracion = duracion;
	}
    public Plan(String nombre_ingreso) {
    	this.nombre=nombre_ingreso;
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
    public Float getPrecio() {
        return precio;
    }
    public void setPrecio(Float precio) {
        this.precio = precio;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	@Override
	public String toString() {
		return this.nombre;
	}
}
