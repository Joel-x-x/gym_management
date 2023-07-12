package com.gym.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import com.gym.controller.AdministradorController;
import com.gym.model.Administrador;

import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CambiarPassword extends JPanel {
	private RegistroFrame registroFrame;
	private AdministradorController administradorController;
	private int administrador_id;

	private static final long serialVersionUID = 8591903511777449959L;
	private JPasswordField textPassword;
	private JPasswordField textPasswordConfirmar;
	private JLabel labelIniciarSesion;
	private JButton btnCambiar;
	
	public void cambiarPassword() {
		// No se inicializa en el constructor porque aun no recibe un valor el id
		administrador_id = new Administrador().getId();
		
		if(administradorController.cambiarPassword(String.valueOf(textPassword.getPassword()), administrador_id)) {
			JOptionPane.showMessageDialog(null, "Haz cambiado tu contraseña");
			registroFrame.mostrarPanelInicioSesion();
			limpiarFormulario();
		} else {
			JOptionPane.showMessageDialog(null, "No se puedo cambiar tu contraseña");
		}
	}
	
	public void limpiarFormulario() {
		textPassword.setText("");
		textPasswordConfirmar.setText("");
	}

	public CambiarPassword(RegistroFrame registroFrame) {
		this.registroFrame = registroFrame;
		administradorController = new AdministradorController();
		
        setSize(1280, 720);
        setBackground(Color.white);
        setLayout(null);
        
        JLabel lblCambiarContrasea = new JLabel("Cambiar Contraseña");
        lblCambiarContrasea.setHorizontalAlignment(SwingConstants.CENTER);
        lblCambiarContrasea.setForeground(new Color(163, 175, 175));
        lblCambiarContrasea.setFont(new Font("Dialog", Font.BOLD, 35));
        lblCambiarContrasea.setBounds(465, 95, 350, 50);
        add(lblCambiarContrasea);
        
        JLabel lblCorreo = new JLabel("Nueva contraseña");
        lblCorreo.setForeground(new Color(163, 175, 175));
        lblCorreo.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo.setBounds(465, 193, 350, 30);
        add(lblCorreo);
        
        JLabel lblCorreo_1_1 = new JLabel("Confirma tu contraseña");
        lblCorreo_1_1.setForeground(new Color(163, 175, 175));
        lblCorreo_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo_1_1.setBounds(465, 274, 350, 30);
        add(lblCorreo_1_1);
        
        labelIniciarSesion = new JLabel("IniciarSesion ");
        labelIniciarSesion.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		registroFrame.mostrarPanelInicioSesion();
        	}
        });
        labelIniciarSesion.setHorizontalAlignment(SwingConstants.RIGHT);
        labelIniciarSesion.setForeground(new Color(163, 175, 175));
        labelIniciarSesion.setFont(new Font("Dialog", Font.BOLD, 15));
        labelIniciarSesion.setBounds(700, 355, 115, 23);
        add(labelIniciarSesion);
        
        btnCambiar = new JButton("Verificar");
        btnCambiar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		cambiarPassword();
        	}
        });
        btnCambiar.setForeground(Color.BLACK);
        btnCambiar.setFont(new Font("Dialog", Font.BOLD, 20));
        btnCambiar.setFocusPainted(false);
        btnCambiar.setBorderPainted(false);
        btnCambiar.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnCambiar.setBackground(new Color(163, 175, 175));
        btnCambiar.setBounds(549, 389, 200, 40);
        add(btnCambiar);
        
        textPassword = new JPasswordField();
        textPassword.setBounds(465, 223, 350, 40);
        add(textPassword);
        
        textPasswordConfirmar = new JPasswordField();
        textPasswordConfirmar.setBounds(465, 304, 350, 40);
        add(textPasswordConfirmar);
	}
}
