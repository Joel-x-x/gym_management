package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.EntrenadorController;
import com.gym.controller.PlanController;
import com.gym.controller.UsuarioController;
import com.gym.model.Clase;
import com.gym.model.Entrenador;
import com.gym.model.Plan;
import com.gym.model.Registro;
import com.gym.model.Usuario;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class PlanesPanel extends JPanel {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7779240291090260615L;
    private JTextField txt_nombre_planes;
    private JTextField txt_descripcion_planes;
    private JTextField txt_buscar_planes;
    private JTextField txt_precio_planes;
    private JTextField txt_buscar_entrenador;
    private JTextField txt_nombre_entrenador;
    private JTextField txt_telefono_entrenador;
    private JTextField txt_correo_entrenador;
    private JTextField txt_apellido_entrenador;
    private JTextField txt_nombre_clase;
    private JTextField txt_descripcion_clase;
    private JTextField txt_buscar_clase;
    private JRadioButton rdbtn_anual_planes;
    private JRadioButton rdbtn_mensual_planes;
    private JRadioButton rdbtn_diario_planes;
    private JButton btn_agregar_planes;
    private JButton btn_modificar_planes;
    private JButton btn_eliminar_planes;
    private JButton btn_agregar_entrenador;
    private JButton btn_modificar_entrenador;
    private JButton btn_eliminar_entrenador;
    private JTable tabla_planes;
    private JButton btn_agregar_clase;
    private JButton btn_modificar_clase;
    private JButton btn_eliminar_clase;
    private DefaultTableModel modelo;
    private PlanController planController;
    private JScrollPane scrollPane_planes;
    private JScrollPane scrollPane_entrenador;
    private JScrollPane scrollPane_clases;
    private JTable table_entrenador;
    private JTable table_clases;
    public static DefaultTableModel modelo1;
    public static DefaultTableModel modelo_entrenador;
    int codigo=0;
    String[] id_entrenadores;
    String[] nombre_entrenadores;
    int codigo_entrenador=0;
    int codigo_clase=0;
    int codigo_clase_id_entrenador=0;
    String seleccion_planes = "";
    String seleccion_entrenador = "";
    Float precio_plan=(float) 0;
    private JButton btn_nuevo_planes;
    private JButton btn_nuevo_planes_1;
    private JButton btn_nuevo_entrenador;
    private JButton btn_nuevo_planes_3;
    private JRadioButton rdbtn_sexo_hombre_entrenador;
    private JRadioButton rdbtn_sexo_mujer_entrenador;
    private JButton btn_eliminar_planes_1_2;
    private JComboBox cb_id_entrenador;
    
    public static DefaultComboBoxModel<String> modelo_combo_entrenador = new DefaultComboBoxModel<>();
    private JTextField txt_cedula_entrenador;
    public void llenar_tabla() {
		String cabeceras[] = {"id","nombre","precio","descripción","duración"};
		
		
	}
    

    public Plan llenarPlan() {

    	seleccion_planes = "";
    	

		if(rdbtn_anual_planes.isSelected()) {
			seleccion_planes = rdbtn_mensual_planes.getText();
		}else if (rdbtn_diario_planes.isSelected()) {
			seleccion_planes = rdbtn_diario_planes.getText();
		}
		
		Float precio_plan = Float.parseFloat(txt_precio_planes.getText());
		
		return new Plan(txt_nombre_planes.getText(),
						precio_plan,
						txt_descripcion_planes.getText(),
						seleccion_planes);
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
				txt_cedula_entrenador.getText()
				);
    }
    public Clase llenarClase() {
    	cb_id_entrenador.getSelectedIndex();
    	
		return new Clase(
				codigo_clase,
				txt_nombre_clase.getText(),
				txt_descripcion_clase.getText(),
				codigo_clase_id_entrenador
								);
    }

	public PlanesPanel(int panelAncho, int panelAlto) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				llenar_modelo_box();
			}
		});
		
		PlanController planController = new PlanController();
		EntrenadorController entrenadorController = new EntrenadorController();
	    ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtn_anual_planes);
		buttonGroup.add(rdbtn_mensual_planes);
		buttonGroup.add(rdbtn_diario_planes);
		
		
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
        
        txt_nombre_planes = new JTextField();
        txt_nombre_planes.setEnabled(false);
        txt_nombre_planes.setBounds(21, 54, 225, 20);
        add(txt_nombre_planes);
        txt_nombre_planes.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Descripción");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2.setBounds(21, 84, 93, 14);
        add(lblNewLabel_2);
        
        txt_descripcion_planes = new JTextField();
        txt_descripcion_planes.setEnabled(false);
        txt_descripcion_planes.setBounds(21, 98, 225, 68);
        add(txt_descripcion_planes);
        txt_descripcion_planes.setColumns(10);
        
        JLabel lblNewLabel_3 = new JLabel("Buscar");
        lblNewLabel_3.setBounds(466, 17, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Precio");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_4.setBounds(289, 38, 62, 14);
        add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Duración");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_5.setBounds(289, 84, 75, 14);
        add(lblNewLabel_5);
        
        txt_buscar_planes = new JTextField();
        txt_buscar_planes.setBounds(432, 35, 137, 20);
        add(txt_buscar_planes);
        txt_buscar_planes.setColumns(10);
        
        txt_precio_planes = new JTextField();
        txt_precio_planes.setEnabled(false);
        txt_precio_planes.setBounds(289, 54, 109, 20);
        add(txt_precio_planes);
        txt_precio_planes.setColumns(10);
        
        rdbtn_anual_planes = new JRadioButton("Anual");
        rdbtn_anual_planes.setEnabled(false);
        rdbtn_anual_planes.setBackground(new Color(255, 255, 255));
        rdbtn_anual_planes.setBounds(289, 98, 109, 23);
        
        add(rdbtn_anual_planes);
        
        rdbtn_diario_planes = new JRadioButton("Diario");
        rdbtn_diario_planes.setEnabled(false);
        rdbtn_diario_planes.setBackground(new Color(255, 255, 255));
        rdbtn_diario_planes.setBounds(289, 118, 109, 23);
        add(rdbtn_diario_planes);
        
        rdbtn_mensual_planes = new JRadioButton("Mensual");
        rdbtn_mensual_planes.setEnabled(false);
        rdbtn_mensual_planes.setBackground(new Color(255, 255, 255));
        rdbtn_mensual_planes.setBounds(394, 98, 109, 23);
        add(rdbtn_mensual_planes);
        
        btn_agregar_planes = new JButton("Agregar");
        btn_agregar_planes.setEnabled(false);
        btn_agregar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Plan plan_llenado = llenarPlan();
        		
        		if(planController.registrar(plan_llenado)) {
        			JOptionPane.showMessageDialog(null, "Se ha registrado el plan");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		llenarPlan_();
        		limpiar_planes();
        		llenarTabla_plan();
        	}
        });
        btn_agregar_planes.setBounds(137, 177, 89, 23);
        btn_agregar_planes.setBackground(new Color(46, 56, 64));
        btn_agregar_planes.setForeground(new Color(163, 175, 175));
        btn_agregar_planes.setBorder(null);
        add(btn_agregar_planes);
        
        btn_modificar_planes = new JButton("Modificar");
        btn_modificar_planes.setEnabled(false);
        btn_modificar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabal_plan();
        		Plan plan_llenado= llenarRegistro_id();
        		if(planController.consult(plan_llenado)) {
        			JOptionPane.showMessageDialog(null, "Modificacion exitosa");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		
        		limpiar_planes();
        		llenarTabla_plan();
        		llenar_modelo_box();
        		
        	}
        });
        btn_modificar_planes.setBounds(246, 177, 89, 23);
        btn_modificar_planes.setBackground(new Color(46, 56, 64));
        btn_modificar_planes.setForeground(new Color(163, 175, 175));
        btn_modificar_planes.setBorder(null);
        add(btn_modificar_planes);
        
        btn_eliminar_planes = new JButton("Eliminar");
        btn_eliminar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabal_plan();
        		Plan plan_llenado= llenarRegistro_id();
        		if(planController.eliminar(plan_llenado)) {
        			JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		
        		
        		limpiar_planes();
        		llenarTabla_plan();
        	}
        });
        btn_eliminar_planes.setEnabled(false);
        btn_eliminar_planes.setBounds(351, 177, 89, 23);
        btn_eliminar_planes.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes.setBorder(null);
        add(btn_eliminar_planes);
        
        JLabel lblEntrenador = new JLabel("ENTRENADOR");
        lblEntrenador.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblEntrenador.setBounds(21, 211, 160, 43);
        add(lblEntrenador);
        
        JLabel lblNewLabel_3_1 = new JLabel("Buscar");
        lblNewLabel_3_1.setBounds(480, 211, 46, 14);
        add(lblNewLabel_3_1);
        
        txt_buscar_entrenador = new JTextField();
        txt_buscar_entrenador.setBounds(449, 225, 137, 20);
        add(txt_buscar_entrenador);
        txt_buscar_entrenador.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(21, 246, 75, 14);
        add(lblNewLabel_1_1);
        
        txt_nombre_entrenador = new JTextField();
        txt_nombre_entrenador.setEnabled(false);
        txt_nombre_entrenador.setBounds(21, 265, 225, 20);
        add(txt_nombre_entrenador);
        txt_nombre_entrenador.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Teléfono");
        lblNewLabel_6.setBounds(21, 289, 75, 14);
        add(lblNewLabel_6);
        
        txt_telefono_entrenador = new JTextField();
        txt_telefono_entrenador.setEnabled(false);
        txt_telefono_entrenador.setBounds(21, 302, 225, 20);
        add(txt_telefono_entrenador);
        txt_telefono_entrenador.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Correo Electrónico");
        lblNewLabel_7.setBounds(21, 327, 160, 14);
        add(lblNewLabel_7);
        
        txt_correo_entrenador = new JTextField();
        txt_correo_entrenador.setEnabled(false);
        txt_correo_entrenador.setBounds(21, 343, 225, 20);
        add(txt_correo_entrenador);
        txt_correo_entrenador.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Apellido");
        lblNewLabel_8.setBounds(289, 246, 69, 14);
        add(lblNewLabel_8);
        
        txt_apellido_entrenador = new JTextField();
        txt_apellido_entrenador.setEnabled(false);
        txt_apellido_entrenador.setBounds(267, 265, 214, 20);
        add(txt_apellido_entrenador);
        txt_apellido_entrenador.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Sexo");
        lblNewLabel_9.setBounds(289, 327, 46, 14);
        add(lblNewLabel_9);
        
        rdbtn_sexo_hombre_entrenador = new JRadioButton("Masculino");
        rdbtn_sexo_hombre_entrenador.setEnabled(false);
        rdbtn_sexo_hombre_entrenador.setBackground(new Color(255, 255, 255));
        rdbtn_sexo_hombre_entrenador.setBounds(289, 342, 109, 23);
        add(rdbtn_sexo_hombre_entrenador);
        
        rdbtn_sexo_mujer_entrenador = new JRadioButton("Femenino");
        rdbtn_sexo_mujer_entrenador.setEnabled(false);
        rdbtn_sexo_mujer_entrenador.setBackground(new Color(255, 255, 255));
        rdbtn_sexo_mujer_entrenador.setBounds(394, 342, 109, 23);
        add(rdbtn_sexo_mujer_entrenador);
        
        btn_agregar_entrenador = new JButton("Agregar");

        btn_agregar_entrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Entrenador entrenador_llenado = llenarEntrenador();
        		
        		if(entrenadorController.registrar(entrenador_llenado)) {
        			JOptionPane.showMessageDialog(null, "Se ha registrado el plan");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		llenarEntrenador();
        		limpiar_entrenador();
        		llenarTabla_entrenador();
        		llenar_modelo_box();
        	}
        });
        btn_agregar_entrenador.setEnabled(false);
        btn_agregar_entrenador.setBounds(87, 374, 89, 23);

        btn_agregar_entrenador.setBounds(137, 374, 89, 23);

        btn_agregar_entrenador.setBackground(new Color(46, 56, 64));
        btn_agregar_entrenador.setForeground(new Color(163, 175, 175));
        btn_agregar_entrenador.setBorder(null);
        add(btn_agregar_entrenador);
        
        btn_modificar_entrenador = new JButton("Modificar");

        btn_modificar_entrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabal_entrenador();
        		Entrenador entrenador_llenado= llenarRegistro_ID();
        		if(entrenadorController.consult(entrenador_llenado)) {
        			JOptionPane.showMessageDialog(null, "Modificacion exitosa");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		
        		limpiar_entrenador();
        		llenarTabla_entrenador();
        		llenar_modelo_box();
        		
        		
        		
        	}
        });
        btn_modificar_entrenador.setEnabled(false);
        btn_modificar_entrenador.setBounds(190, 374, 89, 23);

        btn_modificar_entrenador.setBounds(246, 374, 89, 23);

        btn_modificar_entrenador.setBackground(new Color(46, 56, 64));
        btn_modificar_entrenador.setForeground(new Color(163, 175, 175));
        btn_modificar_entrenador.setBorder(null);
        add(btn_modificar_entrenador);
        
        btn_eliminar_entrenador = new JButton("Eliminar");

        btn_eliminar_entrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		tabal_entrenador();
        		Entrenador entrenador_llenado= llenarRegistro_ID();
        		if(entrenadorController.eliminar(entrenador_llenado)) {
        			JOptionPane.showMessageDialog(null, "Eliminacion exitosa");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		
        		
        		limpiar_entrenador();
        		llenarTabla_entrenador();
        		llenarTabla_entrenador();
        		llenar_modelo_box();
        	}
        });
        btn_eliminar_entrenador.setEnabled(false);
        btn_eliminar_entrenador.setBounds(289, 374, 89, 23);

        btn_eliminar_entrenador.setBounds(351, 374, 89, 23);

        btn_eliminar_entrenador.setBackground(new Color(46, 56, 64));
        btn_eliminar_entrenador.setForeground(new Color(163, 175, 175));
        btn_eliminar_entrenador.setBorder(null);
        add(btn_eliminar_entrenador);
        
        JLabel lblClase = new JLabel("CLASE");
        lblClase.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblClase.setBounds(21, 399, 160, 43);
        add(lblClase);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1.setBounds(21, 435, 75, 14);
        add(lblNewLabel_1_1_1);
        
        txt_nombre_clase = new JTextField();
        txt_nombre_clase.setBounds(21, 449, 225, 20);
        add(txt_nombre_clase);
        txt_nombre_clase.setColumns(10);
        
        JLabel lblNewLabel_2_1 = new JLabel("Descripción");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1.setBounds(21, 471, 93, 14);
        add(lblNewLabel_2_1);
        
        txt_descripcion_clase = new JTextField();
        txt_descripcion_clase.setBounds(21, 486, 225, 68);
        add(txt_descripcion_clase);
        txt_descripcion_clase.setColumns(10);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("ID Entrenador");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_2.setBounds(291, 486, 168, 14);
        add(lblNewLabel_1_1_2);
        
        JLabel lblNewLabel_3_1_1 = new JLabel("Buscar");
        lblNewLabel_3_1_1.setBounds(466, 435, 46, 14);
        add(lblNewLabel_3_1_1);
        
        txt_buscar_clase = new JTextField();
        txt_buscar_clase.setBounds(432, 449, 137, 20);
        add(txt_buscar_clase);
        txt_buscar_clase.setColumns(10);
        
        btn_agregar_clase = new JButton("Agregar");
        btn_agregar_clase.setBounds(137, 565, 89, 23);
        btn_agregar_clase.setBackground(new Color(46, 56, 64));
        btn_agregar_clase.setForeground(new Color(163, 175, 175));
        btn_agregar_clase.setBorder(null);
        add(btn_agregar_clase);
        
        btn_modificar_clase = new JButton("Modificar");
        btn_modificar_clase.setBounds(246, 565, 89, 23);
        btn_modificar_clase.setBackground(new Color(46, 56, 64));
        btn_modificar_clase.setForeground(new Color(163, 175, 175));
        btn_modificar_clase.setBorder(null);
        add(btn_modificar_clase);
        
        btn_eliminar_clase = new JButton("Eliminar");
        btn_eliminar_clase.setBounds(351, 565, 89, 23);
        btn_eliminar_clase.setBackground(new Color(46, 56, 64));
        btn_eliminar_clase.setForeground(new Color(163, 175, 175));
        btn_eliminar_clase.setBorder(null);
        add(btn_eliminar_clase);
        
        scrollPane_planes = new JScrollPane();
        scrollPane_planes.setBounds(590, 0, 465, 186);
        add(scrollPane_planes);
        //hhjgvh
        tabla_planes = new JTable();
        tabla_planes.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		 codigo =(int )tabla_planes.getValueAt(tabla_planes.getSelectedRow(), 0);
        		Plan plan = llenarRegistro_id();
        		System.out.println(codigo);
				String[]dato = new String[3]; 
				dato=planController.consultar_(plan);
				
				if(dato!=null) {
					
					
					
					txt_nombre_planes.setText(dato[0]);
					
					txt_precio_planes.setText(dato[1]);
					txt_descripcion_planes.setText( dato[2]);
					if(rdbtn_anual_planes.getText().toLowerCase().equals(dato[3])) {
						System.out.println("este: "+rdbtn_anual_planes.getText().toLowerCase()+" con ste "+dato[3]);
						rdbtn_anual_planes.setSelected(true);
					}else if(rdbtn_mensual_planes.getText().toLowerCase().equals(dato[3])) {
						System.out.println("este: "+rdbtn_mensual_planes.getText().toLowerCase()+" con ste "+dato[3]);
						rdbtn_mensual_planes.setSelected(true);
					}else if (rdbtn_diario_planes.getText().toLowerCase().equals(dato[3])) {
						System.out.println("este: "+rdbtn_diario_planes.getText().toLowerCase()+" con ste "+dato[3]);
						rdbtn_diario_planes.setSelected(true);
					}
					System.out.println("este: "+rdbtn_anual_planes.getText().toLowerCase()+" con ste "+dato[3]);
					System.out.println("este: "+rdbtn_mensual_planes.getText().toLowerCase()+" con ste "+dato[3]);
					System.out.println("este: "+rdbtn_diario_planes.getText().toLowerCase()+" con ste "+dato[3]);
					
				}
				dbotones_planes();
				btn_modificar_planes.setEnabled(true);
				btn_eliminar_planes.setEnabled(true);
				atextos_planes();
        	}
        	
        });
        scrollPane_planes.setViewportView(tabla_planes);
        
        scrollPane_entrenador = new JScrollPane();
        scrollPane_entrenador.setBounds(590, 211, 465, 186);
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
        scrollPane_clases.setBounds(590, 439, 465, 186);
        add(scrollPane_clases);
        
        table_clases = new JTable();
        scrollPane_clases.setViewportView(table_clases);
        
        JButton btn_eliminar_planes_1 = new JButton("Buscar");
        btn_eliminar_planes_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		llenarTabla_plan();
        	}
        });
        btn_eliminar_planes_1.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_1.setBorder(null);
        btn_eliminar_planes_1.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_1.setBounds(466, 66, 89, 23);
        add(btn_eliminar_planes_1);
        
        btn_nuevo_planes = new JButton("Nuevo");
        btn_nuevo_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		//abotones_planes();
        		atextos_planes();
        		btn_agregar_planes.setEnabled(true);
        	}
        });
        btn_nuevo_planes.setForeground(new Color(163, 175, 175));
        btn_nuevo_planes.setBorder(null);
        btn_nuevo_planes.setBackground(new Color(46, 56, 64));
        btn_nuevo_planes.setBounds(31, 177, 89, 23);
        add(btn_nuevo_planes);
        
        btn_nuevo_planes_1 = new JButton("Cancelar");
        btn_nuevo_planes_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dbotones_planes();
        		btn_nuevo_planes.setEnabled(true);
        		limpiar_planes();
        	}
        });
        btn_nuevo_planes_1.setForeground(new Color(163, 175, 175));
        btn_nuevo_planes_1.setBorder(null);
        btn_nuevo_planes_1.setBackground(new Color(46, 56, 64));
        btn_nuevo_planes_1.setBounds(464, 177, 89, 23);
        add(btn_nuevo_planes_1);
        
        btn_nuevo_entrenador = new JButton("Nuevo");

        btn_nuevo_entrenador.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		atextos_entrenador();
        		btn_agregar_entrenador.setEnabled(true);
        	}
        });
        btn_nuevo_entrenador.setForeground(new Color(163, 175, 175));
        btn_nuevo_entrenador.setBorder(null);
        btn_nuevo_entrenador.setBackground(new Color(46, 56, 64));
        btn_nuevo_entrenador.setBounds(-12, 374, 89, 23);
        add(btn_nuevo_entrenador);
        
        btn_nuevo_planes_3 = new JButton("Cancelar");
        btn_nuevo_planes_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dbotones_entrenador();
        		btn_nuevo_entrenador.setEnabled(true);
        		limpiar_entrenador();
        	}
        });
        btn_nuevo_planes_3.setForeground(new Color(163, 175, 175));
        btn_nuevo_planes_3.setBorder(null);
        btn_nuevo_planes_3.setBackground(new Color(46, 56, 64));
        btn_nuevo_planes_3.setBounds(449, 372, 89, 23);
        add(btn_nuevo_planes_3);

        btn_nuevo_entrenador.setForeground(new Color(163, 175, 175));
        btn_nuevo_entrenador.setBorder(null);
        btn_nuevo_entrenador.setBackground(new Color(46, 56, 64));
        btn_nuevo_entrenador.setBounds(38, 374, 89, 23);
        add(btn_nuevo_entrenador);
        
        JButton btn_nuevo_clase = new JButton("Nuevo");
        btn_nuevo_clase.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		llenar_modelo_box();
        	}
        });
        btn_nuevo_clase.setForeground(new Color(163, 175, 175));
        btn_nuevo_clase.setBorder(null);
        btn_nuevo_clase.setBackground(new Color(46, 56, 64));
        btn_nuevo_clase.setBounds(38, 565, 89, 23);
        add(btn_nuevo_clase);
        
        JButton btn_cancelar_clase = new JButton("Cancelar");
        btn_cancelar_clase.setForeground(new Color(163, 175, 175));
        btn_cancelar_clase.setBorder(null);
        btn_cancelar_clase.setBackground(new Color(46, 56, 64));
        btn_cancelar_clase.setBounds(466, 565, 89, 23);
        add(btn_cancelar_clase);

        
        JButton btn_eliminar_planes_1_1 = new JButton("Buscar");
        btn_eliminar_planes_1_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		llenarTabla_entrenador();
        	}
        });
        btn_eliminar_planes_1_1.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_1_1.setBorder(null);
        btn_eliminar_planes_1_1.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_1_1.setBounds(500, 242, 89, 23);
        add(btn_eliminar_planes_1_1);
        
        btn_eliminar_planes_1_2 = new JButton("Buscar");
        btn_eliminar_planes_1_2.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes_1_2.setBorder(null);
        btn_eliminar_planes_1_2.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_1_2.setBounds(480, 467, 89, 23);
        add(btn_eliminar_planes_1_2);
        
        cb_id_entrenador = new JComboBox();
        cb_id_entrenador.setBounds(289, 509, 75, 22);
        add(cb_id_entrenador);
        
        JLabel lblNewLabel_8_1 = new JLabel("Cedula");
        lblNewLabel_8_1.setBounds(282, 289, 69, 14);
        add(lblNewLabel_8_1);
        
        txt_cedula_entrenador = new JTextField();
        txt_cedula_entrenador.setEnabled(false);
        txt_cedula_entrenador.setColumns(10);
        txt_cedula_entrenador.setBounds(267, 302, 214, 20);
        add(txt_cedula_entrenador);
        
    }
private Plan llenarPlan_() {
		
		
		return new Plan(txt_buscar_planes.getText());
		
	}
public  void llenarTabla_plan() {
	 PlanController planController= new PlanController();
	Plan plan = llenarPlan_();
	String[] cabeceras = {"Codigo","Nombre","Precio","Descripcion","Duracion"};
	
	modelo = new DefaultTableModel(planController.consulta(plan),cabeceras);
	tabla_planes.setModel(modelo);
	llenar_modelo_box();
	}
public  void llenarTabla_entrenador() {
	 EntrenadorController entrenadorController= new EntrenadorController();
	Entrenador entrenador = llenarEntrenador();
	String[] cabeceras = {"Codigo","Nombre","Precio","Descripcion","Duracion"};
	
	modelo_entrenador = new DefaultTableModel(entrenadorController.consulta(entrenador),cabeceras);
	table_entrenador.setModel(modelo_entrenador);
	}
private Plan llenarRegistro_id() {
	
	return new Plan(
			codigo,
			txt_nombre_planes.getText(),
			precio_plan,
			txt_descripcion_planes.getText(),
			seleccion_planes
			
			
			);
}
private Entrenador llenarRegistro_ID() {
	
	return new Entrenador(
			codigo_entrenador,
			txt_nombre_entrenador.getText(),
			txt_apellido_entrenador.getText(),
			seleccion_entrenador,
			txt_correo_entrenador.getText(),
			txt_telefono_entrenador.getText(),
			txt_cedula_entrenador.getText()
			);
}
public void tabal_plan() {
	
	if(rdbtn_anual_planes.isSelected()) {
		seleccion_planes = rdbtn_anual_planes.getText();
	}else if(rdbtn_mensual_planes.isSelected()) {
		seleccion_planes = rdbtn_mensual_planes.getText();
	}else if (rdbtn_diario_planes.isSelected()) {
		seleccion_planes = rdbtn_diario_planes.getText();
	}
	precio_plan = Float.parseFloat(txt_precio_planes.getText());
}
public void tabal_entrenador() {
	
	if(rdbtn_sexo_hombre_entrenador.isSelected()) {
		seleccion_entrenador = rdbtn_sexo_hombre_entrenador.getText();
	}else if(rdbtn_sexo_mujer_entrenador.isSelected()) {
		seleccion_entrenador = rdbtn_sexo_mujer_entrenador.getText();
	}
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

public void  limpiar_planes() {
	
	txt_nombre_planes.setText("");
	txt_precio_planes.setText("");
	txt_descripcion_planes.setText("");
	rdbtn_anual_planes.setSelected(false);
	rdbtn_mensual_planes.setSelected(false);
	rdbtn_diario_planes.setSelected(false);
}
public void  abotones_planes(){
	btn_modificar_planes.setEnabled(true);
	btn_agregar_planes.setEnabled(true);
	btn_eliminar_planes.setEnabled(true);
	btn_nuevo_planes.setEnabled(true);
	
	
}
public void  dbotones_planes(){
	btn_modificar_planes.setEnabled(false);
	btn_agregar_planes.setEnabled(false);
	btn_eliminar_planes.setEnabled(false);
	btn_nuevo_planes.setEnabled(false);
	
}
public void  atextos_planes(){
	rdbtn_mensual_planes.setEnabled(true);
	rdbtn_diario_planes.setEnabled(true);
	rdbtn_anual_planes.setEnabled(true);
	txt_nombre_planes.setEnabled(true);
	txt_descripcion_planes.setEnabled(true);
	txt_precio_planes.setEnabled(true);
}
public void  dtextos_planes(){
	rdbtn_mensual_planes.setEnabled(false);
	rdbtn_diario_planes.setEnabled(false);
	rdbtn_anual_planes.setEnabled(false);
	txt_nombre_planes.setEnabled(false);
	txt_descripcion_planes.setEnabled(false);
	txt_precio_planes.setEnabled(false);
}
public void llenar_modelo_box() {
	 EntrenadorController entrenadorController= new EntrenadorController();
	 Entrenador entrenador = llenarEntrenador();
	  id_entrenadores = new String[entrenadorController.consulta_id_nombres_entrenador(entrenador).length];
	 for(int i=0;i<entrenadorController.consulta_id_nombres_entrenador(entrenador).length;i++) {
			id_entrenadores[i]=entrenadorController.consulta_id_nombres_entrenador(entrenador)[i][1];
			System.out.println("id del entrenador"+id_entrenadores[i]);
		}
	 
	 nombre_entrenadores= new String[entrenadorController.consulta_id_nombres_entrenador(entrenador).length];
	for(int i=0;i<entrenadorController.consulta_id_nombres_entrenador(entrenador).length;i++) {
		nombre_entrenadores[i]=entrenadorController.consulta_id_nombres_entrenador(entrenador)[i][1];
		System.out.println("id del entrenador"+nombre_entrenadores[i]);
	}
	
	modelo_combo_entrenador = new DefaultComboBoxModel<String>(nombre_entrenadores);
	cb_id_entrenador.setModel(modelo_combo_entrenador);
	
}
}
