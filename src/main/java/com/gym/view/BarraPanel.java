package com.gym.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarraPanel extends JPanel {

    private JButton inicioButton;
    private JButton usuariosButton;
    private JButton membresiasButton;
    private JButton planesButton;
    private AdminFrame adminFrame;
    int panelAncho = 1080, panelAlto = 750;
    private JPanel panel_1;

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
        setLayout(null);
        System.out.println("Hola");
        add(inicioButton);
        add(usuariosButton);
        add(membresiasButton);
        add(planesButton);
        
        panel_1 = new JPanel();
        panel_1.setBounds(58, 32, 80, 83);
        add(panel_1); 
        
        JButton btnCerrarSesin = new JButton("     Cerrar Sesión");
        btnCerrarSesin.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnCerrarSesin.setHorizontalAlignment(SwingConstants.LEFT);
        btnCerrarSesin.setForeground(new Color(163, 175, 175));
        btnCerrarSesin.setFont(new Font("Candara", Font.PLAIN, 18));
        btnCerrarSesin.setFocusTraversalKeysEnabled(false);
        btnCerrarSesin.setFocusPainted(false);
        btnCerrarSesin.setBorder(null);
        btnCerrarSesin.setBackground(new Color(46, 56, 64));
        btnCerrarSesin.setBounds(0, 646, 200, 40);
        add(btnCerrarSesin);
    }
}