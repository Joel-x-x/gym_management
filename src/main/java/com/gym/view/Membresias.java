package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.MembresiaController;
import com.gym.controller.TipoMembresiaController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Clase;
import com.gym.model.TipoMembresia;
import com.gym.utilidades.FechasUtilidades;
import com.gym.utilidades.Utilidades;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class Membresias extends JPanel {
	private int administrador_id;
	private int idSeleccionado;
	private DefaultTableModel modelo;
	private TipoMembresiaController tipoMembresiaController;
	private MembresiaController membresiaController;
	private DefaultComboBoxModel<String> comboBoxModelTipoDuracion = new DefaultComboBoxModel<>();
	private DefaultComboBoxModel<Clase> comboBoxModelClase = new DefaultComboBoxModel<>();
	
	private static final long serialVersionUID = -6442770399461125032L;
	private JTable tableMembresias;
	private JTextField textBuscar;

	private void listarTipoMembresias() {
		String[] cabeceras = {"Id","Nombre", "Descripcion", "Precio", "Duración", "Tipo Duración", "Clase"};
		
		modelo = new DefaultTableModel(tipoMembresiaController.consultar(textBuscar.getText(), administrador_id), cabeceras);
		tableMembresias.setModel(modelo);
	}



	
	

	
	

	
   
    }

