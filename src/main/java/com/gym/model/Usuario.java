package com.gym.model;

import java.sql.Date;

public class Usuario{
    int id;
    String nombre;
    String apellido;
    Date fecha_nacimiento;
    String sexo;
    String email;
    String cedula;
    String direccion;
    String telefono;
    String fecha_creacion;
    int administrador_id;
    
    public Usuario(int id, String nombre, String apellido, Date fecha_nacimiento, String sexo, String email, String cedula, String direccion, String telefono, String fecha_creacion, int administrador_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.email = email;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_creacion = fecha_creacion;
        this.administrador_id = administrador_id;
    }
    
    public Usuario(int id, String nombre, String apellido, Date fecha_nacimiento, String sexo, String email, String cedula, String direccion, String telefono, int administrador_id) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.email = email;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.administrador_id = administrador_id;
    }
    
    public Usuario(String nombre, String apellido, Date fecha_nacimiento, String sexo, String email, String cedula, String direccion, String telefono, int administrador_id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.email = email;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.administrador_id = administrador_id;
    }

    public Usuario(int id, String nombre, String apellido, Date fecha_nacimiento, String sexo, String email, String cedula, String direccion, String telefono, String fecha_creacion) {
    	this.id = id;
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.fecha_nacimiento = fecha_nacimiento;
    	this.sexo = sexo;
    	this.email = email;
    	this.cedula = cedula;
    	this.direccion = direccion;
    	this.telefono = telefono;
    	this.fecha_creacion = fecha_creacion;
    }
    
    // UsuariosFrame para el usuario seleccionado 
    public Usuario(int id, String nombre, String apellido, String cedula) {
    	this.id = id;
    	this.nombre = nombre;
    	this.apellido = apellido;
    	this.cedula = cedula;
    }
    
    public Usuario(  String cedula) {
		this.cedula = cedula;
	}
    
    public Usuario(int administrador_id, String cedula) {
		this.administrador_id = administrador_id;
		this.cedula = cedula;
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

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getAdministrador_id() {
        return administrador_id;
    }

    public void setAdministrador_id(int administrador_id) {
        this.administrador_id = administrador_id;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

}