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

import java.awt.Font;

public class InicioSesionPanel extends JPanel {
    private RegistroFrame registroFrame;
    private JButton registrarButton;
    private JButton iniciarSesionButton;
    private JTextField textUsuario;
    private JTextField textContra;
    private AdministradorController administradorController;
    
    public InicioSesionPanel(RegistroFrame frame) {
        registroFrame = frame;
        AdministradorController administradorController = new AdministradorController();
        setSize(1280, 720);
        setBackground(Color.white);
        
        iniciarSesionButton = new JButton("Iniciar Sesion");
        iniciarSesionButton.setBounds(535, 229, 95, 23);
        iniciarSesionButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Administrador administrador = llenarAdministrador();
        		if(administradorController.login(usuario, contra)) {
					panelLogin.setVisible(false);
					JOptionPane.showMessageDialog(null, "Todo bien!");
				} else {
					JOptionPane.showMessageDialog(null, "Intruso");
					
				
					
					
					
					
				}
        		// Realizar las operaciones de inicio de sesión
        		if(administradorController.registrar(administrador)) {
        			JOptionPane.showMessageDialog(null, "Registrado con exito");
        		} else {
        			JOptionPane.showMessageDialog(null, "Error");
        		}
        		
        		// Abre la ventana del panel de administrador
        		AdminFrame adminFrame = new AdminFrame();
        		adminFrame.setVisible(true);
        		
        		registroFrame.dispose(); // Cierra el frame de registro
        	}
        });
        
        registrarButton = new JButton("Registrarse");
        registrarButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
        registrarButton.setBounds(649, 267, 79, 14);
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	registroFrame.mostrarPanelRegistro();
            }
        });
        setLayout(null);
        

        
        add(registrarButton);
        add(iniciarSesionButton);
        
        JLabel lblIniciarSesion = new JLabel("INICIAR SESION");
        lblIniciarSesion.setBounds(535, 71, 95, 14);
        add(lblIniciarSesion);
        
        textUsuario = new JTextField();
        textUsuario.setColumns(10);
        textUsuario.setBounds(438, 111, 289, 35);
        add(textUsuario);
        
        JLabel lblCorreo = new JLabel("Email");
        lblCorreo.setBounds(438, 96, 63, 14);
        add(lblCorreo);
        
        textContra = new JTextField();
        textContra.setColumns(10);
        textContra.setBounds(438, 173, 289, 35);
        add(text Contra);
        
        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setBounds(438, 157, 63, 14);
        add(lblContrasea);
    }
}
