package com.gym.view;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;

import com.gym.controller.MembresiaController;
import com.gym.model.Administrador;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class MembresiasPanel extends JPanel {
	private int administrador_id;
	private MembresiaController membresiaController;
	private DefaultTableModel modelo;
	
	private static final long serialVersionUID = 2171384064364316402L;
	private JTable table;
	private JTextField textBuscar;
	
	public void listar() {
		String[] cabeceras = {"Id", "Membresía", "Nombre", "Cédula", "Finalización", "Clase", "Entrenador", "No Factura", "Estado"};
		
		modelo = new DefaultTableModel(membresiaController.listar(administrador_id, textBuscar.getText()), cabeceras);
		
		table.setModel(modelo);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(7).setPreferredWidth(25);
		table.getColumnModel().getColumn(8).setPreferredWidth(10);
		
		// Asiganar un color a la comuna estado
		table.getColumnModel().getColumn(8).setCellRenderer(renderer);
		
	}
	
	// Crea el render para asignar un color a la columna estado en base a este
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = -8267339185456423635L;

		@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String estado = (String) table.getValueAt(row, 8);
            if (estado.equals("Activa")) {
                component.setBackground(new Color(112, 236, 98));
            } else if(estado.equals("Caducada")){
            	component.setBackground(new Color(241, 151, 120));
            }

            return component;
        }
    };

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
        textBuscar.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		listar();
        	}
        });
        textBuscar.setColumns(10);
        textBuscar.setBounds(163, 98, 200, 25);
        add(textBuscar);
        
        listar();
	}
}
