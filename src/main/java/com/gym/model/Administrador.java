package com.gym.model;

public class Administrador {
    int id;
    String nombre;
    String apellido; 
    String email;
    String password;
    boolean sesion_iniciada;
    boolean super_admin;
    String clave;
    public Administrador(int id, String nombre, String apellido, String email, String password, boolean sesion_iniciada,
            boolean super_admin, String clave) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.sesion_iniciada = sesion_iniciada;
        this.super_admin = super_admin;
        this.clave = clave;
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
    public boolean getSesion_iniciada() {
        return sesion_iniciada;
    }
    public void setSesion_iniciada(boolean sesion_iniciada) {
        this.sesion_iniciada = sesion_iniciada;
    }
    public boolean isSuper_admin() {
        return super_admin;
    }
    public void setSuper_admin(boolean super_admin) {
        this.super_admin = super_admin;
    }
    public String getClave() {
        return clave;
    }
    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
}
