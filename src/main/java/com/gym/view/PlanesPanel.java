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

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PlanesPanel extends JPanel {
	private int administrador_id;
	private int idSeleccionadoPlan;
	
	private PlanController planController;
	private EntrenadorController entrenadorController;
	private ClaseController claseController;
	
	private static final long serialVersionUID = -7779240291090260615L;
    private JTextField textNombrePlan;
    private JTextField textDescripcionPlan;
    private JTextField textBuscarPlan;
    private JTextField textPrecioPlan;
    private JTextField txt_buscar_entrenador;
    private JTextField txt_nombre_entrenador;
    private JTextField txt_telefono_entrenador;
    private JTextField txt_correo_entrenador;
    private JTextField txt_apellido_entrenador;
    private JTextField txt_nombre_clase;
    private JTextField txt_descripcion_clase;
    private JTextField txt_buscar_clase;
    private JRadioButton radioAnual;
    private JRadioButton radioMensual;
    private JRadioButton radioDiario;
    private JButton btn_agregar_planes;
    private JButton btn_modificar_planes;
    private JButton btn_eliminar_planes;
    private JTable tabla_planes;
    private JButton btn_agregar_clase;
    private JButton btn_modificar_clase;
    private JButton btn_eliminar_clase;
    private DefaultTableModel modelo;
    private JScrollPane scrollPane_planes;
    private JScrollPane scrollPane_entrenador;
    private JScrollPane scrollPane_clases;
    private JTable table_entrenador;
    private JTable table_clases;
    public static DefaultTableModel modelo1;
    public static DefaultTableModel modelo_entrenador;
    public static DefaultTableModel modelo_clase;
    String[] id_entrenadores;
    String[] nombre_entrenadores;
    int codigo_entrenador=0;
    int codigo_clase=0;
    int codigo_clase_id_entrenador=0;
    String seleccion_planes = "";
    String seleccion_entrenador = "";
    String clase_nombreclase ="";
    int seleccion_clase_entrenador = 0;
    Float precio_plan=(float) 0;
    private JRadioButton rdbtn_sexo_hombre_entrenador;
    private JRadioButton rdbtn_sexo_mujer_entrenador;
    private JButton btn_eliminar_planes_1_2;
    private JComboBox cb_id_entrenador;
    
    public static DefaultComboBoxModel<String> modelo_combo_entrenador = new DefaultComboBoxModel<>();
    private JTextField txt_cedula_entrenador;
    private ButtonGroup buttonGroupEntrenador;
    ButtonGroup buttonGroupPlan;
    private JButton btn_eliminar_planes_3;
    private JButton btn_eliminar_planes_4;
    private JButton btn_eliminar_planes_5;
    
    // Plan 
    
	public void consultarPlan() {
		String[] cabeceras = {"Id","Nombre","Precio","Descripcion","Duracion"};
		
		modelo = new DefaultTableModel(planController.consultar(textBuscarPlan.getText(), administrador_id),cabeceras);
		tabla_planes.setModel(modelo);
		llenar_modelo_box();
	}
    
	public void guardar() {
		Plan plan = llenarPlan();
		
		if(planController.guardar(plan)) {
			JOptionPane.showMessageDialog(null, "Se ha registrado el plan");
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
		}
		limpiarFormularioPlanes();
		consultarPlan();
	}
	
	public void modificar() {
		
		Plan plan = llenarPlan();
		
		plan.setId(idSeleccionadoPlan);
		
		if(planController.modificar(plan)) {
			JOptionPane.showMessageDialog(null, "Modificacion exitosa");
		}else {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
		}
		
		limpiarFormularioPlanes();
		consultarPlan();
		llenar_modelo_box();
	}
	
	public void eliminar() {
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
		btn_modificar_planes.setEnabled(true);
		btn_eliminar_planes.setEnabled(true);
	}
	
	public void  bloquearBotonesPlanes(){
		btn_modificar_planes.setEnabled(false);
		btn_eliminar_planes.setEnabled(false);
	}
    
	
    public Entrenador llenarEntrenador() {
    	seleccion_entrenador = "";
		if(rdbtn_sexo_hombre_entrenador.isSelected()) {
			seleccion_entrenador = rdbtn_sexo_hombre_entrenador.getText();
		}else if(rdbtn_sexo_mujer_entrenador.isSelected()) {
			seleccion_entrenador = rdbtn_sexo_mujer_entrenador.getText();
		}
		
		return new Entrenador(
				codigo_entrenador,
				txt_nombre_entrenador.getText(),
				txt_apellido_entrenador.getText(),
				seleccion_entrenador,
				txt_correo_entrenador.getText(),
				txt_telefono_entrenador.getText(),
				txt_cedula_entrenador.getText(),
				administrador_id
				);
    }
    
    public Clase llenarClase() {
    	
    	entrenador_encontrar_id_clases();
		return new Clase(
				codigo_clase,
				txt_nombre_clase.getText(),
				txt_descripcion_clase.getText(),
				seleccion_clase_entrenador,
				administrador_id
								);
    }
	
	private Entrenador llenarEntrenador_() {
		return new Entrenador(txt_buscar_entrenador.getText());
		
	}
	
	private Clase llenarClase_() {
		
		
		return new Clase(txt_buscar_clase.getText());
		
	}
		
	public  void llenarTabla_entrenador() {
		 EntrenadorController entrenadorController= new EntrenadorController();
		Entrenador entrenador = llenarEntrenador_();
		String[] cabeceras = {"Codigo","Nombre","Precio","Descripcion","Duracion"};
		
		modelo_entrenador = new DefaultTableModel(entrenadorController.consulta(entrenador),cabeceras);
		table_entrenador.setModel(modelo_entrenador);
	}
	
	public  void llenarTabla_clase() {
		
		 ClaseController claseController= new ClaseController();
		Clase clase = llenarClase_();
		String[] cabeceras = {"Codigo","Nombre Clase","Descripcion","Entrenador Id"};
		
		modelo_clase = new DefaultTableModel(claseController.consulta(clase),cabeceras);
		table_clases.setModel(modelo_clase);
		
		entrenador_encontrar_id_clases();
	}
	
	private Entrenador llenarRegistro_ID() {
		
		return new Entrenador(
				codigo_entrenador,
				txt_nombre_entrenador.getText(),
				txt_apellido_entrenador.getText(),
				seleccion_entrenador,
				txt_correo_entrenador.getText(),
				txt_telefono_entrenador.getText(),
				txt_cedula_entrenador.getText(),
				administrador_id
				);
	}
	
	private Clase llenarRegistro_ID_clase() {
		
		return new Clase(
				codigo_clase,
				txt_nombre_clase.getText(),
				txt_descripcion_clase.getText(),
				seleccion_clase_entrenador,
				administrador_id
				);
	}
	
	public void tabal_entrenador() {
		
		if(rdbtn_sexo_hombre_entrenador.isSelected()) {
			seleccion_entrenador = rdbtn_sexo_hombre_entrenador.getText();
		}else if(rdbtn_sexo_mujer_entrenador.isSelected()) {
			seleccion_entrenador = rdbtn_sexo_mujer_entrenador.getText();
		}
	}
	
	public void  limpiar_clase() {
		txt_nombre_clase.setText("");
		txt_descripcion_clase.setText("");
		
	}
	
	public void  abotones_clase(){
		btn_modificar_clase.setEnabled(true);
		btn_agregar_clase.setEnabled(true);
		btn_eliminar_clase.setEnabled(true);
		
		
	}
	
	public void  dbotones_clase(){
		btn_modificar_clase.setEnabled(false);
		btn_agregar_clase.setEnabled(false);
		btn_eliminar_clase.setEnabled(false);
		
	}
	
	public void  atextos_clase(){
		txt_nombre_clase.setEnabled(true);;
		txt_descripcion_clase.setEnabled(true);
		cb_id_entrenador.setEnabled(true);
	}
	public void  dtextos_clase(){
		txt_nombre_clase.setEnabled(false);;
		txt_descripcion_clase.setEnabled(false);
		cb_id_entrenador.setEnabled(false);
	}
	
	public void  limpiar_entrenador() {
		txt_cedula_entrenador.setText("");
		txt_nombre_entrenador.setText("");
		txt_telefono_entrenador.setText("");
		txt_correo_entrenador.setText("");
		txt_apellido_entrenador.setText("");
		rdbtn_sexo_hombre_entrenador.setEnabled(false);
		rdbtn_sexo_mujer_entrenador.setEnabled(false);
	}
	
	public void  abotones_entrenador(){
		btn_modificar_entrenador.setEnabled(true);
		btn_agregar_entrenador.setEnabled(true);
		btn_eliminar_entrenador.setEnabled(true);
		btn_nuevo_entrenador.setEnabled(true);
		
		
	}
	
	public void  dbotones_entrenador(){
		btn_modificar_entrenador.setEnabled(false);
		btn_agregar_entrenador.setEnabled(false);
		btn_eliminar_entrenador.setEnabled(false);
		btn_nuevo_entrenador.setEnabled(false);
		
	}
	
	public void  atextos_entrenador(){
		txt_cedula_entrenador.setEnabled(true);
		txt_nombre_entrenador.setEnabled(true);
		txt_telefono_entrenador.setEnabled(true);
		txt_correo_entrenador.setEnabled(true);
		txt_apellido_entrenador.setEnabled(true);
		rdbtn_sexo_hombre_entrenador.setEnabled(true);
		rdbtn_sexo_mujer_entrenador.setEnabled(true);
	}
	
	public void  dtextos_entrenador(){
		txt_nombre_entrenador.setEnabled(false);
		txt_telefono_entrenador.setEnabled(false);
		txt_correo_entrenador.setEnabled(false);
		txt_apellido_entrenador.setEnabled(false);
		rdbtn_sexo_hombre_entrenador.setEnabled(false);
		rdbtn_sexo_mujer_entrenador.setEnabled(false);
	}
	
	public void llenar_modelo_box() {
		 modificacion_llamada();
		
		modelo_combo_entrenador = new DefaultComboBoxModel<String>(nombre_entrenadores);
		cb_id_entrenador.setModel(modelo_combo_entrenador);
		
	}
	
	public void entrenador_encontrar_id_clases() {
		int cod_entrenador_validacion=0;
		cod_entrenador_validacion= cb_id_entrenador.getSelectedIndex();
		System.out.println("el index "+cod_entrenador_validacion);
		seleccion_clase_entrenador= Integer.parseInt(   id_entrenadores[cod_entrenador_validacion]);
		System.out.println("codigo entee   "+seleccion_clase_entrenador);
	}
	
	public void modificacion_llamada(){
		 EntrenadorController entrenadorController= new EntrenadorController();
		 Entrenador entrenador = llenarEntrenador();
		  id_entrenadores = new String[entrenadorController.consulta_id_nombres_entrenador(entrenador).length];
		 for(int i=0;i<entrenadorController.consulta_id_nombres_entrenador(entrenador).length;i++) {
				id_entrenadores[i]=entrenadorController.consulta_id_nombres_entrenador(entrenador)[i][0];
				System.out.println("id del entrenador"+id_entrenadores[i]);
			}
		 
		 nombre_entrenadores= new String[entrenadorController.consulta_id_nombres_entrenador(entrenador).length];
		for(int i=0;i<entrenadorController.consulta_id_nombres_entrenador(entrenador).length;i++) {
			nombre_entrenadores[i]=entrenadorController.consulta_id_nombres_entrenador(entrenador)[i][1];
			System.out.println("id del entrenador"+nombre_entrenadores[i]);
		}
	}

	public PlanesPanel(int panelAncho, int panelAlto) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				llenar_modelo_box();
			}
		});
		 buttonGroupEntrenador  = new  ButtonGroup();
		
		administrador_id = new Administrador().getId();
		
		planController = new PlanController();
		entrenadorController = new EntrenadorController();
		claseController = new ClaseController();
		
	    buttonGroupPlan = new ButtonGroup();
		buttonGroupPlan.add(radioAnual);
		buttonGroupPlan.add(radioMensual);
		buttonGroupPlan.add(radioDiario);
		
		
    	setPreferredSize(new Dimension(1280, 800));
        setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("PLANES");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(21, 0, 137, 43);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nombre");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1.setBounds(21, 38, 75, 14);
        add(lblNewLabel_1);
        
        textNombrePlan = new JTextField();
        textNombrePlan.setBounds(21, 54, 225, 25);
        add(textNombrePlan);
        textNombrePlan.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Descripción");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2.setBounds(21, 84, 93, 14);
        add(lblNewLabel_2);
        
        textDescripcionPlan = new JTextField();
        textDescripcionPlan.setBounds(21, 98, 225, 68);
        add(textDescripcionPlan);
        textDescripcionPlan.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Buscar");
        lblNewLabel_3.setBounds(449, 0, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Precio");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_4.setBounds(267, 38, 62, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Duración");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_5.setBounds(267, 84, 75, 14);
        add(lblNewLabel_5);
        
        textBuscarPlan = new JTextField();
        textBuscarPlan.setBounds(449, 15, 137, 25);
        add(textBuscarPlan);
        textBuscarPlan.setColumns(10);
        
        textPrecioPlan = new JTextField();
        textPrecioPlan.setBounds(267, 54, 109, 25);
        add(textPrecioPlan);
        textPrecioPlan.setColumns(10);
        
        radioAnual = new JRadioButton("Anual");
        radioAnual.setBackground(new Color(255, 255, 255));
        radioAnual.setBounds(267, 98, 75, 23);
        
        add(radioAnual);
        
        radioDiario = new JRadioButton("Diario");
        radioDiario.setBackground(new Color(255, 255, 255));
        radioDiario.setBounds(267, 118, 75, 23);
        add(radioDiario);
        
        radioMensual = new JRadioButton("Mensual");
        radioMensual.setBackground(new Color(255, 255, 255));
        radioMensual.setBounds(344, 98, 93, 23);
        add(radioMensual);
        
        buttonGroupPlan.add(radioDiario);
        buttonGroupPlan.add(radioMensual);
        buttonGroupPlan.add(radioAnual);
        
        btn_agregar_planes = new JButton("Agregar");
        btn_agregar_planes.setFocusPainted(false);
        btn_agregar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guardar();
        	}
        });
        btn_agregar_planes.setBounds(21, 177, 89, 30);
        btn_agregar_planes.setBackground(new Color(46, 56, 64));
        btn_agregar_planes.setForeground(new Color(163, 175, 175));
        btn_agregar_planes.setBorder(null);
        add(btn_agregar_planes);
        
        btn_modificar_planes = new JButton("Modificar");
        btn_modificar_planes.setFocusPainted(false);
        btn_modificar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificar();
        	}
        });
        btn_modificar_planes.setBounds(120, 177, 89, 30);
        btn_modificar_planes.setBackground(new Color(46, 56, 64));
        btn_modificar_planes.setForeground(new Color(163, 175, 175));
        btn_modificar_planes.setBorder(null);
        add(btn_modificar_planes);
        
        btn_eliminar_planes = new JButton("Eliminar");
        btn_eliminar_planes.setFocusPainted(false);
        btn_eliminar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminar();
        	}
        });
        btn_eliminar_planes.setBounds(219, 177, 89, 30);
        btn_eliminar_planes.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes.setBorder(null);
        add(btn_eliminar_planes);
        
        JLabel lblEntrenador = new JLabel("ENTRENADOR");
        lblEntrenador.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblEntrenador.setBounds(21, 215, 160, 30);
        add(lblEntrenador);
        
        JLabel lblNewLabel_3_1 = new JLabel("Buscar");
        lblNewLabel_3_1.setBounds(449, 211, 46, 14);
        add(lblNewLabel_3_1);
        
        txt_buscar_entrenador = new JTextField();
        txt_buscar_entrenador.setBounds(449, 225, 137, 25);
        add(txt_buscar_entrenador);
        txt_buscar_entrenador.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(21, 246, 75, 14);
        add(lblNewLabel_1_1);
        
        txt_nombre_entrenador = new JTextField();
        txt_nombre_entrenador.setBounds(21, 261, 225, 25);
        add(txt_nombre_entrenador);
        txt_nombre_entrenador.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Teléfono");
        lblNewLabel_6.setBounds(21, 297, 75, 14);
        add(lblNewLabel_6);
        
        txt_telefono_entrenador = new JTextField();
        txt_telefono_entrenador.setBounds(21, 312, 225, 25);
        add(txt_telefono_entrenador);
        txt_telefono_entrenador.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Correo Electrónico");
        lblNewLabel_7.setBounds(21, 344, 160, 14);
        add(lblNewLabel_7);
        
        txt_correo_entrenador = new JTextField();
        txt_correo_entrenador.setBounds(21, 360, 225, 25);
        add(txt_correo_entrenador);
        txt_correo_entrenador.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Apellido");
        lblNewLabel_8.setBounds(267, 246, 69, 14);
        add(lblNewLabel_8);
        
        txt_apellido_entrenador = new JTextField();
        txt_apellido_entrenador.setBounds(267, 261, 214, 25);
        add(txt_apellido_entrenador);
        txt_apellido_entrenador.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Sexo");
        lblNewLabel_9.setBounds(267, 344, 46, 14);
        add(lblNewLabel_9);
        
        rdbtn_sexo_hombre_entrenador = new JRadioButton("Masculino");
        rdbtn_sexo_hombre_entrenador.setBackground(new Color(255, 255, 255));
        rdbtn_sexo_hombre_entrenador.setBounds(267, 360, 93, 23);
        add(rdbtn_sexo_hombre_entrenador);
        
        rdbtn_sexo_mujer_entrenador = new JRadioButton("Femenino");
        rdbtn_sexo_mujer_entrenador.setBackground(new Color(255, 255, 255));
        rdbtn_sexo_mujer_entrenador.setBounds(362, 361, 99, 23);
        add(rdbtn_sexo_mujer_entrenador);
        
        
        buttonGroupEntrenador.add(rdbtn_sexo_hombre_entrenador);
        buttonGroupEntrenador.add(rdbtn_sexo_mujer_entrenador);
        
        JLabel lblClase = new JLabel("CLASE");
        lblClase.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblClase.setBounds(21, 437, 160, 20);
        add(lblClase);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1.setBounds(21, 468, 75, 14);
        add(lblNewLabel_1_1_1);
        
        txt_nombre_clase = new JTextField();
        txt_nombre_clase.setBounds(21, 482, 225, 25);
        add(txt_nombre_clase);
        txt_nombre_clase.setColumns(10);
        
        JLabel lblNewLabel_2_1 = new JLabel("Descripción");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1.setBounds(21, 515, 93, 14);
        add(lblNewLabel_2_1);
        
        txt_descripcion_clase = new JTextField();
        txt_descripcion_clase.setBounds(21, 530, 225, 68);
        add(txt_descripcion_clase);
        txt_descripcion_clase.setColumns(10);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Entrenador");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_2.setBounds(269, 468, 93, 14);
        add(lblNewLabel_1_1_2);
        
        JLabel lblNewLabel_3_1_1 = new JLabel("Buscar");
        lblNewLabel_3_1_1.setBounds(449, 468, 46, 14);
        add(lblNewLabel_3_1_1);
        
        txt_buscar_clase = new JTextField();
        txt_buscar_clase.setBounds(449, 482, 137, 25);
        add(txt_buscar_clase);
        txt_buscar_clase.setColumns(10);
        
        btn_agregar_clase = new JButton("Agregar");
        btn_agregar_clase.setFocusPainted(false);
        btn_agregar_clase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Clase clase_llenado = llenarClase();
        		        		if(claseController.agregar(clase_llenado)) {
        			JOptionPane.showMessageDialog(null, "Se ha registrado el plan");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		        		entrenador_encontrar_id_clases();
        		limpiar_clase();
        		clase_llenado.setClase("");        		
        		llenarTabla_clase();
        		
        		llenarClase();
        		
        		
        	}
        });
        btn_agregar_clase.setBounds(21, 614, 89, 30);
        btn_agregar_clase.setBackground(new Color(46, 56, 64));
        btn_agregar_clase.setForeground(new Color(163, 175, 175));
        btn_agregar_clase.setBorder(null);
        add(btn_agregar_clase);
        
        btn_modificar_clase = new JButton("Modificar");
        btn_modificar_clase.setFocusPainted(false);
        btn_modificar_clase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		 modificacion_llamada();
        		entrenador_encontrar_id_clases();
        		Clase clase_llenado= llenarRegistro_ID_clase();
        		if(claseController.consult(clase_llenado)) {
        			JOptionPane.showMessageDialog(null, "Modificacion exitosa");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		
        		limpiar_clase();
        		llenarTabla_clase();
        		
        	}
        });
        btn_modificar_clase.setBounds(120, 614, 89, 30);
        btn_modificar_clase.setBackground(new Color(46, 56, 64));
        btn_modificar_clase.setForeground(new Color(163, 175, 175));
        btn_modificar_clase.setBorder(null);
        add(btn_modificar_clase);
        
        btn_eliminar_clase = new JButton("Eliminar");
        btn_eliminar_clase.setFocusPainted(false);
        btn_eliminar_clase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Clase clase_llenado= llenarRegistro_ID_clase();
        		if(claseController.eliminar(clase_llenado)) {
        			JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		
        		
        		limpiar_clase();
        		llenarTabla_clase();
        		llenarTabla_clase();
        		llenar_modelo_box();
        	}
        });
        btn_eliminar_clase.setBounds(219, 614, 89, 30);
        btn_eliminar_clase.setBackground(new Color(46, 56, 64));
        btn_eliminar_clase.setForeground(new Color(163, 175, 175));
        btn_eliminar_clase.setBorder(null);
        add(btn_eliminar_clase);
        
        scrollPane_planes = new JScrollPane();
        scrollPane_planes.setBounds(590, 16, 465, 186);
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
        scrollPane_entrenador.setBounds(590, 227, 465, 186);
        add(scrollPane_entrenador);
        
        table_entrenador = new JTable();
        table_entrenador.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		codigo_entrenador =(int )table_entrenador.getValueAt(table_entrenador.getSelectedRow(), 0);
        		Entrenador entrenador = llenarRegistro_ID();
        		System.out.println(codigo_entrenador);
				String[]dato = new String[5]; 
				dato=entrenadorController.consultar_(entrenador);
				
				if(dato!=null) {
					
					
					
					txt_nombre_entrenador.setText(dato[0]);
					
					txt_apellido_entrenador.setText(dato[1]);
					//txt_descripcion_planes.setText( dato[2]);
					if(rdbtn_sexo_hombre_entrenador.getText().equals(dato[2])) {
						rdbtn_sexo_hombre_entrenador.setSelected(true);
					}else if(rdbtn_sexo_mujer_entrenador.getText().equals(dato[2])) {
						rdbtn_sexo_mujer_entrenador.setSelected(true);
					}
					txt_correo_entrenador.setText(dato[3]);
					txt_telefono_entrenador.setText(dato[4]);
				}
				dbotones_entrenador();
				btn_modificar_entrenador.setEnabled(true);
				btn_eliminar_entrenador.setEnabled(true);
				atextos_entrenador();
        	}
        });
        scrollPane_entrenador.setViewportView(table_entrenador);
        
        scrollPane_clases = new JScrollPane();
        scrollPane_clases.setBounds(590, 484, 465, 186);
        add(scrollPane_clases);
        
        table_clases = new JTable();
        table_clases.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		codigo_clase =(int )table_clases.getValueAt(table_clases.getSelectedRow(), 0);
        		Clase clase = llenarRegistro_ID_clase();
        		
				String[]dato = new String[3]; 
				dato=claseController.consultar_(clase);
				
				if(dato!=null) {
					
					
					
					txt_nombre_clase.setText(dato[0]);
					
					txt_descripcion_clase.setText(dato[1]);
					//txt_descripcion_planes.setText( dato[2]);
					
				}
				dbotones_clase();
				btn_modificar_clase.setEnabled(true);
				btn_eliminar_clase.setEnabled(true);
				atextos_clase();
				
        	}
        });
        scrollPane_clases.setViewportView(table_clases);
        
        JButton btn_eliminar_planes_1 = new JButton("Buscar");
        btn_eliminar_planes_1.setFocusPainted(false);
        btn_eliminar_planes_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		consultarPlan();
        	}
        });
        btn_eliminar_planes_1.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_1.setBorder(null);
        btn_eliminar_planes_1.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_1.setBounds(497, 45, 89, 30);
        add(btn_eliminar_planes_1);

        
        JButton btn_eliminar_planes_1_1 = new JButton("Buscar");
        btn_eliminar_planes_1_1.setFocusPainted(false);
        btn_eliminar_planes_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		llenarTabla_entrenador();
        	}
        });
        btn_eliminar_planes_1_1.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_1_1.setBorder(null);
        btn_eliminar_planes_1_1.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_1_1.setBounds(497, 255, 89, 30);
        add(btn_eliminar_planes_1_1);
        
        btn_eliminar_planes_1_2 = new JButton("Buscar");
        btn_eliminar_planes_1_2.setFocusPainted(false);
        btn_eliminar_planes_1_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		llenar_modelo_box();
        		llenarTabla_clase();
        		
        	}
        });
        
        btn_eliminar_planes_1_2.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_1_2.setBorder(null);
        btn_eliminar_planes_1_2.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_1_2.setBounds(497, 511, 89, 30);
        add(btn_eliminar_planes_1_2);
        
        cb_id_entrenador = new JComboBox();
        cb_id_entrenador.setBounds(267, 482, 118, 25);
        add(cb_id_entrenador);
        
        JLabel lblNewLabel_8_1 = new JLabel("Cédula");
        lblNewLabel_8_1.setBounds(267, 297, 69, 14);
        add(lblNewLabel_8_1);
        
        txt_cedula_entrenador = new JTextField();
        txt_cedula_entrenador.setColumns(10);
        txt_cedula_entrenador.setBounds(267, 312, 214, 25);
        add(txt_cedula_entrenador);
        
        JButton btn_agregar_planes_1 = new JButton("Agregar");
        btn_agregar_planes_1.setForeground(new Color(163, 175, 175));
        btn_agregar_planes_1.setFocusPainted(false);
        btn_agregar_planes_1.setBorder(null);
        btn_agregar_planes_1.setBackground(new Color(46, 56, 64));
        btn_agregar_planes_1.setBounds(21, 396, 89, 30);
        add(btn_agregar_planes_1);
        
        JButton btn_modificar_planes_1 = new JButton("Modificar");
        btn_modificar_planes_1.setForeground(new Color(163, 175, 175));
        btn_modificar_planes_1.setFocusPainted(false);
        btn_modificar_planes_1.setBorder(null);
        btn_modificar_planes_1.setBackground(new Color(46, 56, 64));
        btn_modificar_planes_1.setBounds(120, 396, 89, 30);
        add(btn_modificar_planes_1);
        
        JButton btn_eliminar_planes_2 = new JButton("Eliminar");
        btn_eliminar_planes_2.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_2.setFocusPainted(false);
        btn_eliminar_planes_2.setBorder(null);
        btn_eliminar_planes_2.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_2.setBounds(219, 396, 89, 30);
        add(btn_eliminar_planes_2);
        
        btn_eliminar_planes_3 = new JButton("Limpiar");
        btn_eliminar_planes_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormularioPlanes();
        	}
        });
        btn_eliminar_planes_3.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_3.setFocusPainted(false);
        btn_eliminar_planes_3.setBorder(null);
        btn_eliminar_planes_3.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_3.setBounds(318, 177, 89, 30);
        add(btn_eliminar_planes_3);
        
        btn_eliminar_planes_4 = new JButton("Limpiar");
        btn_eliminar_planes_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        
        btn_eliminar_planes_4.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_4.setFocusPainted(false);
        btn_eliminar_planes_4.setBorder(null);
        btn_eliminar_planes_4.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_4.setBounds(318, 396, 89, 30);
        add(btn_eliminar_planes_4);
        
        btn_eliminar_planes_5 = new JButton("Limpiar");
        btn_eliminar_planes_5.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_5.setFocusPainted(false);
        btn_eliminar_planes_5.setBorder(null);
        btn_eliminar_planes_5.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_5.setBounds(318, 614, 89, 30);
        add(btn_eliminar_planes_5);
        
        bloquearBotonesPlanes();
        
    }
}
