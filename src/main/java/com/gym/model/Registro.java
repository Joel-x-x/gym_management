package com.gym.model;

import com.gym.utilidades.Utilidades;

public class Registro {
    private int id;
    private String fecha_entrada;
    private String fecha_salida;
    private int usuario_id;
    private String nombreUsuario;
    private String nombrePlan;
    private String clase;
    private int activo;
    private int membresia_id;

	private Membresia membresia;
    
    // Registrar
    public Registro(int id, String fecha_entrada, String fecha_salida, int usuario) {
        this.id = id;
        this.fecha_entrada = fecha_entrada;
        this.fecha_salida = fecha_salida;
        this.usuario_id = usuario;
    }
    
    // Listar
    public Registro(int id, String fecha_entrada, String fecha_salida, int usuario_id, String nombreUsuario,
			String nombrePlan, String clase, int activo) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.usuario_id = usuario_id;
		this.nombreUsuario = nombreUsuario;
		this.nombrePlan = nombrePlan;
		this.clase = clase;
		this.activo = activo;
	}

	// Listar2
	public Registro(int id, String fecha_entrada, String fecha_salida, int usuario_id, String nombreUsuario,
			String nombrePlan, String clase) {
		this.id = id;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.usuario_id = usuario_id;
		this.nombreUsuario = nombreUsuario;
		this.nombrePlan = nombrePlan;
		this.clase = clase;
	}
	
	public String mensajeMembresia() {
		
		if(new Utilidades().toBoolean(this.activo)) {
			return "Activa";
		} else {
			return "Caducada";
		}
		
	}

	public Membresia getMembresia() {
		return membresia;
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha_entrada() {
		return fecha_entrada;
	}

	public void setFecha_entrada(String fecha_entrada) {
		this.fecha_entrada = fecha_entrada;
	}

	public String getFecha_salida() {
		return fecha_salida;
	}

	public void setFecha_salida(String fecha_salida) {
		this.fecha_salida = fecha_salida;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getNombrePlan() {
		return nombrePlan;
	}

	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}
	
    public int getRegistro_id() {
		return membresia_id;
	}

	public void setRegistro_id(int registro_id) {
		this.membresia_id = registro_id;
	}
	
    public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public int getMembresia_id() {
		return membresia_id;
	}

	public void setMembresia_id(int membresia_id) {
		this.membresia_id = membresia_id;
	}

}
