package com.gym.view;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroPanel extends JPanel {

    private RegistroFrame registroFrame;
    private JButton iniciarSesionButton;
    
    public RegistroPanel(RegistroFrame frame) {
        registroFrame = frame;
        
        iniciarSesionButton = new JButton("Iniciar Sesión");
        iniciarSesionButton.setBounds(165, 231, 95, 23);
        iniciarSesionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registroFrame.mostrarPanelInicioSesion(); // Cambia al panel de inicio de sesión
            }
        });
        setLayout(null);
        
        add(iniciarSesionButton);
    }

}
