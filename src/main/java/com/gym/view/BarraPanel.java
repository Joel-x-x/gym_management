package com.gym.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import com.gym.utilidades.*;

import com.gym.controller.AdministradorController;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Administrador;


public class BarraPanel extends JPanel {
	private AdministradorController administradorController;
	private int administrador_id;
	
	private static final long serialVersionUID = -5747117121149449033L;
	private AdminFrame adminFrame;
	private JButton inicioButton;
    private JButton usuariosButton;
    private JButton membresiasButton;
    private JButton planesButton;
    private JButton adminButton;
    int panelAncho = 1080, panelAlto = 750;

    public BarraPanel(AdminFrame frame) {
        adminFrame = frame;
        administradorController = new AdministradorController();
        administrador_id = new Administrador().getId();
        
        setPreferredSize(new Dimension(200, 750));
        setBackground(new Color(46, 56, 64));
        
        inicioButton = new JButton("     Inicio");
        inicioButton.setHorizontalAlignment(SwingConstants.LEFT);
        inicioButton.setFocusPainted(false);
        inicioButton.setFocusTraversalKeysEnabled(false);
        inicioButton.setBackground(new Color(46, 56, 64));
        inicioButton.setForeground(new Color(163, 175, 175));
        inicioButton.setBorder(null);
        inicioButton.setFont(new Font("Candara", Font.PLAIN, 18));
        inicioButton.setBounds(0, 197, 200, 40);
        usuariosButton = new JButton("     Usuarios");
        usuariosButton.setHorizontalAlignment(SwingConstants.LEFT);
        usuariosButton.setFocusPainted(false);
        usuariosButton.setFocusTraversalKeysEnabled(false);
        usuariosButton.setBackground(new Color(46, 56, 64));
        usuariosButton.setForeground(new Color(163, 175, 175));
        usuariosButton.setBorder(null);
        usuariosButton.setFont(new Font("Candara", Font.PLAIN, 18));
        usuariosButton.setBounds(0, 248, 200, 40);
        membresiasButton = new JButton("     Membresias");
        membresiasButton.setHorizontalAlignment(SwingConstants.LEFT);
        membresiasButton.setFocusPainted(false);
        membresiasButton.setFocusTraversalKeysEnabled(false);
        membresiasButton.setBackground(new Color(46, 56, 64));
        membresiasButton.setForeground(new Color(163, 175, 175));
        membresiasButton.setBorder(null);
        membresiasButton.setFont(new Font("Candara", Font.PLAIN, 18));
        membresiasButton.setBounds(0, 299, 200, 40);
        planesButton = new JButton("     Planes");
        planesButton.setHorizontalAlignment(SwingConstants.LEFT);
        planesButton.setFocusPainted(false);
        planesButton.setFocusTraversalKeysEnabled(false);
        planesButton.setBackground(new Color(46, 56, 64));
        planesButton.setForeground(new Color(163, 175, 175));
        planesButton.setBorder(null);
        planesButton.setFont(new Font("Candara", Font.PLAIN, 18));
        planesButton.setBounds(0, 350, 200, 40);
        
        if(administradorController.superUsuario(administrador_id)) {
            adminButton = new JButton("     Admin");
            adminButton.setHorizontalAlignment(SwingConstants.LEFT);
            adminButton.setFocusPainted(false);
            adminButton.setFocusTraversalKeysEnabled(false);
            adminButton.setBackground(new Color(46, 56, 64));
            adminButton.setForeground(new Color(163, 175, 175));
            adminButton.setBorder(null);
            adminButton.setFont(new Font("Candara", Font.PLAIN, 18));
            adminButton.setBounds(0, 400, 200, 40);
        }

        inicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
                adminFrame.cambiarPanel(new InicioPanel(panelAncho, panelAlto));
            }
        });

        usuariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.cambiarPanel(new UsuariosPanel(panelAncho, panelAlto));
            }
        });

        membresiasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.cambiarPanel(new MembresiasPanel(panelAncho, panelAlto));
            }
        });

        planesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminFrame.cambiarPanel(new PlanesPanel(panelAncho, panelAlto));
            }
        });
        
        if(administradorController.superUsuario(administrador_id)) {
        	adminButton.addActionListener(new ActionListener() {
        		@Override
        		public void actionPerformed(ActionEvent e) {
        			adminFrame.cambiarPanel(new AdminPanel(panelAncho, panelAlto));
        		}
        	});
        }
        
        setLayout(null);
        add(inicioButton);
        add(usuariosButton);
        add(membresiasButton);
        add(planesButton);
        
        if(administradorController.superUsuario(administrador_id)) {
        	add(adminButton);
        }
        
//        CircularPanel logoEmpresa = new CircularPanel();
//        add(logoEmpresa); 
        
        JButton btnCerrarSesion = new JButton("     Cerrar Sesión");
        btnCerrarSesion.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        			
					// Abre la ventana del panel de administrador
	        		RegistroFrame registroFrame = new RegistroFrame();
	        		registroFrame.setVisible(true);
	        		
	        		// Cierra el frame de registro
	        		adminFrame.dispose(); 
	        		new ConnectionFactory();
					ConnectionFactory.desconectar();
        	}
        });
        btnCerrarSesion.setHorizontalAlignment(SwingConstants.LEFT);
        btnCerrarSesion.setForeground(new Color(163, 175, 175));
        btnCerrarSesion.setFont(new Font("Candara", Font.PLAIN, 18));
        btnCerrarSesion.setFocusTraversalKeysEnabled(false);
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBorder(null);
        btnCerrarSesion.setBackground(new Color(46, 56, 64));
        btnCerrarSesion.setBounds(0, 646, 200, 40);
        add(btnCerrarSesion);
        
        JLabel lblNewLabel = new JLabel("Gym");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(163, 175, 175));
        lblNewLabel.setFont(new Font("Candara", Font.BOLD, 18));
        lblNewLabel.setBounds(0, 137, 200, 22);
        add(lblNewLabel);
        
        CircularLabel lblNewLabel_1 = new CircularLabel();
        lblNewLabel_1.setText("Icono");
        lblNewLabel_1.setSize(new Dimension(90, 90));
        
        // Crear una instancia de la imagen para poder redondearla
        BufferedImage image = null;
        
        try {
            URL imageUrl = BarraPanel.class.getResource("/com/gym/view/popeye.jpg");
            image = ImageIO.read(imageUrl);
            
            // Operaciones con la imagen...
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Recibe una imagen tipo BufferedImagen
        lblNewLabel_1.setImage(image);
        lblNewLabel_1.setZoom(20);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setForeground(new Color(50, 50, 50));
        lblNewLabel_1.setBackground(new Color(217, 217, 217));
        lblNewLabel_1.setBounds(52, 41, 90, 90);
        add(lblNewLabel_1);
    }
    
   
}