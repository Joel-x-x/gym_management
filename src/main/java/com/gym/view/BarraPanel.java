package com.gym.view;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

import com.gym.factory.ConnectionFactory;


public class BarraPanel extends JPanel {
	
	private static final long serialVersionUID = -5747117121149449033L;
	private AdminFrame adminFrame;
	private JButton inicioButton;
    private JButton usuariosButton;
    private JButton membresiasButton;
    private JButton planesButton;
    private JButton claveButton;
    int panelAncho = 1080, panelAlto = 750;

    public BarraPanel(AdminFrame frame) {
        adminFrame = frame;
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
        claveButton = new JButton("     Clave");
        claveButton.setHorizontalAlignment(SwingConstants.LEFT);
        claveButton.setFocusPainted(false);
        claveButton.setFocusTraversalKeysEnabled(false);
        claveButton.setBackground(new Color(46, 56, 64));
        claveButton.setForeground(new Color(163, 175, 175));
        claveButton.setBorder(null);
        claveButton.setFont(new Font("Candara", Font.PLAIN, 18));
        claveButton.setBounds(0, 400, 200, 40);

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
        
        claveButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		adminFrame.cambiarPanel(new ClavePanel(panelAncho, panelAlto));
        	}
        });
        
        setLayout(null);
        add(inicioButton);
        add(usuariosButton);
        add(membresiasButton);
        add(planesButton);
        add(claveButton);
        
        CircularPanel logoEmpresa = new CircularPanel();
        add(logoEmpresa); 
        
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
    }

    public class CircularPanel extends JPanel {

		private static final long serialVersionUID = -7560565167117132164L;
		private Image image;

        public CircularPanel() {
            try {
                // Carga la imagen desde un archivo o recurso
                URL imageUrl = getClass().getResource("sapo8.jpg");
                image = ImageIO.read(imageUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            setBounds(48, 32, 100, 100);
            //setBounds(58, 32, 175, 175);
        }

        @Override
        protected void paintComponent(Graphics g) {
        	super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            // Establece el color de fondo del panel a transparente
            setBackground(new Color(0, 0, 0, 0));

            // Habilita el suavizado de dibujado para una apariencia más suave            
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            // Dibuja el círculo
            g2d.setColor(new Color(46, 56, 64));
            g2d.setStroke(new BasicStroke(10.0f)); // Trazo de grosor 2
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Shape circle = new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1);
            g2d.fill(circle);

            // Guarda el área de recorte actual
            Shape previousClip = g2d.getClip();

            // Establece el área de recorte al círculo
            g2d.setClip(circle);

            // Calcula las coordenadas y dimensiones para ajustar la imagen al tamaño del panel
            int imageSize = Math.min(getWidth(), getHeight());
            int x = (getWidth() - imageSize) / 2;
            int y = (getHeight() - imageSize) / 2;

            // Dibuja la imagen ajustada al tamaño del panel dentro del área de recorte
            g2d.drawImage(image, x, y, imageSize, imageSize, null);

            // Restaura el área de recorte anterior
            g2d.setClip(previousClip);

            // Libera los recursos del objeto Graphics2D
            g2d.dispose();
        }
    }
    
}