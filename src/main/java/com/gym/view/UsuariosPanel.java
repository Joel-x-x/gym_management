package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UsuariosPanel extends JPanel {
	private int administrador_id;
	
	private static final long serialVersionUID = 6939534476162663420L;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCedula;
	private JTextField textTelefono;
	private JTextField textNacimiento;
	private JTextField textDireccion;
	private JTextField textPeso;
	private JTextField textAltura;
	private JTextField textBuscar;
	private JTable tableUsuarios;
	private JTable tableFisico;
	private JScrollPane scrollPane;
	private JRadioButton radioHombre;
	private JRadioButton radioFactory;
	private JButton btnAgregarUsuario;
	private JButton btnModificarUsuario;
	private JButton btnEliminarUsuario;
	private JButton btnAgregarFisico;
	private JButton btnModificarFisico;
	private JButton btnEliminarFisico;
	private DefaultTableModel modelo;
	private UsuarioController usuarioController;
	
	private void listar(int id) {
		String[] cabeceras = {"Id","Nombre","Apellido","Nacimiento","Sexo","Email","Cedula","Dirección","Teléfono"};
		
		modelo = new DefaultTableModel(usuarioController.listar(administrador_id), cabeceras);
		tableUsuarios.setModel(modelo);
	}
	
	// Constructor
	public UsuariosPanel(int panelAncho, int panelAlto) {
		
		administrador_id = new Administrador().getId();
		usuarioController = new UsuarioController();
		
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
        
        textNombre = new JTextField();
        textNombre.setColumns(10);
        textNombre.setBounds(30, 90, 225, 25);
        add(textNombre);
        
        JLabel lblNewLabel_1_1 = new JLabel("Apellido");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(280, 74, 75, 14);
        add(lblNewLabel_1_1);
        
        textApellido = new JTextField();
        textApellido.setColumns(10);
        textApellido.setBounds(280, 90, 225, 25);
        add(textApellido);
        
        JLabel lblNewLabel_1_2 = new JLabel("Cedula");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2.setBounds(530, 74, 75, 14);
        add(lblNewLabel_1_2);
        
        textCedula = new JTextField();
        textCedula.setColumns(10);
        textCedula.setBounds(530, 90, 225, 25);
        add(textCedula);
        
        JLabel lblNewLabel_1_3 = new JLabel("Teléfono");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_3.setBounds(30, 128, 75, 14);
        add(lblNewLabel_1_3);
        
        textTelefono = new JTextField();
        textTelefono.setColumns(10);
        textTelefono.setBounds(30, 144, 225, 25);
        add(textTelefono);
        
        JLabel lblNewLabel_9 = new JLabel("Sexo");
        lblNewLabel_9.setBounds(280, 126, 46, 14);
        add(lblNewLabel_9);
        
        radioHombre = new JRadioButton("Hombre");
        radioHombre.setBackground(Color.WHITE);
        radioHombre.setBounds(280, 141, 109, 23);
        add(radioHombre);
        
        radioFactory = new JRadioButton("Mujer");
        radioFactory.setBackground(Color.WHITE);
        radioFactory.setBounds(385, 141, 109, 23);
        add(radioFactory);
        
        JLabel lblDatosOpcionales = new JLabel("DATOS OPCIONALES");
        lblDatosOpcionales.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDatosOpcionales.setBounds(30, 187, 225, 37);
        add(lblDatosOpcionales);
        
        JLabel lblNewLabel_1_4 = new JLabel("Fecha de nacimiento");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4.setBounds(30, 235, 125, 14);
        add(lblNewLabel_1_4);
        
        textNacimiento = new JTextField();
        textNacimiento.setColumns(10);
        textNacimiento.setBounds(30, 251, 225, 25);
        add(textNacimiento);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Dirección");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1.setBounds(280, 235, 109, 14);
        add(lblNewLabel_1_1_1);
        
        textDireccion = new JTextField();
        textDireccion.setColumns(10);
        textDireccion.setBounds(280, 251, 225, 25);
        add(textDireccion);
        
        JLabel lblFsico = new JLabel("FÍSICO");
        lblFsico.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFsico.setBounds(530, 187, 225, 37);
        add(lblFsico);
        
        JLabel lblNewLabel_1_4_1 = new JLabel("Peso");
        lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_1.setBounds(530, 235, 125, 14);
        add(lblNewLabel_1_4_1);
        
        textPeso = new JTextField();
        textPeso.setColumns(10);
        textPeso.setBounds(530, 251, 225, 25);
        add(textPeso);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Altura");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1_1.setBounds(780, 235, 109, 14);
        add(lblNewLabel_1_1_1_1);
        
        textAltura = new JTextField();
        textAltura.setColumns(10);
        textAltura.setBounds(780, 251, 225, 25);
        add(textAltura);
        
        btnAgregarUsuario = new JButton("Agregar");
        btnAgregarUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnAgregarUsuario.setForeground(new Color(255, 255, 255));
        btnAgregarUsuario.setBorder(null);
        btnAgregarUsuario.setBackground(new Color(46, 56, 64));
        btnAgregarUsuario.setBounds(30, 309, 150, 30);
        add(btnAgregarUsuario);
        
        btnModificarUsuario = new JButton("Modificar");
        btnModificarUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificarUsuario.setForeground(new Color(255, 255, 255));
        btnModificarUsuario.setBorder(null);
        btnModificarUsuario.setBackground(new Color(46, 56, 64));
        btnModificarUsuario.setBounds(190, 309, 150, 30);
        add(btnModificarUsuario);
        
        btnEliminarUsuario = new JButton("Eliminar");
        btnEliminarUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnEliminarUsuario.setForeground(new Color(255, 255, 255));
        btnEliminarUsuario.setBorder(null);
        btnEliminarUsuario.setBackground(new Color(46, 56, 64));
        btnEliminarUsuario.setBounds(350, 309, 150, 30);
        add(btnEliminarUsuario);
        
        btnAgregarFisico = new JButton("Agregar");
        btnAgregarFisico.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregarFisico.setForeground(new Color(255, 255, 255));
        btnAgregarFisico.setBorder(null);
        btnAgregarFisico.setBackground(new Color(46, 56, 64));
        btnAgregarFisico.setBounds(632, 309, 89, 30);
        add(btnAgregarFisico);
        
        btnModificarFisico = new JButton("Modificar");
        btnModificarFisico.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificarFisico.setForeground(new Color(255, 255, 255));
        btnModificarFisico.setBorder(null);
        btnModificarFisico.setBackground(new Color(46, 56, 64));
        btnModificarFisico.setBounds(731, 309, 89, 30);
        add(btnModificarFisico);
        
        btnEliminarFisico = new JButton("Eliminar");
        btnEliminarFisico.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminarFisico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnEliminarFisico.setForeground(new Color(255, 255, 255));
        btnEliminarFisico.setBorder(null);
        btnEliminarFisico.setBackground(new Color(46, 56, 64));
        btnEliminarFisico.setBounds(830, 309, 89, 30);
        add(btnEliminarFisico);
        
        scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setAutoscrolls(true);
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBounds(30, 430, 583, 248);
        add(scrollPane);
        
        tableUsuarios = new JTable();
        tableUsuarios.setBackground(new Color(255, 255, 255));
        scrollPane.setViewportView(tableUsuarios);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(null);
        scrollPane_1.setAutoscrolls(true);
        scrollPane_1.setBounds(632, 430, 422, 248);
        add(scrollPane_1);
        
        tableFisico = new JTable();
        tableFisico.setBackground(new Color(255, 255, 255));
        scrollPane_1.setViewportView(tableFisico);
        
        textBuscar = new JTextField();
        textBuscar.setColumns(10);
        textBuscar.setBounds(71, 394, 137, 25);
        add(textBuscar);
        
        JLabel lblNewLabel_1_4_2 = new JLabel("Buscar:");
        lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_2.setBounds(30, 402, 53, 14);
        add(lblNewLabel_1_4_2);
        
        JLabel lblNewLabel_1_4_3 = new JLabel("dd-mm-yyyy");
        lblNewLabel_1_4_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_3.setBounds(30, 284, 125, 14);
        add(lblNewLabel_1_4_3);
        
        // Listar Usuarios
        listar(administrador_id);
    }

}
