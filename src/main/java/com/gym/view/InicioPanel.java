package com.gym.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.RegistroController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Registro;
import com.gym.model.Usuario;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InicioPanel extends JPanel {
	UsuarioController usuarioController;
	RegistroController registroController;
	private int administrador_id; 

	private static final long serialVersionUID = 1L;
    private JTable tabla_registro;
    private JTextField txt_cedula;
    int codigo=0;
    String codigo_id ="";
  
    public static DefaultTableModel modelo;
    public static DefaultTableModel modelo1;
    private JTable table;
    private JButton btn_entrada;
    private JButton btn_salida;
    
    private void listarUsuarios() {
		String[] cabeceras = {"Id","Nombre","Apellido","Fecha_Nacimiento","Sexo","Correo","Cedula"};
		
		modelo = new DefaultTableModel(usuarioController.listar(administrador_id),cabeceras);
		tabla_registro.setModel(modelo);
    }
    
	public InicioPanel(int panelAncho, int panelAlto) {
		
		 usuarioController = new UsuarioController();
		 registroController = new RegistroController();  
		 administrador_id = new Administrador().getId();
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				btn_entrada.setEnabled(false);
				btn_salida.setEnabled(false);
				dbotones();
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
        
        tabla_registro = new JTable();
        tabla_registro.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		//abotones();
        		 codigo = (int )tabla_registro.getValueAt(tabla_registro.getSelectedRow(),0);
        		System.out.println(codigo);
        		Registro registro = llenarRegistro();
        		llenarTabla_registro();
        		botones_ides();
        		
        	}
        });
        scrollPane.setViewportView(tabla_registro);
        
        txt_cedula = new JTextField();
        txt_cedula.setColumns(10);
        txt_cedula.setBounds(129, 88, 194, 25);
        add(txt_cedula);
        
        JLabel lblNewLabel_2_1 = new JLabel("Buscar por cédula");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1.setBounds(29, 93, 101, 14);
        add(lblNewLabel_2_1);
        System.out.println("xdxd");
        
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
        btn_entrada.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Registro registro = llenarRegistro();
        		registroController.guardar(registro);
        		llenarTabla_registro();
        		botones_ides();
        	}
        });
        btn_entrada.setForeground(Color.WHITE);
        btn_entrada.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_entrada.setBorder(null);
        btn_entrada.setBackground(new Color(31, 33, 38));
        btn_entrada.setBounds(30, 409, 150, 30);
        add(btn_entrada);
        
        btn_salida = new JButton("Registrar Salida");
        btn_salida.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Registro registro = llenarRegistro_id();
        		registroController.guardar_salida(registro);
        		llenarTabla_registro();
        		botones_ides();
        	}
        });
        btn_salida.setForeground(Color.WHITE);
        btn_salida.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_salida.setBorder(null);
        btn_salida.setBackground(new Color(203, 39, 39));
        btn_salida.setBounds(190, 409, 150, 30);
        add(btn_salida);
        
        JButton btn_buscar = new JButton("Buscar");
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
        
        listarUsuarios();
    }
	
	private Usuario llenarUsuario() {
		
		
		return new Usuario(txt_cedula.getText());
		
	}
	private Registro llenarRegistro() {
		return new Registro(codigo);
	}
	private Registro llenarRegistro_id() {
		return new Registro(codigo_id);
	}

	public  void consultarUsuario() {
		Usuario usuario = llenarUsuario();
		String[] cabeceras = {"Id","Nombre","Apellido","Fecha_Nacimiento","Sexo","Correo","Cedula"};
		
		modelo = new DefaultTableModel(usuarioController.consultar(administrador_id, txt_cedula.getText()),cabeceras);
		tabla_registro.setModel(modelo);
		
		}
	
	public  void llenarTabla_registro() {
		Registro registro = llenarRegistro();
		String[] cabeceras = {"Id","Fecha de entrada","Fecha de salida"};
		
		modelo1 = new DefaultTableModel(registroController.consulta(registro),cabeceras);
		table.setModel(modelo1);
		
	}
	public void abotones(){
		//btn_entrada.setEnabled(true);
		
	}
	public void botones_ides() {
		 RegistroController registroController= new RegistroController();
			Registro registro = llenarRegistro();
			boolean salida=false;
		for(int i=0;i<registroController.consulta(registro).length;i++) {
			if(registroController.consulta(registro)[i][2]==null) {
				
				salida = true;
				codigo_id=registroController.consulta(registro)[i][0].toString();
			}
			System.out.println(" impresion :"+String.valueOf(registroController.consulta(registro)[i][2]));
		}
		if(salida) {
			btn_salida.setEnabled(true);
			btn_entrada.setEnabled(false);
		}else {
			btn_entrada.setEnabled(true);
			btn_salida.setEnabled(false);
		}
			//System.out.println(usuarioController.consulta(usuario)[0][0]);
		
	}

	public void dbotones(){
		btn_entrada.setEnabled(false);
		btn_salida.setEnabled(false);
	}
}
