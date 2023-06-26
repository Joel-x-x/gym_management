package com.gym.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.RegistroController;
import com.gym.controller.UsuarioController;
import com.gym.model.Registro;
import com.gym.model.Usuario;


import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InicioPanel extends JPanel {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTable tabla_registro;
    private JTextField txt_nombre;
    private JTextField txt_cedula;
    private JButton btn_entrada;
    int codigo=0;
    String codigo_id ="";
  
    public static DefaultTableModel modelo;
    public static DefaultTableModel modelo1;
    private JButton btn_salida;
    private JTable table;
    
	public InicioPanel(int panelAncho, int panelAlto) {
		
		 UsuarioController usuarioController = new UsuarioController();
		 RegistroController  registroController = new RegistroController();  
		
		
		//Usuario usuario = llenarUsuario();
		 
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				btn_entrada.setEnabled(false);
				btn_salida.setEnabled(false);
				dbotones();
			}
		});
    	
    	setPreferredSize(new Dimension(1080, 800));
        setBackground(new Color(192, 192, 192));
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("REGISTRO");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblNewLabel.setBounds(28, 223, 156, 64);
        add(lblNewLabel);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 322, 682, 248);
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
        
        txt_nombre = new JTextField();
        txt_nombre.setBounds(54, 291, 180, 20);
        add(txt_nombre);
        txt_nombre.setColumns(10);
        
        txt_cedula = new JTextField();
        txt_cedula.setColumns(10);
        txt_cedula.setBounds(348, 291, 194, 20);
        add(txt_cedula);
        
        JLabel lblNewLabel_1 = new JLabel("-O-");
        lblNewLabel_1.setFont(new Font("Monkey", Font.PLAIN, 34));
        lblNewLabel_1.setBounds(274, 281, 46, 31);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("Nombre");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2.setBounds(54, 273, 84, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Cedula");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_2_1.setBounds(348, 275, 84, 14);
        add(lblNewLabel_2_1);
        
        btn_entrada = new JButton("Registro Entrada");
        btn_entrada.setEnabled(false);
        btn_entrada.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		Registro registro = llenarRegistro();
        		registroController.guardar(registro);
        		llenarTabla_registro();
        		botones_ides();
        	}
        });
        btn_entrada.setBackground(new Color(0, 128, 0));
        btn_entrada.setBounds(684, 290, 134, 23);
        add(btn_entrada);
        System.out.println("xdxd");
        btn_salida = new JButton("Registro Salida");
        btn_salida.setEnabled(false);
        btn_salida.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		Registro registro = llenarRegistro_id();
        		registroController.guardar_salida(registro);
        		llenarTabla_registro();
        		botones_ides();
        	}
        });
        btn_salida.setBackground(new Color(255, 128, 0));
        btn_salida.setBounds(846, 290, 134, 23);
        add(btn_salida);
        
        JLabel lblInicio = new JLabel("INICIO");
        lblInicio.setFont(new Font("Tahoma", Font.PLAIN, 26));
        lblInicio.setBounds(28, 11, 156, 64);
        add(lblInicio);
        
        JButton btnNewButton = new JButton("Buscar");
        btnNewButton.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		Usuario usuario = llenarUsuario();
        		
        	
        		
        		llenarTabla();
        		
        		
        	}
        });
        btnNewButton.setBounds(585, 288, 89, 23);
        add(btnNewButton);
        
        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(702, 322, 352, 248);
        add(scrollPane_1);
        
        table = new JTable();
        scrollPane_1.setViewportView(table);
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

	public  void llenarTabla() {
		 UsuarioController usuarioController= new UsuarioController();
		Usuario usuario = llenarUsuario();
		String[] cabeceras = {"Codigo","Nombre","Apellido","Fecha_Nacimiento","Sexo","Correo","Cedula"};
		
		modelo = new DefaultTableModel(usuarioController.consulta(usuario),cabeceras);
		tabla_registro.setModel(modelo);
			System.out.println(usuarioController.consulta(usuario)[0][0]);
		}
	public  void llenarTabla_registro() {
		 RegistroController registroController= new RegistroController();
		Registro registro = llenarRegistro();
		String[] cabeceras = {"id","Fecha entrada","Fecha salida"};
		
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
