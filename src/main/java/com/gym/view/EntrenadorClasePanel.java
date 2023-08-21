package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.ClaseController;
import com.gym.controller.EntrenadorController;
import com.gym.controller.TipoMembresiaController;
import com.gym.model.Administrador;
import com.gym.model.Clase;
import com.gym.model.Entrenador;
import com.gym.model.TipoMembresia;
import com.gym.utilidades.Utilidades;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class EntrenadorClasePanel extends JPanel {
	private int administrador_id;
	private int idSeleccionadoEntrenador;
	private int idSeleccionadoClase;
	
	private EntrenadorController entrenadorController;
	private ClaseController claseController;
	
	private static final long serialVersionUID = -7779240291090260615L;
    private JTextField textBuscarEntrenador;
    private JTextField textNombreEntrenador;
    private JTextField textTelefonoEntrenador;
    private JTextField textCorreoEntrenador;
    private JTextField textApellidoEntrenador;
    private JTextField textClase;
    private JTextField textDescripcionClase;
    private JTextField textBuscarClase;
    private JButton btnAgregarClase;
    private JButton btnModificarClase;
    private JButton btnEliminarClase;
    private DefaultTableModel modelo;
    private JScrollPane scrollPane_entrenador;
    private JScrollPane scrollPane_clases;
    private JTable tableEntrenador;
    private JTable tableClase;
    
    private JRadioButton radioMasculino;
    private JRadioButton radioFemenino;
    private JButton btnBuscarClase;
    
    private JTextField textCedulaEntrenador;
    private ButtonGroup buttonGroupEntrenador;
    ButtonGroup buttonGroupPlan;
    private JButton btnLimpiarEntrenador;
    private JButton btnLimpiarClase;
    private JButton btnBuscarEntrenador;
    private JButton btnAgregarEntrenador;
    private JButton btnModificarEntrenador;
    private JButton btnEliminarEntrenador;
    
    private JComboBox<Entrenador> comboBoxEntrenador = new JComboBox<>();
	private DefaultComboBoxModel<Entrenador> comboBoxModelEntrenador = new DefaultComboBoxModel<>();
    
	public void  bloquearBotonesPlanes(){
	}
    
	// Entrenador
	
	public void listarEntrenador() {
		String[] cabeceras = {"Id", "Nombre", "Correo", "Telefono", "Cédula"};
		
		modelo = new DefaultTableModel(entrenadorController.consultar("", administrador_id),cabeceras);
		tableEntrenador.setModel(modelo);
	}
	
	public void consultarEntrenador() {
		String[] cabeceras = {"Id", "Nombre", "Correo", "Telefono", "Cédula"};
		
		modelo = new DefaultTableModel(entrenadorController.consultar(textBuscarEntrenador.getText(), administrador_id),cabeceras);
		tableEntrenador.setModel(modelo);
	}
	
	public void guardarEntrenador() {
		Entrenador entrenador = llenarEntrenador();
		
		if(entrenadorController.guardar(entrenador)) {
			JOptionPane.showMessageDialog(null, "Guardado con exito!");
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo guardar");
		}
		
		listarEntrenador();
		limpiarFormularioEntrenador();
		listarComboEntrenador();
	}
	
	public void modificarEntrenador() {
		Entrenador entrenador = llenarEntrenador();
		
		if(entrenadorController.modificar(entrenador)) {
			JOptionPane.showMessageDialog(null, "Modificado con exito!");
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo modificar");
		}
		
		listarEntrenador();
		limpiarFormularioEntrenador();
		listarComboEntrenador();
		bloquearBotonesEntrenador();
	}
	
	public void eliminarEntrenador() {
		
		JOptionPane.showMessageDialog(null, Utilidades.codigoToMensajeEliminar(entrenadorController.eliminar(idSeleccionadoEntrenador),
				"No se pudo eliminar, parece que tienes clases que dependen de este entrenador, "
				+ "eliminas sus clases primero o tambien puedes modificar este entrenador"));
		
		listarEntrenador();
		limpiarFormularioEntrenador();
		listarComboEntrenador();
		bloquearBotonesEntrenador();
	}
	
    public Entrenador llenarEntrenador() {
		return new Entrenador(
				idSeleccionadoEntrenador,
				textNombreEntrenador.getText(),
				textApellidoEntrenador.getText(),
				this.getRadioButtonEntrenador(),
				textCorreoEntrenador.getText(),
				textTelefonoEntrenador.getText(),
				textCedulaEntrenador.getText(),
				administrador_id
				);
    }
    
	// Obtener el botón seleccionado
	private String getRadioButtonEntrenador() {
		if(radioMasculino.isSelected()) {
			return radioMasculino.getText();
		}else {
			return radioFemenino.getText();
		}
	}
	
	public void llenarFormularioEntrenador() {
		Entrenador entrenador = entrenadorController.consulta(idSeleccionadoEntrenador);
		
		textNombreEntrenador.setText(entrenador.getNombre());
		textApellidoEntrenador.setText(entrenador.getApellido());
		textTelefonoEntrenador.setText(entrenador.getTelefono());
		textCedulaEntrenador.setText(entrenador.getCedula());
		textCorreoEntrenador.setText(entrenador.getCorreo());
		
		if(entrenador.getSexo().equals("masculino")) {
			radioMasculino.setSelected(true);
		} else {
			radioFemenino.setSelected(true);
		}
	}
	
	public void bloquearBotonesEntrenador() {
		btnModificarEntrenador.setEnabled(false);
		btnEliminarEntrenador.setEnabled(false);
	}
	
	public void activarBotonesEntrenador() {
		btnModificarEntrenador.setEnabled(true);
		btnEliminarEntrenador.setEnabled(true);
	}
	
	public void  limpiarFormularioEntrenador() {
		textCedulaEntrenador.setText("");
		textNombreEntrenador.setText("");
		textTelefonoEntrenador.setText("");
		textCorreoEntrenador.setText("");
		textApellidoEntrenador.setText("");
		buttonGroupEntrenador.clearSelection();
	}
	
	// Clase
	
	public void consultarClase() {
		String[] cabeceras = {"Id","Clase","Descripcion","Entrenador"};
		
		modelo = new DefaultTableModel(claseController.consultar(textBuscarClase.getText(), administrador_id),cabeceras);
		tableClase.setModel(modelo);
	}
	
	public void listarClase() {
		String[] cabeceras = {"Id","Clase","Descripcion","Entrenador"};
		
		modelo = new DefaultTableModel(claseController.consultar("", administrador_id),cabeceras);
		tableClase.setModel(modelo);
	}
	
	public void guardarClase() {
		Clase clase = llenarClase();
		if(claseController.guardar(clase)) {
			JOptionPane.showMessageDialog(null, "Guardado con Exito!");
		}else {
			JOptionPane.showMessageDialog(null, "No se ha podido Guardar");
		}
		
		limpiarFormularioClase();
		listarClase();
	}
	
	public void modificarClase() {
		Clase clase = llenarClase();
		
		if(claseController.modificar(clase)) {
			JOptionPane.showMessageDialog(null, "Modificado con Exito!");
		}else {
			JOptionPane.showMessageDialog(null, "No se pudo modificar");
		}
		
		limpiarFormularioClase();
		listarClase();
	}
	
	public void eliminarClase() {
		JOptionPane.showMessageDialog(null, Utilidades.codigoToMensajeEliminar(claseController.eliminar(idSeleccionadoClase),
				"No se pudo eliminar, parece que tienes membresías que dependen de esta clase, "
				+ "eliminas estas membresías primero o tambien modificar la clase"));
		
		limpiarFormularioClase();
		listarClase();
	}
	
	private void listarComboEntrenador() {
		comboBoxModelEntrenador.removeAllElements();
		
		comboBoxModelEntrenador.addAll(entrenadorController.consultarLista("", administrador_id));
		comboBoxEntrenador.setModel(comboBoxModelEntrenador);
		comboBoxEntrenador.setSelectedIndex(0);
	}
	
	public void llenarFormularioClase() {
		Clase clase = claseController.consulta(idSeleccionadoClase);
		
		textClase.setText(clase.getClase());
		textDescripcionClase.setText(clase.getDescripcion());
		
		for(int i = 0; i < comboBoxEntrenador.getItemCount(); i++) {
			Entrenador entrenador = (Entrenador) comboBoxEntrenador.getItemAt(i);
			
			if(clase.getEntrenador_id() == entrenador.getId()) {
				comboBoxEntrenador.setSelectedItem(entrenador);
			}
		}
		
		comboBoxEntrenador.setSelectedItem(entrenadorController.consulta(clase.getEntrenador_id()));
	}
	
    public Clase llenarClase() {
    	
    	int idEntrenador = ((Entrenador) comboBoxEntrenador.getSelectedItem()).getId();
    	
		return new Clase(
				idSeleccionadoClase,
				textClase.getText(),
				textDescripcionClase.getText(),
				idEntrenador,
				administrador_id);
    }
	
	public void limpiarFormularioClase() {
		textClase.setText("");
		textDescripcionClase.setText("");
		comboBoxEntrenador.setSelectedIndex(0);
	}
	
	public void bloquearBotonesClase() {
		btnModificarClase.setEnabled(false);
		btnEliminarClase.setEnabled(false);
	}
	
	public void activarBotonesClase() {
		btnModificarClase.setEnabled(true);
		btnEliminarClase.setEnabled(true);
	}
    
		
	public EntrenadorClasePanel(int panelAncho, int panelAlto) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

			}
		});
		 buttonGroupEntrenador  = new  ButtonGroup();
		
		administrador_id = new Administrador().getId();
		
		entrenadorController = new EntrenadorController();
		claseController = new ClaseController();
		
		comboBoxEntrenador = new JComboBox<>();
		comboBoxModelEntrenador = new DefaultComboBoxModel<>();
		
    	setPreferredSize(new Dimension(1280, 800));
        setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblEntrenador = new JLabel("ENTRENADOR");
        lblEntrenador.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblEntrenador.setBounds(40, 40, 160, 30);
        add(lblEntrenador);
        
        JLabel lblNewLabel_3_1 = new JLabel("Buscar por cédula");
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_3_1.setBounds(445, 168, 109, 14);
        add(lblNewLabel_3_1);
        
        textBuscarEntrenador = new JTextField();
        textBuscarEntrenador.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		consultarEntrenador();
        	}
        });
        textBuscarEntrenador.setBounds(445, 182, 137, 25);
        add(textBuscarEntrenador);
        textBuscarEntrenador.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(40, 71, 75, 14);
        add(lblNewLabel_1_1);
        
        textNombreEntrenador = new JTextField();
        textNombreEntrenador.setBounds(40, 86, 225, 25);
        add(textNombreEntrenador);
        textNombreEntrenador.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Teléfono");
        lblNewLabel_6.setBounds(40, 122, 75, 14);
        add(lblNewLabel_6);
        
        textTelefonoEntrenador = new JTextField();
        textTelefonoEntrenador.setBounds(40, 137, 225, 25);
        add(textTelefonoEntrenador);
        textTelefonoEntrenador.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Correo Electrónico");
        lblNewLabel_7.setBounds(521, 70, 160, 14);
        add(lblNewLabel_7);
        
        textCorreoEntrenador = new JTextField();
        textCorreoEntrenador.setBounds(521, 86, 225, 25);
        add(textCorreoEntrenador);
        textCorreoEntrenador.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Apellido");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_8.setBounds(286, 71, 69, 14);
        add(lblNewLabel_8);
        
        textApellidoEntrenador = new JTextField();
        textApellidoEntrenador.setBounds(286, 86, 214, 25);
        add(textApellidoEntrenador);
        textApellidoEntrenador.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Sexo");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_9.setBounds(521, 122, 46, 14);
        add(lblNewLabel_9);
        
        radioMasculino = new JRadioButton("Masculino");
        radioMasculino.setBackground(new Color(255, 255, 255));
        radioMasculino.setBounds(521, 138, 93, 23);
        add(radioMasculino);
        
        radioFemenino = new JRadioButton("Femenino");
        radioFemenino.setBackground(new Color(255, 255, 255));
        radioFemenino.setBounds(616, 139, 99, 23);
        add(radioFemenino);
        
        
        buttonGroupEntrenador.add(radioMasculino);
        buttonGroupEntrenador.add(radioFemenino);
        
        JLabel lblClase = new JLabel("CLASE");
        lblClase.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblClase.setBounds(40, 427, 160, 20);
        add(lblClase);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1.setBounds(40, 482, 75, 14);
        add(lblNewLabel_1_1_1);
        
        textClase = new JTextField();
        textClase.setBounds(40, 496, 225, 25);
        add(textClase);
        textClase.setColumns(10);
        
        JLabel lblNewLabel_2_1 = new JLabel("Descripción");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1.setBounds(40, 527, 93, 14);
        add(lblNewLabel_2_1);
        
        textDescripcionClase = new JTextField();
        textDescripcionClase.setBounds(40, 541, 460, 83);
        add(textDescripcionClase);
        textDescripcionClase.setColumns(10);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Entrenador");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_2.setBounds(288, 482, 211, 14);
        add(lblNewLabel_1_1_2);
        
        JLabel labelbuscarent = new JLabel("Buscar por clase");
        labelbuscarent.setFont(new Font("Tahoma", Font.PLAIN, 11));
        labelbuscarent.setBounds(264, 427, 99, 14);
        add(labelbuscarent);
        
        textBuscarClase = new JTextField();
        textBuscarClase.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		consultarClase();
        	}
        });
        textBuscarClase.setBounds(264, 441, 137, 25);
        add(textBuscarClase);
        textBuscarClase.setColumns(10);
        
        btnAgregarClase = new JButton("Agregar");
        btnAgregarClase.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregarClase.setFocusPainted(false);
        btnAgregarClase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {        		
        		guardarClase();
        	}
        });
        btnAgregarClase.setBounds(40, 635, 89, 30);
        btnAgregarClase.setBackground(new Color(46, 56, 64));
        btnAgregarClase.setForeground(new Color(255, 255, 255));
        btnAgregarClase.setBorder(null);
        add(btnAgregarClase);
        
        btnModificarClase = new JButton("Modificar");
        btnModificarClase.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificarClase.setFocusPainted(false);
        btnModificarClase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificarClase();
        		
        		listarClase();
        		limpiarFormularioClase();
        	}
        });
        btnModificarClase.setBounds(139, 635, 89, 30);
        btnModificarClase.setBackground(new Color(46, 56, 64));
        btnModificarClase.setForeground(new Color(255, 255, 255));
        btnModificarClase.setBorder(null);
        add(btnModificarClase);
        
        btnEliminarClase = new JButton("Eliminar");
        btnEliminarClase.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminarClase.setFocusPainted(false);
        btnEliminarClase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminarClase();
        	}
        });
        btnEliminarClase.setBounds(238, 635, 89, 30);
        btnEliminarClase.setBackground(new Color(46, 56, 64));
        btnEliminarClase.setForeground(new Color(255, 255, 255));
        btnEliminarClase.setBorder(null);
        add(btnEliminarClase);
        
        scrollPane_entrenador = new JScrollPane();
        scrollPane_entrenador.setBounds(39, 220, 958, 186);
        add(scrollPane_entrenador);
        
        tableEntrenador = new JTable();
        tableEntrenador.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		idSeleccionadoEntrenador =(int )tableEntrenador.getValueAt(tableEntrenador.getSelectedRow(), 0);
        		llenarFormularioEntrenador();
        		activarBotonesEntrenador();
        	}
        });
        scrollPane_entrenador.setViewportView(tableEntrenador);
        
        scrollPane_clases = new JScrollPane();
        scrollPane_clases.setBounds(509, 432, 488, 259);
        add(scrollPane_clases);
        
        tableClase = new JTable();
        tableClase.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		idSeleccionadoClase =(int )tableClase.getValueAt(tableClase.getSelectedRow(), 0);
        		llenarFormularioClase();
        		activarBotonesClase();
        	}
        });
        scrollPane_clases.setViewportView(tableClase);

        
        btnBuscarEntrenador = new JButton("Buscar");
        btnBuscarEntrenador.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscarEntrenador.setFocusPainted(false);
        btnBuscarEntrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		consultarEntrenador();
        	}
        });
        btnBuscarEntrenador.setForeground(new Color(255, 255, 255));
        btnBuscarEntrenador.setBorder(null);
        btnBuscarEntrenador.setBackground(new Color(46, 56, 64));
        btnBuscarEntrenador.setBounds(592, 179, 89, 30);
        add(btnBuscarEntrenador);
        
        btnBuscarClase = new JButton("Buscar");
        btnBuscarClase.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscarClase.setFocusPainted(false);
        btnBuscarClase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		consultarClase();
        	}
        });
        
        btnBuscarClase.setForeground(new Color(255, 255, 255));
        btnBuscarClase.setBorder(null);
        btnBuscarClase.setBackground(new Color(46, 56, 64));
        btnBuscarClase.setBounds(411, 438, 89, 30);
        add(btnBuscarClase);
        
        comboBoxEntrenador.setBounds(286, 496, 213, 25);
        add(comboBoxEntrenador);
        
        JLabel lblNewLabel_8_1 = new JLabel("Cédula");
        lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_8_1.setBounds(286, 122, 69, 14);
        add(lblNewLabel_8_1);
        
        textCedulaEntrenador = new JTextField();
        textCedulaEntrenador.setColumns(10);
        textCedulaEntrenador.setBounds(286, 137, 214, 25);
        add(textCedulaEntrenador);
        
        btnAgregarEntrenador = new JButton("Agregar");
        btnAgregarEntrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guardarEntrenador();
        	}
        });
        btnAgregarEntrenador.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregarEntrenador.setForeground(new Color(255, 255, 255));
        btnAgregarEntrenador.setFocusPainted(false);
        btnAgregarEntrenador.setBorder(null);
        btnAgregarEntrenador.setBackground(new Color(46, 56, 64));
        btnAgregarEntrenador.setBounds(40, 173, 89, 30);
        add(btnAgregarEntrenador);
        
        btnModificarEntrenador = new JButton("Modificar");
        btnModificarEntrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificarEntrenador();
        	}
        });
        btnModificarEntrenador.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificarEntrenador.setForeground(new Color(255, 255, 255));
        btnModificarEntrenador.setFocusPainted(false);
        btnModificarEntrenador.setBorder(null);
        btnModificarEntrenador.setBackground(new Color(46, 56, 64));
        btnModificarEntrenador.setBounds(139, 173, 89, 30);
        add(btnModificarEntrenador);
        
        btnEliminarEntrenador = new JButton("Eliminar");
        btnEliminarEntrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminarEntrenador();
        	}
        });
        btnEliminarEntrenador.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminarEntrenador.setForeground(new Color(255, 255, 255));
        btnEliminarEntrenador.setFocusPainted(false);
        btnEliminarEntrenador.setBorder(null);
        btnEliminarEntrenador.setBackground(new Color(46, 56, 64));
        btnEliminarEntrenador.setBounds(238, 173, 89, 30);
        add(btnEliminarEntrenador);
        
        btnLimpiarEntrenador = new JButton("Limpiar");
        btnLimpiarEntrenador.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimpiarEntrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormularioEntrenador();
        	}
        });
        
        btnLimpiarEntrenador.setForeground(new Color(255, 255, 255));
        btnLimpiarEntrenador.setFocusPainted(false);
        btnLimpiarEntrenador.setBorder(null);
        btnLimpiarEntrenador.setBackground(new Color(46, 56, 64));
        btnLimpiarEntrenador.setBounds(337, 173, 89, 30);
        add(btnLimpiarEntrenador);
        
        btnLimpiarClase = new JButton("Limpiar");
        btnLimpiarClase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormularioClase();
        		bloquearBotonesClase();
        	}
        });
        btnLimpiarClase.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimpiarClase.setForeground(new Color(255, 255, 255));
        btnLimpiarClase.setFocusPainted(false);
        btnLimpiarClase.setBorder(null);
        btnLimpiarClase.setBackground(new Color(46, 56, 64));
        btnLimpiarClase.setBounds(337, 635, 89, 30);
        add(btnLimpiarClase);
        
        listarEntrenador();
        listarClase();
        
        listarComboEntrenador();
        
        bloquearBotonesPlanes();
        bloquearBotonesEntrenador();
        bloquearBotonesClase();
    }
}
