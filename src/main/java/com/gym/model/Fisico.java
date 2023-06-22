package com.gym.model;

public class Fisico {
    int id;
    float altura;
    double peso;
    String fecha;
    int usuario_id;
    int usuario_cuenta_id;
    public Fisico(int id, float altura, double peso, String fecha, int usuario_id, int usuario_cuenta_id) {
        this.id = id;
        this.altura = altura;
        this.peso = peso;
        this.fecha = fecha;
        this.usuario_id = usuario_id;
        this.usuario_cuenta_id = usuario_cuenta_id;
    }

      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getUsuario_cuenta_id() {
        return usuario_cuenta_id;
    }

    public void setUsuario_cuenta_id(int usuario_cuenta_id) {
        this.usuario_cuenta_id = usuario_cuenta_id;
    }
    
}
