package com.gym.model;

public class Fisico {
    private int id;
    private double peso;
    private double altura;
    private String fecha;
    private int usuario_id;
    
    public Fisico(int id, double peso, double altura, String fecha, int usuario_id) {
        this.id = id;
        this.peso = peso;
        this.altura = altura;
        this.fecha = fecha;
        this.usuario_id = usuario_id;
    }
    
    public Fisico( double peso, double altura, String fecha, int usuario_id) {
    	this.peso = peso;
        this.altura = altura;
        this.fecha = fecha;
        this.usuario_id = usuario_id;
    }

      public Fisico(int id, double peso, double altura, int usuario_id) {
          this.id = id;
          this.peso = peso;
          this.altura = altura;
          this.usuario_id = usuario_id;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
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
    
}
