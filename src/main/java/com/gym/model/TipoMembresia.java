package com.gym.model;

public class TipoMembresia {
    int id;
    String nombre;
    String descripcion;
    Float precio;
    int duracion;
    String tipoDuracion;
    int clase_id;
    int administrador_id;
    
    // Extras
    String nombreClase;
    
    public TipoMembresia() {}
    
    // Modificar 
    public TipoMembresia(int id, String nombre, String descripcion, Float precio, int duracion, String tipoDuracion,
			int clase_id) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.duracion = duracion;
		this.tipoDuracion = tipoDuracion;
		this.clase_id = clase_id;
	}
    
    // Instanciar clase
    public TipoMembresia(int id, String nombre, String descripcion, Float precio, int duracion, String tipoDuracion,
			int clase_id, int administrador_id) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.duracion = duracion;
		this.tipoDuracion = tipoDuracion;
		this.clase_id = clase_id;
		this.administrador_id = administrador_id;
	}
    
    // Guardar
    public TipoMembresia(String nombre, String descripcion, Float precio, int duracion, String tipoDuracion,
			int clase_id, int administrador_id) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.duracion = duracion;
		this.tipoDuracion = tipoDuracion;
		this.clase_id = clase_id;
		this.administrador_id = administrador_id;
	}
    
    // Consultar - Listar
    public TipoMembresia(int id, String nombre, String descripcion, Float precio, int duracion, String tipoDuracion,
			int clase_id, String nombreClase) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.duracion = duracion;
		this.tipoDuracion = tipoDuracion;
		this.clase_id = clase_id;
		this.nombreClase = nombreClase;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public String getTipoDuracion() {
		return tipoDuracion;
	}

	public void setTipoDuracion(String tipoDuracion) {
		this.tipoDuracion = tipoDuracion;
	}

	public int getClase_id() {
		return clase_id;
	}

	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}

	public int getAdministrador_id() {
		return administrador_id;
	}

	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}

	public String getNombreClase() {
		return nombreClase;
	}

	public void setNombreClase(String nombreClase) {
		this.nombreClase = nombreClase;
	}
    
}
