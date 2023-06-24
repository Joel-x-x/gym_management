package com.gym.view;

import javax.swing.JFrame;

public class AdminFrame extends JFrame {

    private AdminPanel adminPanel;
    
    public AdminFrame() {
        adminPanel = new AdminPanel();
        
        setTitle("Panel de Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setResizable(false);
        
        setContentPane(adminPanel);
    }

}
