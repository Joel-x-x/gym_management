package com.gym.view;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class RecuperarCuentaPanel extends JPanel {
	private RegistroFrame registroFrame;

	private static final long serialVersionUID = 8077406384536139438L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public RecuperarCuentaPanel(RegistroFrame frame) {
        this.registroFrame = frame;
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
        
        textField = new JTextField();
        textField.setText("wacho@gmail.com");
        textField.setFont(new Font("Dialog", Font.PLAIN, 20));
        textField.setColumns(10);
        textField.setBounds(465, 222, 350, 40);
        add(textField);
        
        JButton btnIniciarSesion = new JButton("Verificar");
        btnIniciarSesion.setForeground(Color.BLACK);
        btnIniciarSesion.setFont(new Font("Dialog", Font.BOLD, 20));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setBorderPainted(false);
        btnIniciarSesion.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnIniciarSesion.setBackground(new Color(163, 175, 175));
        btnIniciarSesion.setBounds(546, 498, 200, 40);
        add(btnIniciarSesion);
        
        JLabel lblRegistrarse = new JLabel("IniciarSesion ");
        lblRegistrarse.setHorizontalAlignment(SwingConstants.RIGHT);
        lblRegistrarse.setForeground(new Color(163, 175, 175));
        lblRegistrarse.setFont(new Font("Dialog", Font.BOLD, 15));
        lblRegistrarse.setBounds(700, 445, 115, 23);
        add(lblRegistrarse);
        
        textField_1 = new JTextField();
        textField_1.setText("wacho@gmail.com");
        textField_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        textField_1.setColumns(10);
        textField_1.setBounds(465, 394, 350, 40);
        add(textField_1);
        
        JLabel lblCorreo_1 = new JLabel("Email");
        lblCorreo_1.setForeground(new Color(163, 175, 175));
        lblCorreo_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo_1.setBounds(465, 364, 73, 30);
        add(lblCorreo_1);
        
        JLabel lblCorreo_1_1 = new JLabel("Email");
        lblCorreo_1_1.setForeground(new Color(163, 175, 175));
        lblCorreo_1_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo_1_1.setBounds(465, 283, 73, 30);
        add(lblCorreo_1_1);
        
        textField_2 = new JTextField();
        textField_2.setText("wacho@gmail.com");
        textField_2.setFont(new Font("Dialog", Font.PLAIN, 20));
        textField_2.setColumns(10);
        textField_2.setBounds(465, 313, 350, 40);
        add(textField_2);

	}
}
