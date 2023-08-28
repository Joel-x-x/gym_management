package com.gym.view;


import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class HistorialPreciosFrame extends JFrame {

	private static final long serialVersionUID = 1988158492743918802L;
	private JPanel contentPane;
	private JList<String> list;

	public HistorialPreciosFrame(DefaultListModel<String> listaPrecios) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 434, 461);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHistorialDePrecios = new JLabel("HISTORIAL DE PRECIOS");
		lblHistorialDePrecios.setHorizontalAlignment(SwingConstants.CENTER);
		lblHistorialDePrecios.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHistorialDePrecios.setBounds(0, 0, 434, 37);
		panel.add(lblHistorialDePrecios);
		
		JScrollPane scrollPane_membresias_membresias = new JScrollPane();
		scrollPane_membresias_membresias.setBounds(10, 48, 414, 398);
		panel.add(scrollPane_membresias_membresias);
		
		list = new JList<>(listaPrecios);
		list.setFont(new Font("Tahoma", Font.PLAIN, 20));
		scrollPane_membresias_membresias.setViewportView(list);
	}
}
