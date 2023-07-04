package com.gym.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.AdministradorController;
import com.gym.model.Administrador;
import com.gym.utilidades.Utilidades;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class AdminPanel extends JPanel {
	private int administrador_id = new Administrador().getId();
	private AdministradorController administradorController;
	
	private static final long serialVersionUID = 9177201872921733019L;
	private JTextField textBuscar;
	private JTable tableAdministradores;
	private JTextField textClave;
	private DefaultTableModel modelo;
	private JButton btnCopiar;
	
	public void listar() {
		String[] cabeceras = {"Id","Nombre","Apellido","Email", "Cedula	"};
		
		modelo = new DefaultTableModel(administradorController.listar(""), cabeceras);
		
		tableAdministradores.setModel(modelo);
	}
	
	public void consultar() {
		String[] cabeceras = {"Id","Nombre","Apellido","Email", "Cedula	"};
		
		modelo = new DefaultTableModel(administradorController.listar(textBuscar.getText()), cabeceras);
		
		tableAdministradores.setModel(modelo);
	}
	
	public void generar() {
		
		String clave = new Administrador().generarClave();
		
		if(administradorController.modificarClave(clave, administrador_id)) {
			textClave.setText(clave);
		} else {
			JOptionPane.showMessageDialog(null, "No se puedo generar la clave");
		}
		
		btnCopiar.setEnabled(true);
		btnCopiar.setText("Copiar");
	}
	
	public void borrarClave() {
		if(!administradorController.eliminarClave(textClave.getText())) {
			JOptionPane.showMessageDialog(null, "No se puedo borrar la clave");
			return;
		}
		
		textClave.setText("");
		btnCopiar.setEnabled(false);
	}
	
	public void copiar() {
		new Utilidades().copiar(textClave.getText());
		
		btnCopiar.setText("Copiado!");
		btnCopiar.setEnabled(false);
	}
	
	public AdminPanel(int panelAncho, int panelAlto) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		administradorController = new AdministradorController();
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Buscar por nombre:");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(30, 80, 99, 14);
		add(lblNewLabel_1_4_2);
		
		textBuscar = new JTextField();
		textBuscar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				consultar();
			}
		});
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
		btnGenerar.setFocusPainted(false);
		btnGenerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generar();
			}
		});
		btnGenerar.setForeground(Color.WHITE);
		btnGenerar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGenerar.setBorder(null);
		btnGenerar.setBackground(new Color(46, 56, 64));
		btnGenerar.setBounds(30, 478, 99, 25);
		add(btnGenerar);
		
		textClave = new JTextField();
		textClave.setDisabledTextColor(new Color(109, 109, 109));
		textClave.setEditable(false);
		textClave.setHorizontalAlignment(SwingConstants.CENTER);
		textClave.setForeground(new Color(64, 128, 128));
		textClave.setFont(new Font("Tahoma", Font.BOLD, 18));
		textClave.setColumns(10);
		textClave.setBounds(30, 437, 260, 30);
		add(textClave);
		
		btnCopiar = new JButton("Copiar");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copiar();
			}
		});
		btnCopiar.setFocusPainted(false);
		btnCopiar.setForeground(Color.WHITE);
		btnCopiar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCopiar.setBorder(null);
		btnCopiar.setBackground(new Color(0, 0, 0));
		btnCopiar.setBounds(300, 437, 83, 25);
		add(btnCopiar);
		
		JButton btnCancelar = new JButton("Borrar");
		btnCancelar.setFocusPainted(false);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrarClave();
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBorder(null);
		btnCancelar.setBackground(new Color(46, 56, 64));
		btnCancelar.setBounds(145, 478, 99, 25);
		add(btnCancelar);
		
		listar();
		btnCopiar.setEnabled(false);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnBuscar.setForeground(Color.WHITE);
		btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscar.setBorder(null);
		btnBuscar.setBackground(new Color(46, 56, 64));
		btnBuscar.setBounds(286, 72, 89, 30);
		add(btnBuscar);
	}
}
