package com.gym.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;


import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.FacturaController;
import com.gym.model.Administrador;
import com.gym.model.Factura;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class FacturaPanel extends JPanel implements GenerarFacturaFrameInterfaz{
	private int administrador_id;
	private int idSeleccionado;
	private FacturaController facturaController;
	private DefaultTableModel modelo;
	private int factura_imprimir;
	
	
	
	
	private static final long serialVersionUID = 1L;
	private JTextField textBuscar;
	private JTable table;
	private JTextField textField_1;
	private JButton btnNuevo;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnRefrescar;
	
	
	public void listar() {
		String[] cabecera = {"Id" , "No Factura", "Cliente", "Subtotal", "IVA", "Total", "Forma de pago", "Fecha", "Establecimiento", "Punto de Emisión"};
		
		modelo = new DefaultTableModel(facturaController.listarFactura(administrador_id, textBuscar.getText()), cabecera);
		
		table.setModel(modelo);
	}
	
	@Override
	public void llamarFrame() {
		new FacturaFrame(this);
	}

	@Override
	public void accion(Factura factura) {
		JOptionPane.showMessageDialog(null, "Se guardo correctamente!");
		listar();
	}
	
	
	public void bloquearBotones() {
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
	
	public void activarBotones() {
		btnModificar.setEnabled(true);
		btnEliminar.setEnabled(true);
		
	}
	
	public FacturaPanel(int panelAncho, int panelAlto) {
		administrador_id = new Administrador().getId();
		facturaController = new FacturaController();
		
		setLayout(null);
		setFocusTraversalPolicyProvider(true);
		btnNuevo = new JButton("Nuevo");
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llamarFrame();
			}
		});
		btnNuevo.setForeground(Color.WHITE);
		btnNuevo.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNuevo.setFocusPainted(false);
		btnNuevo.setBorder(null);
		btnNuevo.setBackground(new Color(46, 56, 64));
		btnNuevo.setBounds(40, 98, 100, 30);
		add(btnNuevo);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.setFocusPainted(false);
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(46, 56, 64));
		btnModificar.setBounds(150, 98, 100, 30);
		add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setForeground(Color.WHITE);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminar.setFocusPainted(false);
		btnEliminar.setBorder(null);
		btnEliminar.setBackground(new Color(46, 56, 64));
		btnEliminar.setBounds(260, 98, 100, 30);
		add(btnEliminar);
		
		btnRefrescar = new JButton("Refrescar");
		btnRefrescar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textBuscar.setText("");
				listar();
			}
		});
		btnRefrescar.setForeground(Color.WHITE);
		btnRefrescar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefrescar.setFocusPainted(false);
		btnRefrescar.setBorder(null);
		btnRefrescar.setBackground(new Color(46, 56, 64));
		btnRefrescar.setBounds(370, 98, 100, 30);
		add(btnRefrescar);
		
		JLabel lblFactura = new JLabel("FACTURA");
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBounds(40, 11, 1000, 46);
		add(lblFactura);
		
		JLabel lblNewLabel_2_1 = new JLabel("Buscar Nombre/Cedula:");
		lblNewLabel_2_1.setBounds(659, 102, 160, 22);
		add(lblNewLabel_2_1);
		
		textBuscar = new JTextField();
		textBuscar.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent e) {
				listar();
			}
		});
		textBuscar.setBounds(803, 101, 237, 25);
		add(textBuscar);
		textBuscar.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 139, 1000, 547);
		add(scrollPane);
		
		table = new JTable();
		table.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				bloquearBotones();
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
				activarBotones();
			}
		});
		
		
		scrollPane.setViewportView(table);
		setPreferredSize(new Dimension(1080, 800));
        setBackground(Color.WHITE);
        
        JButton btnNewButton = new JButton(">>");
        btnNewButton.setBounds(503, 683, 49, 23);
        add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("<<");
        btnNewButton_1.setBounds(421, 683, 49, 23);
        add(btnNewButton_1);
        
        textField_1 = new JTextField();
        textField_1.setText("1");
        textField_1.setBounds(472, 684, 30, 20);
        add(textField_1);
        textField_1.setColumns(10);
        
        JButton btnNewButton_2 = new JButton("Imprimir");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.out.println("xdxd");
				File path = new File(table.getValueAt(table.getSelectedRow(), 1) + ".pdf");
				System.out.println(path);
				
				if (path.exists()) {
		            try {
		                Desktop.getDesktop().open(path);
		            } catch (IOException e1) {
		                e1.printStackTrace();
		            }
				}
        	}
        });
        btnNewButton_2.setForeground(Color.WHITE);
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setBorder(null);
        btnNewButton_2.setBackground(new Color(46, 56, 64));
        btnNewButton_2.setBounds(370, 98, 100, 30);
        add(btnNewButton_2);
        
        listar();
        bloquearBotones();
	}
}
