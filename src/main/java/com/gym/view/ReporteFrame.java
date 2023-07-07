package com.gym.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.ReporteController;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Color;

public class ReporteFrame extends JFrame {

	private static final long serialVersionUID = 3784835300009942490L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lbl_reporte_precio_total;
	private DefaultTableModel modelo;
	private ReporteController reporteController;
	private int usuario_id;
	private float sumatotalvalor=0;
	
	private void listarMembresias() {
		String[] cabeceras = {"Fecha de Inicio","Fecha de Fin", "Plan", "Clase", "Membresia", "Valor Total"};

		modelo = new DefaultTableModel(reporteController.listar_reporte(usuario_id), cabeceras);
		table.setModel(modelo);
	}
	private void sumatotal() {
		for(int i=0; i<table.getRowCount();i++) {
			Object value = table.getValueAt(i, 5);
			System.out.println(value);
			sumatotalvalor = sumatotalvalor+ (float) value;
		}
		lbl_reporte_precio_total.setText("$ " + String.valueOf(sumatotalvalor));
	}

	public ReporteFrame(int usuario_id) {
		this.usuario_id = usuario_id;
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				listarMembresias();
				sumatotal();
			}
		});
		
		reporteController = new ReporteController();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 801, 601);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotalAPagar = new JLabel("Total a pagar");
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotalAPagar.setBounds(10, 386, 765, 37);
		contentPane.add(lblTotalAPagar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 765, 303);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		scrollPane.setViewportView(table);
		
		lbl_reporte_precio_total = new JLabel("0.0");
		lbl_reporte_precio_total.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reporte_precio_total.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lbl_reporte_precio_total.setBounds(10, 434, 765, 59);
		contentPane.add(lbl_reporte_precio_total);
		
		JLabel lblReporteMembresias = new JLabel("Reporte Membresias");
		lblReporteMembresias.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteMembresias.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReporteMembresias.setBounds(10, 11, 765, 37);
		contentPane.add(lblReporteMembresias);
	}
}
