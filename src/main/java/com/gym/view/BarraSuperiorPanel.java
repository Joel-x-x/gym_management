package com.gym.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.gym.controller.AdministradorController;
import com.gym.controller.CuentaController;
import com.gym.model.Administrador;
import com.gym.utilidades.CircularLabel;
import com.gym.utilidades.Utilidades;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BarraSuperiorPanel extends JPanel {
	private AdminFrame adminFrame;
	private AdministradorController administradorController;
	private CuentaController cuentaController;
	private int administrador_id;
	int panelAncho = 1080, panelAlto = 750;

	private static final long serialVersionUID = -1014488060944978809L;
	private CircularLabel labelPerfil;

	public BarraSuperiorPanel(AdminFrame frame) {
		adminFrame = frame;
		
		administrador_id = new Administrador().getId();
		administradorController = new AdministradorController();
		cuentaController = new CuentaController();
		
        setPreferredSize(new Dimension(1280, 80));
        setBackground(new Color(230, 230, 230));
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Gym");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Candara", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 16, 66, 22);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon(BarraSuperiorPanel.class.getResource("/com/gym/resources/pesa.png")));
        lblNewLabel_1.setBounds(20, 10, 30, 30);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("");
        lblNewLabel_1_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		adminFrame.cambiarPanel(new ConfiguracionPanel(panelAlto, panelAncho, adminFrame));
        	}
        });
        lblNewLabel_1_1.setIcon(new ImageIcon(BarraSuperiorPanel.class.getResource("/com/gym/resources/config.png")));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBounds(1210, 8, 35, 35);
        add(lblNewLabel_1_1);
        
        labelPerfil = new CircularLabel();
        
        agregarImagenPerfil();
        
        labelPerfil.setHorizontalAlignment(SwingConstants.CENTER);
        labelPerfil.setBounds(1085, 8, 40, 33);
        add(labelPerfil);
        
        JLabel lblUsuario = new JLabel();
        
        String nombre = administradorController.getNombreUsuario(administrador_id).trim();
        
        lblUsuario.setText(nombre);
        
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setForeground(Color.BLACK);
        lblUsuario.setFont(new Font("Candara", Font.BOLD, 16));
        lblUsuario.setBounds(1125, 16, 85, 22);
        add(lblUsuario);
    }
	
    public void agregarImagenPerfil() {
        byte[] imagenPerfil = cuentaController.getPerfil(administrador_id);
        //byte[] logoEmpresa = null;
        // Crear una instancia de la imagen para poder redondearla
        BufferedImage image = Utilidades.obtenerBufferedImage(imagenPerfil);
        
        if(image != null) {
            // Recibe una imagen tipo BufferedImagen
            labelPerfil.setImage(image);
            labelPerfil.setZoom(0);
        } else {
        	
        	System.out.println("Ocurrio un error Imagen perfil");
            try {
                URL imageUrl = BarraPanel.class.getResource("/com/gym/resources/user.png");
                image = ImageIO.read(imageUrl);
                
                labelPerfil.setImage(image);
                labelPerfil.setZoom(5);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}