package com.gym.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame implements ActionListener{

    private BarraPanel barraPanel;
    private JPanel panelPrincipal;

    public AdminFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);

        // BarraPanel
        barraPanel = new BarraPanel(this);
        barraPanel.setPreferredSize(new Dimension(280, 720));

        // Panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setPreferredSize(new Dimension(1000, 720));

        // Agregar BarraPanel y panelPrincipal al JFrame
        add(barraPanel, BorderLayout.WEST);
        add(panelPrincipal, BorderLayout.CENTER);

        setVisible(true);
    }

    public void cambiarPanel(JPanel nuevoPanel) {
        panelPrincipal.removeAll();
        panelPrincipal.add(nuevoPanel);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        switch (comando) {
            case "Inicio":
                cambiarPanel(new InicioPanel());
                break;
            case "Usuarios":
                cambiarPanel(new UsuariosPanel());
                break;
            case "Membresias":
                cambiarPanel(new MembresiasPanel());
                break;
            case "Planes":
                cambiarPanel(new PlanesPanel());
                break;
        }
    }

}
