package com.gym.model;

public class Membresia {
    private int id;
    private String fecha_inicio;
    private String fecha_fin;
    private int usuario_id;
    private int plan_id;
    private int clase_id;
    private float valor_extra;
    private float valor_total;
    private int administrador_id;

	public Membresia(int id, String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id,
			float valor_extra, float valor_total) {
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.plan_id = plan_id;
		this.clase_id = clase_id;
		this.valor_extra = valor_extra;
		this.valor_total = valor_total;
	}
	
	public Membresia(String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id,
			float valor_extra, float valor_total) {
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

	public float getValor_extra() {
		return valor_extra;
	}

	public void setValor_extra(float valor_extra) {
		this.valor_extra = valor_extra;
	}

	public float getValor_total() {
		return valor_total;
	}

	public void setValor_total(float valor_total) {
		this.valor_total = valor_total;
	}
	
	public int getAdministrador_id() {
		return administrador_id;
	}

	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	
}
