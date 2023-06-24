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
    int panelAncho = 1080, panelAlto = 640;

    public BarraPanel(AdminFrame frame) {
        adminFrame = frame;
        setPreferredSize(new Dimension(200, 640));
        setBackground(Color.LIGHT_GRAY);

        setLayout(new GridLayout(4, 1));

        inicioButton = new JButton("Inicio");
        usuariosButton = new JButton("Usuarios");
        membresiasButton = new JButton("Membresias");
        planesButton = new JButton("Planes");

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

        add(inicioButton);
        add(usuariosButton);
        add(membresiasButton);
        add(planesButton);
    }
}