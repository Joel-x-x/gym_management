package com.gym.view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.gym.controller.AdministradorController;
import com.gym.model.Administrador;

public class RegistroPanel extends JPanel {
	
	private AdministradorController administradorController;

    private RegistroFrame registroFrame;
    private JButton iniciarSesionButton;
    private JTextField textEmail;
    private JTextField textPassword;
    private JTextField textPasswordConfirm;
    private JTextField textClave;
    private JTextField textNombre;
    
    
    public RegistroPanel(RegistroFrame frame) {
        registroFrame = frame;
        AdministradorController administradorController = new AdministradorController();
        
        setSize(1280, 720);
        setBackground(Color.white);
        
        iniciarSesionButton = new JButton("Iniciar Sesion");
        iniciarSesionButton.setBounds(497, 472, 95, 23);
        iniciarSesionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registroFrame.mostrarPanelInicioSesion(); // Cambia al panel de inicio de sesión
            }
        });
        setLayout(null);
        
        add(iniciarSesionButton);
        
        JLabel lblNewLabel = new JLabel("Registrarse");
        lblNewLabel.setBounds(594, 90, 79, 14);
        add(lblNewLabel);
        
        textNombre = new JTextField();
        textNombre.setBounds(497, 148, 289, 35);
        add(textNombre);
        textNombre.setColumns(10);
        
        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(497, 218, 289, 35);
        add(textEmail);
        
        textPassword = new JTextField();
        textPassword.setColumns(10);
        textPassword.setBounds(497, 291, 289, 35);
        add(textPassword);
        
        textPasswordConfirm = new JTextField();
        textPasswordConfirm.setColumns(10);
        textPasswordConfirm.setBounds(497, 360, 289, 35);
        add(textPasswordConfirm);
        
        JButton btnNewButton = new JButton("Registrarse");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Administrador administrador = llenarAdministrador();
        		
        		if(administradorController.registrar(administrador)) {
        			JOptionPane.showMessageDialog(null, "Registrado con exito");
        		} else {
        			JOptionPane.showMessageDialog(null, "Error");
        		}
        	}
        });
        btnNewButton.setBounds(606, 523, 89, 23);
        add(btnNewButton);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(497, 132, 63, 14);
        add(lblNombre);
        
        JLabel lblCorreo = new JLabel("Email");
        lblCorreo.setBounds(497, 203, 63, 14);
        add(lblCorreo);
        
        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setBounds(497, 275, 63, 14);
        add(lblContrasea);
        
        JLabel lblClave = new JLabel("Confirmar Contraseña");
        lblClave.setBounds(497, 346, 111, 14);
        add(lblClave);
        
        textClave = new JTextField();
        textClave.setColumns(10);
        textClave.setBounds(497, 426, 289, 35);
        add(textClave);
        
        JLabel lblClave_1 = new JLabel("Clave");
        lblClave_1.setBounds(497, 412, 63, 14);
        add(lblClave_1);
    }
    
    public Administrador llenarAdministrador() {
    	return new Administrador(
    			textNombre.getText(),
    			textEmail.getText(),
    			textPassword.getText(),
    			0,
    			0,
    			textClave.getText());
    }
    
    
}
