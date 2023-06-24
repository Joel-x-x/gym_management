package com.gym.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gym.controller.AdministradorController;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroDefaultFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPanel panelLogin;
	private JButton btnIngresar;
	private JPasswordField textContra;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroDefaultFrame frame = new RegistroDefaultFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistroDefaultFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1179, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(10, 15, 31));
		panel.setBounds(0, 0, 1163, 589);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 255, 255));
		panelLogin.setBounds(325, 134, 350, 350);
		panel.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(149, 37, 46, 25);
		panelLogin.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(76, 89, 46, 14);
		panelLogin.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(76, 159, 71, 14);
		panelLogin.add(lblNewLabel_1_1);
		
		textUsuario = new JTextField();
		textUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(textUsuario.getText().equals("Ingrese su correo de usuario")) {
					textUsuario.setText("");
					textUsuario.setForeground(Color.black);
				}
				if(String.valueOf(textContra.getPassword()).isEmpty()) {
					textContra.setText("********");
					
					textContra.setForeground(Color.gray);
				}
				
			}
		});
		textUsuario.setForeground(new Color(192, 192, 192));
		textUsuario.setText("Ingrese su correo de usuario");
		textUsuario.setBounds(69, 114, 192, 20);
		panelLogin.add(textUsuario);
		textUsuario.setColumns(10);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnIngresar.setBorderPainted(false);
		btnIngresar.setBackground(Color.black);
		btnIngresar.setForeground(Color.white);
		btnIngresar.setRolloverEnabled(true);
		btnIngresar.setFont(new Font("Arial", Font.PLAIN,14));
//		btnIngresar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String usuario = textUsuario.getText();
//				String contra = (String.valueOf( textContra.getPassword()));
//				
//				AdministradorController administradorController = new AdministradorController();
//				
//				if(administradorController.login(usuario, contra)) {
//					panelLogin.setVisible(false);
//					JOptionPane.showMessageDialog(null, "Todo bien!");
//				} else {
//					JOptionPane.showMessageDialog(null, "Intruso");
//					
//					textUsuario.setText("Ingrese su correo de usuario");
//					
//					textUsuario.setForeground(Color.gray);
//textContra.setText("********");
//					
//					textContra.setForeground(Color.gray);
//				}
//			}
//		});
		btnIngresar.setBounds(122, 240, 89, 23);
		panelLogin.add(btnIngresar);
		
		textContra = new JPasswordField();
		textContra.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(textUsuario.getText().isEmpty()) {
					textUsuario.setText("Ingrese su correo de usuario");
					
					textUsuario.setForeground(Color.gray);
				}
				if(String.valueOf(textContra.getPassword()).equals("********")) {
					textContra.setText("");
					textContra.setForeground(Color.black);
					
				}
				
				
			}
		});
		textContra.setForeground(new Color(192, 192, 192));
		textContra.setText("********");
		textContra.setBounds(69, 184, 192, 20);
		panelLogin.add(textContra);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(".\\src\\main\\java\\com\\gym\\resources\\interfaz_login.png"));
		lblNewLabel_2.setBounds(0, 0, 350, 350);
		panelLogin.add(lblNewLabel_2);
	}
}
