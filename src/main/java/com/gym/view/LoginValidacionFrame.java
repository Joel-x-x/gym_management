package com.gym.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gym.controller.AdministradorController;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginValidacionFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private AdministradorController administradorController;
	private ConfiguracionPanel configuracionPanel;
	private int administrador_id;
	private String metodo;
	
	public void validarPasswordEjecutarMetodo() {
		if(administradorController.validarPassword(administrador_id, String.valueOf(passwordField.getPassword()))) {
			
			if(this.metodo.equals("actualizarRecuperacionCuenta")) {
				configuracionPanel.actualizarRecuperacionCuenta();
			} else {
				configuracionPanel.cambiarPassword();
			}
			
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(null, "Contraseña incorrecta intenta de nuevo");
		}
	}

	public LoginValidacionFrame(int administrador_id, ConfiguracionPanel configuracionPanel, String metodo) {
		this.metodo = metodo;
		this.configuracionPanel = configuracionPanel; 
		administradorController = new AdministradorController();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		this.administrador_id = administrador_id;  

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblContrasea = new JLabel("Contraseña");
		lblContrasea.setForeground(new Color(163, 175, 175));
		lblContrasea.setFont(new Font("Dialog", Font.PLAIN, 20));
		lblContrasea.setBounds(38, 84, 176, 30);
		panel.add(lblContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 12));
		passwordField.setBounds(38, 114, 350, 40);
		panel.add(passwordField);
		
		JButton btnIniciarSesion = new JButton("Confirmar");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				validarPasswordEjecutarMetodo();
			}
		});
		btnIniciarSesion.setForeground(Color.BLACK);
		btnIniciarSesion.setFont(new Font("Dialog", Font.BOLD, 20));
		btnIniciarSesion.setFocusPainted(false);
		btnIniciarSesion.setBorderPainted(false);
		btnIniciarSesion.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnIniciarSesion.setBackground(new Color(163, 175, 175));
		btnIniciarSesion.setBounds(109, 185, 200, 40);
		panel.add(btnIniciarSesion);
		
		JLabel lblPorMotivosDe = new JLabel("Por motivos de seguridad ingresa tu contraseña");
		lblPorMotivosDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblPorMotivosDe.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPorMotivosDe.setBounds(0, 21, 434, 37);
		panel.add(lblPorMotivosDe);
	}
}
