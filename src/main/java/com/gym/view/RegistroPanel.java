package com.gym.view;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class RegistroPanel extends JPanel {
	private AdministradorController administradorController;
	private int administrador_id;

	private static final long serialVersionUID = -4705635535839437717L;
    private RegistroFrame registroFrame;
    private JLabel iniciarSesionButton;
    private JTextField textEmail;
    private JPasswordField textPassword;
    private JPasswordField textPasswordConfirm;
    private JTextField textClave;
    private JTextField textNombre;
    
    public void registrar() {
		Administrador administrador = llenarAdministrador();
		
		if(administradorController.registrar(administrador)) {
			if(!administradorController.crearCuenta(administrador_id) && !administradorController.crearRecuperacionCuenta(administrador_id)) {
				JOptionPane.showMessageDialog(null, "No se creo correctamente la cuenta");
			}
			JOptionPane.showMessageDialog(null, "Registrado con exito");
			administrador_id = administradorController.getId();
			
			
			limpiarFormulario();
			registroFrame.mostrarPanelInicioSesion();
		} else {
			JOptionPane.showMessageDialog(null, "No se puedo registrar");
		}
	}
    
    public Administrador llenarAdministrador() {
    	return new Administrador(
    			textNombre.getText(),
    			textEmail.getText(),
    			String.valueOf(textPassword.getPassword()),
    			0,
    			0,
    			textClave.getText());
    }
    
    public void limpiarFormulario() {
    	textNombre.setText("");
    	textEmail.setText("");
    	textPassword.setText("");
    	textPasswordConfirm.setText("");
    	textClave.setText("");
    }
    
    public RegistroPanel(RegistroFrame frame) {
        registroFrame = frame;
        administradorController = new AdministradorController();
        
        setSize(1280, 720);
        setBackground(Color.white);
        
        iniciarSesionButton = new JLabel("¡Ya tengo una cuenta!, Iniciar Sesión");
        iniciarSesionButton.setForeground(new Color(163, 175, 175));
        iniciarSesionButton.setFont(new Font("Dialog", Font.BOLD, 15));
        iniciarSesionButton.setHorizontalAlignment(SwingConstants.CENTER);
        iniciarSesionButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		registroFrame.mostrarPanelInicioSesion(); // Cambia al panel de inicio de sesión
        	}
        });
        iniciarSesionButton.setBounds(250, 550, 350, 23);
        
        setLayout(null);
        
        add(iniciarSesionButton);
        
        textNombre = new JTextField();
        textNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
        textNombre.setBounds(250, 178, 350, 40);
        add(textNombre);
        textNombre.setColumns(10);
        
        textEmail = new JTextField();
        textEmail.setFont(new Font("Dialog", Font.PLAIN, 20));
        textEmail.setColumns(10);
        textEmail.setBounds(250, 253, 350, 40);
        add(textEmail);
        
        textPassword = new JPasswordField();
        textPassword.setColumns(10);
        textPassword.setBounds(250, 331, 350, 40);
        add(textPassword);
        
        textPasswordConfirm = new JPasswordField();
        textPasswordConfirm.setColumns(10);
        textPasswordConfirm.setBounds(250, 405, 350, 40);
        add(textPasswordConfirm);
        
        JButton btnNewButton = new JButton("Registrarse");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBorderPainted(false);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBackground(new Color(163, 175, 175));
        btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 20));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registrar();
        	}
        });
        btnNewButton.setBounds(332, 595, 200, 40);
        add(btnNewButton);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setForeground(new Color(163, 175, 175));
        lblNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblNombre.setBounds(250, 145, 95, 31);
        add(lblNombre);
        
        JLabel lblCorreo = new JLabel("Email");
        lblCorreo.setForeground(new Color(163, 175, 175));
        lblCorreo.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo.setBounds(250, 229, 95, 21);
        add(lblCorreo);
        
        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setForeground(new Color(163, 175, 175));
        lblContrasea.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblContrasea.setBounds(250, 304, 125, 25);
        add(lblContrasea);
        
        JLabel lblClave = new JLabel("Confirma tu contraseña");
        lblClave.setForeground(new Color(163, 175, 175));
        lblClave.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblClave.setBounds(250, 382, 229, 21);
        add(lblClave);
        
        textClave = new JTextField();
        textClave.setFont(new Font("Dialog", Font.PLAIN, 20));
        textClave.setColumns(10);
        textClave.setBounds(250, 480, 350, 40);
        add(textClave);
        
        JLabel lblClave_1 = new JLabel("Clave");
        lblClave_1.setForeground(new Color(163, 175, 175));
        lblClave_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblClave_1.setBounds(250, 456, 95, 23);
        add(lblClave_1);
        
        JLabel lblRegistro = new JLabel("Registrarse");
        lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistro.setForeground(new Color(128, 128, 128));
        lblRegistro.setFont(new Font("Dialog", Font.BOLD, 35));
        lblRegistro.setBounds(250, 49, 350, 50);
        add(lblRegistro);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(RegistroPanel.class.getResource("/com/gym/resources/poseRegistro.png")));
        lblNewLabel.setBounds(671, 103, 500, 530);
        add(lblNewLabel);
    }
    
}
