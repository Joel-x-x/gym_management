package com.gym.view;

import javax.swing.*;

import com.gym.model.Administrador;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuariosPanel extends JPanel {
	private int administrador_id = new Administrador().getId();
	
	private static final long serialVersionUID = 6939534476162663420L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table;
	private JTable table_1;

	public UsuariosPanel(int panelAncho, int panelAlto) {
		setFocusTraversalPolicyProvider(true);
    	
		setPreferredSize(new Dimension(1080, 800));
        setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("USUARIOS");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(30, 30, 147, 37);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nombre");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1.setBounds(30, 74, 75, 14);
        add(lblNewLabel_1);
        
        textField = new JTextField();
        textField.setColumns(10);
        textField.setBounds(30, 90, 225, 25);
        add(textField);
        
        JLabel lblNewLabel_1_1 = new JLabel("Apellido");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(280, 74, 75, 14);
        add(lblNewLabel_1_1);
        
        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(280, 90, 225, 25);
        add(textField_1);
        
        JLabel lblNewLabel_1_2 = new JLabel("Cedula");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2.setBounds(530, 74, 75, 14);
        add(lblNewLabel_1_2);
        
        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(530, 90, 225, 25);
        add(textField_2);
        
        JLabel lblNewLabel_1_3 = new JLabel("Telefono");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_3.setBounds(30, 128, 75, 14);
        add(lblNewLabel_1_3);
        
        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(30, 144, 225, 25);
        add(textField_3);
        
        JLabel lblNewLabel_9 = new JLabel("Sexo");
        lblNewLabel_9.setBounds(280, 126, 46, 14);
        add(lblNewLabel_9);
        
        JRadioButton rdbtn_sexo_hombre_entrenador = new JRadioButton("Hombre");
        rdbtn_sexo_hombre_entrenador.setBackground(Color.WHITE);
        rdbtn_sexo_hombre_entrenador.setBounds(280, 141, 109, 23);
        add(rdbtn_sexo_hombre_entrenador);
        
        JRadioButton rdbtn_sexo_mujer_entrenador = new JRadioButton("Mujer");
        rdbtn_sexo_mujer_entrenador.setBackground(Color.WHITE);
        rdbtn_sexo_mujer_entrenador.setBounds(385, 141, 109, 23);
        add(rdbtn_sexo_mujer_entrenador);
        
        JLabel lblDatosOpcionales = new JLabel("DATOS OPCIONALES");
        lblDatosOpcionales.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDatosOpcionales.setBounds(30, 187, 225, 37);
        add(lblDatosOpcionales);
        
        JLabel lblNewLabel_1_4 = new JLabel("Fecha de nacimiento");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4.setBounds(30, 235, 125, 14);
        add(lblNewLabel_1_4);
        
        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBounds(30, 251, 225, 25);
        add(textField_4);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Dirección");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1.setBounds(280, 235, 109, 14);
        add(lblNewLabel_1_1_1);
        
        textField_5 = new JTextField();
        textField_5.setColumns(10);
        textField_5.setBounds(280, 251, 225, 25);
        add(textField_5);
        
        JLabel lblFsico = new JLabel("FÍSICO");
        lblFsico.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFsico.setBounds(530, 187, 225, 37);
        add(lblFsico);
        
        JLabel lblNewLabel_1_4_1 = new JLabel("Peso");
        lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_1.setBounds(530, 235, 125, 14);
        add(lblNewLabel_1_4_1);
        
        textField_6 = new JTextField();
        textField_6.setColumns(10);
        textField_6.setBounds(530, 251, 225, 25);
        add(textField_6);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Altura");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1_1.setBounds(780, 235, 109, 14);
        add(lblNewLabel_1_1_1_1);
        
        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(780, 251, 225, 25);
        add(textField_7);
        
        JButton btn_agregar_planes = new JButton("Agregar");
        btn_agregar_planes.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_agregar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_agregar_planes.setForeground(new Color(255, 255, 255));
        btn_agregar_planes.setBorder(null);
        btn_agregar_planes.setBackground(new Color(46, 56, 64));
        btn_agregar_planes.setBounds(30, 309, 150, 30);
        add(btn_agregar_planes);
        
        JButton btn_modificar_planes = new JButton("Modificar");
        btn_modificar_planes.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_modificar_planes.setForeground(new Color(255, 255, 255));
        btn_modificar_planes.setBorder(null);
        btn_modificar_planes.setBackground(new Color(46, 56, 64));
        btn_modificar_planes.setBounds(190, 309, 150, 30);
        add(btn_modificar_planes);
        
        JButton btn_eliminar_planes = new JButton("Eliminar");
        btn_eliminar_planes.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_eliminar_planes.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_eliminar_planes.setForeground(new Color(255, 255, 255));
        btn_eliminar_planes.setBorder(null);
        btn_eliminar_planes.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes.setBounds(350, 309, 150, 30);
        add(btn_eliminar_planes);
        
        JButton btn_agregar_planes_1 = new JButton("Agregar");
        btn_agregar_planes_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_agregar_planes_1.setForeground(new Color(255, 255, 255));
        btn_agregar_planes_1.setBorder(null);
        btn_agregar_planes_1.setBackground(new Color(46, 56, 64));
        btn_agregar_planes_1.setBounds(632, 309, 89, 30);
        add(btn_agregar_planes_1);
        
        JButton btn_modificar_planes_1 = new JButton("Modificar");
        btn_modificar_planes_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_modificar_planes_1.setForeground(new Color(255, 255, 255));
        btn_modificar_planes_1.setBorder(null);
        btn_modificar_planes_1.setBackground(new Color(46, 56, 64));
        btn_modificar_planes_1.setBounds(731, 309, 89, 30);
        add(btn_modificar_planes_1);
        
        JButton btn_eliminar_planes_1 = new JButton("Eliminar");
        btn_eliminar_planes_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_eliminar_planes_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_eliminar_planes_1.setForeground(new Color(255, 255, 255));
        btn_eliminar_planes_1.setBorder(null);
        btn_eliminar_planes_1.setBackground(new Color(46, 56, 64));
        btn_eliminar_planes_1.setBounds(830, 309, 89, 30);
        add(btn_eliminar_planes_1);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setAutoscrolls(true);
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBounds(30, 430, 583, 248);
        add(scrollPane);
        
        table = new JTable();
        table.setBackground(new Color(255, 255, 255));
        scrollPane.setViewportView(table);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(null);
        scrollPane_1.setAutoscrolls(true);
        scrollPane_1.setBounds(632, 430, 422, 248);
        add(scrollPane_1);
        
        table_1 = new JTable();
        table_1.setBackground(new Color(255, 255, 255));
        scrollPane_1.setViewportView(table_1);
        
        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(71, 394, 137, 25);
        add(textField_8);
        
        JLabel lblNewLabel_1_4_2 = new JLabel("Buscar:");
        lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_2.setBounds(30, 402, 53, 14);
        add(lblNewLabel_1_4_2);
        
        JLabel lblNewLabel_1_4_3 = new JLabel("dd-mm-yyyy");
        lblNewLabel_1_4_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_3.setBounds(30, 284, 125, 14);
        add(lblNewLabel_1_4_3);
        
        
    }
}
