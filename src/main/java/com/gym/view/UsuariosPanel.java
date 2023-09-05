package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.FisicoController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Arduino;
import com.gym.model.Email;
import com.gym.model.Fisico;
import com.gym.model.Membresia;
import com.gym.model.Usuario;
import com.gym.utilidades.Utilidades;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class UsuariosPanel extends JPanel {
	private int administrador_id;
	private DefaultTableModel modelo;
	private UsuarioController usuarioController;
	private FisicoController fisicoController;
	private int idSeleccionadoUsuario;
	private int idSeleccionadoFisico;
	
    private Calendar fechaInicio;
    private Calendar fechaFin;
	private JDateChooser dateChooserInicio;
	private JDateChooser dateChooserFin;
	
	private static final long serialVersionUID = 6939534476162663420L;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCedula;
	private JTextField textTelefono;
	private JTextField textDireccion;
	private JTextField textPeso;
	private JTextField textAltura;
	private JTextField textBuscar;
	private JTable tableUsuarios;
	private JTable tableFisico;
	private JScrollPane scrollPane;
	private JRadioButton radioMasculino;
	private JRadioButton radioFemenino;
	private JButton btnAgregarUsuario;
	private JButton btnModificarUsuario;
	private JButton btnEliminarUsuario;
	private JButton btnAgregarFisico;
	private JButton btnModificarFisico;
	private JButton btnEliminarFisico;
	private ButtonGroup buttonGroup;
	private JTextField textEmail;
	private JLabel lblNewLabel_5;
	private JDateChooser dateChooser;
	private Calendar fecha;
	private JButton btnAgregarHuella;
	
	private void listar() {
		String[] cabeceras = {"Id","Nombre","Apellido","Nacimiento","Sexo","Email","Cedula","Dirección","Teléfono"};
		
		modelo = new DefaultTableModel(usuarioController.listar(administrador_id), cabeceras);
		tableUsuarios.setModel(modelo);
	}
	
	private void listarFisico() {
		String[] cabeceras = {"Id","Peso","Altura", "Fecha"};
		
		modelo = new DefaultTableModel(fisicoController.listar(idSeleccionadoUsuario), cabeceras);
		tableFisico.setModel(modelo);
	}
	
	private void guardar() {
		Usuario usuario = llenarUsuario();
		
		if(usuario == null) {
			return;
		}
		
		if(usuarioController.guardar(usuario)) {
			JOptionPane.showMessageDialog(null, "Guardado con Exito!");
			listar();
			limpiarFormularioUsuario();
		    // Crear y ejecutar un nuevo hilo para notificar la membresía en segundo plano.
		    Thread notificarThread = new Thread(() -> {
	    		// Notificamos al usuario por correo una sola ves en vase a su atributo caducando notificar
	    			Email email = new Email(usuario.getEmail(), "System Gym",
	    					" <h1>Bienvenido</h1>\r\n"
	    					+ "    <p>Hola " + usuario.getNombre() + ", nos complace que seas parte de nuestra familia de Xtream Gym. Estamos listos para ayudarte a comenzar tu emocionante viaje hacia una vida más saludable.</p>\r\n"
	    					+ "    <h3>¡Bienvenido/a a la comunidad de Xtream Gym!</h3>\r\n"
	    					+ "    <h3>Atentamente,</h3>\r\n"
	    					+ "    <h3>El Equipo de Xtream Gym</h3>");
	    			if(!email.sendEmail()) {
	    				System.out.println("Ocurrio un error");
	    			}
		    });

		    notificarThread.start();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo Guardar");
		}
		
	}
	
	private void guardarFisico() {
		Fisico fisico = llenarFisico();
		
		if(fisicoController.guardar(fisico)) {
			JOptionPane.showMessageDialog(null, "Guardado con Exito!");
			listarFisico();
			LimpiarFormularioFisico();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo Guardar");
		}
	}
	
	private void modificar() {
		Usuario usuario = llenarUsuario();
		
		if(usuario == null) {
			return;
		}
		
		if(usuarioController.modificar(usuario)) {
			JOptionPane.showMessageDialog(null, "Modificado con Exito!");
			limpiarFormularioUsuario();
			listar();
		} else {
			JOptionPane.showMessageDialog(null, "Error al intentar modificar");
		}
	}
	

	protected void eliminar() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar");
		if(respuesta == 0) {
			
			JOptionPane.showMessageDialog(null, Utilidades.codigoToMensajeEliminar(usuarioController.eliminar(idSeleccionadoUsuario),
					"No se pudo eliminar, parece que tienes registros y membresias que dependen de este usuario, "
					+ "unicamente puedes modificarlo"));
			listar();
		}
		
	}
	
	public void eliminarFisico() {
		int respuesta = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar");
		
		if(respuesta == 0) {
			if(fisicoController.eliminar(idSeleccionadoFisico)) {
				JOptionPane.showMessageDialog(null, "Registro eliminado!");
				listarFisico();
			} else {
				JOptionPane.showMessageDialog(null, "No se puedo eliminar el registro");
			}
		}
	}
	
	public void modificarFisico() {
		Fisico fisico = llenarFisico();
		
		if(fisicoController.modificar(fisico)) {
			JOptionPane.showMessageDialog(null, "Modificado con Exito!");
			listarFisico();
			LimpiarFormularioFisico();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo modificar");
		}
	}
	
	public void consultar() {
		String[] cabeceras = {"Id","Nombre","Apellido","Nacimiento","Sexo","Email","Cedula","Dirección","Teléfono"};
		
		String nombre = textBuscar.getText();
		
		modelo = new DefaultTableModel(usuarioController.consultar(administrador_id, nombre), cabeceras);
		tableUsuarios.setModel(modelo);
	}
	
	private Usuario llenarUsuario() {
		
		// Retorna true si no cumple alguna validación
		if(validarCampos()) {
			return null;
		}
		// Agregar valor defecto a fecha si no fue agregada
		if(dateChooser.getDate() == null) {
			fecha = Calendar.getInstance();
	        fecha.set(Calendar.YEAR, 1800);
	        fecha.set(Calendar.MONTH, Calendar.JANUARY);
	        fecha.set(Calendar.DAY_OF_MONTH, 1);
		} else {
			fecha = dateChooser.getCalendar();
		}
		
		// Transformar a date tipo sql para insertar en la bdd
		Date fechaSQL = new Date(fecha.getTimeInMillis());
		
		return new Usuario( idSeleccionadoUsuario,
							textNombre.getText(), textApellido.getText(),
							fechaSQL, getRadioButton(), 
							textEmail.getText(), textCedula.getText(), 
							textDireccion.getText(), textTelefono.getText(),
							administrador_id);
	}
	
	public void buscarUsuariosFecha() {
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
		
        List<Usuario> listaUsuarios = usuarioController.consultarUsuariosFecha(administrador_id, fechaInicioSQL, fechaFinSQL);
        
        listaUsuarios.forEach(x -> System.out.println(x.getFecha_creacion()));
try {
			
			
			String ruta = System.getProperty("user.home");
			FileOutputStream archivo = new FileOutputStream(ruta + "/Downloads/"+ fechaFinSQL +".pdf");
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			
			Paragraph parrafo = new Paragraph("REPORTE MEMBRESIAS");
			parrafo.setAlignment(1);
			documento.add(parrafo);
			for (int i = 0; i < listaUsuarios.size(); i++) {
	            String elemento = listaUsuarios.get(i).getFecha_creacion();
	            documento.add(new Paragraph(elemento));
	        }
			
			documento.close();
			
		} catch ( FileNotFoundException e1) {
			
			System.out.println(e1);
		} catch (DocumentException e1) {
			
			e1.printStackTrace();
		}
String ruta = System.getProperty("user.home");
File path = new File(ruta +"/Downloads/"+fechaFinSQL+ ".pdf");
System.out.println(path);

if (path.exists()) {
    try {
        Desktop.getDesktop().open(path);
    } catch (IOException e1) {
        e1.printStackTrace();
    }
}
	}
	
	public boolean validarCampos() {
		if(textNombre.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "El campo nombre no puede ir vacio");
			return true;
		}
		
		if(!Utilidades.validarEmail(textEmail.getText())) {
			JOptionPane.showMessageDialog(null, "El campo email no puede ir vacio o no es valido");
			return true;
		}
		
		if(this.getRadioButton().equals("")) {
			JOptionPane.showMessageDialog(null, "Campo sexo vacio, selecciona un sexo");
			return true;
		}
		
		return false;
	}
	
	private Fisico llenarFisico() {
		return new Fisico(
				idSeleccionadoFisico,
				Double.parseDouble(textPeso.getText()),
				Double.parseDouble(textAltura.getText()),
				idSeleccionadoUsuario);
	}
	
	// Obtener el botón seleccionado
	private String getRadioButton() {
		if(radioMasculino.isSelected()) {
			return radioMasculino.getText();
		} else if(radioFemenino.isSelected()) {
			return radioFemenino.getText();
		} else {
			return "";
		}
	}
	
	// Registrar Huella
	public void registrarHuella() {
		AgregarHuellaFrame frameHuella = null;
		try {
			frameHuella = new AgregarHuellaFrame(idSeleccionadoUsuario);
			frameHuella.setVisible(true);
		} catch(Exception e) {
			frameHuella.setVisible(false);
			JOptionPane.showMessageDialog(null, "No se encontro un lector dactilar");
		}
	}
	
	private void limpiarFormularioUsuario() {
		textNombre.setText("");
		textApellido.setText("");
		dateChooser.setDate(null);
		buttonGroup.clearSelection();
		textEmail.setText("");
		textCedula.setText("");
		textDireccion.setText("");
		textTelefono.setText("");
	}
	
	private void LimpiarFormularioFisico() {
		textPeso.setText("");
		textAltura.setText("");
	}
	
	public void llenarFormulario() {
		Usuario usuario = usuarioController.consulta(idSeleccionadoUsuario);
		textNombre.setText(usuario.getNombre());
		textApellido.setText(usuario.getApellido());
		dateChooser.setDate(usuario.getFecha_nacimiento());
		
		if(usuario.getSexo().equals("Masculino")) {
			radioMasculino.setSelected(true);
		} else {
			radioFemenino.setSelected(true);
		}
		
		textEmail.setText(usuario.getEmail());
		textCedula.setText(usuario.getCedula());
		textDireccion.setText(usuario.getDireccion());
		textTelefono.setText(usuario.getTelefono());
	}
	
	public void llenarFormularioFisico() {
		Fisico fisico = fisicoController.consulta(idSeleccionadoFisico, idSeleccionadoUsuario);
		
		textPeso.setText(fisico.getPeso() + "");
		textAltura.setText(fisico.getAltura() + "");
		
	}
	
	public void bloquearBotonesUsuarios() {
        btnModificarUsuario.setEnabled(false);
        btnEliminarUsuario.setEnabled(false);
        btnAgregarHuella.setEnabled(false);
	}
	
	public void activarBotonesUsuarios() {
        btnModificarUsuario.setEnabled(true);
        btnEliminarUsuario.setEnabled(true);
        btnAgregarHuella.setEnabled(true);
	}
	
	public void activarBotonesFisico() {
        btnModificarFisico.setEnabled(true);
        btnEliminarFisico.setEnabled(true);
	}
	
	public void bloquearBotonesFisico() {
		btnAgregarFisico.setEnabled(false);
		btnModificarFisico.setEnabled(false);
		btnEliminarFisico.setEnabled(false);
	}
	
	// Constructor
	public UsuariosPanel(int panelAncho, int panelAlto) {
		
		administrador_id = new Administrador().getId();
		usuarioController = new UsuarioController();
		fisicoController = new FisicoController();
		
		setFocusTraversalPolicyProvider(true);
    	
		setPreferredSize(new Dimension(1080, 800));
        setBackground(Color.WHITE);
        setLayout(null);
        
        buttonGroup = new ButtonGroup();
        
        JLabel lblNewLabel = new JLabel("USUARIOS");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblNewLabel.setBounds(30, 30, 147, 37);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Nombre");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1.setBounds(30, 74, 75, 14);
        add(lblNewLabel_1);
        
        textNombre = new JTextField();
        textNombre.setColumns(10);
        textNombre.setBounds(30, 90, 225, 25);
        add(textNombre);
        
        JLabel lblNewLabel_1_1 = new JLabel("Apellido");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1.setBounds(280, 74, 75, 14);
        add(lblNewLabel_1_1);
        
        textApellido = new JTextField();
        textApellido.setColumns(10);
        textApellido.setBounds(280, 90, 225, 25);
        add(textApellido);
        
        JLabel lblNewLabel_1_2 = new JLabel("Cedula");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2.setBounds(30, 180, 75, 14);
        add(lblNewLabel_1_2);
        
        textCedula = new JTextField();
        textCedula.setColumns(10);
        textCedula.setBounds(30, 196, 225, 25);
        add(textCedula);
        
        JLabel lblNewLabel_1_3 = new JLabel("Teléfono");
        lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_3.setBounds(30, 128, 75, 14);
        add(lblNewLabel_1_3);
        
        textTelefono = new JTextField();
        textTelefono.setColumns(10);
        textTelefono.setBounds(30, 144, 225, 25);
        add(textTelefono);
        
        JLabel lblNewLabel_9 = new JLabel("Sexo");
        lblNewLabel_9.setBounds(280, 183, 46, 14);
        add(lblNewLabel_9);
        
        radioMasculino = new JRadioButton("Masculino");
        radioMasculino.setFocusPainted(false);
        radioMasculino.setBackground(Color.WHITE);
        radioMasculino.setBounds(280, 198, 109, 23);
        add(radioMasculino);
        
        radioFemenino = new JRadioButton("Femenino");
        radioFemenino.setFocusPainted(false);
        radioFemenino.setBackground(Color.WHITE);
        radioFemenino.setBounds(385, 198, 109, 23);
        add(radioFemenino);
        
        JLabel lblDatosOpcionales = new JLabel("DATOS OPCIONALES");
        lblDatosOpcionales.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDatosOpcionales.setBounds(30, 231, 225, 37);
        add(lblDatosOpcionales);
        
        JLabel lblNewLabel_1_4 = new JLabel("Fecha de nacimiento");
        lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4.setBounds(30, 279, 125, 14);
        add(lblNewLabel_1_4);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Dirección");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1.setBounds(280, 279, 109, 14);
        add(lblNewLabel_1_1_1);
        
        textDireccion = new JTextField();
        textDireccion.setColumns(10);
        textDireccion.setBounds(280, 295, 225, 25);
        add(textDireccion);
        
        JLabel lblFsico = new JLabel("FÍSICO");
        lblFsico.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblFsico.setBounds(527, 30, 225, 37);
        add(lblFsico);
        
        JLabel lblNewLabel_1_4_1 = new JLabel("Peso (Kg)");
        lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_1.setBounds(527, 78, 125, 14);
        add(lblNewLabel_1_4_1);
        
        textPeso = new JTextField();
        textPeso.setColumns(10);
        textPeso.setBounds(527, 94, 225, 25);
        add(textPeso);
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Altura (m)");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1_1.setBounds(777, 78, 109, 14);
        add(lblNewLabel_1_1_1_1);
        
        textAltura = new JTextField();
        textAltura.setColumns(10);
        textAltura.setBounds(777, 94, 225, 25);
        add(textAltura);
        
        btnAgregarUsuario = new JButton("Agregar");
        btnAgregarUsuario.setFocusPainted(false);
        btnAgregarUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		guardar();
        	}
        });
        btnAgregarUsuario.setForeground(new Color(255, 255, 255));
        btnAgregarUsuario.setBorder(null);
        btnAgregarUsuario.setBackground(new Color(46, 56, 64));
        btnAgregarUsuario.setBounds(30, 338, 150, 30);
        add(btnAgregarUsuario);
        
        btnModificarUsuario = new JButton("Modificar");
        btnModificarUsuario.setFocusPainted(false);
        btnModificarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificar();
        	}
        });
        btnModificarUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificarUsuario.setForeground(new Color(255, 255, 255));
        btnModificarUsuario.setBorder(null);
        btnModificarUsuario.setBackground(new Color(46, 56, 64));
        btnModificarUsuario.setBounds(190, 338, 150, 30);
        add(btnModificarUsuario);
        
        btnEliminarUsuario = new JButton("Eliminar");
        btnEliminarUsuario.setFocusPainted(false);
        btnEliminarUsuario.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminarUsuario.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminar();
        	}
        });
        btnEliminarUsuario.setForeground(new Color(255, 255, 255));
        btnEliminarUsuario.setBorder(null);
        btnEliminarUsuario.setBackground(new Color(46, 56, 64));
        btnEliminarUsuario.setBounds(350, 338, 150, 30);
        add(btnEliminarUsuario);
        
        btnAgregarFisico = new JButton("Agregar");
        btnAgregarFisico.setFocusPainted(false);
        btnAgregarFisico.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		guardarFisico();
        	}
        });
        btnAgregarFisico.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregarFisico.setForeground(new Color(255, 255, 255));
        btnAgregarFisico.setBorder(null);
        btnAgregarFisico.setBackground(new Color(46, 56, 64));
        btnAgregarFisico.setBounds(627, 128, 89, 30);
        add(btnAgregarFisico);
        
        btnModificarFisico = new JButton("Modificar");
        btnModificarFisico.setFocusPainted(false);
        btnModificarFisico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificarFisico();
        	}
        });
        btnModificarFisico.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnModificarFisico.setForeground(new Color(255, 255, 255));
        btnModificarFisico.setBorder(null);
        btnModificarFisico.setBackground(new Color(46, 56, 64));
        btnModificarFisico.setBounds(726, 128, 89, 30);
        add(btnModificarFisico);
        
        btnEliminarFisico = new JButton("Eliminar");
        btnEliminarFisico.setFocusPainted(false);
        btnEliminarFisico.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnEliminarFisico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		eliminarFisico();
        	}
        });
        btnEliminarFisico.setForeground(new Color(255, 255, 255));
        btnEliminarFisico.setBorder(null);
        btnEliminarFisico.setBackground(new Color(46, 56, 64));
        btnEliminarFisico.setBounds(825, 128, 89, 30);
        add(btnEliminarFisico);
        
        scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setAutoscrolls(true);
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setBounds(30, 430, 972, 248);
        add(scrollPane);
        
        tableUsuarios = new JTable();
        tableUsuarios.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		idSeleccionadoUsuario = (int) tableUsuarios.getValueAt(tableUsuarios.getSelectedRow(),0);
        		activarBotonesUsuarios();
        		llenarFormulario();
        		listarFisico();
        		btnAgregarFisico.setEnabled(true); 
        	}
        });
        tableUsuarios.setBackground(new Color(255, 255, 255));
        scrollPane.setViewportView(tableUsuarios);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBorder(null);
        scrollPane_1.setAutoscrolls(true);
        scrollPane_1.setBounds(527, 179, 475, 189);
        add(scrollPane_1);
        
        tableFisico = new JTable();
        tableFisico.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		idSeleccionadoFisico = (int) tableFisico.getValueAt(tableFisico.getSelectedRow(),0);
        		activarBotonesFisico();
        		llenarFormularioFisico();
        		listarFisico();
        	}
        });
        tableFisico.setBackground(new Color(255, 255, 255));
        scrollPane_1.setViewportView(tableFisico);
        
        textBuscar = new JTextField();
        textBuscar.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		consultar();
        	}
        });
        textBuscar.setColumns(10);
        textBuscar.setBounds(139, 394, 100, 25);
        add(textBuscar);
        
        JLabel lblNewLabel_1_4_2 = new JLabel("Buscar por nombre:");
        lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_2.setBounds(30, 400, 99, 14);
        add(lblNewLabel_1_4_2);
        
        buttonGroup.add(radioMasculino);
        buttonGroup.add(radioFemenino);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Email");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2_1.setBounds(280, 126, 75, 14);
        add(lblNewLabel_1_2_1);
        
        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(280, 142, 225, 25);
        add(textEmail);
        
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		consultar();
        	}
        });
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(new Color(46, 56, 64));
        btnBuscar.setBounds(249, 394, 89, 25);
        add(btnBuscar);
        
        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		limpiarFormularioUsuario();
        	}
        });
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnLimpiar.setBorder(null);
        btnLimpiar.setBackground(new Color(46, 56, 64));
        btnLimpiar.setBounds(348, 394, 89, 25);
        add(btnLimpiar);
        
        lblNewLabel_5 = new JLabel("Selecciona un usuario");
        lblNewLabel_5.setForeground(Color.BLACK);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel_5.setBounds(527, 160, 182, 14);
        add(lblNewLabel_5);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(30, 295, 225, 25);
        add(dateChooser);
        
        btnAgregarHuella = new JButton("Agregar huella");
        btnAgregarHuella.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registrarHuella();
        	}
        });
        btnAgregarHuella.setForeground(Color.WHITE);
        btnAgregarHuella.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnAgregarHuella.setFocusPainted(false);
        btnAgregarHuella.setBorder(null);
        btnAgregarHuella.setBackground(new Color(46, 56, 64));
        btnAgregarHuella.setBounds(447, 394, 100, 25);
        add(btnAgregarHuella);
        
        JButton btnGenerarReporte = new JButton("Generar reporte");
        btnGenerarReporte.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscarUsuariosFecha();
        	}
        });
        btnGenerarReporte.setForeground(Color.WHITE);
        btnGenerarReporte.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGenerarReporte.setFocusPainted(false);
        btnGenerarReporte.setBorder(null);
        btnGenerarReporte.setBackground(new Color(46, 56, 64));
        btnGenerarReporte.setBounds(872, 394, 130, 25);
        add(btnGenerarReporte);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Fecha inicio");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1_1.setBounds(604, 379, 123, 14);
        add(lblNewLabel_2_1_1);
        
        dateChooserInicio = new JDateChooser();
        dateChooserInicio.setBounds(604, 394, 123, 25);
        add(dateChooserInicio);
        
        JLabel lblNewLabel_2_1_1_1 = new JLabel("Fecha fin");
        lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1_1_1.setBounds(737, 379, 125, 14);
        add(lblNewLabel_2_1_1_1);
        
        dateChooserFin = new JDateChooser();
        dateChooserFin.setBounds(737, 394, 125, 25);
        add(dateChooserFin);
        
        // Listar Usuarios
        listar();
        
        bloquearBotonesUsuarios();
        bloquearBotonesFisico();
    }
}
