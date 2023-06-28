package com.gym.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class RoundedPanel extends JPanel {
	private JTextField textField;
	
	public RoundedPanel() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(174, 110, 86, 20);
		add(textField);
		textField.setColumns(10);
		
	}
}
