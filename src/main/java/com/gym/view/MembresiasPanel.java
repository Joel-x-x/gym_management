package com.gym.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.json.ParseException;
import com.gym.controller.MembresiaController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Clase;
import com.gym.model.Membresia;
import com.gym.model.Plan;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class MembresiasPanel extends JPanel {
	private int administrador_id;
	private int idSeleccionado;
	private int idSeleccionadoMembresia;
	private DefaultTableModel modelo;
	private UsuarioController usuarioController;
	private MembresiaController membresiaController;
	private JComboBox<Plan> comboBoxPlan = new JComboBox<>();
	private DefaultComboBoxModel<Plan> comboBoxModelPlan = new DefaultComboBoxModel<>();
	private JComboBox<Clase> comboBoxClase = new JComboBox<>();
	private DefaultComboBoxModel<Clase> comboBoxModelClase = new DefaultComboBoxModel<>();
	
	private static final long serialVersionUID = -6442770399461125032L;
	private JTextField textValorExtra;
	private JTextField textBuscar;
	private JTable tableMembresias;
	private JTable tableUsuarios;
	private JLabel labelTotal;
	private JButton btnBuscar;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	
	private void listarUsuarios() {
		String[] cabeceras = {"Id","Nombre","Apellido","Nacimiento","Sexo","Email","Cedula","Dirección","Teléfono"};
		
		modelo = new DefaultTableModel(usuarioController.listar(administrador_id), cabeceras);
		tableUsuarios.setModel(modelo);
	}
	
	private void listarPlan() {
		comboBoxModelPlan.addAll(membresiaController.listarPlan(administrador_id));
		comboBoxPlan.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				calcularPrecioTotal();
			}
		});
		comboBoxPlan.setModel(comboBoxModelPlan);
		comboBoxPlan.setSelectedIndex(0);
	}
	
	private void listarClase() {
		comboBoxModelClase.addAll(membresiaController.listarClase(administrador_id));
		comboBoxClase.setModel(comboBoxModelClase);
		comboBoxClase.setSelectedIndex(0);
	}
	
	private void listarMembresias() {
		String[] cabeceras = {"Id","Fecha de Fin", "Plan", "Clase", "Valor Total"};
		
		modelo = new DefaultTableModel(membresiaController.listar(idSeleccionado), cabeceras);
		tableMembresias.setModel(modelo);
	}
	
	private void guardar() {
		Membresia membresia = llenarMembresia();
		
		if(membresiaController.guardar(membresia)) {
			JOptionPane.showMessageDialog(null, "Guardado con Exito!");
			listarMembresias();
			limpiarFormulario();
		} else {
			JOptionPane.showMessageDialog(null, "No se puedo guardar");
		}
	}
	
	private void modificar() {
		Membresia membresia = llenarMembresiaModificar();
		
		System.out.println(llenarMembresiaModificar().getFecha_fin());
		
		if(membresiaController.modificar(membresia)) {
			JOptionPane.showMessageDialog(null, "Modificado con Exito!");
			listarMembresias();
			limpiarFormulario();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo modificar");
		}
	}
 	
	private void eliminar() {
		if(membresiaController.eliminar(idSeleccionadoMembresia)) {
			JOptionPane.showMessageDialog(null, "Eliminado con Exito!");
			listarMembresias();
			limpiarFormulario();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo eliminar");
		}
	}
	
	private void calcularPrecioTotal() {
		
		float precio = 0;
		float valorTotal = 0;
		
		// Verificar que no este vacio el plan antes de extraer el precio
		if(!textValorExtra.getText().equals("") && comboBoxPlan.getSelectedIndex() != 0) {
			
			precio = ((Plan) comboBoxPlan.getSelectedItem()).getPrecio();
			
			valorTotal = precio + Float.parseFloat(textValorExtra.getText());
			
			labelTotal.setText(valorTotal+"");
		}
		
	}
	
	private Membresia llenarMembresia() {
		
		int idPlan = ((Plan) comboBoxPlan.getSelectedItem()).getId();
		String duracion = ((Plan) comboBoxPlan.getSelectedItem()).getDuracion();
		String fechaFin = fechaFin(duracion, "");
		
		int idClase = ((Clase) comboBoxClase.getSelectedItem()).getId();
		
		return new Membresia(
				fechaFin,
				idSeleccionado,
				idPlan,
				idClase,
				Float.parseFloat(textValorExtra.getText()),
				Float.parseFloat(labelTotal.getText()),
				administrador_id);
	}
	
	private Membresia llenarMembresiaModificar() {
		
		String fechaInicio = membresiaController.consulta(idSeleccionadoMembresia, idSeleccionado).getFecha_inicio();
		
		int idPlan = ((Plan) comboBoxPlan.getSelectedItem()).getId();
		String duracion = ((Plan) comboBoxPlan.getSelectedItem()).getDuracion();
		String fechaFin = fechaFin(duracion, fechaInicio);
		
		int idClase = ((Clase) comboBoxClase.getSelectedItem()).getId();
		
		return new Membresia(
				idSeleccionadoMembresia,
				fechaFin,
				idSeleccionado,
				idPlan,
				idClase,
				Float.parseFloat(textValorExtra.getText()),
				Float.parseFloat(labelTotal.getText()),
				administrador_id);
	}
	
	private void llenarFormulario() {
		Membresia membresia = membresiaController.consulta(idSeleccionadoMembresia, idSeleccionado);
		
		labelTotal.setText(membresia.getValor_total() + "");
		textValorExtra.setText(membresia.getValor_extra() + "");
		
		for(int i = 0; i < comboBoxPlan.getItemCount(); i++) {
			Plan plan = (Plan) comboBoxPlan.getItemAt(i);
			
			if(membresia.getPlan_id() == plan.getId()) {
				comboBoxPlan.setSelectedItem(plan);
			}
		}

		for(int i = 0; i < comboBoxClase.getItemCount(); i++) {
			Clase clase = (Clase) comboBoxClase.getItemAt(i);
			
			if(membresia.getClase_id() == clase.getId()) {
				comboBoxClase.setSelectedItem(clase);
			}
		}
		
	}
	
	private String fechaFin(String duracion, String fechaInicio) {
		
		Calendar calendar = null;
		
		if(fechaInicio.equals("")) {
			// Obtén la fecha actual
			calendar = Calendar.getInstance();
		} else {
			calendar = stringToCalendar(fechaInicio);
		}

		switch (duracion) {
		case "diario":
			// Agrega 1 dia a la fecha actual
			calendar.add(Calendar.DAY_OF_MONTH, 1); 
			break;
		case "mensual":
			// Agrega 1 mes a la fecha actual
			calendar.add(Calendar.MONTH, 1);
			break;
		case "anual":
			// Agrega 1 año a la fecha actual
			calendar.add(Calendar.YEAR, 1);
			break;
		default:
			break;
		}

		// Formatea la fecha en el formato deseado
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fechaFin = dateFormat.format(calendar.getTime());
		
		return fechaFin;
	}
	
	private Calendar stringToCalendar(String fecha) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		
		Calendar calendar = Calendar.getInstance();
        	
    	SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
		try {
			date = dateFormat.parse(fecha);
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        calendar.setTime(date);
            
        return calendar;
	}
	
	private void bloquearBotones() {
		btnAgregar.setEnabled(false);
		btnModificar.setEnabled(false);
		btnEliminar.setEnabled(false);
	}
	
	private void limpiarFormulario() {
		labelTotal.setText("");
		textValorExtra.setText("");
		comboBoxClase.setSelectedIndex(0);
		comboBoxPlan.setSelectedIndex(0);
	}
	
    public MembresiasPanel(int panelAncho, int panelAlto) {
    	setFocusTraversalKeysEnabled(false);
    	
    	setPreferredSize(new Dimension(1080, 800));

        setBackground(Color.WHITE);
        setLayout(null);
        
		administrador_id = new Administrador().getId();
		usuarioController = new UsuarioController();
		membresiaController = new MembresiaController();
		        
        comboBoxPlan.setBounds(30, 99, 218, 25);
        add(comboBoxPlan);
        
        comboBoxClase.setBounds(30, 150, 218, 25);
        add(comboBoxClase);
        
        JLabel lblNewLabel = new JLabel("MEMBRESIAS");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(30, 30, 150, 33);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Plan");
        lblNewLabel_1.setBounds(30, 84, 46, 14);
        add(lblNewLabel_1);
        
        textValorExtra = new JTextField();
        textValorExtra.setText("0.0");
        textValorExtra.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		calcularPrecioTotal();
        	}
        });
        textValorExtra.setBounds(30, 201, 218, 25);
        add(textValorExtra);
        textValorExtra.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Valor Extra");
        lblNewLabel_2.setBounds(30, 186, 218, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Clase");
        lblNewLabel_3.setBounds(30, 135, 46, 14);
        add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("VALOR TOTAL");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel_4.setBounds(91, 247, 164, 64);
        add(lblNewLabel_4);
        
        labelTotal = new JLabel("0");
        labelTotal.setFont(new Font("Tahoma", Font.PLAIN, 30));
        labelTotal.setBounds(133, 297, 108, 46);
        add(labelTotal);
        
        btnAgregar = new JButton("Agregar");
        btnAgregar.setFocusPainted(false);
        btnAgregar.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		guardar();
        	}
        });
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregar.setBorder(null);
        btnAgregar.setBackground(new Color(46, 56, 64));
        btnAgregar.setBounds(279, 330, 100, 30);
        add(btnAgregar);
        
        btnModificar = new JButton("Modificar");
        btnModificar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificar();
        	}
        });
        btnModificar.setFocusPainted(false);
        btnModificar.setForeground(Color.WHITE);
        btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificar.setBorder(null);
        btnModificar.setBackground(new Color(46, 56, 64));
        btnModificar.setBounds(389, 330, 100, 30);
        add(btnModificar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFocusPainted(false);
        btnEliminar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminar();
        	}
        });
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminar.setBorder(null);
        btnEliminar.setBackground(new Color(46, 56, 64));
        btnEliminar.setBounds(499, 330, 100, 30);
        add(btnEliminar);
        
        JLabel lblNewLabel_5 = new JLabel("Buscar por cédula:");
        lblNewLabel_5.setBounds(30, 394, 107, 14);
        add(lblNewLabel_5);
        
        textBuscar = new JTextField();
        textBuscar.setBounds(147, 388, 213, 25);
        add(textBuscar);
        textBuscar.setColumns(10);
        
        JScrollPane scrollPane_membresias_membresias = new JScrollPane();
        scrollPane_membresias_membresias.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	}
        });
        scrollPane_membresias_membresias.setBounds(279, 41, 765, 263);
        add(scrollPane_membresias_membresias);
        
        tableMembresias = new JTable();
        tableMembresias.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		idSeleccionadoMembresia = (int) tableMembresias.getValueAt(tableMembresias.getSelectedRow(),0);
        		
        		btnModificar.setEnabled(true);
        		btnEliminar.setEnabled(true);
        		
        		llenarFormulario();
        	}
        });
        scrollPane_membresias_membresias.setViewportView(tableMembresias);
        
        JScrollPane scrollPane_usuarios_membresias = new JScrollPane();
        scrollPane_usuarios_membresias.setBounds(30, 424, 1014, 292);
        add(scrollPane_usuarios_membresias);
        
        tableUsuarios = new JTable();
        tableUsuarios.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		idSeleccionado = (int) tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(),0);
        		listarMembresias();
        		
        		btnAgregar.setEnabled(true);
        	}
        });
        scrollPane_usuarios_membresias.setViewportView(tableUsuarios);
        
        btnBuscar = new JButton("Buscar");
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(new Color(46, 56, 64));
        btnBuscar.setBounds(370, 388, 150, 25);
        add(btnBuscar);
        
        JLabel lblNewLabel_5_1 = new JLabel("¡ Selecciona un usuario !");
        lblNewLabel_5_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_5_1.setBounds(87, 346, 182, 14);
        add(lblNewLabel_5_1);
        
        // Listar Usuarios
        listarUsuarios();
        
        listarPlan();
        listarClase();
        bloquearBotones();
    }
}
