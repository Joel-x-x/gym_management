package com.gym.utilidades;

import java.util.List;

import com.gym.model.Administrador;
import com.gym.model.Clase;
import com.gym.model.Entrenador;
import com.gym.model.Fisico;
import com.gym.model.Membresia;
import com.gym.model.Plan;
import com.gym.model.Registro;
import com.gym.model.Reporte;
import com.gym.model.Usuario;

public class ArrayUtilidades {
	
	public Object[][] toMatrizAdministrador(List<Administrador> lista) {
		int contador = 0;
		Object[][] matrizAdministrador =  new Object[lista.size()][5];
    	
    	for(Administrador administrador : lista) {
    		matrizAdministrador[contador][0] = administrador.getAdministrador_id();
    		matrizAdministrador[contador][1] = administrador.getNombre();
    		matrizAdministrador[contador][2] = administrador.getApellido();
    		matrizAdministrador[contador][3] = administrador.getEmail();
    		matrizAdministrador[contador][4] = administrador.getCedula();
    				
    		contador++;
    	}
    	return matrizAdministrador;
	}

	public Object[][] toMatrizUsuario(List<Usuario> lista) {
		int contador = 0;
		Object[][] matrizUsuarios =  new Object[lista.size()][10];
    	
    	for(Usuario usuario : lista) {
    		matrizUsuarios[contador][0] = usuario.getId();
    		matrizUsuarios[contador][1] = usuario.getNombre();
    		matrizUsuarios[contador][2] = usuario.getApellido();
    		matrizUsuarios[contador][3] = usuario.getFecha_nacimiento();
    		matrizUsuarios[contador][4] = usuario.getSexo();
    		matrizUsuarios[contador][5] = usuario.getEmail();
    		matrizUsuarios[contador][6] = usuario.getCedula();
    		matrizUsuarios[contador][7] = usuario.getDireccion();
    		matrizUsuarios[contador][8] = usuario.getTelefono();
    		matrizUsuarios[contador][9] = usuario.getFecha_creacion();
    				
    		contador++;
    	}
    	return matrizUsuarios;
	}
	
	public Object[][] toMatrizFisico(List<Fisico> lista) {
		int contador = 0;
		Object[][] matrizFisico =  new Object[lista.size()][4];
    	
    	for(Fisico fisico : lista) {
    		matrizFisico[contador][0] = fisico.getId();
    		matrizFisico[contador][1] = fisico.getPeso();
    		matrizFisico[contador][2] = fisico.getAltura();
    		matrizFisico[contador][3] = fisico.getFecha();

    		contador++;
    	}
    	return matrizFisico;
	}
	
	public Object[][] toMatrizPlan(List<Plan> lista) {
		int contador = 0;
		Object[][] matrizPlan =  new Object[lista.size()][5];
    	
    	for(Plan plan : lista) {
    		matrizPlan[contador][0] = plan.getId();
    		matrizPlan[contador][1] = plan.getNombre();
    		matrizPlan[contador][2] = plan.getPrecio();
    		matrizPlan[contador][3] = plan.getDescripcion();
    		matrizPlan[contador][4] = plan.getDuracion();

    		contador++;
    	}
    	return matrizPlan;
	}
	
	public Object[][] toMatrizClase(List<Clase> lista) {
		int contador = 0;
		Object[][] matrizClase =  new Object[lista.size()][4];
    	
    	for(Clase clase : lista) {
    		matrizClase[contador][0] = clase.getId();
    		matrizClase[contador][1] = clase.getClase();
    		matrizClase[contador][2] = clase.getDescripcion();
    		matrizClase[contador][3] = clase.getNombreEntrenador();

    		contador++;
    	}
    	return matrizClase;
	}
	
	public Object[][] toMatrizEntrenador(List<Entrenador> lista) {
		int contador = 0;
		Object[][] matrizEntrenador =  new Object[lista.size()][5];
    	
    	for(Entrenador entrenador : lista) {
    		matrizEntrenador[contador][0] = entrenador.getId();
    		matrizEntrenador[contador][1] = entrenador.getNombre();
    		matrizEntrenador[contador][2] = entrenador.getCorreo();
    		matrizEntrenador[contador][3] = entrenador.getTelefono();
    		matrizEntrenador[contador][4] = entrenador.getCedula();

    		contador++;
    	}
    	return matrizEntrenador;
	}
	
	public Object[][] toMatrizMembresia(List<Membresia> lista) {
		int contador = 0;
		Object[][] matrizMembresia =  new Object[lista.size()][7];
    	
    	for(Membresia membresia : lista) {
    		
    		matrizMembresia[contador][0] = membresia.getId();
    		matrizMembresia[contador][1] = FechasUtilidades.cambiarFormatoFecha(membresia.getFecha_fin());
    		matrizMembresia[contador][2] = membresia.getPlan();
    		matrizMembresia[contador][3] = membresia.getClase();
    		matrizMembresia[contador][4] = membresia.mensajeMembresia();
    		matrizMembresia[contador][5] = membresia.getAnticipacion();
    		matrizMembresia[contador][6] = membresia.getValor_total();

    		contador++;
    	}
    	return matrizMembresia;
	}
	
	public Object[][] toMatrizMembresiaRegistro(List<Membresia> lista) {
		int contador = 0;
		Object[][] matrizMembresia =  new Object[lista.size()][7];
    	
    	for(Membresia membresia : lista) {
    			
    		matrizMembresia[contador][0] = membresia.getId();
    		matrizMembresia[contador][1] = membresia.getNombreUsuario();
    		matrizMembresia[contador][2] = FechasUtilidades.cambiarFormatoFecha(membresia.getFecha_entrada());
    		matrizMembresia[contador][3] = FechasUtilidades.cambiarFormatoFecha(membresia.getFecha_salida());
    		matrizMembresia[contador][4] = membresia.getPlan();
    		matrizMembresia[contador][5] = membresia.getClase();
    		matrizMembresia[contador][6] = membresia.mensajeMembresia();

    		contador++;
    	}
    	return matrizMembresia;
	}
	public Object[][] toMatrizMembresiaReporte(List<Reporte> lista_reporte) {
		int contador = 0;
		Object[][] matrizMembresia =  new Object[lista_reporte.size()][6];
    	
    	for(Reporte membresia : lista_reporte) {
    		matrizMembresia[contador][0] = membresia.getFecha_inicio();
    		matrizMembresia[contador][1] = membresia.getFecha_fin();
    		matrizMembresia[contador][2] = membresia.getPlan();
    		matrizMembresia[contador][3] = membresia.getClase();
    		matrizMembresia[contador][4] = membresia.mensajeMembresia();
    		matrizMembresia[contador][5] = membresia.getValor_total();

    		contador++;
    	}
    	return matrizMembresia;
	}
	

	public Object[][] toMatrizRegistro(List<Registro> lista) {
		int contador = 0;
		Object[][] matrizRegistro =  new Object[lista.size()][7];
    	
    	for(Registro registro : lista) {
    		
    		
    		matrizRegistro[contador][0] = registro.getId();
    		matrizRegistro[contador][1] = registro.getNombreUsuario();
    		matrizRegistro[contador][2] = registro.getFecha_entrada();
    		matrizRegistro[contador][3] = registro.getFecha_salida();
    		matrizRegistro[contador][4] = registro.getNombrePlan();
    		matrizRegistro[contador][5] = registro.getClase();
    		matrizRegistro[contador][6] = registro.mensajeMembresia();

    		contador++;
    	}
    	
    	return matrizRegistro;
	}
	
	
	
}
