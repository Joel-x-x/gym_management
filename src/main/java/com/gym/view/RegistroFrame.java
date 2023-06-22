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

public class RegistroFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textContra;
	private JPanel panelLogin;
	private JButton btnIngresar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroFrame frame = new RegistroFrame();
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
	public RegistroFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1179, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1163, 589);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panelLogin = new JPanel();
		panelLogin.setBackground(new Color(255, 255, 255));
		panelLogin.setBounds(330, 140, 345, 291);
		panel.add(panelLogin);
		panelLogin.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(145, 26, 46, 25);
		panelLogin.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setBounds(76, 89, 46, 14);
		panelLogin.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setBounds(76, 159, 71, 14);
		panelLogin.add(lblNewLabel_1_1);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(69, 114, 192, 20);
		panelLogin.add(textUsuario);
		textUsuario.setColumns(10);
		
		textContra = new JTextField();
		textContra.setColumns(10);
		textContra.setBounds(69, 184, 192, 20);
		panelLogin.add(textContra);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String usuario = textUsuario.getText();
				String contra = textContra.getText();
				
				AdministradorController administradorController = new AdministradorController();
				
				if(administradorController.login(usuario, contra)) {
					panelLogin.setVisible(false);
					JOptionPane.showMessageDialog(null, "Todo bien!");
				} else {
					JOptionPane.showMessageDialog(null, "Intruso");
					
					textUsuario.setText("");
					textContra.setText("");
				}
			}
		});
		btnIngresar.setBounds(122, 240, 89, 23);
		panelLogin.add(btnIngresar);
	}
}
