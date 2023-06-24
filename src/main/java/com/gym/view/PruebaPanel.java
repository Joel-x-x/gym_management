package com.gym.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JButton;

public class PruebaPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PruebaPanel() {
		setLayout(null);
		setBackground(Color.BLACK);
		setBounds(0,0,1280,720);
		
		JButton boton = new JButton("Cambiar Panel");
		boton.setBounds(335, 267, 148, 23);
		add(boton);
	}

}
