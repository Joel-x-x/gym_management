package com.gym.utilidades;

import java.util.List;

import com.gym.model.Usuario;

public class ArrayUtilidades {

	public Object[][] toMatrizUsuario(List<Usuario> lista) {
		int contador = 0;
		Object[][] matrizUsuarios =  new Object[lista.size()][6];
    	
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
	
}
