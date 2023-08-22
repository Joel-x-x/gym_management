package com.gym.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import com.gym.model.Clase;

public class FacturaFrame extends JFrame {
	private static final long serialVersionUID = -7374175822145503705L;
	private JPanel contentPane;
	private JTextField textCliente;
	private JTextField textBuscar;
	private JTextField textEstablecimiento;
	private JTextField textField_1;
	private JTable table;
	private JButton btnConsumidor;
	private JButton btnBuscarCliente;
	private JButton btnLimpiarCliente;
	private JLabel textNombre;
	private JButton btnBuscarMembresia;
	private JButton btnLimpiarMembresia;
	private JScrollPane scrollPane;
	private JLabel labelEstablecimiento;
	private JLabel labelNumeroFactura;
	private JComboBox<Clase> comboBoxClase;
	private JDateChooser dateChooser;
	private JButton btnGuardar;
	private JLabel labelSubtotal;
	private JLabel labelIVA;
	private JLabel labelTotal;
	
	public static void main(String[] args) {
		new FacturaFrame().setVisible(true);;
		
	}

	public FacturaFrame() {
		
		setSize(1080, 800);
		setLocationRelativeTo(null);
		setTitle("Factura");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1064, 761);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblFactura = new JLabel("FACTURA");
		lblFactura.setHorizontalAlignment(SwingConstants.CENTER);
		lblFactura.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFactura.setBounds(0, 25, 1064, 46);
		panel.add(lblFactura);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Cliente");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4_2.setBounds(36, 88, 50, 14);
		panel.add(lblNewLabel_1_4_2);
		
		textCliente = new JTextField();
		textCliente.setColumns(10);
		textCliente.setBounds(85, 82, 150, 25);
		panel.add(textCliente);
		
		btnConsumidor = new JButton("Cons. Final");
		btnConsumidor.setForeground(Color.WHITE);
		btnConsumidor.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnConsumidor.setFocusPainted(false);
		btnConsumidor.setBorder(null);
		btnConsumidor.setBackground(new Color(46, 56, 64));
		btnConsumidor.setBounds(36, 124, 100, 30);
		panel.add(btnConsumidor);
		
		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setForeground(Color.WHITE);
		btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarCliente.setFocusPainted(false);
		btnBuscarCliente.setBorder(null);
		btnBuscarCliente.setBackground(new Color(46, 56, 64));
		btnBuscarCliente.setBounds(146, 124, 100, 30);
		panel.add(btnBuscarCliente);
		
		btnLimpiarCliente = new JButton("Limpiar");
		btnLimpiarCliente.setForeground(Color.WHITE);
		btnLimpiarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiarCliente.setFocusPainted(false);
		btnLimpiarCliente.setBorder(null);
		btnLimpiarCliente.setBackground(new Color(46, 56, 64));
		btnLimpiarCliente.setBounds(256, 124, 100, 30);
		panel.add(btnLimpiarCliente);
		
		JLabel lblNewLabel_1_4_2_1 = new JLabel("Nombre:");
		lblNewLabel_1_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1.setBounds(36, 184, 50, 14);
		panel.add(lblNewLabel_1_4_2_1);
		
		textNombre = new JLabel("Nombre");
		textNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		textNombre.setBounds(85, 166, 350, 46);
		panel.add(textNombre);
		
		JLabel lblNewLabel_1_4_2_2 = new JLabel("Buscar membresía");
		lblNewLabel_1_4_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_2.setBounds(36, 228, 99, 14);
		panel.add(lblNewLabel_1_4_2_2);
		
		textBuscar = new JTextField();
		textBuscar.setColumns(10);
		textBuscar.setBounds(129, 223, 150, 25);
		panel.add(textBuscar);
		
		btnBuscarMembresia = new JButton("Buscar");
		btnBuscarMembresia.setForeground(Color.WHITE);
		btnBuscarMembresia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarMembresia.setFocusPainted(false);
		btnBuscarMembresia.setBorder(null);
		btnBuscarMembresia.setBackground(new Color(46, 56, 64));
		btnBuscarMembresia.setBounds(289, 223, 100, 25);
		panel.add(btnBuscarMembresia);
		
		btnLimpiarMembresia = new JButton("Limpiar");
		btnLimpiarMembresia.setForeground(Color.WHITE);
		btnLimpiarMembresia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpiarMembresia.setFocusPainted(false);
		btnLimpiarMembresia.setBorder(null);
		btnLimpiarMembresia.setBackground(new Color(46, 56, 64));
		btnLimpiarMembresia.setBounds(399, 223, 100, 25);
		panel.add(btnLimpiarMembresia);
		
		JLabel lblNewLabel_1_4_2_3 = new JLabel("No Factura");
		lblNewLabel_1_4_2_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4_2_3.setBounds(618, 88, 77, 14);
		panel.add(lblNewLabel_1_4_2_3);
		
		labelEstablecimiento = new JLabel("001-001");
		labelEstablecimiento.setForeground(new Color(128, 128, 128));
		labelEstablecimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelEstablecimiento.setBounds(689, 70, 70, 46);
		panel.add(labelEstablecimiento);
		
		labelNumeroFactura = new JLabel("000000001");
		labelNumeroFactura.setForeground(new Color(0, 0, 0));
		labelNumeroFactura.setFont(new Font("Tahoma", Font.BOLD, 24));
		labelNumeroFactura.setBounds(774, 70, 250, 46);
		panel.add(labelNumeroFactura);
		
		JLabel Establecimiento = new JLabel("Establecimiento");
		Establecimiento.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento.setBounds(618, 129, 99, 14);
		panel.add(Establecimiento);
		
		textEstablecimiento = new JTextField();
		textEstablecimiento.setEditable(false);
		textEstablecimiento.setDragEnabled(true);
		textEstablecimiento.setColumns(10);
		textEstablecimiento.setBounds(711, 124, 250, 25);
		panel.add(textEstablecimiento);
		
		JLabel Establecimiento_1 = new JLabel("Forma de pago");
		Establecimiento_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1.setBounds(618, 171, 99, 14);
		panel.add(Establecimiento_1);
		
		JLabel Establecimiento_1_1 = new JLabel("Descuento %");
		Establecimiento_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1_1.setBounds(618, 211, 77, 14);
		panel.add(Establecimiento_1_1);
		
		textField_1 = new JTextField();
		textField_1.setDragEnabled(true);
		textField_1.setColumns(10);
		textField_1.setBounds(689, 206, 50, 25);
		panel.add(textField_1);
		
		JLabel Establecimiento_1_1_1 = new JLabel("Fecha");
		Establecimiento_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		Establecimiento_1_1_1.setBounds(759, 211, 50, 14);
		panel.add(Establecimiento_1_1_1);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(799, 206, 162, 25);
		panel.add(dateChooser);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(Color.WHITE);
		scrollPane.setAutoscrolls(true);
		scrollPane.setBounds(36, 259, 988, 248);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento.setBounds(745, 545, 129, 46);
		panel.add(lblDescuento);
		
		comboBoxClase = new JComboBox<Clase>();
		//comboBoxClase.setSelectedIndex(0);
		comboBoxClase.setBounds(710, 166, 250, 25);
		panel.add(comboBoxClase);
		
		JLabel lblDescuento_1 = new JLabel("Subtotal");
		lblDescuento_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento_1.setBounds(745, 592, 129, 46);
		panel.add(lblDescuento_1);
		
		JLabel lblDescuento_2 = new JLabel("IVA");
		lblDescuento_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento_2.setBounds(745, 640, 129, 46);
		panel.add(lblDescuento_2);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(745, 687, 129, 46);
		panel.add(lblTotal);
		
		JLabel labelDescuento = new JLabel("0.0");
		labelDescuento.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelDescuento.setBounds(884, 545, 129, 46);
		panel.add(labelDescuento);
		
		labelSubtotal = new JLabel("0.0");
		labelSubtotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelSubtotal.setBounds(884, 592, 129, 46);
		panel.add(labelSubtotal);
		
		labelIVA = new JLabel("0.0");
		labelIVA.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelIVA.setBounds(884, 640, 129, 46);
		panel.add(labelIVA);
		
		labelTotal = new JLabel("0.0");
		labelTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelTotal.setBounds(884, 687, 129, 46);
		panel.add(labelTotal);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setForeground(Color.WHITE);
		btnGuardar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnGuardar.setFocusPainted(false);
		btnGuardar.setBorder(null);
		btnGuardar.setBackground(new Color(46, 56, 64));
		btnGuardar.setBounds(36, 701, 100, 30);
		panel.add(btnGuardar);
	}
}
