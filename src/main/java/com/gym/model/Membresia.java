package com.gym.model;

import java.util.Calendar;

import com.gym.controller.MembresiaController;
import com.gym.utilidades.FechasUtilidades;

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

	private String plan;
    private String clase;
    private int activo;
    private int anticipacion;
    
    private String nombreUsuario;
    private String fecha_entrada;
    private String fecha_salida;
    private int membresia_id;
    
    
    private MembresiaController membresiaController;
    
    public Membresia() {
    	
    }
    
    public Membresia(int id, String clase) {
    	this.id = id;
    	this.clase = clase;
    }

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
	
	// Consulta Membresia
	public Membresia(int id, String fecha_inicio, String fecha_fin, int usuario_id, int activo, int anticipacion) {
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.activo = activo;
		this.anticipacion = anticipacion;
	}
	
	// Llenar Clase Membresia para Modificar
	public Membresia(int id, String fecha_fin, int usuario_id, int plan_id, int clase_id,
			float valor_extra, float valor_total, int activo, int anticipacion, int administrador_id) {
		this.id = id;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.plan_id = plan_id;
		this.clase_id = clase_id;
		this.valor_extra = valor_extra;
		this.valor_total = valor_total;
		this.administrador_id = administrador_id;
		this.activo = activo;
		this.anticipacion = anticipacion;
	}
	
	// Guardar
	public Membresia(String fecha_fin, int usuario_id, int plan_id, int clase_id, float valor_extra, float valor_total, int activo, int anticipacion, int administrador_id) {
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.plan_id = plan_id;
		this.clase_id = clase_id;
		this.valor_extra = valor_extra;
		this.valor_total = valor_total;
		this.activo = activo;
		this.anticipacion = anticipacion;
		this.administrador_id = administrador_id;
	}

	public Membresia(String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id, float valor_extra, float valor_total) {
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.plan_id = plan_id;
		this.clase_id = clase_id;
		this.valor_extra = valor_extra;
		this.valor_total = valor_total;
	}
	
	// Listar - Consultar
	public Membresia(int id, String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id,
			float valor_extra, float valor_total, int administrador_id, String plan, String clase, int activo, int anticipacion) {
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.plan_id = plan_id;
		this.clase_id = clase_id;
		this.valor_extra = valor_extra;
		this.valor_total = valor_total;
		this.administrador_id = administrador_id;
		this.plan = plan;
		this.clase = clase;
		this.activo = activo;
		this.anticipacion = anticipacion;
	}
	
	// Listar Registro
	public Membresia(int id, String nombreUsuario, String fecha_entrada, String fecha_salida, String plan, String clase, int membresia_id, String fecha_inicio, String fecha_fin, int activo, int anticipacion) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.plan = plan;
		this.clase = clase;
		this.membresia_id = membresia_id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.activo = activo;
		this.anticipacion = anticipacion;
	}
	
    public int getAnticipacion() {
		return anticipacion;
	}

	public void setAnticipacion(int anticipacion) {
		this.anticipacion = anticipacion;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
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

	public int getMembresia_id() {
		return membresia_id;
	}

	public void setMembresia_id(int membresia_id) {
		this.membresia_id = membresia_id;
	}

	public MembresiaController getMembresiaController() {
		return membresiaController;
	}

	public void setMembresiaController(MembresiaController membresiaController) {
		this.membresiaController = membresiaController;
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
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
	
	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public int getAdministrador_id() {
		return administrador_id;
	}

	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	
	public boolean validarMembresia() {
		Calendar calendar = new FechasUtilidades().stringToCalendar(this.getFecha_fin());
		
		if (Calendar.getInstance().before(calendar)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean notificarMembresia() {
	    Calendar calendar = new FechasUtilidades().stringToCalendar(this.getFecha_fin());
	    
	    // Restar días a la fecha fin membresía
	    calendar.add(Calendar.DAY_OF_MONTH, - this.getAnticipacion() - 1);
	    
	    // Obtener la fecha actual
	    Calendar fechaActual = Calendar.getInstance();

	    // Comparar la fecha resultante con la fecha actual
	    if (calendar.before(fechaActual)) {
	    	System.out.println("true");
	        return true;
	    } else {
	    	System.out.println("false");
	        return false;
	    }
	}
	
	// Valida si la membresia es vigente y actualiza el activo
	public void cambiarActivoMembresia() {
		membresiaController = new MembresiaController();
		if(this.validarMembresia()) {
			this.setActivo(1);
			membresiaController.modificarActivo(this.getId(), 1);
		} else {
			this.setActivo(0);
			membresiaController.modificarActivo(this.getId(), 0);
		}
	}
	
	// Valida membresia y devuelve un mensaje si activa o caducada
	public String mensajeMembresia() {
		
		if(this.validarMembresia()) {
			return "Activa";
		} else {
			return "Caducada";
		}
		
	}
	
    @Override
	public String toString() {
		return this.clase;
	}
	
}
