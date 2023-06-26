package com.gym.model;

public class Membresia {
    private int id;
    private String fecha_inicio;
    private String fecha_fin;
    private int usuario_id;
    private int plan_id;
    private int clase_id;
    private double valor_extra;
    private double valor_total;
    
	public Membresia(int id, String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id,
			double valor_extra, double valor_total) {
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.plan_id = plan_id;
		this.clase_id = clase_id;
		this.valor_extra = valor_extra;
		this.valor_total = valor_total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public String getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	public int getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(int usuario_id) {
		this.usuario_id = usuario_id;
	}

	public int getPlan_id() {
		return plan_id;
	}

	public void setPlan_id(int plan_id) {
		this.plan_id = plan_id;
	}

	public int getClase_id() {
		return clase_id;
	}

	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}

	public double getValor_extra() {
		return valor_extra;
	}

	public void setValor_extra(double valor_extra) {
		this.valor_extra = valor_extra;
	}

	public double getValor_total() {
		return valor_total;
	}

	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	
}
