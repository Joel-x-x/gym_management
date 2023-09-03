package com.gym.model;

import java.sql.Date;

public class Factura {
	private int id;
	private String numero_factura;
	private double descuento_porcentaje;
	private double descuento;
	private double subtotal;
	private double iva;
	private double total;
	private String forma_pago;
	private Date fecha;
	private String establecimiento;
	private String punto_emision;
	private int usuario_id;
	private int administrador_id;
	
	// Extras
	private String nombreUsuario;
	private String apellidoUsuario;
	private String cedulaUsuario;
	
	// Iva
	private double ivaPorcentaje;
	private String fechaIva;
	
	// Listar y Consultar Facturas
	public Factura(int id, String numero_factura, double descuento_porcentaje, double descuento, double subtotal,
			double iva, double total, String forma_pago, Date fecha, String establecimiento, String punto_emision,
			int usuario_id, int administrador_id, String nombreUsuario, String cedulaUsuario) {
		this.id = id;
		this.numero_factura = numero_factura;
		this.descuento_porcentaje = descuento_porcentaje;
		this.descuento = descuento;
		this.subtotal = subtotal;
		this.iva = iva;
		this.total = total;
		this.forma_pago = forma_pago;
		this.fecha = fecha;
		this.establecimiento = establecimiento;
		this.punto_emision = punto_emision;
		this.usuario_id = usuario_id;
		this.administrador_id = administrador_id;
		this.nombreUsuario = nombreUsuario;
		this.cedulaUsuario = cedulaUsuario;
	}
	
	// Consultar factura clase
	public Factura(int id, String numero_factura, double descuento_porcentaje, double descuento, double subtotal,
			double iva, double total, String forma_pago, Date fecha, String establecimiento, String punto_emision,
			int usuario_id, int administrador_id, String nombreUsuario, String apellidoUsuario, String cedulaUsuario) {
		this.id = id;
		this.numero_factura = numero_factura;
		this.descuento_porcentaje = descuento_porcentaje;
		this.descuento = descuento;
		this.subtotal = subtotal;
		this.iva = iva;
		this.total = total;
		this.forma_pago = forma_pago;
		this.fecha = fecha;
		this.establecimiento = establecimiento;
		this.punto_emision = punto_emision;
		this.usuario_id = usuario_id;
		this.administrador_id = administrador_id;
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.cedulaUsuario = cedulaUsuario;
	}
	
	// Consultar ultima factura insertada
	public Factura(int id, String numero_factura, String forma_pago, Date fecha, String establecimiento, String punto_emision) {
		this.id = id;
		this.numero_factura = numero_factura;
		this.forma_pago = forma_pago;
		this.fecha = fecha;
		this.establecimiento = establecimiento;
		this.punto_emision = punto_emision;
	}
	
	public Factura(int id, double descuento_porcentaje, double descuento, double subtotal, double iva,
			double total, String forma_pago, Date fecha, int usuario_id) {
		this.id = id;
		this.descuento_porcentaje = descuento_porcentaje;
		this.descuento = descuento;
		this.subtotal = subtotal;
		this.iva = iva;
		this.total = total;
		this.forma_pago = forma_pago;
		this.fecha = fecha;
		this.usuario_id = usuario_id;
	}
	
	//  IVA
	public Factura(double ivaPorcentaje, int administrador_id, String fechaIva) {
		this.ivaPorcentaje = ivaPorcentaje;
		this.fechaIva = fechaIva;
	}
	
	// Forma de pago
	public Factura(int id, String forma_pago, double total, Date fecha) {
		this.id = id;
		this.forma_pago = forma_pago;
		this.fecha = fecha;
		this.total = total;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero_factura() {
		return numero_factura;
	}
	public void setNumero_factura(String numero_factura) {
		this.numero_factura = numero_factura;
	}
	public double getDescuento_porcentaje() {
		return descuento_porcentaje;
	}
	public void setDescuento_porcentaje(double descuento_porcentaje) {
		this.descuento_porcentaje = descuento_porcentaje;
	}
	public double getDescuento() {
		return descuento;
	}
	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getForma_pago() {
		return forma_pago;
	}
	public void setForma_pago(String forma_pago) {
		this.forma_pago = forma_pago;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	public String getPunto_emision() {
		return punto_emision;
	}
	public void setPunto_emision(String punto_emision) {
		this.punto_emision = punto_emision;
	}
	public int getUsuario_id() {
		return usuario_id;
	}
	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}
	public int getAdministrador_id() {
		return administrador_id;
	}
	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}

	public String getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public double getIvaPorcentaje() {
		return ivaPorcentaje;
	}

	public void setIvaPorcentaje(double ivaPorcentaje) {
		this.ivaPorcentaje = ivaPorcentaje;
	}

	public String getFechaIva() {
		return fechaIva;
	}

	public void setFechaIva(String fechaIva) {
		this.fechaIva = fechaIva;
	}
	
}
