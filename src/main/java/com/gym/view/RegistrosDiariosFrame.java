package com.gym.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
//import java.util.Calendar;

import javax.swing.SwingConstants;

import com.gym.controller.MembresiaController;
import com.gym.controller.RegistroController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RegistrosDiariosFrame extends JFrame {
	UsuarioController usuarioController;
	RegistroController registroController;
	MembresiaController membresiaController;
	private int administrador_id; 
	
    public static DefaultTableModel modelo;

	private static final long serialVersionUID = 7524007015900934327L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		new RegistrosDiariosFrame();
	}
	
	public void listarRegistros() {
		String[] cabeceras = {"Nombre", "Cedula", "Fecha de entrada", "Fecha de salida", "Membresia"};
		
		modelo = new DefaultTableModel(registroController.consultar(administrador_id),cabeceras);
		table.setModel(modelo);
	}

	public RegistrosDiariosFrame() {
		 usuarioController = new UsuarioController();
		 registroController = new RegistroController(); 
		 membresiaController = new MembresiaController();
		 administrador_id = new Administrador().getId();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1064, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRegistros = new JLabel("REGISTROS");
		lblRegistros.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistros.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRegistros.setBounds(0, 11, 1064, 37);
		panel.add(lblRegistros);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 118, 1044, 432);
		panel.add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("Fecha inicio");
		lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1.setBounds(10, 59, 131, 14);
		panel.add(lblNewLabel_2_1_1);
		
		JDateChooser dateChooserInicio = new JDateChooser();
		dateChooserInicio.setBounds(10, 77, 138, 30);
		panel.add(dateChooserInicio);
		
		JLabel lblNewLabel_2_1_1_1 = new JLabel("Fecha fin");
		lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_2_1_1_1.setBounds(158, 59, 131, 14);
		panel.add(lblNewLabel_2_1_1_1);
		
		JDateChooser dateChooserFin = new JDateChooser();
		dateChooserFin.setBounds(158, 77, 138, 30);
		panel.add(dateChooserFin);
		
		JButton btnBuscarFecha = new JButton("Buscar");
		btnBuscarFecha.setForeground(Color.WHITE);
		btnBuscarFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarFecha.setFocusPainted(false);
		btnBuscarFecha.setEnabled(false);
		btnBuscarFecha.setBorder(null);
		btnBuscarFecha.setBackground(new Color(46, 56, 64));
		btnBuscarFecha.setBounds(306, 77, 100, 30);
		panel.add(btnBuscarFecha);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setForeground(Color.WHITE);
		btnListar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnListar.setFocusPainted(false);
		btnListar.setEnabled(false);
		btnListar.setBorder(null);
		btnListar.setBackground(new Color(46, 56, 64));
		btnListar.setBounds(416, 77, 100, 30);
		panel.add(btnListar);
		
		
		listarRegistros();
	}
}
