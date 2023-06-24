package com.gym.view;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InicioSesionPanel extends JPanel {
    private RegistroFrame registroFrame;
    private JButton registrarButton;
    private JButton iniciarSesionButton;
    
    public InicioSesionPanel(RegistroFrame frame) {
        registroFrame = frame;
        
        setSize(1280, 720);
        setBackground(Color.white);
        
        iniciarSesionButton = new JButton("Iniciar Sesion");
        iniciarSesionButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		// Realizar las operaciones de inicio de sesión
        		
        		// Abre la ventana del panel de administrador
        		AdminFrame adminFrame = new AdminFrame();
        		adminFrame.setVisible(true);
        		
        		registroFrame.dispose(); // Cierra el frame de registro
        	}
        });
        
        registrarButton = new JButton("Registrarse");
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	registroFrame.mostrarPanelRegistro();
            }
        });
        

        
        add(registrarButton);
        add(iniciarSesionButton);
    }

}
