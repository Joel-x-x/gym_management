package com.gym.view;

import java.awt.Color;

import javax.swing.JPanel;

import com.gym.controller.MembresiaController;
import com.gym.model.Administrador;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MembresiasPanel extends JPanel {
	private int administrador_id;
	private MembresiaController membresiaController;
	private DefaultTableModel modelo;
	
	private static final long serialVersionUID = 2171384064364316402L;
	private JTable table;
	private JTextField textBuscar;
	
	public void listar() {
		String[] cabeceras = {"Id","Finaliza", "Clase", "Membresia", "Notificación", "Valor Total"};
		
		modelo = new DefaultTableModel(membresiaController.listar(administrador_id), cabeceras);
		table.setModel(modelo);
	}

	public MembresiasPanel(int panelAncho, int panelAlto) {
		administrador_id = new Administrador().getId();
		membresiaController = new MembresiaController();
		
		setSize(1080, 800);
        setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblMembresas = new JLabel("MEMBRESÍAS");
        lblMembresas.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMembresas.setBounds(40, 40, 170, 37);
        add(lblMembresas);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setAutoscrolls(true);
        scrollPane.setBounds(40, 134, 996, 550);
        add(scrollPane);
        
        table = new JTable();
        table.setBackground(Color.WHITE);
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel_1_4_2 = new JLabel("Buscar Nombre/Cédula");
        lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_2.setBounds(40, 104, 150, 14);
        add(lblNewLabel_1_4_2);
        
        textBuscar = new JTextField();
        textBuscar.setColumns(10);
        textBuscar.setBounds(163, 98, 200, 25);
        add(textBuscar);
	}
}
