package com.gym.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.FisicoController;
import com.gym.controller.MembresiaController;
import com.gym.controller.RegistroController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Arduino;
import com.gym.model.ArduinoDataListener;
import com.gym.model.Email;
import com.gym.model.Fisico;
import com.gym.model.Membresia;
import com.gym.model.Usuario;
import com.gym.utilidades.FechasUtilidades;
import com.gym.utilidades.Utilidades;
import com.toedter.calendar.JDateChooser;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class RegistrosDiariosPanel extends JPanel implements GenerarFrameInterfaz {
	private int usuario_id;
	UsuarioController usuarioController;
	RegistroController registroController;
	MembresiaController membresiaController;
	FisicoController fisicoController;
	private int administrador_id; 
	private JComboBox<Membresia> comboBoxMembresia = new JComboBox<>();
	private DefaultComboBoxModel<Membresia> comboBoxModelMembresia = new DefaultComboBoxModel<>();

	private static final long serialVersionUID = 1L;
    private int idSeleccionadoUsuario = 0;
    private int idUltimoRegistro;
    public static DefaultTableModel modelo;
    private JButton btn_entrada_1;
    private JLabel lblInicio_1;
    private JButton btn_entrada_2;
    private JLabel labelFechaHoy;
    private JLabel lblNombre;
    private JLabel lblApellido;
    private JTextField textNombre;
    private JTextField textApellido;
    private JTextField textEstatura;
    private JLabel lblPeso;
    private JTextField textPeso;
    private JTextField textVencimiento;
    private JLabel labelDesde;
    private JLabel lblNewLabel_1;
    private JLabel lblInicio_2;
    private JPanel panel_1;
    private JLabel labelMensaje;
    private JPanel panel_2;
    private JLabel lblNewLabel_2;
    private JLabel lblNewLabel_4;
    private JButton btn_registrar;
    private JLabel labelCedula;
    private JPanel panelUsuario;
    
	public void verificarHuella() {
		try {
			Arduino.sendCommand("v");
			ArduinoDataListener.setClassVerficar(this);
		} catch(Exception e) {
			System.out.println("No se encontro una huella");
		}
	}
	
	// Mensaje en el panel de la huella
	public void modificarLabel(String mensaje) {
		labelMensaje.setText(mensaje);
	}
	
	public void usuarioEncontrado(int id) {
		usuario_id = id;
		llenarFormularioUsuario();
	}
	
	public void listarRegistros() {
		new RegistrosDiariosFrame();
	}

	@Override
	public void listarUsuarios() {
		UsuariosFrame usuarios = new UsuariosFrame(this);
		usuarios.setVisible(true);
	}

	@Override
	public void usuarioSeleccionado(Usuario usuarioLista) {
		usuario_id = usuarioLista.getId();
		
		llenarFormularioUsuario();
		
	}
	
	public void llenarFormularioUsuario() {
		Usuario usuario = usuarioController.consulta(usuario_id);
		
		// Llenar campos de usuario
		textNombre.setText("   " + usuario.getNombre());
		textApellido.setText("   " + usuario.getApellido());
		labelCedula.setText("CI. " + usuario.getCedula());
		labelDesde.setText("Miembro desde el " + FechasUtilidades.cambiarFormatoFecha(usuario.getFecha_creacion()));
		
		// Llenar campos fisico
		Fisico fisico = fisicoController.consultarUltimoFisico(usuario_id);
		
		if(fisico != null) {
			textEstatura.setText("         " + fisico.getAltura());
			textPeso.setText("         " + fisico.getPeso());
		} else {
			textEstatura.setText("");
			textPeso.setText("");
		}
		
		// Llenar campos membresias
		List<Membresia> membresias = membresiaController.listarMembresiasUsuario(usuario_id);
		
		// No tiene membresias activas
		if(membresias.size() == 0) {
			textVencimiento.setText("   No tiene membresías activas");
			panelUsuario.setBackground(Color.WHITE);
			comboBoxModelMembresia.removeAllElements();
			return;
		}
		
		// LLenar el combo box membresía
		comboBoxModelMembresia.removeAllElements();
		comboBoxModelMembresia.addAll(membresias);
		comboBoxMembresia.setModel(comboBoxModelMembresia);
		comboBoxMembresia.setSelectedIndex(0);
		
		if(membresias.size() == 1) {
			String finaliza = FechasUtilidades.obtenerTiempoRestante(FechasUtilidades.stringToLocalDateTime(membresias.get(0).getFecha_fin()));
			textVencimiento.setText("   Vence en " + finaliza);
			// Registrar
			registrarUsuario(usuario.getId(), membresias.get(0));
			// Asignar color en base a en cuanto caduca
			colorMembresia(membresias.get(0));
		} else {
			textVencimiento.setText("   Selecciona una membresía y registrala");
		}
	}
	
	public void registrarUsuario(int usuario_id, Membresia membresia) {
		if(registroController.registrar(usuario_id, membresia.getId())) {
			System.out.println("Registrado");
		} else {
			JOptionPane.showMessageDialog(null, "Ocurrio un error intentando registrar");
		}
		
	    // Crear y ejecutar un nuevo hilo para notificar la membresía en segundo plano.
	    Thread notificarThread = new Thread(() -> {
	        membresia.notificarMembresia();
	    });

	    notificarThread.start();
	}
	
	// Cambiar el color del panel dependiendo de los dias faltantes para que caduque
	public void colorMembresia(Membresia membresia) {
		if(membresia.caducaDias() == 0) {
			panelUsuario.setBackground(new Color(238, 93, 47)); // Naranja caducara en menos de 24h
		} else if(membresia.caducaDias() < 3) {
			panelUsuario.setBackground(new Color(220, 205, 69)); // Caducara pronto
		}
		if(membresia.caducaDias() > 3) {
			panelUsuario.setBackground(new Color(23, 159, 78)); // Todo bien
		}
	}
	
	public void registrarMembresiaSeleccionada() {
		Membresia membresia = membresiaController.consulta(getIdClaseComboBox());
		registrarUsuario(usuario_id, membresia);
		colorMembresia(membresia);
	}
	
	// Obtener el combo box seleccionado
	public int getIdClaseComboBox() {
		return ((Membresia) comboBoxMembresia.getSelectedItem()).getId();
	}

    
	public RegistrosDiariosPanel(int panelAncho, int panelAlto) {
		 usuarioController = new UsuarioController();
		 registroController = new RegistroController(); 
		 membresiaController = new MembresiaController();
		 fisicoController = new FisicoController();
		 administrador_id = new Administrador().getId();
		 
		 Arduino.initializeSerialPort();
		
    	setPreferredSize(new Dimension(1080, 800));
        setBackground(new Color(244, 244, 244));
        setLayout(null);
        
        JLabel lblInicio = new JLabel("Registro de usuarios");

        lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblInicio.setBounds(30, 30, 281, 49);
        add(lblInicio);
        
        comboBoxMembresia = new JComboBox<Membresia>();
        comboBoxMembresia.setBackground(new Color(255, 255, 255));
        comboBoxMembresia.setBounds(289, 485, 250, 30);
        add(comboBoxMembresia);
        
        JLabel lblNewLabel_3 = new JLabel("Membresía");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_3.setBounds(289, 467, 95, 14);
        add(lblNewLabel_3);
        
        btn_entrada_1 = new JButton("Escoger usuario");
        btn_entrada_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		listarUsuarios();
        	}
        });
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
        btn_entrada_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		listarRegistros();
        	}
        });
        btn_entrada_2.setForeground(Color.WHITE);
        btn_entrada_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_entrada_2.setFocusPainted(false);
        btn_entrada_2.setBorder(null);
        btn_entrada_2.setBackground(new Color(31, 33, 38));
        btn_entrada_2.setBounds(538, 653, 200, 30);
        add(btn_entrada_2);
        
        panelUsuario = new JPanel();
        panelUsuario.setBackground(new Color(255, 255, 255));
        panelUsuario.setBounds(240, 156, 600, 300);
        add(panelUsuario);
        panelUsuario.setLayout(null);
        
        lblNombre = new JLabel("Nombre");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNombre.setBounds(29, 24, 87, 30);
        panelUsuario.add(lblNombre);
        
        lblApellido = new JLabel("Apellido");
        lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblApellido.setBounds(29, 65, 87, 30);
        panelUsuario.add(lblApellido);
        
        textNombre = new JTextField();
        textNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
        textNombre.setBorder(null);
        textNombre.setEditable(false);
        textNombre.setBounds(110, 29, 250, 25);
        panelUsuario.add(textNombre);
        textNombre.setColumns(10);
        
        textApellido = new JTextField();
        textApellido.setFont(new Font("Tahoma", Font.BOLD, 14));
        textApellido.setEditable(false);
        textApellido.setColumns(10);
        textApellido.setBorder(null);
        textApellido.setBounds(110, 70, 250, 25);
        panelUsuario.add(textApellido);
        
        labelCedula = new JLabel("CI. 1850038314");
        labelCedula.setFont(new Font("Tahoma", Font.BOLD, 14));
        labelCedula.setBounds(420, 11, 142, 30);
        panelUsuario.add(labelCedula);
        
        JLabel lblEstatura = new JLabel("Estatura");
        lblEstatura.setHorizontalAlignment(SwingConstants.CENTER);
        lblEstatura.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEstatura.setBounds(62, 106, 100, 30);
        panelUsuario.add(lblEstatura);
        
        textEstatura = new JTextField();
        textEstatura.setFont(new Font("Tahoma", Font.BOLD, 14));
        textEstatura.setEditable(false);
        textEstatura.setColumns(10);
        textEstatura.setBorder(null);
        textEstatura.setBounds(62, 133, 100, 25);
        panelUsuario.add(textEstatura);
        
        lblPeso = new JLabel("Peso");
        lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
        lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblPeso.setBounds(181, 106, 100, 30);
        panelUsuario.add(lblPeso);
        
        textPeso = new JTextField();
        textPeso.setFont(new Font("Tahoma", Font.BOLD, 14));
        textPeso.setEditable(false);
        textPeso.setColumns(10);
        textPeso.setBorder(null);
        textPeso.setBounds(181, 133, 100, 25);
        panelUsuario.add(textPeso);
        
        textVencimiento = new JTextField();
        textVencimiento.setFont(new Font("Tahoma", Font.BOLD, 14));
        textVencimiento.setEditable(false);
        textVencimiento.setColumns(10);
        textVencimiento.setBorder(null);
        textVencimiento.setBounds(29, 196, 331, 30);
        panelUsuario.add(textVencimiento);
        
        labelDesde = new JLabel("Miembro desde el 30 de Septiembre del 2022");
        labelDesde.setFont(new Font("Tahoma", Font.PLAIN, 12));
        labelDesde.setBounds(29, 259, 399, 30);
        panelUsuario.add(labelDesde);
        
        lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(RegistrosDiariosPanel.class.getResource("/com/gym/resources/negro.png")));
        lblNewLabel_1.setBounds(432, 100, 100, 100);
        panelUsuario.add(lblNewLabel_1);
        
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
        
        labelMensaje = new JLabel("Coloca tu dedo");
        labelMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        labelMensaje.setFont(new Font("Tahoma", Font.PLAIN, 16));
        labelMensaje.setBounds(0, 23, 200, 49);
        panel_1.add(labelMensaje);
        
        panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBackground(new Color(23, 159, 38));
        panel_2.setBounds(339, 526, 399, 54);
        add(panel_2);
        
        lblNewLabel_2 = new JLabel("Si el usuario tiene más de una membresía vigente");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_2.setBounds(10, 0, 379, 26);
        panel_2.add(lblNewLabel_2);
        
        lblNewLabel_4 = new JLabel("tienes que seleccionar la que vaya a tomar.");
        lblNewLabel_4.setForeground(new Color(255, 255, 255));
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_4.setBounds(10, 29, 379, 14);
        panel_2.add(lblNewLabel_4);
        
        btn_registrar = new JButton("Registrar");
        btn_registrar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registrarMembresiaSeleccionada();
        	}
        });
        btn_registrar.setForeground(Color.WHITE);
        btn_registrar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_registrar.setFocusPainted(false);
        btn_registrar.setBorder(null);
        btn_registrar.setBackground(new Color(31, 33, 38));
        btn_registrar.setBounds(549, 485, 250, 30);
        add(btn_registrar);
        
        verificarHuella(); 
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        Runnable intervalo = () -> {
            labelFechaHoy.setText("Hoy es " + FechasUtilidades.cambiarFormatoFecha(FechasUtilidades.localDateTimeToString(LocalDateTime.now())));
        };

        // Ejecutar el intervalo cada segundo
        scheduler.scheduleAtFixedRate(intervalo, 0, 1, TimeUnit.SECONDS);        

    }

	@Override
	public void listarTipoMembresias() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tipoMembresiaSeleccionada(int id) {
		// TODO Auto-generated method stub
		
	}
}
