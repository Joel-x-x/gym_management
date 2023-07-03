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
	
	private static final long serialVersionUID = -8083841348236094297L;
	private RegistroFrame registroFrame;
    private JButton registrarButton;
    private JButton iniciarSesionButton;
    private JTextField textUsuario;
    private AdministradorController administradorController;
    private JPasswordField textContra;
    
    
    public InicioSesionPanel(RegistroFrame frame) {
        registroFrame = frame;
        setSize(1280, 720);
        setBackground(Color.white);
        
        administradorController = new AdministradorController();
        
        iniciarSesionButton = new JButton("Iniciar Sesion");
        iniciarSesionButton.setBounds(473, 220, 95, 23);
        iniciarSesionButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Administrador administrador = llenarAdministrador();
        		if(administradorController.sesion(administrador)) {
					
        			// Ya logeado asignaremos el id statico a la clase Administrador para que usuario tenga su referencia
        			new Administrador().setId(administradorController.consultarId(administrador.getEmail()));
        			
					JOptionPane.showMessageDialog(null, "Todo bien!");
					// Abre la ventana del panel de administrador
	        		AdminFrame adminFrame = new AdminFrame();
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
        registrarButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        registrarButton.setBounds(592, 221, 95, 21);
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
