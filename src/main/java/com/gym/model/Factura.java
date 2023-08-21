package com.gym.model;

import java.util.Date;

public class Factura {
	private int id;
	private String numero_factura;
	private double descuento_porcentaje;
	private double descuento;
	private double subtotal;
	private double iva;
	private double total;
	private String forma_pago;
	private String fecha;
	private String establecimiento;
	private String punto_emision;
	private int usuario_id;
	private int administrador_id;
	
	// Extras
	private String nombreUsuario;

	// 
	public Factura(int id, String numero_factura, double descuento_porcentaje, double descuento, double subtotal,
			double iva, double total, String forma_pago, String fecha, String establecimiento, String punto_emision,
			int usuario_id, int administrador_id, String nombreUsuario) {
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
	}
	
	// Consultar ultima factura insertada
	public Factura(int id, String numero_factura, String forma_pago, String fecha, String establecimiento, String punto_emision) {
		this.id = id;
		this.numero_factura = numero_factura;
		this.forma_pago = forma_pago;
		this.fecha = fecha;
		this.establecimiento = establecimiento;
		this.punto_emision = punto_emision;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
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
	
}
