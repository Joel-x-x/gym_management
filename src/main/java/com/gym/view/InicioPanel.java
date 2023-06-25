package com.gym.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.gym.controller.AdministradorController;
import com.gym.controller.UsuarioController;
import com.gym.model.Administrador;
import com.gym.model.Usuario;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class InicioPanel extends JPanel {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private JTable tabla_registro;
    private JTextField txt_nombre;
    private JTextField txt_cedula;
    private JButton btn_entrada;
    
    
  
    public static DefaultTableModel modelo;
    
	public InicioPanel(int panelAncho, int panelAlto) {
		 UsuarioController usuarioController = new UsuarioController();
		
		//Usuario usuario = llenarUsuario();
		 
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				
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
        scrollPane.setBounds(10, 322, 1011, 248);
        add(scrollPane);
        
        tabla_registro = new JTable();
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
        btn_entrada.setBackground(new Color(0, 128, 0));
        btn_entrada.setBounds(684, 290, 134, 23);
        add(btn_entrada);
        
        JButton btn_salida = new JButton("Registro Salida");
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
    }
	
	private Usuario llenarUsuario() {
		
		
		return new Usuario(txt_cedula.getText());
	}

	public  void llenarTabla() {
		 UsuarioController usuarioController= new UsuarioController();
		Usuario usuario = llenarUsuario();
		String[] cabeceras = {"Codigo","Nombre","Apellido","Fecha_Nacimiento","Sexo","Correo","Cedula"};
		
		modelo = new DefaultTableModel(usuarioController.consulta(usuario),cabeceras);
		tabla_registro.setModel(modelo);
			System.out.println(usuarioController.consulta(usuario)[0][0]);
		}

}
