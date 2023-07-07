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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class InicioSesionPanel extends JPanel {
	
	private static final long serialVersionUID = -8083841348236094297L;
	private RegistroFrame registroFrame;
    private JTextField textUsuario;
    private AdministradorController administradorController;
    private JPasswordField textContra;
    private JLabel lblRegistro;
    private JLabel lblRegistrarse;
    
    public void iniciarSesion() {
		Administrador administrador = llenarAdministrador();
		
		if(administradorController.sesion(administrador)) {
			
			// Ya logeado asignaremos el id statico a la clase Administrador para que usuario tenga su referencia
			new Administrador().setId(administradorController.consultarId(administrador.getEmail()));
			
			// Abre la ventana del panel de administrador
    		AdminFrame adminFrame = new AdminFrame();
    		adminFrame.setVisible(true);

    		registroFrame.dispose(); 

		} else {
			JOptionPane.showMessageDialog(null, "Datos incorrectos, vuelve a intentarlo");
		}
    }
    
    public InicioSesionPanel(RegistroFrame frame) {
        registroFrame = frame;
        setSize(1280, 720);
        setBackground(Color.white);
        
        administradorController = new AdministradorController();
        setLayout(null);
        
        textUsuario = new JTextField();
        textUsuario.setFont(new Font("Dialog", Font.PLAIN, 20));
        textUsuario.setText("wacho@gmail.com");
        textUsuario.setColumns(10);
        textUsuario.setBounds(465, 258, 350, 40);
        add(textUsuario);
        
        JLabel lblCorreo = new JLabel("Email");
        lblCorreo.setForeground(new Color(163, 175, 175));
        lblCorreo.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo.setBounds(465, 228, 73, 30);
        add(lblCorreo);
        
        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setForeground(new Color(163, 175, 175));
        lblContrasea.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblContrasea.setBounds(465, 319, 176, 30);
        add(lblContrasea);
        
        textContra = new JPasswordField();
        textContra.setFont(new Font("Dialog", Font.PLAIN, 12));
        textContra.setText("1234");
        textContra.setBounds(465, 349, 350, 40);
        add(textContra);
        
        lblRegistro = new JLabel("Bienvenido");
        lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistro.setForeground(new Color(163, 175, 175));
        lblRegistro.setFont(new Font("Dialog", Font.BOLD, 35));
        lblRegistro.setBounds(465, 130, 350, 50);
        add(lblRegistro);
        
        JButton btnIniciarSesion = new JButton("¡A entrenar!");
        btnIniciarSesion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		iniciarSesion();
        	}
        });
        btnIniciarSesion.setForeground(Color.BLACK);
        btnIniciarSesion.setFont(new Font("Dialog", Font.BOLD, 20));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnIniciarSesion.setBackground(new Color(163, 175, 175));
        btnIniciarSesion.setBounds(543, 462, 200, 40);
        add(btnIniciarSesion);
        
        lblRegistrarse = new JLabel("Registrarse");
        lblRegistrarse.setForeground(new Color(163, 175, 175));
        lblRegistrarse.setFont(new Font("Dialog", Font.BOLD, 15));
        lblRegistrarse.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		registroFrame.mostrarPanelRegistro();
        	}
        });
        lblRegistrarse.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRegistrarse.setBounds(700, 404, 115, 23);
        add(lblRegistrarse);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(InicioSesionPanel.class.getResource("/com/gym/resources/pesaLogo.png")));
        lblNewLabel.setBounds(587, 29, 100, 100);
        add(lblNewLabel);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(InicioSesionPanel.class.getResource("/com/gym/resources/pose.png")));
        lblNewLabel_2.setBounds(1070, 445, 200, 275);
        add(lblNewLabel_2);
        
        JLabel labelOlvidaste = new JLabel("¿Olvidaste tu contraseña?");
        labelOlvidaste.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		registroFrame.mostrarPanelRecuperarCuenta();
        	}
        });
        labelOlvidaste.setHorizontalAlignment(SwingConstants.LEFT);
        labelOlvidaste.setForeground(new Color(163, 175, 175));
        labelOlvidaste.setFont(new Font("Dialog", Font.PLAIN, 15));
        labelOlvidaste.setBounds(465, 404, 225, 23);
        add(labelOlvidaste);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(InicioSesionPanel.class.getResource("/com/gym/resources/poseGrande.png")));
        lblNewLabel_1.setBounds(33, 290, 500, 500);
        add(lblNewLabel_1);
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
