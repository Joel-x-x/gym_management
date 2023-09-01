package com.gym.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import com.gym.controller.AdministradorController;
import com.gym.controller.CuentaController;
import com.gym.controller.RecuperacionCuentaController;
import com.gym.model.Administrador;
import com.gym.model.Cuenta;
import com.gym.model.RecuperacionCuenta;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.border.LineBorder;

public class ConfiguracionPanel extends JPanel {
	private int administrador_id;
	private CuentaController cuentaController;
	private RecuperacionCuentaController recuperacionCuentaController;
	private AdministradorController administradorController;
	
	private JButton examinarButtonLogo;
	private JButton examinarButtonPerfil;
	private byte[] logo;
	private byte[] perfil;

	private static final long serialVersionUID = 9108840646856992941L;
	private JTextField textNombreGym;
	private JTextField textNombreAmigo;
	private JTextField textNombreMascota;
	private JTextField textColor;
	private JPasswordField textPassword;
	private JPasswordField textPasswordConfirmar;
	
	public void modificarGimnasio() {
		Cuenta cuenta = llenarCuenta();
		
		if(cuenta.getNombre_empresa().equals("")) {
			JOptionPane.showMessageDialog(null, "El campo nombre gym no puede ir vacio");
			return;
		}
		
		if(cuenta.getLogo_empresa() == null) {
			JOptionPane.showMessageDialog(null, "No has seleccionado ninguna imagen");
			return;
		}
		
		if(cuentaController.modificarEmpresa(cuenta)) {
			JOptionPane.showMessageDialog(null, "Datos actualizados correctamente, vuelve a ingresar para que se registren los cambios");
			textNombreGym.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "No se a podido actualizar los datos");
		}
	}
	
	public Cuenta llenarCuenta() {
		return new Cuenta(
				textNombreGym.getText(),
				logo,
				administrador_id);
	}
	
	
	public void actualizarPerfil() {
		
		if(perfil == null) {
			JOptionPane.showMessageDialog(null, "No has seleccionado ninguna imagen");
			return;
		}
		System.out.println(perfil);
		if(cuentaController.modificarPerfil(perfil, administrador_id)) {
			JOptionPane.showMessageDialog(null, "Datos actualizados correctamente, vuelve a ingresar para que se registren los cambios");
		} else {
			JOptionPane.showMessageDialog(null, "No se a podido actualizar los datos");
		}
	}
	
	public byte[] prepararImagen() {
	    byte[] imageData = null;
	    
	    JFileChooser fileChooser = new JFileChooser();

	    int result = fileChooser.showOpenDialog(this);

	    if (result == JFileChooser.APPROVE_OPTION) {
	        File selectedFile = fileChooser.getSelectedFile();

	        try {
	            BufferedImage image = ImageIO.read(selectedFile);

	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(image, "jpg", baos);
	            imageData = baos.toByteArray();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return imageData;
	}
	
	// Datos recuperacion cuenta
	
	public void actualizarRecuperacionCuenta() {		
		RecuperacionCuenta recuperacionCuenta = llenarRecuperacionCuenta();
		
		if(recuperacionCuentaController.modificar(recuperacionCuenta)) {
			JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
			limpiarFormularioRecuperacion();
		} else {
			JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
		}
	}
	
	public void validarLoginActualizar() {
		new LoginValidacionFrame(administrador_id, this, "actualizarRecuperacionCuenta");
	}
	
	public RecuperacionCuenta llenarRecuperacionCuenta() {
		return new RecuperacionCuenta(
				textNombreAmigo.getText(),
				textNombreMascota.getText(),
				textColor.getText(),
				administrador_id);
	}
	
	public void limpiarFormularioRecuperacion() {
		textNombreAmigo.setText("");
		textNombreMascota.setText("");
		textColor.setText("");
	}
	
	// Cambiar Contraseña
	
	public void cambiarPassword() {
		if(administradorController.cambiarPassword(String.valueOf(textPassword.getPassword()), administrador_id)) {
			JOptionPane.showMessageDialog(null, "Haz cambiado tu contraseña");
			limpiarFormularioPassword();
		} else {
			JOptionPane.showMessageDialog(null, "No se pudo cambiar la contraseña");
		}
	}
	
	public void validarCambiarPassword() {
		new LoginValidacionFrame(administrador_id, this, "cambiarPassword");
	}
	
	public void limpiarFormularioPassword() {
		textPassword.setText("");
		textPasswordConfirmar.setText("");
	}

	public ConfiguracionPanel(int panelAncho, int panelAlto, AdminFrame adminFrame) {
		administrador_id = new Administrador().getId();
		cuentaController = new CuentaController();
		recuperacionCuentaController = new RecuperacionCuentaController();
		administradorController = new AdministradorController();
		
		setBackground(new Color(255, 255, 255));
		setLayout(null);
		
		JLabel lblConfiguracion = new JLabel("CONFIGURACION");
		lblConfiguracion.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConfiguracion.setBounds(30, 30, 194, 37);
		add(lblConfiguracion);
		
		JLabel lblGimnasio = new JLabel("GIMNASIO");
		lblGimnasio.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGimnasio.setBounds(30, 87, 194, 37);
		add(lblGimnasio);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre Gimnasio");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(30, 140, 103, 14);
		add(lblNewLabel_1);
		
		textNombreGym = new JTextField();
		textNombreGym.setColumns(10);
		textNombreGym.setBounds(143, 135, 225, 25);
		add(textNombreGym);
		
		JLabel lblNewLabel_1_1 = new JLabel("Logo Gimnasio");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(31, 191, 103, 14);
		add(lblNewLabel_1_1);
		

        // Crear el botón
		examinarButtonLogo = new JButton("Seleccionar Imagen");
		examinarButtonLogo.setBorderPainted(false);
		examinarButtonLogo.setFocusPainted(false);
		examinarButtonLogo.setBackground(new Color(192, 192, 192));
		examinarButtonLogo.setBorder(new LineBorder(new Color(0, 0, 0)));
		examinarButtonLogo.setFont(new Font("Tahoma", Font.BOLD, 11));
		examinarButtonLogo.setBounds(143, 186, 225, 25);
        // Configurar el evento de clic del botón
		examinarButtonLogo.addActionListener(e -> {
        	logo = prepararImagen();
        	System.out.println(logo + " logo");
        });
        add(examinarButtonLogo);
        
        JButton btnGuardarGym = new JButton("Actualizar");
        btnGuardarGym.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		modificarGimnasio();
        	}
        });
        btnGuardarGym.setForeground(Color.WHITE);
        btnGuardarGym.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGuardarGym.setFocusPainted(false);
        btnGuardarGym.setBorder(null);
        btnGuardarGym.setBackground(new Color(46, 56, 64));
        btnGuardarGym.setBounds(30, 222, 150, 30);
        add(btnGuardarGym);
        
        JLabel lblPerfilDeUsuario = new JLabel("PERFIL DE USUARIO");
        lblPerfilDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblPerfilDeUsuario.setBounds(542, 87, 194, 37);
        add(lblPerfilDeUsuario);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Imagen de perfil");
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_1.setBounds(542, 156, 103, 14);
        add(lblNewLabel_1_1_1);
        
        JButton btnGuardarPerfil = new JButton("Actualizar");
        btnGuardarPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		actualizarPerfil();
        	}
        });
        btnGuardarPerfil.setForeground(Color.WHITE);
        btnGuardarPerfil.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGuardarPerfil.setFocusPainted(false);
        btnGuardarPerfil.setBorder(null);
        btnGuardarPerfil.setBackground(new Color(46, 56, 64));
        btnGuardarPerfil.setBounds(542, 222, 150, 30);
        add(btnGuardarPerfil);
        
        examinarButtonPerfil = new JButton("Seleccionar Imagen");
        examinarButtonPerfil.setBorderPainted(false);
        examinarButtonPerfil.setFocusPainted(false);
        examinarButtonPerfil.setBackground(new Color(192, 192, 192));
        examinarButtonPerfil.setBorder(new LineBorder(new Color(0, 0, 0)));
        examinarButtonPerfil.setFont(new Font("Tahoma", Font.BOLD, 11));
        examinarButtonPerfil.setBounds(654, 151, 225, 25);
        // Configurar el evento de clic del botón
        examinarButtonPerfil.addActionListener(e -> {
        	perfil = prepararImagen();
        });
        add(examinarButtonPerfil);
        
        JLabel lblDatosParaRecuperar = new JLabel("DATOS PARA RECUPERAR TU CUENTA");
        lblDatosParaRecuperar.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblDatosParaRecuperar.setBounds(30, 287, 392, 37);
        add(lblDatosParaRecuperar);
        
        JLabel lblNewLabel_1_2 = new JLabel("Nombre de tu mejor amigo");
        lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2.setBounds(30, 335, 137, 14);
        add(lblNewLabel_1_2);
        
        textNombreAmigo = new JTextField();
        textNombreAmigo.setColumns(10);
        textNombreAmigo.setBounds(30, 351, 225, 25);
        add(textNombreAmigo);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("Nombre de tu mascota");
        lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2_1.setBounds(268, 335, 184, 14);
        add(lblNewLabel_1_2_1);
        
        textNombreMascota = new JTextField();
        textNombreMascota.setColumns(10);
        textNombreMascota.setBounds(268, 351, 225, 25);
        add(textNombreMascota);
        
        JLabel lblNewLabel_1_2_2 = new JLabel("Color favorito");
        lblNewLabel_1_2_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2_2.setBounds(30, 387, 184, 14);
        add(lblNewLabel_1_2_2);
        
        textColor = new JTextField();
        textColor.setColumns(10);
        textColor.setBounds(30, 403, 225, 25);
        add(textColor);
        
        JButton btnGuardarRecuperar = new JButton("Actualizar");
        btnGuardarRecuperar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		validarLoginActualizar();
        	}
        });
        btnGuardarRecuperar.setForeground(Color.WHITE);
        btnGuardarRecuperar.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGuardarRecuperar.setFocusPainted(false);
        btnGuardarRecuperar.setBorder(null);
        btnGuardarRecuperar.setBackground(new Color(46, 56, 64));
        btnGuardarRecuperar.setBounds(30, 439, 150, 30);
        add(btnGuardarRecuperar);
        
        JLabel lblCambiarContrasea = new JLabel("CAMBIAR CONTRASEÑA");
        lblCambiarContrasea.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblCambiarContrasea.setBounds(30, 500, 361, 37);
        add(lblCambiarContrasea);
        
        JLabel lblNewLabel_1_2_4 = new JLabel("Nueva contraseña");
        lblNewLabel_1_2_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2_4.setBounds(30, 548, 137, 14);
        add(lblNewLabel_1_2_4);
        
        JLabel lblNewLabel_1_2_2_1 = new JLabel("Confirma tu contraseña");
        lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2_2_1.setBounds(30, 600, 184, 14);
        add(lblNewLabel_1_2_2_1);
        
        JButton btnGuardar_2_1 = new JButton("Confirmar");
        btnGuardar_2_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		validarCambiarPassword();
        	}
        });
        btnGuardar_2_1.setForeground(Color.WHITE);
        btnGuardar_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGuardar_2_1.setFocusPainted(false);
        btnGuardar_2_1.setBorder(null);
        btnGuardar_2_1.setBackground(new Color(46, 56, 64));
        btnGuardar_2_1.setBounds(30, 652, 150, 30);
        add(btnGuardar_2_1);
        
        textPassword = new JPasswordField();
        textPassword.setBounds(30, 564, 225, 25);
        add(textPassword);
        
        textPasswordConfirmar = new JPasswordField();
        textPasswordConfirmar.setBounds(30, 616, 225, 25);
        add(textPasswordConfirmar);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Solo se permite formato .jpg");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_2.setBounds(143, 171, 225, 14);
        add(lblNewLabel_1_1_2);
        
        JLabel lblNewLabel_1_1_2_1 = new JLabel("Solo se permite formato .jpg");
        lblNewLabel_1_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_1_2_1.setBounds(654, 135, 225, 14);
        add(lblNewLabel_1_1_2_1);
	}
}
