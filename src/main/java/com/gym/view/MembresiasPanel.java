package com.gym.view;

import javax.swing.*;
import java.awt.*;

public class MembresiasPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6442770399461125032L;
	
    public MembresiasPanel(int panelAncho, int panelAlto) {
    	
    	setPreferredSize(new Dimension(1080, 800));
        setBackground(Color.GREEN);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Proximamente...");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(443, 339, 163, 30);
        add(lblNewLabel);
    }
}
