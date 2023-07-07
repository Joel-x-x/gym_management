package com.gym.model;

public class Reporte extends Membresia{
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
	    
		public Reporte(String fecha_inicio, String fecha_fin, float valor_total, String plan, String clase, int activo) {
			this.fecha_inicio = fecha_inicio;
			this.fecha_fin = fecha_fin;
			this.valor_total = valor_total;
			this.plan = plan;
			this.clase = clase;
			this.activo = activo;
		}
	    
		public Reporte(int id, String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id,
				float valor_extra, float valor_total, int administrador_id, String plan, String clase, int activo,
				int anticipacion, String nombreUsuario, String fecha_entrada, String fecha_salida, int membresia_id) {
			super();
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
			this.nombreUsuario = nombreUsuario;
			this.fecha_entrada = fecha_entrada;
			this.fecha_salida = fecha_salida;
			this.membresia_id = membresia_id;
		}
		public Reporte(int id, String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id, float valor_extra,
				float valor_total, int administrador_id, String plan, String clase, int activo, int anticipacion) {
			// TODO Auto-generated constructor stub
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
		
		public Reporte(int usuario_id) {
			this.usuario_id = usuario_id;
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
		public int getActivo() {
			return activo;
		}
		public void setActivo(int activo) {
			this.activo = activo;
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
	    
}
