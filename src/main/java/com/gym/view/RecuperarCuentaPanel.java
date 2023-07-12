package com.gym.view;

import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import com.gym.controller.AdministradorController;
import com.gym.controller.RecuperacionCuentaController;
import com.gym.model.Administrador;
import com.gym.model.RecuperacionCuenta;
import com.gym.utilidades.Utilidades;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RecuperarCuentaPanel extends JPanel {
	private RegistroFrame registroFrame;
	private AdministradorController administradorController;
	RecuperacionCuentaController recuperacionCuentaController;

	private static final long serialVersionUID = 8077406384536139438L;
	private JTextField textEmail;
	private JTextField textColor;
	private JTextField textAmigo;
	private JTextField textMascota;
	private JButton btnVerificar;

	public void validar() {
		RecuperacionCuenta recuperacionCuenta = llenarClase();
		
		if(recuperacionCuenta.getAdministrador_id() == 0) {
			JOptionPane.showMessageDialog(null, "El correo no existe");
			return;
		}
		
		if(recuperacionCuentaController.validarDatos(recuperacionCuenta)) {
			registroFrame.mostrarCambiarPassword();
			new Administrador().setId(recuperacionCuenta.getAdministrador_id()); // Se agrega el id después de llamar al metodo
			limpiarFormulario();
		} else {
			JOptionPane.showMessageDialog(null, "Datos incorrectos");
		}
	}
	
	public void limpiarFormulario() {
		textEmail.setText("");
		textAmigo.setText("");
		textMascota.setText("");
		textColor.setText("");
	}
	
	public RecuperacionCuenta llenarClase() {
		return new RecuperacionCuenta(
				textEmail.getText(),
				textAmigo.getText(),
				textMascota.getText(),
				textColor.getText(),
				administradorController.consultarId(textEmail.getText()));
	}
	
	public RecuperarCuentaPanel(RegistroFrame frame) {
        this.registroFrame = frame;
        administradorController = new AdministradorController();
        recuperacionCuentaController = new RecuperacionCuentaController();
        
        setSize(1280, 720);
        setBackground(Color.white);
        setLayout(null);
        
        JLabel lblRegistro = new JLabel("Recuperar Cuenta");
        lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistro.setForeground(new Color(163, 175, 175));
        lblRegistro.setFont(new Font("Dialog", Font.BOLD, 35));
        lblRegistro.setBounds(465, 94, 350, 50);
        add(lblRegistro);
        
        JLabel lblCorreo = new JLabel("Email");
        lblCorreo.setForeground(new Color(163, 175, 175));
        lblCorreo.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo.setBounds(465, 192, 73, 30);
        add(lblCorreo);
        
        textEmail = new JTextField();
        textEmail.setFont(new Font("Dialog", Font.PLAIN, 20));
        textEmail.setColumns(10);
        textEmail.setBounds(465, 222, 350, 40);
        add(textEmail);
        
        btnVerificar = new JButton("Verificar");
        btnVerificar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		validar();
        	}
        });
        btnVerificar.setForeground(Color.BLACK);
        btnVerificar.setFont(new Font("Dialog", Font.BOLD, 20));
        btnVerificar.setFocusPainted(false);
        btnVerificar.setBorderPainted(false);
        btnVerificar.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnVerificar.setBackground(new Color(163, 175, 175));
        btnVerificar.setBounds(549, 550, 200, 40);
        add(btnVerificar);
        
        JLabel labelIniciarSesion = new JLabel("IniciarSesion ");
        labelIniciarSesion.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		registroFrame.mostrarPanelInicioSesion();
        	}

        });
        labelIniciarSesion.setHorizontalAlignment(SwingConstants.RIGHT);
        labelIniciarSesion.setForeground(new Color(163, 175, 175));
        labelIniciarSesion.setFont(new Font("Dialog", Font.BOLD, 15));
        labelIniciarSesion.setBounds(700, 516, 115, 23);
        add(labelIniciarSesion);
        
        textColor = new JTextField();
        textColor.setFont(new Font("Dialog", Font.PLAIN, 20));
        textColor.setColumns(10);
        textColor.setBounds(465, 465, 350, 40);
        add(textColor);
        
        JLabel lblCorreo_1 = new JLabel("Ingresa tu color favorito");
        lblCorreo_1.setForeground(new Color(163, 175, 175));
        lblCorreo_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo_1.setBounds(465, 435, 350, 30);
        add(lblCorreo_1);
        
        JLabel lblCorreo_1_1 = new JLabel("Ingresa el nombre de tu mejor amigo");
        lblCorreo_1_1.setForeground(new Color(163, 175, 175));
        lblCorreo_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo_1_1.setBounds(465, 273, 350, 30);
        add(lblCorreo_1_1);
        
        textAmigo = new JTextField();
        textAmigo.setFont(new Font("Dialog", Font.PLAIN, 20));
        textAmigo.setColumns(10);
        textAmigo.setBounds(465, 303, 350, 40);
        add(textAmigo);
        
        JLabel lblCorreo_1_1_1 = new JLabel("Ingresa el nombre de tu mascota");
        lblCorreo_1_1_1.setForeground(new Color(163, 175, 175));
        lblCorreo_1_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo_1_1_1.setBounds(465, 354, 350, 30);
        add(lblCorreo_1_1_1);
        
        textMascota = new JTextField();
        textMascota.setFont(new Font("Dialog", Font.PLAIN, 20));
        textMascota.setColumns(10);
        textMascota.setBounds(465, 384, 350, 40);
        add(textMascota);

	}
}
