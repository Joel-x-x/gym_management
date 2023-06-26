package com.gym.model;

public class Administrador {
    static int id;
    String nombre;
    String apellido; 
    String email;
    String password;
    int sesion_iniciada;
    int super_admin;
    String clave;
    public Administrador(int id, String nombre, String apellido, String email, String password, int sesion_iniciada,
            int super_admin, String clave) {
        Administrador.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.sesion_iniciada = sesion_iniciada;
        this.super_admin = super_admin;
        this.clave = clave;
    }
    
    public Administrador(String nombre, String email, String password, int sesion_iniciada, int super_admin, String clave) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.sesion_iniciada = sesion_iniciada;
        this.super_admin = super_admin;
        this.clave = clave;
    }
    
    public Administrador() {
    	
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        Administrador.id = id;
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getSesion_iniciada() {
        return sesion_iniciada;
    }
    public void setSesion_iniciada(int sesion_iniciada) {
        this.sesion_iniciada = sesion_iniciada;
    }
    public int isSuper_admin() {
        return super_admin;
    }
    public void setSuper_admin(int super_admin) {
        this.super_admin = super_admin;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    
}
