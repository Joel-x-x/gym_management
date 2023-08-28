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

public class TipoMembresiasPanel extends JPanel {
	private int administrador_id;
	private int idSeleccionado;
	private DefaultTableModel modelo;
	private TipoMembresiaController tipoMembresiaController;
	private MembresiaController membresiaController;
	private JComboBox<String> comboBoxTipoDuracion = new JComboBox<>();
	private DefaultComboBoxModel<String> comboBoxModelTipoDuracion = new DefaultComboBoxModel<>();
	private JComboBox<Clase> comboBoxClase = new JComboBox<>();
	private DefaultComboBoxModel<Clase> comboBoxModelClase = new DefaultComboBoxModel<>();
	
	private static final long serialVersionUID = -6442770399461125032L;
	private JTextField textPrecio;
	private JTable tableMembresias;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JSpinner spinnerDuracion;
	private JButton btnLimpiar;
	private JTextField textBuscar;
	private JTextField textNombre;
	private JTextField textDescripcion;
	private JButton btnLimpiarTabla;

	private void listarTipoMembresias() {
		String[] cabeceras = {"Id","Nombre", "Descripcion", "Precio", "Duración", "Tipo Duración", "Clase"};
		
		modelo = new DefaultTableModel(tipoMembresiaController.consultar(textBuscar.getText(), administrador_id), cabeceras);
		tableMembresias.setModel(modelo);
	}
	
	private void listarClase() {
		comboBoxModelClase.addAll(membresiaController.listarClase(administrador_id));
		comboBoxClase.setModel(comboBoxModelClase);
		comboBoxClase.setSelectedIndex(0);
	}
	
	private void listarTipoDuracion() {
		// Elmentos tipo duración
		List<String> tipo = new ArrayList<>(Arrays.asList("Hora","Día","Mes","Año"));
		
		comboBoxModelTipoDuracion.addAll(tipo);
		comboBoxTipoDuracion.setModel(comboBoxModelTipoDuracion);
		comboBoxTipoDuracion.setSelectedIndex(0);
	}
	
	private void guardar() {
		
		validarCampoPrecio();
		
		TipoMembresia tipoMembresia = llenarTipoMembresia();
		
		if(tipoMembresiaController.guardar(tipoMembresia)) {
			JOptionPane.showMessageDialog(null, "Guardado con Exito!");
			listarTipoMembresias();
			limpiarFormulario();
		} else {
			JOptionPane.showMessageDialog(null, "No se puedo guardar");
		}
	}
	
	public void validarCampoPrecio() {
		if(textPrecio.getText().equals("")) {
			textPrecio.setText("0.0");
		}
	}
	
	private void modificar() {
		
		validarCampoPrecio();
		
		TipoMembresia membresia = llenarTipoMembresia();
		
		System.out.println(membresia.getTipoDuracion());
		
		if(tipoMembresiaController.modificar(membresia)) {
			JOptionPane.showMessageDialog(null, "Modificado con Exito!");
			listarTipoMembresias();
			limpiarFormulario();
			bloquearBotones();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo modificar");
		}
	}
 	
	private void eliminar() {
		JOptionPane.showMessageDialog(null, Utilidades.codigoToMensajeEliminar(tipoMembresiaController.eliminar(idSeleccionado),
				"No se pudo eliminar, parece que tienes membresias que dependen de este tipo de membresia, "
				+ "solo puedes modificar este tipo de membresia"));
		listarTipoMembresias();
		limpiarFormulario();
	}
	
	private void listarHistorial() {
		List<TipoMembresia> listaPrecios = tipoMembresiaController.listarPrecios(idSeleccionado);
		
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (TipoMembresia item : listaPrecios) {
            listModel.addElement(item.getPrecioHistorial() + "     " + item.getFecha());
        }
        
        new HistorialPreciosFrame(listModel);
	}
	
	private TipoMembresia llenarTipoMembresia() {
		
		String tipoDuracion = Utilidades.cambiarTipoDuracion((String) comboBoxTipoDuracion.getSelectedItem());
		
		int idClase = ((Clase) comboBoxClase.getSelectedItem()).getId();
		
		return new TipoMembresia(
				idSeleccionado,
				textNombre.getText(),
				textDescripcion.getText(),
				Float.parseFloat(textPrecio.getText()),
				(int) spinnerDuracion.getValue(),
				tipoDuracion,
				idClase,
				administrador_id);
	}
	
	private void llenarFormulario() {
		TipoMembresia membresia = tipoMembresiaController.consulta(idSeleccionado);
		
		textNombre.setText(membresia.getNombre());
		textDescripcion.setText(membresia.getDescripcion());
		textPrecio.setText(membresia.getPrecio() + "");
		spinnerDuracion.setValue(membresia.getDuracion());
		
		// Cambiar de ingles a español y seleccionarlo
		String tipo = Utilidades.cambiarTipoDuracionInverso(membresia.getTipoDuracion());
		System.out.println(membresia.getTipoDuracion());
		comboBoxTipoDuracion.setSelectedItem(tipo);

		for(int i = 0; i < comboBoxClase.getItemCount(); i++) {
			Clase clase = (Clase) comboBoxClase.getItemAt(i);
			
			if(membresia.getClase_id() == clase.getId()) {
				comboBoxClase.setSelectedItem(clase);
			}
		}
		
	}
	
	private void bloquearBotones() {
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
	
	private void limpiarFormulario() {
		textNombre.setText("");
		textDescripcion.setText("");
		textPrecio.setText("0.0");
		comboBoxClase.setSelectedIndex(0);
		comboBoxTipoDuracion.setSelectedIndex(0);
		spinnerDuracion.setValue(0);
	}
	
    public TipoMembresiasPanel(int panelAncho, int panelAlto) {
    	
    	setFocusTraversalKeysEnabled(false);
    	
    	setPreferredSize(new Dimension(1080, 800));

        setBackground(Color.WHITE);
        setLayout(null);
        
		administrador_id = new Administrador().getId();
		tipoMembresiaController = new TipoMembresiaController();
		membresiaController = new MembresiaController();
		
		idSeleccionado = 0;
		        
        comboBoxTipoDuracion.setBounds(279, 83, 140, 25);
        add(comboBoxTipoDuracion);
        
        comboBoxClase.setBounds(279, 134, 261, 25);
        add(comboBoxClase);
        
        JLabel lblNewLabel = new JLabel("TIPOS DE MEMBRESÍAS");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(30, 30, 261, 33);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Tipo de duración");
        lblNewLabel_1.setBounds(279, 68, 140, 14);
        add(lblNewLabel_1);
        
        textPrecio = new JTextField();
        textPrecio.setText("0.0");
        textPrecio.setBounds(30, 136, 225, 25);
        add(textPrecio);
        textPrecio.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Precio");
        lblNewLabel_2.setBounds(30, 121, 225, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Clase");
        lblNewLabel_3.setBounds(279, 119, 261, 14);
        add(lblNewLabel_3);
        
        btnAgregar = new JButton("Agregar");
        btnAgregar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guardar();
        	}
        });
        btnAgregar.setFocusPainted(false);
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregar.setBorder(null);
        btnAgregar.setBackground(new Color(46, 56, 64));
        btnAgregar.setBounds(30, 198, 100, 30);
        add(btnAgregar);
        
        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificar();
        	}
        });
        btnModificar.setFocusPainted(false);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificar.setBorder(null);
        btnModificar.setBackground(new Color(46, 56, 64));
        btnModificar.setBounds(140, 198, 100, 30);
        add(btnModificar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminar();
        	}
        });
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminar.setBorder(null);
        btnEliminar.setBackground(new Color(46, 56, 64));
        btnEliminar.setBounds(250, 198, 100, 30);
        add(btnEliminar);
        
        JScrollPane scrollPane_membresias_membresias = new JScrollPane();
        scrollPane_membresias_membresias.setBounds(30, 288, 998, 398);
        add(scrollPane_membresias_membresias);
        
        tableMembresias = new JTable();
        tableMembresias.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		idSeleccionado = (int) tableMembresias.getValueAt(tableMembresias.getSelectedRow(),0);
        		
        		btnModificar.setEnabled(true);
        		btnEliminar.setEnabled(true);
        		
        		llenarFormulario();
        	}
        });
        scrollPane_membresias_membresias.setViewportView(tableMembresias);
        
        JLabel lblNewLabel_2_1 = new JLabel("Duración");
        lblNewLabel_2_1.setBounds(440, 67, 100, 14);
        add(lblNewLabel_2_1);
        
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, 365, 1);
        
        spinnerDuracion = new JSpinner(spinnerModel);
        spinnerDuracion.setBounds(440, 83, 100, 25);
        add(spinnerDuracion);
        
        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormulario();
        	}
        });
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setBorder(null);
        btnLimpiar.setBackground(new Color(46, 56, 64));
        btnLimpiar.setBounds(361, 198, 100, 30);
        add(btnLimpiar);
        
        textBuscar = new JTextField();
        textBuscar.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		listarTipoMembresias();
        	}
        });
        textBuscar.setColumns(10);
        textBuscar.setBounds(139, 257, 137, 25);
        add(textBuscar);
        
        JLabel lblNewLabel_1_4_2 = new JLabel("Buscar por nombre:");
        lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_2.setBounds(30, 263, 99, 14);
        add(lblNewLabel_1_4_2);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(new Color(46, 56, 64));
        btnBuscar.setBounds(286, 257, 89, 25);
        add(btnBuscar);
        
        btnLimpiarTabla = new JButton("Limpiar");
        btnLimpiarTabla.setForeground(Color.WHITE);
        btnLimpiarTabla.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimpiarTabla.setFocusPainted(false);
        btnLimpiarTabla.setBorder(null);
        btnLimpiarTabla.setBackground(new Color(46, 56, 64));
        btnLimpiarTabla.setBounds(385, 257, 89, 25);
        add(btnLimpiarTabla);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(30, 68, 75, 14);
        add(lblNewLabel_1_1);
        
        textNombre = new JTextField();
        textNombre.setColumns(10);
        textNombre.setBounds(30, 84, 225, 25);
        add(textNombre);
        
        textDescripcion = new JTextField();
        textDescripcion.setColumns(10);
        textDescripcion.setBounds(557, 83, 471, 145);
        add(textDescripcion);
        
        JLabel lblNewLabel_2_2 = new JLabel("Descripción");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_2.setBounds(557, 68, 231, 14);
        add(lblNewLabel_2_2);
        
        JButton btnHistorialPrecios = new JButton("Historial de precios");
        btnHistorialPrecios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		listarHistorial();
        	}
        });
        btnHistorialPrecios.setForeground(Color.WHITE);
        btnHistorialPrecios.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnHistorialPrecios.setFocusPainted(false);
        btnHistorialPrecios.setBorder(null);
        btnHistorialPrecios.setBackground(new Color(46, 56, 64));
        btnHistorialPrecios.setBounds(557, 257, 150, 25);
        add(btnHistorialPrecios);
        
        listarTipoMembresias();
        listarTipoDuracion();
        listarClase();
        bloquearBotones();
    }
}
