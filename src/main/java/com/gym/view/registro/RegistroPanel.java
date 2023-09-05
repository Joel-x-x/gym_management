package com.gym.view.registro;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.gym.controller.AdministradorController;
import com.gym.model.Administrador;
import com.gym.utilidades.Utilidades;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class RegistroPanel extends JPanel {
	private AdministradorController administradorController;
	private int administrador_id;

	private static final long serialVersionUID = -4705635535839437717L;
    private RegistroFrame registroFrame;
    private JLabel iniciarSesionButton;
    private JTextField textEmail;
    private JPasswordField txt_Password;
    private JPasswordField txt_confirmar_contraseña;
    private JTextField textClave;
    private JTextField textNombre;
    private JLabel lbl_verificador_contraseña;
    private JLabel lbl_coincidencia_contraseñas;
    
    public void registrar() {
		Administrador administrador = llenarAdministrador();
		
		// Validaciones
		if(administrador.getNombre().equals("")) {
			JOptionPane.showMessageDialog(null, "El campo nombre no puede ir vacio");
			return;
		}
		
		if(!Utilidades.validarEmail(textEmail.getText())) {
			JOptionPane.showMessageDialog(null, "El campo email no puede ir vacio o no es valido");
			return;
		}
		
		if(administrador.getPassword().equals("")) {
			JOptionPane.showMessageDialog(null, "El campo contraseña no puede ir vacio");
			return;
		}
		
		if(!administrador.getPassword().equals(String.valueOf(txt_confirmar_contraseña.getText()))) {
			JOptionPane.showMessageDialog(null, "Las contraseñan no coinciden");
			return;
		}
		
		// Registrar
		if(administradorController.registrar(administrador)) {
			administrador_id = administradorController.getId();
			
			JOptionPane.showMessageDialog(null, "Registrado con exito");
			
			
			limpiarFormulario();
			registroFrame.mostrarPanelInicioSesion();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo registrar verifica la clave");
		}
	}
    
    public Administrador llenarAdministrador() {
    	return new Administrador(
    			textNombre.getText(),
    			textEmail.getText(),
    			String.valueOf(txt_Password.getText()),
    			0,
    			0,
    			textClave.getText());
    }
    
    public void limpiarFormulario() {
    	textNombre.setText("");
    	textEmail.setText("");
    	txt_Password.setText("");
    	txt_confirmar_contraseña.setText("");
    	textClave.setText("");
    }
    
    public RegistroPanel(RegistroFrame frame) {
        registroFrame = frame;
        administradorController = new AdministradorController();
        
        setSize(1280, 720);
        setBackground(Color.white);
        
        iniciarSesionButton = new JLabel("¡Ya tengo una cuenta!, Iniciar Sesión");
        iniciarSesionButton.setForeground(new Color(163, 175, 175));
        iniciarSesionButton.setFont(new Font("Dialog", Font.BOLD, 15));
        iniciarSesionButton.setHorizontalAlignment(SwingConstants.CENTER);
        iniciarSesionButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		registroFrame.mostrarPanelInicioSesion(); // Cambia al panel de inicio de sesión
        	}
        });
        iniciarSesionButton.setBounds(250, 550, 350, 23);
        
        setLayout(null);
        
        add(iniciarSesionButton);
        
        textNombre = new JTextField();
        textNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
        textNombre.setBounds(250, 178, 350, 40);
        add(textNombre);
        textNombre.setColumns(10);
        
        textEmail = new JTextField();
        textEmail.setFont(new Font("Dialog", Font.PLAIN, 20));
        textEmail.setColumns(10);
        textEmail.setBounds(250, 253, 350, 40);
        add(textEmail);
        
        txt_confirmar_contraseña = new JPasswordField();
        txt_confirmar_contraseña.addCaretListener(new CaretListener() {
        	
			public void caretUpdate(CaretEvent e) {
        		if(!String.valueOf(txt_confirmar_contraseña.getPassword()).equals(String.valueOf(txt_Password.getPassword()))) {
                	lbl_coincidencia_contraseñas.setText("Sin coincidencias");
                }else if(String.valueOf(txt_confirmar_contraseña.getPassword()).equals(String.valueOf(txt_Password.getPassword()))){
                	lbl_coincidencia_contraseñas.setText("");
                }
        	}
        });
        
        txt_Password = new JPasswordField();
        txt_Password.addCaretListener(new CaretListener() {
        	String contraseña= "";
        	String contraseña_array [];
        	
        	public void caretUpdate(CaretEvent e) {
        		contraseña = String.valueOf(txt_Password.getPassword());
        		contraseña_array = new String[contraseña.length()];
        		
        		for(int i = 0; i<contraseña.length(); i++) {
    				contraseña_array[i] = contraseña.substring(i,i+1);
    			}
        		if(contraseña.length()>=8) {
        			boolean mayusculas = false ;
        			for(int i = 0; i<contraseña.length(); i++) {
        				if(contraseña.contains(contraseña_array[i].toUpperCase())) {
        					mayusculas = true;
        				}
        			}
        			
        			if(mayusculas) {
        				if(contraseña.matches(".*\\d.*")) {
        					if(contraseña.matches(".*[^a-zA-Z0-9\\s].*")) {
        						lbl_verificador_contraseña.setForeground(Color.GREEN);
        						lbl_verificador_contraseña.setText("Contraseña Valida");
        					}else {
        						lbl_verificador_contraseña.setForeground(Color.RED);
        	        			lbl_verificador_contraseña.setText("Minimo un caracter especial(*,?,+,-,_,....)");
        	        		}
        				}else {
        					lbl_verificador_contraseña.setForeground(Color.RED);
                			lbl_verificador_contraseña.setText("Minimo un número");
                		}
					}else {
						lbl_verificador_contraseña.setForeground(Color.RED);
	        			lbl_verificador_contraseña.setText("Minimo una Mayúscula");
	        		}
        			
        		}else {
        			lbl_verificador_contraseña.setForeground(Color.RED);
        			lbl_verificador_contraseña.setText("Minimo 8 caracteres");
        		}
        	}
        });
        

        txt_Password.setColumns(10);
        txt_Password.setBounds(250, 331, 350, 40);
        add(txt_Password);
        

        txt_confirmar_contraseña.setColumns(10);
        txt_confirmar_contraseña.setBounds(250, 405, 350, 40);
        add(txt_confirmar_contraseña);
        
        JButton btnNewButton = new JButton("Registrarse");
        btnNewButton.setForeground(new Color(0, 0, 0));
        btnNewButton.setBorderPainted(false);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setBackground(new Color(163, 175, 175));
        btnNewButton.setBorder(new EmptyBorder(0, 0, 0, 0));
        btnNewButton.setFont(new Font("Dialog", Font.BOLD, 20));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		registrar();
        	}
        });
        btnNewButton.setBounds(332, 595, 200, 40);
        add(btnNewButton);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setForeground(new Color(163, 175, 175));
        lblNombre.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblNombre.setBounds(250, 145, 95, 31);
        add(lblNombre);
        
        JLabel lblCorreo = new JLabel("Email");
        lblCorreo.setForeground(new Color(163, 175, 175));
        lblCorreo.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblCorreo.setBounds(250, 229, 95, 21);
        add(lblCorreo);
        
        JLabel lblContrasea = new JLabel("Contraseña");
        lblContrasea.setForeground(new Color(163, 175, 175));
        lblContrasea.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblContrasea.setBounds(250, 304, 125, 25);
        add(lblContrasea);
        
        JLabel lblClave = new JLabel("Confirma tu contraseña");
        lblClave.setForeground(new Color(163, 175, 175));
        lblClave.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblClave.setBounds(250, 382, 229, 21);
        add(lblClave);
        
        textClave = new JTextField();
        textClave.setFont(new Font("Dialog", Font.PLAIN, 20));
        textClave.setColumns(10);
        textClave.setBounds(250, 480, 350, 40);
        add(textClave);
        
        JLabel lblClave_1 = new JLabel("Clave");
        lblClave_1.setForeground(new Color(163, 175, 175));
        lblClave_1.setFont(new Font("Dialog", Font.PLAIN, 20));
        lblClave_1.setBounds(250, 456, 95, 23);
        add(lblClave_1);
        
        JLabel lblRegistro = new JLabel("Registrarse");
        lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        lblRegistro.setForeground(new Color(128, 128, 128));
        lblRegistro.setFont(new Font("Dialog", Font.BOLD, 35));
        lblRegistro.setBounds(250, 49, 350, 50);
        add(lblRegistro);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(RegistroPanel.class.getResource("/com/gym/resources/poseRegistro.png")));
        lblNewLabel.setBounds(671, 103, 500, 530);
        add(lblNewLabel);
        
        lbl_verificador_contraseña = new JLabel("");
        lbl_verificador_contraseña.setBounds(615, 344, 247, 14);
        add(lbl_verificador_contraseña);
        
        lbl_coincidencia_contraseñas = new JLabel("");
        lbl_coincidencia_contraseñas.setBounds(615, 418, 159, 14);
        add(lbl_coincidencia_contraseñas);
        

        txt_confirmar_contraseña.setColumns(10);
        txt_confirmar_contraseña.setBounds(250, 405, 350, 40);
        add(txt_confirmar_contraseña);
    }
}
