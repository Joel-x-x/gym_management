package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.PlanController;
import com.gym.model.Plan;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
    private JTextField txt_cedula_entrenador_clase;
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
    private JTable tabla_entrenador;
    private JTable tabla_clase;
    private JButton btn_agregar_clase;
    private JButton btn_modificar_clase;
    private JButton btn_eliminar_clase;
    private DefaultTableModel modelo;
    private PlanController planController;
    
    public void llenar_tabla() {
		String cabeceras[] = {"id","nombre","precio","descripción","duración"};
		
		
	}
    

    public Plan llenarPlan() {
    	String seleccion = "";
		if(rdbtn_anual_planes.isSelected()) {
			seleccion = rdbtn_anual_planes.getText();
		}else if(rdbtn_mensual_planes.isSelected()) {
			seleccion = rdbtn_mensual_planes.getText();
		}else if (rdbtn_diario_planes.isSelected()) {
			seleccion = rdbtn_diario_planes.getText();
		}
		Float precio_plan = Float.parseFloat(txt_precio_planes.getText());
		
		return new Plan(txt_nombre_planes.getText(),
						precio_plan,
						txt_descripcion_planes.getText(),
						seleccion);
	}

	public PlanesPanel(int panelAncho, int panelAlto) {
		
		PlanController planController = new PlanController();
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
        txt_nombre_planes.setBounds(21, 54, 225, 20);
        add(txt_nombre_planes);
        txt_nombre_planes.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Descripción");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2.setBounds(21, 84, 93, 14);
        add(lblNewLabel_2);
        
        txt_descripcion_planes = new JTextField();
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
        txt_precio_planes.setBounds(289, 54, 109, 20);
        add(txt_precio_planes);
        txt_precio_planes.setColumns(10);
        
        rdbtn_anual_planes = new JRadioButton("Anual");
        rdbtn_anual_planes.setBounds(289, 98, 109, 23);
        
        add(rdbtn_anual_planes);
        
        rdbtn_diario_planes = new JRadioButton("Diario");
        rdbtn_diario_planes.setBounds(289, 118, 109, 23);
        add(rdbtn_diario_planes);
        
        rdbtn_mensual_planes = new JRadioButton("Mensual");
        rdbtn_mensual_planes.setBounds(394, 98, 109, 23);
        add(rdbtn_mensual_planes);
        
        btn_agregar_planes = new JButton("Agregar");
        btn_agregar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Plan plan_llenado = llenarPlan();
        		
        		if(planController.registrar(plan_llenado)) {
        			JOptionPane.showMessageDialog(null, "Se ha registrado el plan");
        		}else {
        			JOptionPane.showMessageDialog(null, "Ha ocurrido un error");
        		}
        		
        	}
        });
        btn_agregar_planes.setBounds(21, 177, 89, 23);
        btn_agregar_planes.setBackground(new Color(46, 56, 64));
        btn_agregar_planes.setForeground(new Color(163, 175, 175));
        btn_agregar_planes.setBorder(null);
        add(btn_agregar_planes);
        
        btn_modificar_planes = new JButton("Modificar");
        btn_modificar_planes.setBounds(157, 177, 89, 23);
        btn_modificar_planes.setBackground(new Color(46, 56, 64));
        btn_modificar_planes.setForeground(new Color(163, 175, 175));
        btn_modificar_planes.setBorder(null);
        add(btn_modificar_planes);
        
        btn_eliminar_planes = new JButton("Eliminar");
        btn_eliminar_planes.setBounds(289, 177, 89, 23);
        btn_eliminar_planes.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes.setForeground(new Color(163, 175, 175));
        btn_eliminar_planes.setBorder(null);
        add(btn_eliminar_planes);
        
        JLabel lblEntrenador = new JLabel("ENTRENADOR");
        lblEntrenador.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblEntrenador.setBounds(21, 211, 160, 43);
        add(lblEntrenador);
        
        JLabel lblNewLabel_3_1 = new JLabel("Buscar");
        lblNewLabel_3_1.setBounds(466, 246, 46, 14);
        add(lblNewLabel_3_1);
        
        txt_buscar_entrenador = new JTextField();
        txt_buscar_entrenador.setBounds(432, 265, 137, 20);
        add(txt_buscar_entrenador);
        txt_buscar_entrenador.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nombre");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(21, 246, 75, 14);
        add(lblNewLabel_1_1);
        
        txt_nombre_entrenador = new JTextField();
        txt_nombre_entrenador.setBounds(21, 265, 225, 20);
        add(txt_nombre_entrenador);
        txt_nombre_entrenador.setColumns(10);
        
        JLabel lblNewLabel_6 = new JLabel("Teléfono");
        lblNewLabel_6.setBounds(21, 289, 75, 14);
        add(lblNewLabel_6);
        
        txt_telefono_entrenador = new JTextField();
        txt_telefono_entrenador.setBounds(21, 302, 225, 20);
        add(txt_telefono_entrenador);
        txt_telefono_entrenador.setColumns(10);
        
        JLabel lblNewLabel_7 = new JLabel("Correo Electrónico");
        lblNewLabel_7.setBounds(21, 327, 160, 14);
        add(lblNewLabel_7);
        
        txt_correo_entrenador = new JTextField();
        txt_correo_entrenador.setBounds(21, 343, 225, 20);
        add(txt_correo_entrenador);
        txt_correo_entrenador.setColumns(10);
        
        JLabel lblNewLabel_8 = new JLabel("Apellido");
        lblNewLabel_8.setBounds(289, 278, 69, 14);
        add(lblNewLabel_8);
        
        txt_apellido_entrenador = new JTextField();
        txt_apellido_entrenador.setBounds(289, 302, 214, 20);
        add(txt_apellido_entrenador);
        txt_apellido_entrenador.setColumns(10);
        
        JLabel lblNewLabel_9 = new JLabel("Sexo");
        lblNewLabel_9.setBounds(289, 327, 46, 14);
        add(lblNewLabel_9);
        
        JRadioButton rdbtn_sexo_hombre_entrenador = new JRadioButton("Hombre");
        rdbtn_sexo_hombre_entrenador.setBounds(289, 342, 109, 23);
        add(rdbtn_sexo_hombre_entrenador);
        
        JRadioButton rdbtn_sexo_mujer_entrenador = new JRadioButton("Mujer");
        rdbtn_sexo_mujer_entrenador.setBounds(394, 342, 109, 23);
        add(rdbtn_sexo_mujer_entrenador);
        
        btn_agregar_entrenador = new JButton("Agregar");
        btn_agregar_entrenador.setBounds(21, 374, 89, 23);
        btn_agregar_entrenador.setBackground(new Color(46, 56, 64));
        btn_agregar_entrenador.setForeground(new Color(163, 175, 175));
        btn_agregar_entrenador.setBorder(null);
        add(btn_agregar_entrenador);
        
        btn_modificar_entrenador = new JButton("Modificar");
        btn_modificar_entrenador.setBounds(157, 374, 89, 23);
        btn_modificar_entrenador.setBackground(new Color(46, 56, 64));
        btn_modificar_entrenador.setForeground(new Color(163, 175, 175));
        btn_modificar_entrenador.setBorder(null);
        add(btn_modificar_entrenador);
        
        btn_eliminar_entrenador = new JButton("Eliminar");
        btn_eliminar_entrenador.setBounds(289, 374, 89, 23);
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
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Cédula Entrenador");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_2.setBounds(291, 486, 168, 14);
        add(lblNewLabel_1_1_2);
        
        txt_cedula_entrenador_clase = new JTextField();
        txt_cedula_entrenador_clase.setBounds(292, 510, 211, 20);
        add(txt_cedula_entrenador_clase);
        txt_cedula_entrenador_clase.setColumns(10);
        
        JLabel lblNewLabel_3_1_1 = new JLabel("Buscar");
        lblNewLabel_3_1_1.setBounds(457, 452, 46, 14);
        add(lblNewLabel_3_1_1);
        
        txt_buscar_clase = new JTextField();
        txt_buscar_clase.setBounds(432, 468, 137, 20);
        add(txt_buscar_clase);
        txt_buscar_clase.setColumns(10);
        
        btn_agregar_clase = new JButton("Agregar");
        btn_agregar_clase.setBounds(21, 565, 89, 23);
        btn_agregar_clase.setBackground(new Color(46, 56, 64));
        btn_agregar_clase.setForeground(new Color(163, 175, 175));
        btn_agregar_clase.setBorder(null);
        add(btn_agregar_clase);
        
        btn_modificar_clase = new JButton("Modificar");
        btn_modificar_clase.setBounds(157, 565, 89, 23);
        btn_modificar_clase.setBackground(new Color(46, 56, 64));
        btn_modificar_clase.setForeground(new Color(163, 175, 175));
        btn_modificar_clase.setBorder(null);
        add(btn_modificar_clase);
        
        btn_eliminar_clase = new JButton("Eliminar");
        btn_eliminar_clase.setBounds(289, 565, 89, 23);
        btn_eliminar_clase.setBackground(new Color(46, 56, 64));
        btn_eliminar_clase.setForeground(new Color(163, 175, 175));
        btn_eliminar_clase.setBorder(null);
        add(btn_eliminar_clase);
        
        JDesktopPane desktopPane_planes = new JDesktopPane();
        desktopPane_planes.setBounds(579, 11, 691, 210);
        add(desktopPane_planes);
        
        tabla_planes = new JTable();
        tabla_planes.setBounds(0, 0, 691, 210);
        desktopPane_planes.add(tabla_planes);
        
        JDesktopPane desktopPane_entrenador = new JDesktopPane();
        desktopPane_entrenador.setBounds(579, 232, 691, 210);
        add(desktopPane_entrenador);
        
        tabla_entrenador = new JTable();
        tabla_entrenador.setBounds(0, 0, 691, 210);
        desktopPane_entrenador.add(tabla_entrenador);
        
        JDesktopPane desktopPane_clase = new JDesktopPane();
        desktopPane_clase.setBounds(579, 449, 691, 210);
        add(desktopPane_clase);
        
        tabla_clase = new JTable();
        tabla_clase.setBounds(0, 0, 691, 210);
        desktopPane_clase.add(tabla_clase);
        
    }
	
}
