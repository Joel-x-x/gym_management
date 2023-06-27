package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.MembresiaController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Clase;
import com.gym.model.Plan;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MembresiasPanel extends JPanel {
	private int administrador_id;	
	private DefaultTableModel modelo;
	private UsuarioController usuarioController;
	private MembresiaController membresiaController;
	private JComboBox<Plan> comboBoxPlan = new JComboBox<>();
	private DefaultComboBoxModel<Plan> comboBoxModelPlan = new DefaultComboBoxModel<>();
	private JComboBox<Clase> comboBoxClase = new JComboBox<>();
	private DefaultComboBoxModel<Clase> comboBoxModelClase = new DefaultComboBoxModel<>();
	
	private static final long serialVersionUID = -6442770399461125032L;
	private JTextField textValorExtra;
	private JTextField textBuscar;
	private JTable tableMembresias;
	private JTable tableUsuarios;
	private JLabel labelTotal;
	private JButton btnBuscar;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	private void listarUsuarios() {
		String[] cabeceras = {"Id","Nombre","Apellido","Nacimiento","Sexo","Email","Cedula","Dirección","Teléfono"};
		
		modelo = new DefaultTableModel(usuarioController.listar(administrador_id), cabeceras);
		tableUsuarios.setModel(modelo);
	}
	
	private void listarPlan() {
		
		comboBoxModelPlan.addAll(membresiaController.listarPlan(administrador_id));
		
		comboBoxPlan.setModel(comboBoxModelPlan);
	}
	
	private void listarClase() {
		comboBoxModelClase.addAll(membresiaController.listarClase(administrador_id));
		
		comboBoxClase.setModel(comboBoxModelClase);
	}
	
    public MembresiasPanel(int panelAncho, int panelAlto) {
    	
    	setPreferredSize(new Dimension(1080, 800));

        setBackground(Color.WHITE);
        setLayout(null);
        
		administrador_id = new Administrador().getId();
		usuarioController = new UsuarioController();
		membresiaController = new MembresiaController();
		        
        comboBoxPlan.setBounds(30, 99, 235, 25);
        add(comboBoxPlan);
        
        comboBoxClase.setBounds(304, 99, 218, 25);
        add(comboBoxClase);
        
        JLabel lblNewLabel = new JLabel("MEMBRESIAS");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(30, 30, 150, 33);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Plan");
        lblNewLabel_1.setBounds(30, 84, 46, 14);
        add(lblNewLabel_1);
        
        textValorExtra = new JTextField();
        textValorExtra.setBounds(30, 161, 235, 25);
        add(textValorExtra);
        textValorExtra.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Valor Extra");
        lblNewLabel_2.setBounds(30, 146, 72, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Clase");
        lblNewLabel_3.setBounds(304, 84, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("VALOR TOTAL");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_4.setBounds(198, 188, 164, 64);
        add(lblNewLabel_4);
        
        labelTotal = new JLabel("0");
        labelTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
        labelTotal.setBounds(240, 238, 101, 46);
        add(labelTotal);
        
        btnAgregar = new JButton("Agregar");
        btnAgregar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		var plan = (Plan) comboBoxPlan.getSelectedItem();
        		System.out.println(plan.getPrecio());
        	}
        });
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregar.setBorder(null);
        btnAgregar.setBackground(new Color(46, 56, 64));
        btnAgregar.setBounds(30, 309, 150, 30);
        add(btnAgregar);
        
        btnModificar = new JButton("Modificar");
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificar.setBorder(null);
        btnModificar.setBackground(new Color(46, 56, 64));
        btnModificar.setBounds(198, 309, 150, 30);
        add(btnModificar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminar.setBorder(null);
        btnEliminar.setBackground(new Color(46, 56, 64));
        btnEliminar.setBounds(370, 309, 150, 30);
        add(btnEliminar);
        
        JLabel lblNewLabel_5 = new JLabel("Buscar:");
        lblNewLabel_5.setBounds(30, 394, 46, 14);
        add(lblNewLabel_5);
        
        textBuscar = new JTextField();
        textBuscar.setBounds(72, 389, 213, 25);
        add(textBuscar);
        textBuscar.setColumns(10);
        
        JScrollPane scrollPane_membresias_membresias = new JScrollPane();
        scrollPane_membresias_membresias.setBounds(532, 41, 468, 233);
        add(scrollPane_membresias_membresias);
        
        tableMembresias = new JTable();
        scrollPane_membresias_membresias.setViewportView(tableMembresias);
        
        JScrollPane scrollPane_usuarios_membresias = new JScrollPane();
        scrollPane_usuarios_membresias.setBounds(30, 424, 970, 292);
        add(scrollPane_usuarios_membresias);
        
        tableUsuarios = new JTable();
        scrollPane_usuarios_membresias.setViewportView(tableUsuarios);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(new Color(46, 56, 64));
        btnBuscar.setBounds(295, 389, 150, 25);
        add(btnBuscar);
        
        // Listar Usuarios
        listarUsuarios();
        
        listarPlan();
        listarClase();
    }
}
