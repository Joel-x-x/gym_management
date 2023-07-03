package com.gym.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.AdministradorController;
import com.gym.model.Administrador;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class AdminPanel extends JPanel {
	// private int administrador_id = new Administrador().getId();
	private AdministradorController administradorController;
	
	private static final long serialVersionUID = 9177201872921733019L;
	private JTextField textBuscar;
	private JTable tableAdministradores;
	private JTextField textClave;
	private DefaultTableModel modelo;
	
	public void listar() {
		String[] cabeceras = {"Id","Nombre","Apellido","Email", "Cedula	"};
		
		modelo = new DefaultTableModel(administradorController.listar(), cabeceras);
		
		tableAdministradores.setModel(modelo);
	}
	
	public AdminPanel(int panelAncho, int panelAlto) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		administradorController = new AdministradorController();
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Buscar por cédula:");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(30, 80, 99, 14);
		add(lblNewLabel_1_4_2);
		
		textBuscar = new JTextField();
		textBuscar.setColumns(10);
		textBuscar.setBounds(139, 74, 137, 25);
		add(textBuscar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(30, 110, 972, 248);
		add(scrollPane);
		
		tableAdministradores = new JTable();
		tableAdministradores.setBackground(Color.WHITE);
		scrollPane.setViewportView(tableAdministradores);
		
		JLabel lblAdministradores = new JLabel("ADMINISTRADORES");
		lblAdministradores.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAdministradores.setBounds(30, 30, 241, 37);
		add(lblAdministradores);
		
		JLabel lblGenerarClave = new JLabel("GENERAR CLAVE");
		lblGenerarClave.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGenerarClave.setBounds(30, 389, 241, 37);
		add(lblGenerarClave);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.setForeground(Color.WHITE);
		btnGenerar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGenerar.setBorder(null);
		btnGenerar.setBackground(new Color(46, 56, 64));
		btnGenerar.setBounds(30, 478, 131, 30);
		add(btnGenerar);
		
		textClave = new JTextField();
		textClave.setColumns(10);
		textClave.setBounds(30, 437, 300, 30);
		add(textClave);
		
		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.setForeground(Color.WHITE);
		btnCopiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCopiar.setBorder(null);
		btnCopiar.setBackground(new Color(0, 0, 0));
		btnCopiar.setBounds(345, 437, 83, 30);
		add(btnCopiar);
		
		JButton btnCancelar = new JButton("Borrar");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(46, 56, 64));
		btnCancelar.setBounds(176, 478, 131, 30);
		add(btnCancelar);
		
		listar();
	}
}
