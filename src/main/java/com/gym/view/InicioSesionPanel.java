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
import javax.swing.JPasswordField;

public class InicioSesionPanel extends JPanel {
    private RegistroFrame registroFrame;
    private JButton registrarButton;
    private JButton iniciarSesionButton;
    private JTextField textUsuario;
    private AdministradorController administradorController;
    private JPasswordField textContra;
    
    
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
        		System.out.println(textContra.getPassword());
        		if(administradorController.sesion(administrador)) {
					
        			// Ya logeado asignaremos el id statico a la clase Administrador para que usuario tenga su referencia
        			new Administrador().setId(administradorController.consultarId(administrador.getEmail()));
        			
        			System.out.println(new Administrador().getId());
        			
					JOptionPane.showMessageDialog(null, "Todo bien!");
					// Abre la ventana del panel de administrador
	        		AdminFrame adminFrame = new AdminFrame();
	        		adminFrame.setVisible(true);
	        		adminFrame.setVisible(true);
	        		
	        		registroFrame.dispose(); 
	        		 // Cierra el frame de registro
				} else {
					JOptionPane.showMessageDialog(null, "Intruso");
					
				
					
					
					
					
				}
        		// Realizar las operaciones de inicio de sesión
        		
        		
        		// Abre la ventana del panel de administrador
        		
        		// Cierra el frame de registro
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
        textUsuario.setText("wacho@gmai.com");
        textUsuario.setColumns(10);
        textUsuario.setBounds(438, 111, 289, 35);
        add(textUsuario);
        
        JLabel lblCorreo = new JLabel("Email");
        lblCorreo.setBounds(438, 96, 63, 14);
        add(lblCorreo);
        
        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setBounds(438, 157, 63, 14);
        add(lblContrasea);
        
        textContra = new JPasswordField();
        textContra.setText("1234");
        textContra.setBounds(438, 174, 290, 35);
        add(textContra);
    }
    public Administrador llenarAdministrador() {
    	return new Administrador(
    			"",
    			textUsuario.getText(),
    			String.valueOf(textContra.getPassword()),
    			0,
    			0,
    			"");
    }
}
