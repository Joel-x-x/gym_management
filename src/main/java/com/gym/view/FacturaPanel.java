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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.FacturaController;
import com.gym.model.Administrador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FacturaPanel extends JPanel {
	private int administrador_id;
	private int idSeleccionado;
	private FacturaController facturaController;
	private DefaultTableModel modelo;
	
	
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	
	public void listar() {
		String[] cabecera = {"Id" , "No Factura", "Cliente", "Subtotal", "IVA", "Total", "Forma de pago", "Fecha", "Establecimiento", "Punto de Emisión"};
		
		modelo = new DefaultTableModel(facturaController.listarFactura(administrador_id), cabecera);
		
		table.setModel(modelo);
	}

	public FacturaPanel(int panelAncho, int panelAlto) {
		administrador_id = new Administrador().getId();
		facturaController = new FacturaController();
		
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
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblFactura.setBounds(40, 11, 1000, 46);
		add(lblFactura);
		
		JLabel lblNewLabel_2_1 = new JLabel("Buscar Nombre/Cedula");
		lblNewLabel_2_1.setBounds(660, 106, 133, 14);
		add(lblNewLabel_2_1);
		
		textField = new JTextField();
		textField.setBounds(803, 103, 237, 25);
		add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 139, 1000, 547);
		add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
			}
		});
		scrollPane.setViewportView(table);
		setPreferredSize(new Dimension(1080, 800));
        setBackground(Color.WHITE);
        
        listar();
	}
}
