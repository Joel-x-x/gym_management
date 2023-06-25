package com.gym.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class RegistroFrame extends JFrame {

	 private RegistroPanel registroPanel;
	 private InicioSesionPanel inicioSesionPanel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroFrame frame = new RegistroFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegistroFrame() {
		
        registroPanel = new RegistroPanel(this);
        inicioSesionPanel = new InicioSesionPanel(this);
		
		setTitle("Gym Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 800);
		setResizable(false);
		setLocationRelativeTo(null);
		
		setContentPane(inicioSesionPanel);
		
	}
	
    public void mostrarPanelInicioSesion() {
        setContentPane(inicioSesionPanel); // Cambia al panel de inicio de sesión
        revalidate();
        repaint();
    }
    
    public void mostrarPanelRegistro() {
        setContentPane(registroPanel); // Cambia al panel de inicio de sesión
        revalidate();
        repaint();
    }
    
}
