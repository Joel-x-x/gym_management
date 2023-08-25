package com.gym.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;

import com.gym.controller.AdministradorController;
import com.gym.controller.FacturaController;
import com.gym.controller.MembresiaController;
import com.gym.model.Administrador;
import com.gym.model.Clase;
import com.gym.model.Factura;
import com.gym.model.Membresia;
import com.gym.model.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FacturaFrame extends JFrame implements GenerarFrameInterfaz{
	private GenerarFacturaFrameInterfaz frame;
	private FacturaController facturaController;
	private AdministradorController administradorController;
	private MembresiaController membresiaController;
	private DefaultTableModel modelo;
	private int administrador_id;
	private int usuario_id;
	private int factura_id;
	private int idSeleccionado;
	
	private DefaultComboBoxModel<String> comboBoxModelFormaPago;
	private JComboBox<String> comboBoxFormaPago;
	
	private static final long serialVersionUID = -7374175822145503705L;
	private JPanel contentPane;
	private JTextField textCliente;
	private JTextField textEstablecimiento;
	private JTextField textField_1;
	private JTable table;
	private JButton btnBuscarCliente;
	private JLabel labelCliente;
	private JButton btnBuscarMembresia;
	private JButton btnEliminarMembresia;
	private JScrollPane scrollPane;
	private JLabel labelEstablecimiento;
	private JLabel labelNumeroFactura;
	private JDateChooser dateChooser;
	private JButton btnGuardar;
	private JLabel labelSubtotal;
	private JLabel labelIVA;
	private JLabel labelTotal;
	
	public void crearFactura() {
		
		if(!facturaController.ultimaFacturaIncompleta(administrador_id)) {
			// Crear Factura
			facturaController.crearFactura(administrador_id);
		}
		
		// Traer los datos de la factura
		Factura factura = facturaController.consultarUltimaFactura(administrador_id);
		
		factura_id = factura.getId();
		
		labelEstablecimiento.setText(factura.getEstablecimiento() + " - " + factura.getPunto_emision());
		labelNumeroFactura.setText(factura.getNumero_factura());
		textEstablecimiento.setText(administradorController.getNombreUsuario(administrador_id) 
		+ " " + administradorController.getApellidoUsuario(administrador_id));		
		
		
		// Elmentos tipo pago
		List<String> tipo = new ArrayList<>(Arrays.asList("efectivo", "transferencia"));
		
		comboBoxModelFormaPago.addAll(tipo);
		comboBoxFormaPago.setModel(comboBoxModelFormaPago);
		comboBoxFormaPago.setSelectedItem(factura.getForma_pago());
		
		dateChooser.setDate(factura.getFecha());	
	}
	
	public void listarMembresias() {
		String[] cabeceras = {"Id","Membresia","Clase","Entrenador","Precio"};
		
		modelo = new DefaultTableModel(membresiaController.listarMembresiaFactura(administrador_id, factura_id), cabeceras);
		table.setModel(modelo);
	}
	
	@Override
	public void listarUsuarios() {
		UsuariosFrame usuarios = new UsuariosFrame(this);
		usuarios.setVisible(true);
	}

	@Override
	public void usuarioSeleccionado(Usuario usuario) {
		textCliente.setText("      " + usuario.getCedula());
		labelCliente.setText(usuario.getNombre() + " " + usuario.getApellido());
		
		usuario_id = usuario.getId();
	}
	
	@Override
	public void listarTipoMembresias() {
		if(textCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecciona un usuario primero");
			return;
		}
		
		TiposMembresiasFrame tiposMembresiasFrame = new TiposMembresiasFrame(this);
		tiposMembresiasFrame.setVisible(true);
	}
	
	// Insertar membresias
	@Override
	public void tipoMembresiaSeleccionada(int id) {
		// Elemento ya seleccionado no hacer nada
		if(validarSiExiste(id)) return;
		
		// Si ocurre un error al agregar la membresia
		if(!membresiaController.crearMembresia(administrador_id, usuario_id, factura_id, id)) 
			JOptionPane.showMessageDialog(null, "Ocurrio un error");
		
		listarMembresias();
		
	}
	
	// Eliminar membresias
	public void eliminarMembresia() {
		if(membresiaController.eliminar(idSeleccionado)) {
			JOptionPane.showMessageDialog(null, "Eliminado con exito!");
			listarMembresias();
			btnEliminarMembresia.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, "No se puedo eliminar");
		}
	}
	
	private boolean validarSiExiste(int id) {
		// Si esta vacio retornamos false
		if(membresiaController.listarMembresiaFacturaList(administrador_id, factura_id).isEmpty()) return false;
		
		for(Membresia membresia : membresiaController.listarMembresiaFacturaList(administrador_id, factura_id)) {
			if(membresia.getId() == id) {
				return true;
			}
		}
		
		return false;
	}
	
	// Actuliza el total subtotal e iva
	public void actualizarDatosFactura() {
	}

	public FacturaFrame(GenerarFacturaFrameInterfaz frame) {
		this.frame = frame;
		administrador_id = new Administrador().getId();
		
		facturaController = new FacturaController();
		administradorController = new AdministradorController();
		membresiaController = new MembresiaController();
		comboBoxModelFormaPago = new DefaultComboBoxModel<>();
		
		setSize(1080, 800);
		setLocationRelativeTo(null);
		setTitle("Factura");
		setResizable(false);
		setVisible(true);
		
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
		textCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		textCliente.setForeground(new Color(0, 64, 128));
		textCliente.setEditable(false);
		textCliente.setColumns(10);
		textCliente.setBounds(85, 82, 150, 25);
		panel.add(textCliente);
		
		btnBuscarCliente = new JButton("Buscar un cliente");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarUsuarios();
			}
		});
		btnBuscarCliente.setForeground(Color.WHITE);
		btnBuscarCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarCliente.setFocusPainted(false);
		btnBuscarCliente.setBorder(null);
		btnBuscarCliente.setBackground(new Color(46, 56, 64));
		btnBuscarCliente.setBounds(36, 125, 199, 30);
		panel.add(btnBuscarCliente);
		
		JLabel lblNewLabel_1_4_2_1 = new JLabel("Nombre:");
		lblNewLabel_1_4_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_4_2_1.setBounds(36, 184, 50, 14);
		panel.add(lblNewLabel_1_4_2_1);
		
		labelCliente = new JLabel("Nombre");
		labelCliente.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelCliente.setBounds(85, 166, 350, 46);
		panel.add(labelCliente);
		
		btnBuscarMembresia = new JButton("Buscar membresías");
		btnBuscarMembresia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarTipoMembresias();
			}
		});
		btnBuscarMembresia.setForeground(Color.WHITE);
		btnBuscarMembresia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnBuscarMembresia.setFocusPainted(false);
		btnBuscarMembresia.setBorder(null);
		btnBuscarMembresia.setBackground(new Color(46, 56, 64));
		btnBuscarMembresia.setBounds(36, 223, 199, 25);
		panel.add(btnBuscarMembresia);
		
		btnEliminarMembresia = new JButton("Eliminar");
		btnEliminarMembresia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarMembresia();
			}
		});
		btnEliminarMembresia.setEnabled(false);
		btnEliminarMembresia.setForeground(Color.WHITE);
		btnEliminarMembresia.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnEliminarMembresia.setFocusPainted(false);
		btnEliminarMembresia.setBorder(null);
		btnEliminarMembresia.setBackground(new Color(46, 56, 64));
		btnEliminarMembresia.setBounds(245, 223, 100, 25);
		panel.add(btnEliminarMembresia);
		
		JLabel lblNewLabel_1_4_2_3 = new JLabel("No Factura");
		lblNewLabel_1_4_2_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1_4_2_3.setBounds(618, 88, 77, 14);
		panel.add(lblNewLabel_1_4_2_3);
		
		labelEstablecimiento = new JLabel("");
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
		textField_1.setEditable(false);
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
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				idSeleccionado = (int) table.getValueAt(table.getSelectedRow(), 0);
				
				btnEliminarMembresia.setEnabled(true);
			}
		});
		table.setBackground(Color.WHITE);
		scrollPane.setViewportView(table);
		
		JLabel lblDescuento = new JLabel("Descuento");
		lblDescuento.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDescuento.setBounds(745, 545, 129, 46);
		panel.add(lblDescuento);
		
		comboBoxFormaPago = new JComboBox<>();
		//comboBoxClase.setSelectedIndex(0);
		comboBoxFormaPago.setBounds(710, 166, 250, 25);
		panel.add(comboBoxFormaPago);
		
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
		
		crearFactura();
		listarMembresias();
	}

}
