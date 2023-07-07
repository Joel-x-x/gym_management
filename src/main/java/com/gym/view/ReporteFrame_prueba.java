package com.gym.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class ReporteFrame_prueba extends JFrame {

	private JPanel contentPane;
	private JLabel lbl_reporte_nombre_usuario;
	private JLabel lbl_reporte_apellido_usuario;
	private JLabel lbl_reporte_correo_usuario;
	private JLabel lbl_reporte_cedula_usuario;
	private JLabel lbl_reporte_telefono_usuario;
	private JLabel lbl_reporte_edad_usuario;
	private JLabel lbl_reporte_fecha_fisico_usuario;
	private JLabel lbl_reporte_peso_usuario;
	private JLabel lbl_reporte_altura_usuario;
	private JLabel lbl_reporte_total_suma_de_membresias;
	private JPanel panel_reporte_membresias;
	
	// Membresias
	private JLabel lbl_fecha_inicio;
	private JLabel lbl_fecha_fin;
	private JLabel lbl_fecha_fi3n;
	private JLabel lbl_fecha_fi4n;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReporteFrame_prueba frame = new ReporteFrame_prueba();
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
	public ReporteFrame_prueba() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 984, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblReporteUsuario = new JLabel("REPORTE USUARIO");
		lblReporteUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblReporteUsuario.setBounds(10, 25, 964, 37);
		panel.add(lblReporteUsuario);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsuario.setBounds(24, 104, 197, 37);
		panel.add(lblUsuario);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(24, 152, 75, 14);
		panel.add(lblNewLabel_1);
		
		lbl_reporte_nombre_usuario = new JLabel("Nombre");
		lbl_reporte_nombre_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_nombre_usuario.setBounds(109, 152, 112, 14);
		panel.add(lbl_reporte_nombre_usuario);
		
		JLabel lblFisico = new JLabel("FISICO");
		lblFisico.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFisico.setBounds(24, 377, 197, 37);
		panel.add(lblFisico);
		
		JLabel lblMembresia = new JLabel("MEMBRESIAS");
		lblMembresia.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMembresia.setBounds(329, 104, 197, 37);
		panel.add(lblMembresia);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(329, 152, 645, 282);
		panel.add(scrollPane);
		
		panel_reporte_membresias = new JPanel();
		scrollPane.setViewportView(panel_reporte_membresias);
		panel_reporte_membresias.setPreferredSize(new Dimension(600, 1000));
		panel_reporte_membresias.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel = new JLabel("Apellido:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(24, 182, 61, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Correo:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(24, 212, 61, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cedula:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_1.setBounds(24, 237, 61, 14);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("Telefono:");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_2.setBounds(24, 262, 61, 14);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("Dirección:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_3.setBounds(24, 287, 61, 14);
		panel.add(lblNewLabel_2_3);
		
		lbl_reporte_apellido_usuario = new JLabel("Nombre");
		lbl_reporte_apellido_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_apellido_usuario.setBounds(109, 182, 112, 14);
		panel.add(lbl_reporte_apellido_usuario);
		
		lbl_reporte_correo_usuario = new JLabel("Nombre");
		lbl_reporte_correo_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_correo_usuario.setBounds(109, 212, 112, 14);
		panel.add(lbl_reporte_correo_usuario);
		
		lbl_reporte_cedula_usuario = new JLabel("Nombre");
		lbl_reporte_cedula_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_cedula_usuario.setBounds(109, 237, 112, 14);
		panel.add(lbl_reporte_cedula_usuario);
		
		lbl_reporte_telefono_usuario = new JLabel("Nombre");
		lbl_reporte_telefono_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_telefono_usuario.setBounds(109, 262, 112, 14);
		panel.add(lbl_reporte_telefono_usuario);
		
		JLabel lbl_reprote_direccion_usuario = new JLabel("Nombre");
		lbl_reprote_direccion_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reprote_direccion_usuario.setBounds(109, 287, 112, 14);
		panel.add(lbl_reprote_direccion_usuario);
		
		JLabel lblNewLabel_2_3_1 = new JLabel("Edad:");
		lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_3_1.setBounds(24, 312, 61, 14);
		panel.add(lblNewLabel_2_3_1);
		
		lbl_reporte_edad_usuario = new JLabel("Nombre");
		lbl_reporte_edad_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_edad_usuario.setBounds(109, 312, 112, 14);
		panel.add(lbl_reporte_edad_usuario);
		
		JLabel lblNewLabel_2_3_1_1 = new JLabel("Fecha:");
		lblNewLabel_2_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_3_1_1.setBounds(24, 420, 47, 14);
		panel.add(lblNewLabel_2_3_1_1);
		
		lbl_reporte_fecha_fisico_usuario = new JLabel("Nombre");
		lbl_reporte_fecha_fisico_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_fecha_fisico_usuario.setBounds(81, 420, 238, 14);
		panel.add(lbl_reporte_fecha_fisico_usuario);
		
		JLabel lblNewLabel_2_3_1_1_1 = new JLabel("Peso:");
		lblNewLabel_2_3_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_3_1_1_1.setBounds(24, 456, 47, 14);
		panel.add(lblNewLabel_2_3_1_1_1);
		
		JLabel lblNewLabel_2_3_1_1_2 = new JLabel("Altura:");
		lblNewLabel_2_3_1_1_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_3_1_1_2.setBounds(24, 486, 47, 14);
		panel.add(lblNewLabel_2_3_1_1_2);
		
		lbl_reporte_peso_usuario = new JLabel("Nombre");
		lbl_reporte_peso_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_peso_usuario.setBounds(81, 456, 238, 14);
		panel.add(lbl_reporte_peso_usuario);
		
		lbl_reporte_altura_usuario = new JLabel("Nombre");
		lbl_reporte_altura_usuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_altura_usuario.setBounds(81, 486, 238, 14);
		panel.add(lbl_reporte_altura_usuario);
		
		JLabel lblNewLabel_2_3_1_1_2_1 = new JLabel("TOTAL:");
		lblNewLabel_2_3_1_1_2_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2_3_1_1_2_1.setBounds(665, 445, 47, 14);
		panel.add(lblNewLabel_2_3_1_1_2_1);
		
		lbl_reporte_total_suma_de_membresias = new JLabel("Nombre");
		lbl_reporte_total_suma_de_membresias.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lbl_reporte_total_suma_de_membresias.setBounds(736, 445, 238, 14);
		panel.add(lbl_reporte_total_suma_de_membresias);
	}
}
