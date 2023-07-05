package com.gym.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class ConfiguracionPanel extends JPanel {
	private JButton examinarButtonLogo;
	private JButton examinarButtonPerfil;
	private byte[] logo;
	private byte[] perfil;

	private static final long serialVersionUID = 9108840646856992941L;
	private JTextField textNombreGym;
	private JTextField textNombreAmigo;
	private JTextField textNombreMascota;
	private JTextField textColor;
	private JTextField textCedula;
	private JTextField textPassword;
	private JTextField textPasswordConfirmar;
	
	public byte[] prepararImagen() {
	    byte[] imageData = null;
	    
	    // Crear un objeto JFileChooser
	    JFileChooser fileChooser = new JFileChooser();

	    // Mostrar el cuadro de diálogo de selección de archivo
	    int result = fileChooser.showOpenDialog(this);

	    // Verificar si se seleccionó un archivo
	    if (result == JFileChooser.APPROVE_OPTION) {
	        // Obtener el archivo seleccionado
	        File selectedFile = fileChooser.getSelectedFile();

	        try {
	            // Leer la imagen como BufferedImage
	            BufferedImage image = ImageIO.read(selectedFile);

	            // Convertir la imagen a un arreglo de bytes
	            ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            ImageIO.write(image, "jpg", baos);
	            imageData = baos.toByteArray();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    return imageData;
	}

	public ConfiguracionPanel(int panelAncho, int panelAlto) {
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
		examinarButtonLogo.setFont(new Font("Tahoma", Font.BOLD, 11));
		examinarButtonLogo.setBounds(143, 186, 225, 25);
        // Configurar el evento de clic del botón
		examinarButtonLogo.addActionListener(e -> {
        	logo = prepararImagen();
        	System.out.println(logo + " logo");
        });
        add(examinarButtonLogo);
        
        JButton btnGuardarGym = new JButton("Guardar");
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
        
        JButton btnGuardarPerfil = new JButton("Guardar");
        btnGuardarPerfil.setForeground(Color.WHITE);
        btnGuardarPerfil.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGuardarPerfil.setFocusPainted(false);
        btnGuardarPerfil.setBorder(null);
        btnGuardarPerfil.setBackground(new Color(46, 56, 64));
        btnGuardarPerfil.setBounds(542, 222, 150, 30);
        add(btnGuardarPerfil);
        
        examinarButtonPerfil = new JButton("Seleccionar Imagen");
        examinarButtonPerfil.setFont(new Font("Tahoma", Font.BOLD, 11));
        examinarButtonPerfil.setBounds(654, 151, 225, 25);
        // Configurar el evento de clic del botón
        examinarButtonPerfil.addActionListener(e -> {
        	perfil = prepararImagen();
        	System.out.println(perfil + " perfil");
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
        
        JLabel lblNewLabel_1_2_3 = new JLabel("Cédula");
        lblNewLabel_1_2_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2_3.setBounds(268, 387, 184, 14);
        add(lblNewLabel_1_2_3);
        
        textCedula = new JTextField();
        textCedula.setColumns(10);
        textCedula.setBounds(268, 403, 225, 25);
        add(textCedula);
        
        JButton btnGuardarRecuperar = new JButton("Guardar");
        btnGuardarRecuperar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
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
        
        JLabel lblNewLabel_1_2_4 = new JLabel("Contraseña");
        lblNewLabel_1_2_4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2_4.setBounds(30, 548, 137, 14);
        add(lblNewLabel_1_2_4);
        
        textPassword = new JTextField();
        textPassword.setColumns(10);
        textPassword.setBounds(30, 564, 225, 25);
        add(textPassword);
        
        JLabel lblNewLabel_1_2_2_1 = new JLabel("Confirma tu contraseña");
        lblNewLabel_1_2_2_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_2_2_1.setBounds(30, 600, 184, 14);
        add(lblNewLabel_1_2_2_1);
        
        textPasswordConfirmar = new JTextField();
        textPasswordConfirmar.setColumns(10);
        textPasswordConfirmar.setBounds(30, 616, 225, 25);
        add(textPasswordConfirmar);
        
        JButton btnGuardar_2_1 = new JButton("Confirmar");
        btnGuardar_2_1.setForeground(Color.WHITE);
        btnGuardar_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnGuardar_2_1.setFocusPainted(false);
        btnGuardar_2_1.setBorder(null);
        btnGuardar_2_1.setBackground(new Color(46, 56, 64));
        btnGuardar_2_1.setBounds(30, 652, 150, 30);
        add(btnGuardar_2_1);
	}
}
