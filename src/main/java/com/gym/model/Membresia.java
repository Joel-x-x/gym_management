package com.gym.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

import com.gym.controller.MembresiaController;
import com.gym.utilidades.FechasUtilidades;
import com.gym.utilidades.Utilidades;

public class Membresia { // Id, Membresia, Nombre, Cedula, Finalización, Clase, Entrenador, No Factura, Estado
    private int id;
    private String fecha_inicio;
    private String fecha_fin;
    private int activo;
    private int usuario_id;
    private int administrador_id;
    private int tipo_membresia_id;
    private int factura_id;
    private int caducada_notificar; // Membresia caducada notificar
    private int caducando_notificar; // Por caducar notificar
    
    // Usuario
    private String nombreUsuario;
    private String apellidoUsuario;
    private String cedula;
    private String email;
    private String fecha_entrada;
    private String fecha_salida;
    
    // Tipo Membresia
	private String nombreTipo;
	private double precio;
	private int clase_id;

	// Clase
    private String clase;
    private int entrenador_id;
    
    // Entrenador
    private String entrenador;
    
    // Factura
    private String numeroFactura;
    
    
    private MembresiaController membresiaController;
    
    public Membresia() {
    	
    }
    
    // Listar Membresías
	public Membresia(int id, String fecha_inicio, String fecha_fin, int activo, int usuario_id,
			int tipo_membresia_id, int factura_id, String nombreUsuario, String cedula,
			String nombreTipo, int clase_id, String clase, int entrenador_id, String entrenador,
			String numeroFactura, int caducada_notificar, int caducando_notificar, String email) {
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.activo = activo;
		this.usuario_id = usuario_id;
		this.tipo_membresia_id = tipo_membresia_id;
		this.factura_id = factura_id;
		this.nombreUsuario = nombreUsuario;
		this.cedula = cedula;
		this.nombreTipo = nombreTipo;
		this.clase_id = clase_id;
		this.clase = clase;
		this.entrenador_id = entrenador_id;
		this.entrenador = entrenador;
		this.numeroFactura = numeroFactura;
		this.caducada_notificar = caducada_notificar;
		this.caducando_notificar = caducando_notificar;
		this.email = email;
	}
	
	// Listar membresias en factura
	public Membresia(int id, String nombreTipo, String clase, String entrenador, double precio, int tipo_membresia_id) {
		this.id = id;
		this.nombreTipo = nombreTipo;
		this.clase = clase;
		this.entrenador = entrenador;
		this.precio = precio;
		this.tipo_membresia_id = tipo_membresia_id;
	}
    
	// Listar Registros Diarios
	public Membresia(int id, String nombreUsuario, String apellidoUsuario, String cedula, String fecha_entrada, String fecha_salida,
			String nombreTipo) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.cedula = cedula;
		this.fecha_entrada = fecha_entrada;
		this.fecha_salida = fecha_salida;
		this.nombreTipo = nombreTipo;
	}
	
	// Anterior borrar si no sirven
	
	// Consulta Membresia
	public Membresia(int id, String fecha_inicio, String fecha_fin, int usuario_id, int activo, int anticipacion) {
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.activo = activo;
	}
	
	// Llenar Clase Membresia para Modificar
	public Membresia(int id, String fecha_fin, int usuario_id, int plan_id, int clase_id,
			float valor_extra, float valor_total, int activo, int anticipacion, int administrador_id) {
		this.id = id;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.tipo_membresia_id = plan_id;
		this.clase_id = clase_id;
		this.administrador_id = administrador_id;
		this.activo = activo;
	}
	
	// Guardar
	public Membresia(String fecha_fin, int usuario_id, int plan_id, int clase_id, float valor_extra, float valor_total, int activo, int anticipacion, int administrador_id) {
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.tipo_membresia_id = plan_id;
		this.clase_id = clase_id;
		this.activo = activo;
		this.administrador_id = administrador_id;
	}

	public Membresia(String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id, float valor_extra, float valor_total) {
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.tipo_membresia_id = plan_id;
		this.clase_id = clase_id;
	}
	
	// Listar - Consultar
	public Membresia(int id, String fecha_inicio, String fecha_fin, int usuario_id, int plan_id, int clase_id,
			float valor_extra, float valor_total, int administrador_id, String plan, String clase, int activo, int anticipacion) {
		this.id = id;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.usuario_id = usuario_id;
		this.tipo_membresia_id = plan_id;
		this.clase_id = clase_id;
		this.administrador_id = administrador_id;
		this.clase = clase;
		this.activo = activo;
	}	
	
	public int getTipo_membresia_id() {
		return tipo_membresia_id;
	}

	public void setTipo_membresia_id(int tipo_membresia_id) {
		this.tipo_membresia_id = tipo_membresia_id;
	}

	public int getFactura_id() {
		return factura_id;
	}

	public void setFactura_id(int factura_id) {
		this.factura_id = factura_id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = nombreTipo;
	}

	public int getEntrenador_id() {
		return entrenador_id;
	}

	public void setEntrenador_id(int entrenador_id) {
		this.entrenador_id = entrenador_id;
	}

	public String getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
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

	public MembresiaController getMembresiaController() {
		return membresiaController;
	}

	public void setMembresiaController(MembresiaController membresiaController) {
		this.membresiaController = membresiaController;
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

	public int getClase_id() {
		return clase_id;
	}

	public void setClase_id(int clase_id) {
		this.clase_id = clase_id;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}
	
	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getAdministrador_id() {
		return administrador_id;
	}

	public void setAdministrador_id(int administrador_id) {
		this.administrador_id = administrador_id;
	}
	
	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public void setApellidoUsuario(String apellidoUsuario) {
		this.apellidoUsuario = apellidoUsuario;
	}
	
	public int getCaducada_notificar() {
		return caducada_notificar;
	}

	public void setCaducada_notificar(int caducada_notificar) {
		this.caducada_notificar = caducada_notificar;
	}

	public int getCaducando_notificar() {
		return caducando_notificar;
	}

	public void setCaducando_notificar(int caducando_notificar) {
		this.caducando_notificar = caducando_notificar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean validarMembresia() {
		Calendar calendar = FechasUtilidades.stringToCalendar(this.getFecha_fin());
		
		if (Calendar.getInstance().before(calendar)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean notificarMembresia() {
	    Calendar calendar = FechasUtilidades.stringToCalendar(this.getFecha_fin());
	    
	    // Restar días a la fecha fin membresía
	    calendar.add(Calendar.DAY_OF_MONTH, - 3);
	    
	    // Obtener la fecha actual
	    Calendar fechaActual = Calendar.getInstance();

	    // Comparar la fecha resultante con la fecha actual
	    if (calendar.before(fechaActual)) {

    		// Notificamos al usuario por correo una sola ves en vase a su atributo caducando notificar
    		if(new Utilidades().toBoolean(this.getCaducando_notificar())) {
    			Email email = new Email(this.getEmail(), "System Gym", "Hola " + this.getNombreUsuario() + ", tu membresía caducara pronto.");
    			if(!email.sendEmail()) {
    				System.out.println("Ocurrio un error");
    			} else {
    				// Una enviado el email cambiamos el valor a 0 para que no se envien mas notificaciones
    				membresiaController.modificarCaducando(this.getId(), 0);
    			}
    		}
    		
	        return true;
	    } else {
	        return false;
	    }
	}
	
	public void membresiaCaducada() {
		if(this.getActivo() == 1) {
			return;
		}
		
		// Notificamos al usuario por correo una sola ves en vase a su atributo caducado notificar
		if(new Utilidades().toBoolean(this.getCaducada_notificar())) {
			Email email = new Email(this.getEmail(), "System Gym", "Hola " + this.getNombreUsuario() + ", tu membresía ha caducado");
			if(!email.sendEmail()) {
				System.out.println("Ocurrio un error");
			} else {
				// Una enviado el email cambiamos el valor a 0 para que no se envien mas notificaciones
				membresiaController.modificarCaducada(this.getId(), 0);
			}
		}
	}
	
	public int caducaDias() {
	    LocalDateTime fechaActual = LocalDateTime.now();
	    LocalDateTime fechaFin = FechasUtilidades.stringToLocalDateTime(this.fecha_fin);

	    // Calcula la diferencia en días entre la fecha actual y la fecha de vencimiento
	    long diasRestantes = ChronoUnit.DAYS.between(fechaActual, fechaFin);

	    return Math.max(0, (int) diasRestantes);
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
		return this.nombreTipo;
	}
	
}
