package com.gym.view;

import java.awt.Color;
import java.awt.Dimension;


import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FacturaPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;

	public FacturaPanel(int panelAncho, int panelAlto) {
		setLayout(null);
		setFocusTraversalPolicyProvider(true);
		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevo.setFocusPainted(false);
		btnNuevo.setEnabled(false);
		btnNuevo.setBorder(null);
		btnNuevo.setBackground(new Color(46, 56, 64));
		btnNuevo.setBounds(40, 98, 100, 30);
		add(btnNuevo);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setFocusPainted(false);
		btnModificar.setEnabled(false);
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(46, 56, 64));
		btnModificar.setBounds(150, 98, 100, 30);
		add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setEnabled(false);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(46, 56, 64));
		btnEliminar.setBounds(260, 98, 100, 30);
		add(btnEliminar);
		
		JButton btnRefrescar = new JButton("Refrescar");
		btnRefrescar.setForeground(Color.WHITE);
		btnRefrescar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setEnabled(false);
		btnRefrescar.setBorder(null);
		btnRefrescar.setBackground(new Color(46, 56, 64));
		btnRefrescar.setBounds(370, 98, 100, 30);
		add(btnRefrescar);
		
		JButton btnImprimir = new JButton("Imprimir");
		btnImprimir.setForeground(Color.WHITE);
		btnImprimir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnImprimir.setFocusPainted(false);
		btnImprimir.setEnabled(false);
		btnImprimir.setBorder(null);
		btnImprimir.setBackground(new Color(46, 56, 64));
		btnImprimir.setBounds(480, 98, 100, 30);
		add(btnImprimir);
		
		JLabel lblFactura = new JLabel("FACTURA");
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBounds(499, 11, 150, 33);
		add(lblFactura);
		
		JLabel lblNewLabel_2_1 = new JLabel("Buscar Nombre/Cedula");
		lblNewLabel_2_1.setBounds(660, 106, 133, 14);
		add(lblNewLabel_2_1);
		
		textField = new JTextField();
		textField.setBounds(803, 103, 237, 20);
		add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 203, 1000, 447);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		setPreferredSize(new Dimension(1280, 800));
        setBackground(Color.WHITE);
        
	}
}
