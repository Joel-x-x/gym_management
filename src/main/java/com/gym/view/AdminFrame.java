package com.gym.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3509542713044310899L;
	private BarraSuperiorPanel barraSuperiorPanel;
    private BarraPanel barraPanel;
    private JPanel panelPrincipal;
    private int panelAncho = 1080, panelAlto = 750;
    
    public AdminFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // BarraSuperiorPanel
        barraSuperiorPanel = new BarraSuperiorPanel();
        barraSuperiorPanel.setPreferredSize(new Dimension(1280, 50));

        // BarraPanel
        barraPanel = new BarraPanel(this);
        barraPanel.setPreferredSize(new Dimension(200, 750));

        // Panel principal
        panelPrincipal = new JPanel();
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setPreferredSize(new Dimension(1080, 750));
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        
        cambiarPanel(new InicioPanel(panelAncho, panelAlto));
        
        // Agregar BarraPanel y panelPrincipal al JFrame
        add(barraSuperiorPanel, BorderLayout.NORTH);
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
                cambiarPanel(new InicioPanel(panelAncho, panelAlto));
                break;
            case "Usuarios":
                cambiarPanel(new UsuariosPanel(panelAncho, panelAlto));
                break;
            case "Membresias":
                cambiarPanel(new MembresiasPanel(panelAncho, panelAlto));
                break;
            case "Planes":
                cambiarPanel(new PlanesPanel(panelAncho, panelAlto));
                break;
        }
    }

}
