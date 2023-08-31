package com.gym.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gym.model.Arduino;
import com.gym.model.ArduinoDataListener;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AgregarHuellaFrame extends JFrame {
	private int id;
	private static final long serialVersionUID = 7152304837393286877L;
	private JPanel contentPane;
	private JLabel labelMensaje;
	
	public void agregarHuella() {
		Arduino.sendCommand("r" + id);
		ArduinoDataListener.setClass(this);
	}
	
	public void modificarLabel(String mensaje) {
		labelMensaje.setText(mensaje);
	}

	public AgregarHuellaFrame(int id) {
		this.id = id;

		Arduino.initializeSerialPort();
		
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		panel.setBackground(new Color(244, 244, 244));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblInicio_2 = new JLabel("Lector dactilar");
		lblInicio_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInicio_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblInicio_2.setBounds(88, 41, 240, 49);
		panel.add(lblInicio_2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(108, 101, 200, 96);
		panel.add(panel_1);
		
		labelMensaje = new JLabel("Coloca tu dedo");
		labelMensaje.setHorizontalAlignment(SwingConstants.CENTER);
		labelMensaje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelMensaje.setBounds(0, 23, 200, 49);
		panel_1.add(labelMensaje);
		
		agregarHuella();
	}
}
