package com.gym.view;

import javax.swing.*;

import com.gym.model.Arduino;
import com.gym.model.ArduinoDataListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AdminFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = -3509542713044310899L;
	private BarraSuperiorPanel barraSuperiorPanel;
    private BarraPanel barraPanel;
    private JPanel panelPrincipal;
    private int panelAncho = 1080, panelAlto = 750;
    
    public AdminFrame() {
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    	    	// Al cambiar de ventana cortar la conexión
    	    	try {
    	    		if(Arduino.isActivo()) {
    	    			Arduino.sendCommand("t"); // Comando que para el arduino
    	    			Arduino.close(); // Cerrar conexión
    	    		}
    	    	} catch(Exception evt) {
    	    		evt.printStackTrace();
    	    	}
    			
    	    	System.exit(0);
    		}
    	});
    	setIconImage(new ImageIcon(getClass().getResource("/com/gym/resources/pesa.png")).getImage());
    	setTitle("Gym");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1280, 800);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // BarraSuperiorPanel
        barraSuperiorPanel = new BarraSuperiorPanel(this);
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
        
        cambiarPanel(new RegistrosDiariosPanel(panelAncho, panelAlto));
        
        // Agregar BarraPanel y panelPrincipal al JFrame
        getContentPane().add(barraSuperiorPanel, BorderLayout.NORTH);
        getContentPane().add(barraPanel, BorderLayout.WEST);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);

        setVisible(true);
    }

    public void cambiarPanel(JPanel nuevoPanel) {
    	// Al cambiar de ventana cortar la conexión
    	try {
    		if(!String.valueOf(nuevoPanel.getClass()).equals("class com.gym.view.RegistrosDiariosPanel") && Arduino.isActivo()) {
    			Arduino.sendCommand("t"); // Comando que para el arduino
    			Arduino.close(); // Cerrar conexión
    			Arduino.setActivo(false); // Poner atributo de arduino activo en falso
    			ArduinoDataListener.limpiarMensaje(); // Resetear el mensaje de arduino
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
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
                cambiarPanel(new RegistrosDiariosPanel(panelAncho, panelAlto));
                break;
            case "Usuarios":
                cambiarPanel(new UsuariosPanel(panelAncho, panelAlto));
                break;
            case "Membresias":
                cambiarPanel(new TipoMembresiasPanel(panelAncho, panelAlto));
                break;
            case "Planes":
                cambiarPanel(new EntrenadorClasePanel(panelAncho, panelAlto));
                break;
        }
    }

}
