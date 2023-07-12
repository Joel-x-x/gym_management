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

public class InicioPanel extends JPanel {
	UsuarioController usuarioController;
	RegistroController registroController;
	MembresiaController membresiaController;
	private int administrador_id; 
	private JComboBox<Membresia> comboBoxMembresia = new JComboBox<>();
	private DefaultComboBoxModel<Membresia> comboBoxModelMembresia = new DefaultComboBoxModel<>();

	private static final long serialVersionUID = 1L;
    private JTable tableUsuarios;
    private JTextField txt_cedula;
    private int idSeleccionadoUsuario = 0;
    private int idUltimoRegistro;
  
    public static DefaultTableModel modelo;
    private JTable table;
    private JButton btn_entrada;
    private JButton btn_salida;
    private JDateChooser dateChooserFin;
    private JDateChooser dateChooserInicio;
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private JButton btnBuscarFecha;
    private JButton btnListar;
    
    private void listarUsuarios() {
		String[] cabeceras = {"Id","Nombre","Apellido","Fecha_Nacimiento","Sexo","Correo","Cedula"};
		
		modelo = new DefaultTableModel(usuarioController.listar(administrador_id),cabeceras);
		tableUsuarios.setModel(modelo);
    }
    
	public void consultarUsuario() {
		String[] cabeceras = {"Id","Nombre","Apellido","Fecha_Nacimiento","Sexo","Correo","Cedula"};
		
		modelo = new DefaultTableModel(usuarioController.consultar(administrador_id, txt_cedula.getText()),cabeceras);
		tableUsuarios.setModel(modelo);
		
	}
	
	public void listarRegistros() {
		String[] cabeceras = {"Id","Nombre", "Fecha de entrada","Fecha de salida", "Plan", "Clase", "Membresia"};
		
		modelo = new DefaultTableModel(registroController.consultar(idSeleccionadoUsuario),cabeceras);
		table.setModel(modelo);
	}
	
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
			JOptionPane.showMessageDialog(null, "La membresia caducara en " + membresia.getAnticipacion() + " días");
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
    
	public InicioPanel(int panelAncho, int panelAlto) {
		
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
        setBackground(new Color(255, 255, 255));
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("REGISTRO");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(30, 359, 113, 39);
        add(lblNewLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 124, 993, 234);
        add(scrollPane);
        
        tableUsuarios = new JTable();
        tableUsuarios.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//abotones();
        		idSeleccionadoUsuario = (int)tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(),0);
        		listarRegistros();
        		validarRegistros();
        		listarClases();
        		btnBuscarFecha.setEnabled(true);
        		btnListar.setEnabled(true);
        	}
        });
        scrollPane.setViewportView(tableUsuarios);
        
        txt_cedula = new JTextField();
        txt_cedula.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		consultarUsuario();
        	}
        });
        txt_cedula.setColumns(10);
        txt_cedula.setBounds(129, 88, 194, 25);
        add(txt_cedula);
        
        JLabel lblNewLabel_2_1 = new JLabel("Buscar por nombre:");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1.setBounds(29, 93, 101, 14);
        add(lblNewLabel_2_1);
        
        JLabel lblInicio = new JLabel("INICIO");

        lblInicio.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblInicio.setBounds(30, 30, 84, 49);
        add(lblInicio);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(30, 450, 993, 248);
        add(scrollPane_1);
        
        table = new JTable();
        scrollPane_1.setViewportView(table);
        
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
        btn_entrada.setBounds(30, 409, 150, 30);
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
        btn_salida.setBounds(190, 409, 150, 30);
        add(btn_salida);
        
        JButton btn_buscar = new JButton("Buscar");
        btn_buscar.setFocusPainted(false);
        btn_buscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		consultarUsuario();
        	}
        });
        btn_buscar.setForeground(Color.WHITE);
        btn_buscar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_buscar.setBorder(null);
        btn_buscar.setBackground(new Color(46, 56, 64));
        btn_buscar.setBounds(333, 88, 150, 25);
        add(btn_buscar);
        
        JLabel lblNewLabel_5_1 = new JLabel("¡ Selecciona un usuario y una clase");
        lblNewLabel_5_1.setForeground(Color.BLACK);
        lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_5_1.setBounds(153, 370, 233, 14);
        add(lblNewLabel_5_1);
        
        comboBoxMembresia = new JComboBox<Membresia>();
        comboBoxMembresia.setBounds(350, 409, 150, 30);
        add(comboBoxMembresia);
        
        JLabel lblNewLabel_3 = new JLabel("Clase");
        lblNewLabel_3.setBounds(350, 391, 76, 14);
        add(lblNewLabel_3);
        
        dateChooserInicio = new JDateChooser();
        dateChooserInicio.setBounds(517, 409, 138, 30);
        add(dateChooserInicio);
        
        dateChooserFin = new JDateChooser();
        dateChooserFin.setBounds(665, 409, 138, 30);
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
        btnBuscarFecha.setBounds(813, 409, 100, 30);
        add(btnBuscarFecha);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Fecha inicio");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1_1.setBounds(517, 391, 131, 14);
        add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_2_1_1_1 = new JLabel("Fecha fin");
        lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1_1_1.setBounds(665, 391, 131, 14);
        add(lblNewLabel_2_1_1_1);
        

        
        JLabel lblNewLabel_5_1_1 = new JLabel("¡ Selecciona un usario para listar todos sus registros");
        lblNewLabel_5_1_1.setForeground(Color.BLACK);
        lblNewLabel_5_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_5_1_1.setBounds(504, 93, 331, 14);
        add(lblNewLabel_5_1_1);
        
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
        btnListar.setBounds(923, 409, 100, 30);
        add(btnListar);
        
        listarUsuarios();
        btnBuscarFecha.setEnabled(false);
        btnListar.setEnabled(false);
    }
}
