package com.gym.model;

public class Plan {
    int id;
    String nombre;
    Float precio;
    String descripcion;
    int tiempo_id;
    public Plan(int id, String nombre, Float precio, String descripcion, int tiempo_id) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tiempo_id = tiempo_id;
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
    public int getTiempo_id() {
        return tiempo_id;
    }
    public void setTiempo_id(int tiempo_id) {
        this.tiempo_id = tiempo_id;
    }
    
    
}
