package com.gym.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.FacturaController;
import com.gym.dao.FacturaDAO;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FormaPagoFrame extends JFrame {
	private static DefaultTableModel modelo;
	private FacturaController facturaController;
	private int factura_id;
	private int usuario_id;
	private String factura_numero;
	private String cedula;
	private Double total;
	private int idSeleccionado;
	
	private DefaultComboBoxModel<String> comboBoxModelFormaPago;
	private JComboBox<String> comboBoxFormaPago;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textNumero;
	private JTextField textCedula;
	private JTextField textMonto;
	private JButton btnAgregarFormaDe;
	private JButton btnBorrar;
	private JTextField textSaldo;
	
	public void listar() {
		String[] cabeceras = {"Id", "Forma de pago", "Monto", "Fecha"};
		
		modelo = new DefaultTableModel(facturaController.listarFormaPago(factura_id),cabeceras);
		table.setModel(modelo);
		
		textNumero.setText(factura_numero);
		textCedula.setText(cedula);
		
		// Elmentos tipo pago
		List<String> tipo = new ArrayList<>(Arrays.asList("efectivo", "transferencia"));
		
		comboBoxModelFormaPago.removeAllElements();
		comboBoxModelFormaPago.addAll(tipo);
		comboBoxFormaPago.setModel(comboBoxModelFormaPago);
		comboBoxFormaPago.setSelectedItem("efectivo");
		
		textSaldo.setText(total + "");
	}
	
	public void agregarPago() {
		String forma_pago = (String) comboBoxFormaPago.getSelectedItem();
		Double monto = Double.parseDouble(textMonto.getText());
		
		if(monto <= 0) {
			JOptionPane.showMessageDialog(null, "Monto no puede ser cero o inferior");
			return;
		}
		
		if(facturaController.agregarFormaPago(forma_pago, monto, usuario_id, factura_id)) {
			listar();
		} else {
			JOptionPane.showMessageDialog(null, "Ocurrio un error");
		}
	}
	
	public void eliminarPago() {
		if(facturaController.borrarFormaPago(idSeleccionado)) {
			JOptionPane.showMessageDialog(null, "Eliminado Correctamente!");
			listar();
		}
	}
	
	public void bloquearBotones() {
		btnBorrar.setEnabled(false);
	}
	
	public void activarBotones() {
		btnBorrar.setEnabled(true);
	}

	public FormaPagoFrame(String factura_numero, String cedula, Double total, int factura_id, int usuario_id) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
	            // Recorrer la tabla
	            int rowCount = table.getRowCount();
	            Double value = 0.0;
	            
	            for (int row = 0; row < rowCount; row++) {
	                value += (Double) table.getValueAt(row, 2);
	            }
	            
	            if(value > total || value < total) {
	            	JOptionPane.showMessageDialog(null, "La forma de pago: " + value + " no puede ser mayor al del saldo: " + total);
	            } else {
	            	dispose();
	            }
			}
		});
		this.factura_id = factura_id;
		this.usuario_id = usuario_id;
		this.factura_numero = factura_numero;
		this.cedula = cedula;
		this.total = total;
		facturaController = new FacturaController();
		comboBoxModelFormaPago = new DefaultComboBoxModel<>();
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 500, 350);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 484, 311);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblInicio_2 = new JLabel("Forma de pago");
		lblInicio_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInicio_2.setBounds(106, 0, 240, 31);
		panel.add(lblInicio_2);
		
		JScrollPane scrollPane_membresias_membresias = new JScrollPane();
		scrollPane_membresias_membresias.setBounds(10, 173, 464, 116);
		panel.add(scrollPane_membresias_membresias);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idSeleccionado = (Integer) table.getValueAt(table.getSelectedRow(), 0);
				activarBotones();
			}
		});
		scrollPane_membresias_membresias.setViewportView(table);
		
		btnAgregarFormaDe = new JButton("Agregar forma de pago");
		btnAgregarFormaDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarPago();
			}
		});
		btnAgregarFormaDe.setForeground(Color.WHITE);
		btnAgregarFormaDe.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAgregarFormaDe.setFocusPainted(false);
		btnAgregarFormaDe.setBorder(null);
		btnAgregarFormaDe.setBackground(new Color(46, 56, 64));
		btnAgregarFormaDe.setBounds(10, 137, 150, 25);
		panel.add(btnAgregarFormaDe);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarPago();
			}
		});
		btnBorrar.setForeground(Color.WHITE);
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBorrar.setFocusPainted(false);
		btnBorrar.setBorder(null);
		btnBorrar.setBackground(new Color(46, 56, 64));
		btnBorrar.setBounds(167, 137, 89, 25);
		panel.add(btnBorrar);
		
		textNumero = new JTextField();
		textNumero.setFont(new Font("Tahoma", Font.BOLD, 14));
		textNumero.setEditable(false);
		textNumero.setColumns(10);
		textNumero.setBorder(null);
		textNumero.setBounds(116, 42, 155, 25);
		panel.add(textNumero);
		
		textCedula = new JTextField();
		textCedula.setFont(new Font("Tahoma", Font.BOLD, 14));
		textCedula.setEditable(false);
		textCedula.setColumns(10);
		textCedula.setBorder(null);
		textCedula.setBounds(324, 42, 150, 25);
		panel.add(textCedula);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Número de factura");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2.setBounds(10, 49, 99, 14);
		panel.add(lblNewLabel_1_4_2);
		
		JLabel lblNewLabel_1_4_2_1 = new JLabel("Cédula");
		lblNewLabel_1_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1.setBounds(281, 49, 43, 14);
		panel.add(lblNewLabel_1_4_2_1);
		
		JLabel lblNewLabel_1_4_2_1_1 = new JLabel("Forma de pago");
		lblNewLabel_1_4_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1_1.setBounds(10, 97, 79, 14);
		panel.add(lblNewLabel_1_4_2_1_1);
		
		JLabel lblNewLabel_1_4_2_1_1_1 = new JLabel("Monto");
		lblNewLabel_1_4_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1_1_1.setBounds(348, 97, 49, 14);
		panel.add(lblNewLabel_1_4_2_1_1_1);
		
		textMonto = new JTextField();
		textMonto.setText("0.0");
		textMonto.setColumns(10);
		textMonto.setBounds(395, 92, 79, 25);
		panel.add(textMonto);
		
		comboBoxFormaPago = new JComboBox<String>();
		comboBoxFormaPago.setEditable(true);
		comboBoxFormaPago.setBounds(94, 92, 230, 25);
		panel.add(comboBoxFormaPago);
		
		JLabel lblNewLabel_1_4_2_1_1_1_1 = new JLabel("Saldo:");
		lblNewLabel_1_4_2_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1_1_1_1.setBounds(311, 142, 49, 14);
		panel.add(lblNewLabel_1_4_2_1_1_1_1);
		
		textSaldo = new JTextField();
		textSaldo.setEditable(false);
		textSaldo.setText("0.0");
		textSaldo.setColumns(10);
		textSaldo.setBounds(348, 139, 79, 25);
		panel.add(textSaldo);
		
		listar();
		bloquearBotones();
	}
}
