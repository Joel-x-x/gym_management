package com.gym.model;

public class Duracion {
    int id;
    String tipo;
    String cantidad;
    public Duracion(int id, String tipo, String cantidad) {
        this.id = id;
        this.tipo = tipo;
        this.cantidad = cantidad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCantidad() {
        return cantidad;
    }
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }
    
}
