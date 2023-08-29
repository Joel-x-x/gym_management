package com.gym.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.MembresiaController;
import com.gym.controller.RegistroController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Membresia;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class RegistrosDiariosPanel extends JPanel {
	UsuarioController usuarioController;
	RegistroController registroController;
	MembresiaController membresiaController;
	private int administrador_id; 
	private JComboBox<Membresia> comboBoxMembresia = new JComboBox<>();
	private DefaultComboBoxModel<Membresia> comboBoxModelMembresia = new DefaultComboBoxModel<>();

	private static final long serialVersionUID = 1L;
    private int idSeleccionadoUsuario = 0;
    private int idUltimoRegistro;
    private JButton btn_entrada;
    private JButton btn_salida;
    public static DefaultTableModel modelo;
    private JDateChooser dateChooserFin;
    private JDateChooser dateChooserInicio;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private JButton btnBuscarFecha;
    private JButton btnListar;
    private JButton btn_entrada_1;
    private JLabel lblInicio_1;
    private JButton btn_entrada_2;
    private JLabel labelFechaHoy;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JLabel lblPeso;
    private JTextField textField_3;
    private JTextField textVencimiento;
    private JLabel labelFechaHoy_1;
    private JLabel lblNewLabel_1;
    private JLabel lblInicio_2;
    private JPanel panel_1;
    private JLabel lblInicio_3;
    private JPanel panel_2;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_4;
    private JLabel lblNewLabel_5;
	
	public void registrar() {

		if(idSeleccionadoUsuario == 0) {
			JOptionPane.showMessageDialog(null, "Selecciona un usuario");
			return;
		}
		
		if(getIdClaseComboBox() == 0) {
			JOptionPane.showMessageDialog(null, "Selecciona una clase si no tiene, crea una nueva membresia");
			return;
		}
		
		if(!membresiaController.consultaActivo(idSeleccionadoUsuario, getIdClaseComboBox())) {
			JOptionPane.showMessageDialog(null, "La membresia de este usuario a caducado o no tiene membresias");
			return;
		}
		
		Membresia membresia = membresiaController.consultaMembresia(idSeleccionadoUsuario, getIdClaseComboBox());
		
		System.out.println(membresia.notificarMembresia() + "membresia");
		
		if(membresia.notificarMembresia()) {
			JOptionPane.showMessageDialog(null, "La membresia caducara pronto");
		}
		
		registroController.registrar(idSeleccionadoUsuario, getIdClaseComboBox());
		listarRegistros();
		validarRegistros();
	}
	
	public int getIdClaseComboBox() {
		return ((Membresia) comboBoxMembresia.getSelectedItem()).getId();
	}
	
	private void listarClases() {
		comboBoxModelMembresia.removeAllElements();
		comboBoxModelMembresia.addAll(membresiaController.listarMembresias(idSeleccionadoUsuario));
		comboBoxMembresia.setModel(comboBoxModelMembresia);
		comboBoxMembresia.setSelectedIndex(0);
	}
	
	public void validarRegistros() {
		boolean salida = false;
		
		for(int i=0;i<registroController.consultar(idSeleccionadoUsuario).length;i++) {
			
			if(registroController.consultar(idSeleccionadoUsuario)[i][3]==null) {
				salida = true;
				System.out.println(salida);
				idUltimoRegistro = registroController.consultarLista(idSeleccionadoUsuario).get(i).getId();
			}
		}
		
		if(salida) {
			btn_salida.setEnabled(true);
			btn_entrada.setEnabled(false);
		}else {
			btn_entrada.setEnabled(true);
			btn_salida.setEnabled(false);
		}
		
	}

	public void bloquearBotones(){
		btn_entrada.setEnabled(false);
		btn_salida.setEnabled(false);
	}
	
	public void buscarRegistros() {
		fechaInicio = dateChooserInicio.getCalendar();
		fechaFin = dateChooserFin.getCalendar();
		
		if(fechaInicio == null) {
			JOptionPane.showMessageDialog(null, "El campo fecha inicio no puede ir vacio");
			return;
		}
		
		if(fechaFin == null) {
			JOptionPane.showMessageDialog(null, "El campo fecha fin no puede ir vacio");
			return;
		}
		
		fechaFin.add(Calendar.DAY_OF_MONTH, 1);
		
        Date fechaInicioSQL = new Date(fechaInicio.getTimeInMillis());
        Date fechaFinSQL = new Date(fechaFin.getTimeInMillis());
		
		String[] cabeceras = {"Id","Nombre", "Fecha de entrada","Fecha de salida", "Plan", "Clase", "Membresia"};
		
		modelo = new DefaultTableModel(registroController.consultarFecha(idSeleccionadoUsuario, fechaInicioSQL, fechaFinSQL ),cabeceras);
		table.setModel(modelo);
	}
    
	public RegistrosDiariosPanel(int panelAncho, int panelAlto) {
		
		 usuarioController = new UsuarioController();
		 registroController = new RegistroController(); 
		 membresiaController = new MembresiaController();
		 administrador_id = new Administrador().getId();
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				bloquearBotones();
			}
		});
    	
    	setPreferredSize(new Dimension(1080, 800));
        setBackground(new Color(244, 244, 244));
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("REGISTRO");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(30, 501, 113, 39);
        add(lblNewLabel);
        
        JLabel lblInicio = new JLabel("Registro de usuarios");

        lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblInicio.setBounds(30, 30, 281, 49);
        add(lblInicio);
        
        btn_entrada = new JButton("Registrar Entrada");
        btn_entrada.setFocusPainted(false);
        btn_entrada.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registrar();
        	}
        });
        btn_entrada.setForeground(Color.WHITE);
        btn_entrada.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_entrada.setBorder(null);
        btn_entrada.setBackground(new Color(31, 33, 38));
        btn_entrada.setBounds(30, 551, 150, 30);
        add(btn_entrada);
        
        btn_salida = new JButton("Registrar Salida");
        btn_salida.setFocusPainted(false);
        btn_salida.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registroController.registrarSalida(idUltimoRegistro);
        		listarRegistros();
        		validarRegistros();
        	}
        });
        btn_salida.setForeground(Color.WHITE);
        btn_salida.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_salida.setBorder(null);
        btn_salida.setBackground(new Color(203, 39, 39));
        btn_salida.setBounds(190, 551, 150, 30);
        add(btn_salida);
        
        comboBoxMembresia = new JComboBox<Membresia>();
        comboBoxMembresia.setBackground(new Color(255, 255, 255));
        comboBoxMembresia.setBounds(423, 485, 250, 30);
        add(comboBoxMembresia);
        
        JLabel lblNewLabel_3 = new JLabel("Membresía");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(423, 467, 95, 14);
        add(lblNewLabel_3);
        
        dateChooserInicio = new JDateChooser();
        dateChooserInicio.setBounds(30, 610, 138, 30);
        add(dateChooserInicio);
        
        dateChooserFin = new JDateChooser();
        dateChooserFin.setBounds(178, 610, 138, 30);
        add(dateChooserFin);
        
        btnBuscarFecha = new JButton("Buscar");
        btnBuscarFecha.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {	
        		buscarRegistros();
        	}
        });
        btnBuscarFecha.setForeground(Color.WHITE);
        btnBuscarFecha.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscarFecha.setFocusPainted(false);
        btnBuscarFecha.setBorder(null);
        btnBuscarFecha.setBackground(new Color(46, 56, 64));
        btnBuscarFecha.setBounds(86, 664, 100, 30);
        add(btnBuscarFecha);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Fecha inicio");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1_1.setBounds(30, 592, 131, 14);
        add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_2_1_1_1 = new JLabel("Fecha fin");
        lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1_1_1.setBounds(178, 592, 131, 14);
        add(lblNewLabel_2_1_1_1);
        
        btnListar = new JButton("Listar");
        btnListar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		listarRegistros();
        		dateChooserInicio.setDate(null);
        		dateChooserFin.setDate(null);
        		
        	}
        });
        btnListar.setForeground(Color.WHITE);
        btnListar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnListar.setFocusPainted(false);
        btnListar.setBorder(null);
        btnListar.setBackground(new Color(46, 56, 64));
        btnListar.setBounds(196, 664, 100, 30);
        add(btnListar);
        
        btnBuscarFecha.setEnabled(false);
        btnListar.setEnabled(false);
        
        btn_entrada_1 = new JButton("Registrar usuario");
        btn_entrada_1.setForeground(Color.WHITE);
        btn_entrada_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_entrada_1.setFocusPainted(false);
        btn_entrada_1.setBorder(null);
        btn_entrada_1.setBackground(new Color(31, 33, 38));
        btn_entrada_1.setBounds(328, 653, 200, 30);
        add(btn_entrada_1);
        
        lblInicio_1 = new JLabel("Registro manual");
        lblInicio_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblInicio_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblInicio_1.setBounds(0, 604, 1080, 49);
        add(lblInicio_1);
        
        btn_entrada_2 = new JButton("Listar registros");
        btn_entrada_2.setForeground(Color.WHITE);
        btn_entrada_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_entrada_2.setFocusPainted(false);
        btn_entrada_2.setBorder(null);
        btn_entrada_2.setBackground(new Color(31, 33, 38));
        btn_entrada_2.setBounds(538, 653, 200, 30);
        add(btn_entrada_2);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        panel.setBounds(240, 156, 600, 300);
        add(panel);
        panel.setLayout(null);
        
        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(29, 24, 87, 30);
        panel.add(lblNombre);
        
        lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblApellido.setBounds(29, 65, 87, 30);
        panel.add(lblApellido);
        
        textField = new JTextField();
        textField.setBorder(null);
        textField.setEditable(false);
        textField.setBounds(110, 29, 250, 25);
        panel.add(textField);
        textField.setColumns(10);
        
        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setColumns(10);
        textField_1.setBorder(null);
        textField_1.setBounds(110, 70, 250, 25);
        panel.add(textField_1);
        
        JLabel lblCi = new JLabel("CI. 1850038314");
        lblCi.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblCi.setBounds(420, 11, 142, 30);
        panel.add(lblCi);
        
        JLabel lblEstatura = new JLabel("Estatura");
        lblEstatura.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstatura.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEstatura.setBounds(62, 106, 100, 30);
        panel.add(lblEstatura);
        
        textField_2 = new JTextField();
        textField_2.setEditable(false);
        textField_2.setColumns(10);
        textField_2.setBorder(null);
        textField_2.setBounds(62, 133, 100, 25);
        panel.add(textField_2);
        
        lblPeso = new JLabel("Peso");
        lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
        lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPeso.setBounds(181, 106, 100, 30);
        panel.add(lblPeso);
        
        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setColumns(10);
        textField_3.setBorder(null);
        textField_3.setBounds(181, 133, 100, 25);
        panel.add(textField_3);
        
        textVencimiento = new JTextField();
        textVencimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
        textVencimiento.setText("Vence en 15 días 3 horas y 4 minutos");
        textVencimiento.setEditable(false);
        textVencimiento.setColumns(10);
        textVencimiento.setBorder(null);
        textVencimiento.setBounds(29, 196, 331, 30);
        panel.add(textVencimiento);
        
        labelFechaHoy_1 = new JLabel("Hoy es Lunes 28 de Agosto del 2023");
        labelFechaHoy_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelFechaHoy_1.setBounds(29, 259, 399, 30);
        panel.add(labelFechaHoy_1);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(RegistrosDiariosPanel.class.getResource("/com/gym/resources/negro.png")));
        lblNewLabel_1.setBounds(432, 100, 100, 100);
        panel.add(lblNewLabel_1);
        
        JLabel lblInicio_1_1 = new JLabel("Registro automático");
        lblInicio_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblInicio_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblInicio_1_1.setBounds(0, 74, 1080, 49);
        add(lblInicio_1_1);
        
        labelFechaHoy = new JLabel("Hoy es Lunes 28 de Agosto del 2023");
        labelFechaHoy.setFont(new Font("Tahoma", Font.PLAIN, 14));
        labelFechaHoy.setBounds(240, 118, 399, 30);
        add(labelFechaHoy);
        
        lblInicio_2 = new JLabel("Lector dactilar");
        lblInicio_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblInicio_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblInicio_2.setBounds(0, 181, 240, 49);
        add(lblInicio_2);
        
        panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel_1.setBounds(20, 241, 200, 96);
        add(panel_1);
        panel_1.setLayout(null);
        
        lblInicio_3 = new JLabel("Coloca tu dedo");
        lblInicio_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblInicio_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblInicio_3.setBounds(0, 23, 200, 49);
        panel_1.add(lblInicio_3);
        
        panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBackground(new Color(23, 159, 38));
        panel_2.setBounds(423, 526, 250, 76);
        add(panel_2);
        
        lblNewLabel_2 = new JLabel("Si el usuario tiene más de una");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(10, 11, 230, 14);
        panel_2.add(lblNewLabel_2);
        
        lblNewLabel_4 = new JLabel("membresía vigente tienes que ");
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_4.setBounds(10, 29, 230, 14);
        panel_2.add(lblNewLabel_4);
        
        lblNewLabel_5 = new JLabel("seleccionar la que vaya a tomar.");
        lblNewLabel_5.setForeground(new Color(255, 255, 255));
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_5.setBounds(10, 48, 230, 14);
        panel_2.add(lblNewLabel_5);
    }
}
