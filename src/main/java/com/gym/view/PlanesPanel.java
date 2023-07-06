package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.ClaseController;
import com.gym.controller.EntrenadorController;
import com.gym.controller.PlanController;
import com.gym.model.Administrador;
import com.gym.model.Clase;
import com.gym.model.Entrenador;
import com.gym.model.Plan;
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

public class PlanesPanel extends JPanel {
	private int administrador_id;
	private int idSeleccionadoPlan;
	private int idSeleccionadoEntrenador;
	private int idSeleccionadoClase;
	
	private PlanController planController;
	private EntrenadorController entrenadorController;
	private ClaseController claseController;
	
	private static final long serialVersionUID = -7779240291090260615L;
    private JTextField textNombrePlan;
    private JTextField textDescripcionPlan;
    private JTextField textBuscarPlan;
    private JTextField textPrecioPlan;
    private JTextField textBuscarEntrenador;
    private JTextField textNombreEntrenador;
    private JTextField textTelefonoEntrenador;
    private JTextField textCorreoEntrenador;
    private JTextField textApellidoEntrenador;
    private JTextField textClase;
    private JTextField textDescripcionClase;
    private JTextField textBuscarClase;
    private JRadioButton radioAnual;
    private JRadioButton radioMensual;
    private JRadioButton radioDiario;
    
    private JButton btnAgregarPlan;
    private JButton btnModificarPlan;
    private JButton btn_eliminar_planes;
    private JButton btn_eliminar_planes_3;

    private JTable tabla_planes;
    private JButton btnAgregarClase;
    private JButton btnModificarClase;
    private JButton btnEliminarClase;
    private DefaultTableModel modelo;
    private JScrollPane scrollPane_planes;
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
    
    // Plan 
    
	public void listarPlan() {
		String[] cabeceras = {"Id","Nombre","Precio","Descripcion","Duracion"};
		
		modelo = new DefaultTableModel(planController.consultar("", administrador_id),cabeceras);
		tabla_planes.setModel(modelo);
	}
	
	public void consultarPlan() {
		String[] cabeceras = {"Id","Nombre","Precio","Descripcion","Duracion"};
		
		modelo = new DefaultTableModel(planController.consultar(textBuscarPlan.getText(), administrador_id),cabeceras);
		tabla_planes.setModel(modelo);
	}
    
	public void guadarPlan() {
		Plan plan = llenarPlan();
		
		if(planController.guardar(plan)) {
			JOptionPane.showMessageDialog(null, "Se ha registrado el plan");
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
		}
		limpiarFormularioPlanes();
		consultarPlan();
	}
	
	public void modificarPlan() {
		
		Plan plan = llenarPlan();
		
		plan.setId(idSeleccionadoPlan);
		
		if(planController.modificar(plan)) {
			JOptionPane.showMessageDialog(null, "Modificacion exitosa");
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
		}
		
		limpiarFormularioPlanes();
		consultarPlan();

	}
	
	public void eliminarPlan() {
		if(planController.eliminar(idSeleccionadoPlan)) {
			JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
		}
		
		
		limpiarFormularioPlanes();
		consultarPlan();
	}
	
    public Plan llenarPlan() {

		Float precio_plan = Float.parseFloat(textPrecioPlan.getText());
		
		return new Plan(
						textNombrePlan.getText(),
						precio_plan,
						textDescripcionPlan.getText(),
						this.getRadioButtonPlan().toLowerCase(),
						administrador_id);
	}
    
    public void llenarFormularioPlan() {
    	Plan plan = planController.consulta(idSeleccionadoPlan);
    	
    	textNombrePlan.setText(plan.getNombre());
    	textPrecioPlan.setText(plan.getPrecio() + "");
    	textDescripcionPlan.setText(plan.getDescripcion());
    	
		if(plan.getDuracion().equals("anual")) {
			radioAnual.setSelected(true);
		} else if(plan.getDuracion().equals("mensual")) {
			radioMensual.setSelected(true);
		} else {
			radioDiario.setSelected(true);
		}
    	
    }
	// Obtener el botón seleccionado
	private String getRadioButtonPlan() {
		if(radioAnual.isSelected()) {
			return radioAnual.getText();
		}else if (radioDiario.isSelected()) {
			return radioDiario.getText();
		} else {
			return radioMensual.getText();
		}
	}
    
	public void  limpiarFormularioPlanes() {
		textNombrePlan.setText("");
		textPrecioPlan.setText("");
		textDescripcionPlan.setText("");
		buttonGroupPlan.clearSelection();
		
		this.bloquearBotonesPlanes();
	}
	
	public void  activarBotonesPlanes(){
		btnModificarPlan.setEnabled(true);
		btn_eliminar_planes.setEnabled(true);
	}
	
	public void  bloquearBotonesPlanes(){
		btnModificarPlan.setEnabled(false);
		btn_eliminar_planes.setEnabled(false);
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
				+ "eliminas sus clases primero o tambien modificar este entrenador"));
		
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
    
		
	public PlanesPanel(int panelAncho, int panelAlto) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

			}
		});
		 buttonGroupEntrenador  = new  ButtonGroup();
		
		administrador_id = new Administrador().getId();
		
		planController = new PlanController();
		entrenadorController = new EntrenadorController();
		claseController = new ClaseController();
		
		comboBoxEntrenador = new JComboBox<>();
		comboBoxModelEntrenador = new DefaultComboBoxModel<>();
		
	    buttonGroupPlan = new ButtonGroup();
		buttonGroupPlan.add(radioAnual);
		buttonGroupPlan.add(radioMensual);
		buttonGroupPlan.add(radioDiario);
		
		
    	setPreferredSize(new Dimension(1280, 800));
        setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("PLANES");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(21, 28, 137, 43);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nombre");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1.setBounds(21, 66, 75, 14);
        add(lblNewLabel_1);
        
        textNombrePlan = new JTextField();
        textNombrePlan.setBounds(21, 82, 225, 25);
        add(textNombrePlan);
        textNombrePlan.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Descripción");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2.setBounds(21, 112, 93, 14);
        add(lblNewLabel_2);
        
        textDescripcionPlan = new JTextField();
        textDescripcionPlan.setBounds(21, 126, 225, 68);
        add(textDescripcionPlan);
        textDescripcionPlan.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Buscar por nombre:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_3.setBounds(449, 28, 137, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Precio");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_4.setBounds(267, 66, 62, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Duración");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_5.setBounds(267, 112, 75, 14);
        add(lblNewLabel_5);
        
        textBuscarPlan = new JTextField();
        textBuscarPlan.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		consultarPlan();
        	}
        });
        textBuscarPlan.setBounds(449, 43, 137, 25);
        add(textBuscarPlan);
        textBuscarPlan.setColumns(10);
        
        textPrecioPlan = new JTextField();
        textPrecioPlan.setBounds(267, 82, 109, 25);
        add(textPrecioPlan);
        textPrecioPlan.setColumns(10);
        
        radioAnual = new JRadioButton("Anual");
        radioAnual.setBackground(new Color(255, 255, 255));
        radioAnual.setBounds(267, 126, 75, 23);
        
        add(radioAnual);
        
        radioDiario = new JRadioButton("Diario");
        radioDiario.setBackground(new Color(255, 255, 255));
        radioDiario.setBounds(267, 146, 75, 23);
        add(radioDiario);
        
        radioMensual = new JRadioButton("Mensual");
        radioMensual.setBackground(new Color(255, 255, 255));
        radioMensual.setBounds(344, 126, 93, 23);
        add(radioMensual);
        
        buttonGroupPlan.add(radioDiario);
        buttonGroupPlan.add(radioMensual);
        buttonGroupPlan.add(radioAnual);
        
        btnAgregarPlan = new JButton("Agregar");
        btnAgregarPlan.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregarPlan.setFocusPainted(false);
        btnAgregarPlan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guadarPlan();
        	}
        });
        btnAgregarPlan.setBounds(21, 205, 89, 30);
        btnAgregarPlan.setBackground(new Color(46, 56, 64));
        btnAgregarPlan.setForeground(new Color(255, 255, 255));
        btnAgregarPlan.setBorder(null);
        add(btnAgregarPlan);
        
        btnModificarPlan = new JButton("Modificar");
        btnModificarPlan.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificarPlan.setFocusPainted(false);
        btnModificarPlan.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificarPlan();
        	}
        });
        btnModificarPlan.setBounds(120, 205, 89, 30);
        btnModificarPlan.setBackground(new Color(46, 56, 64));
        btnModificarPlan.setForeground(new Color(255, 255, 255));
        btnModificarPlan.setBorder(null);
        add(btnModificarPlan);
        
        btn_eliminar_planes = new JButton("Eliminar");
        btn_eliminar_planes.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_eliminar_planes.setFocusPainted(false);
        btn_eliminar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminarPlan();
        	}
        });
        btn_eliminar_planes.setBounds(219, 205, 89, 30);
        btn_eliminar_planes.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes.setForeground(new Color(255, 255, 255));
        btn_eliminar_planes.setBorder(null);
        add(btn_eliminar_planes);
        
        JLabel lblEntrenador = new JLabel("ENTRENADOR");
        lblEntrenador.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblEntrenador.setBounds(21, 243, 160, 30);
        add(lblEntrenador);
        
        JLabel lblNewLabel_3_1 = new JLabel("Buscar por cédula");
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_3_1.setBounds(449, 239, 109, 14);
        add(lblNewLabel_3_1);
        
        textBuscarEntrenador = new JTextField();
        textBuscarEntrenador.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		consultarEntrenador();
        	}
        });
        textBuscarEntrenador.setBounds(449, 253, 137, 25);
        add(textBuscarEntrenador);
        textBuscarEntrenador.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(21, 274, 75, 14);
        add(lblNewLabel_1_1);
        
        textNombreEntrenador = new JTextField();
        textNombreEntrenador.setBounds(21, 289, 225, 25);
        add(textNombreEntrenador);
        textNombreEntrenador.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Teléfono");
        lblNewLabel_6.setBounds(21, 325, 75, 14);
        add(lblNewLabel_6);
        
        textTelefonoEntrenador = new JTextField();
        textTelefonoEntrenador.setBounds(21, 340, 225, 25);
        add(textTelefonoEntrenador);
        textTelefonoEntrenador.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Correo Electrónico");
        lblNewLabel_7.setBounds(21, 372, 160, 14);
        add(lblNewLabel_7);
        
        textCorreoEntrenador = new JTextField();
        textCorreoEntrenador.setBounds(21, 388, 225, 25);
        add(textCorreoEntrenador);
        textCorreoEntrenador.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Apellido");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_8.setBounds(267, 274, 69, 14);
        add(lblNewLabel_8);
        
        textApellidoEntrenador = new JTextField();
        textApellidoEntrenador.setBounds(267, 289, 214, 25);
        add(textApellidoEntrenador);
        textApellidoEntrenador.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Sexo");
        lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_9.setBounds(267, 372, 46, 14);
        add(lblNewLabel_9);
        
        radioMasculino = new JRadioButton("Masculino");
        radioMasculino.setBackground(new Color(255, 255, 255));
        radioMasculino.setBounds(267, 388, 93, 23);
        add(radioMasculino);
        
        radioFemenino = new JRadioButton("Femenino");
        radioFemenino.setBackground(new Color(255, 255, 255));
        radioFemenino.setBounds(362, 389, 99, 23);
        add(radioFemenino);
        
        
        buttonGroupEntrenador.add(radioMasculino);
        buttonGroupEntrenador.add(radioFemenino);
        
        JLabel lblClase = new JLabel("CLASE");
        lblClase.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblClase.setBounds(21, 465, 160, 20);
        add(lblClase);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1.setBounds(21, 496, 75, 14);
        add(lblNewLabel_1_1_1);
        
        textClase = new JTextField();
        textClase.setBounds(21, 510, 225, 25);
        add(textClase);
        textClase.setColumns(10);
        
        JLabel lblNewLabel_2_1 = new JLabel("Descripción");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1.setBounds(21, 543, 93, 14);
        add(lblNewLabel_2_1);
        
        textDescripcionClase = new JTextField();
        textDescripcionClase.setBounds(21, 558, 225, 68);
        add(textDescripcionClase);
        textDescripcionClase.setColumns(10);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Entrenador");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_2.setBounds(270, 571, 211, 14);
        add(lblNewLabel_1_1_2);
        
        JLabel labelbuscarent = new JLabel("Buscar por clase");
        labelbuscarent.setFont(new Font("Tahoma", Font.PLAIN, 11));
        labelbuscarent.setBounds(449, 496, 99, 14);
        add(labelbuscarent);
        
        textBuscarClase = new JTextField();
        textBuscarClase.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		consultarClase();
        	}
        });
        textBuscarClase.setBounds(449, 510, 137, 25);
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
        btnAgregarClase.setBounds(21, 642, 89, 30);
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
        btnModificarClase.setBounds(120, 642, 89, 30);
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
        btnEliminarClase.setBounds(219, 642, 89, 30);
        btnEliminarClase.setBackground(new Color(46, 56, 64));
        btnEliminarClase.setForeground(new Color(255, 255, 255));
        btnEliminarClase.setBorder(null);
        add(btnEliminarClase);
        
        scrollPane_planes = new JScrollPane();
        scrollPane_planes.setBounds(590, 44, 465, 186);
        add(scrollPane_planes);
        //hhjgvh
        tabla_planes = new JTable();
        tabla_planes.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		idSeleccionadoPlan =(int )tabla_planes.getValueAt(tabla_planes.getSelectedRow(), 0);
        		activarBotonesPlanes();
        		llenarFormularioPlan();
        	}
        	
        });
        scrollPane_planes.setViewportView(tabla_planes);
        
        scrollPane_entrenador = new JScrollPane();
        scrollPane_entrenador.setBounds(590, 255, 465, 186);
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
        scrollPane_clases.setBounds(590, 512, 465, 186);
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
        
        JButton btn_eliminar_planes_1 = new JButton("Buscar");
        btn_eliminar_planes_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_eliminar_planes_1.setFocusPainted(false);
        btn_eliminar_planes_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		consultarPlan();
        	}
        });
        btn_eliminar_planes_1.setForeground(new Color(255, 255, 255));
        btn_eliminar_planes_1.setBorder(null);
        btn_eliminar_planes_1.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_1.setBounds(497, 73, 89, 30);
        add(btn_eliminar_planes_1);

        
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
        btnBuscarEntrenador.setBounds(497, 283, 89, 30);
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
        btnBuscarClase.setBounds(497, 539, 89, 30);
        add(btnBuscarClase);
        
        comboBoxEntrenador.setBounds(268, 585, 213, 25);
        add(comboBoxEntrenador);
        
        JLabel lblNewLabel_8_1 = new JLabel("Cédula");
        lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_8_1.setBounds(267, 325, 69, 14);
        add(lblNewLabel_8_1);
        
        textCedulaEntrenador = new JTextField();
        textCedulaEntrenador.setColumns(10);
        textCedulaEntrenador.setBounds(267, 340, 214, 25);
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
        btnAgregarEntrenador.setBounds(21, 424, 89, 30);
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
        btnModificarEntrenador.setBounds(120, 424, 89, 30);
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
        btnEliminarEntrenador.setBounds(219, 424, 89, 30);
        add(btnEliminarEntrenador);
        
        btn_eliminar_planes_3 = new JButton("Limpiar");
        btn_eliminar_planes_3.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_eliminar_planes_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormularioPlanes();
        	}
        });
        btn_eliminar_planes_3.setForeground(new Color(255, 255, 255));
        btn_eliminar_planes_3.setFocusPainted(false);
        btn_eliminar_planes_3.setBorder(null);
        btn_eliminar_planes_3.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_3.setBounds(318, 205, 89, 30);
        add(btn_eliminar_planes_3);
        
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
        btnLimpiarEntrenador.setBounds(318, 424, 89, 30);
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
        btnLimpiarClase.setBounds(318, 642, 89, 30);
        add(btnLimpiarClase);
        
        listarPlan();
        listarEntrenador();
        listarClase();
        
        listarComboEntrenador();
        
        bloquearBotonesPlanes();
        bloquearBotonesEntrenador();
        bloquearBotonesClase();
    }
}
