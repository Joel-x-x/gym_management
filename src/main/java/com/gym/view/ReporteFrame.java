package com.gym.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class ReporteFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteFrame frame = new ReporteFrame();
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
	public ReporteFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 784, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblReporteUsuario = new JLabel("REPORTE USUARIO");
		lblReporteUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReporteUsuario.setBounds(10, 25, 764, 37);
		panel.add(lblReporteUsuario);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsuario.setBounds(24, 104, 197, 37);
		panel.add(lblUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(24, 152, 75, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(109, 152, 112, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblFisico = new JLabel("FISICO");
		lblFisico.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFisico.setBounds(24, 377, 197, 37);
		panel.add(lblFisico);
		
		JLabel lblMembresia = new JLabel("MEMBRESIAS");
		lblMembresia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMembresia.setBounds(329, 104, 197, 37);
		panel.add(lblMembresia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 152, 428, 363);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setBackground(new Color(255, 255, 255));
	}
}
