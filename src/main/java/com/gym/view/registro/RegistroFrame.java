package com.gym.view.registro;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class RegistroFrame extends JFrame {

	private static final long serialVersionUID = -8043917424973103874L;
	private RegistroPanel registroPanel;
	 private InicioSesionPanel inicioSesionPanel;
	 private RecuperarCuentaPanel recuperarCuenta;
	 private CambiarPasswordPanel cambiarPassword;
	
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

	public RegistroFrame() {
		
        registroPanel = new RegistroPanel(this);
        inicioSesionPanel = new InicioSesionPanel(this);
        recuperarCuenta = new RecuperarCuentaPanel(this);
        cambiarPassword = new CambiarPasswordPanel(this);
		
        setIconImage(new ImageIcon(getClass().getResource("/com/gym/resources/pesa.png")).getImage());
		setTitle("Gym");
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
    
    public void mostrarPanelRecuperarCuenta() {
        setContentPane(recuperarCuenta); // Cambia al panel de RecuperarCuenta
        revalidate();
        repaint();
    }
    
    public void mostrarCambiarPassword() {
        setContentPane(cambiarPassword); // Cambia al panel de CambiarPassword
        revalidate();
        repaint();
    }
    
}
