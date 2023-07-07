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

public class ReporteFrame extends JFrame {

	private static final long serialVersionUID = 3784835300009942490L;
	private JPanel contentPane;
	private JTable table;
	private JLabel lbl_reporte_precio_total;
	private DefaultTableModel modelo;
	private ReporteController reporteController;
	private int usuario_id;
	
	private void listarMembresias() {
		String[] cabeceras = {"Fecha de Inicio","Fecha de Fin", "Plan", "Clase", "Membresia", "Valor Total"};

		modelo = new DefaultTableModel(reporteController.listar_reporte(usuario_id), cabeceras);
		table.setModel(modelo);
	}

	public ReporteFrame(int usuario_id) {
		this.usuario_id = usuario_id;
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				listarMembresias();
			}
		});
		
		reporteController = new ReporteController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 801, 601);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTotalAPagar = new JLabel("TOTAL A PAGAR:");
		lblTotalAPagar.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalAPagar.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTotalAPagar.setBounds(42, 419, 301, 37);
		contentPane.add(lblTotalAPagar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 765, 303);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lbl_reporte_precio_total = new JLabel("0.0");
		lbl_reporte_precio_total.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_reporte_precio_total.setFont(new Font("Tahoma", Font.BOLD, 18));
		lbl_reporte_precio_total.setBounds(348, 419, 301, 37);
		contentPane.add(lbl_reporte_precio_total);
	}
}
