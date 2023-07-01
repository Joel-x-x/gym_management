package com.gym.utilidades;

import java.util.List;

import com.gym.model.Fisico;
import com.gym.model.Membresia;
import com.gym.model.Registro;
import com.gym.model.Usuario;

public class ArrayUtilidades {

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
	
	public Object[][] toMatrizMembresia(List<Membresia> lista) {
		int contador = 0;
		Object[][] matrizMembresia =  new Object[lista.size()][7];
    	
    	for(Membresia membresia : lista) {
    		
    		matrizMembresia[contador][0] = membresia.getId();
    		matrizMembresia[contador][1] = membresia.getFecha_fin();
    		matrizMembresia[contador][2] = membresia.getPlan();
    		matrizMembresia[contador][3] = membresia.getClase();
    		matrizMembresia[contador][4] = membresia.mensajeMembresia();
    		matrizMembresia[contador][5] = membresia.getAnticipacion();
    		matrizMembresia[contador][6] = membresia.getValor_total();

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
