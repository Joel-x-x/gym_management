package com.gym.pruebas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gym.model.Usuario;
import com.gym.utilidades.FechasUtilidades;
import com.gym.view.BuscarUsuarioInterfaz;
import com.gym.view.UsuariosFrame;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;

public class PruebaFrame extends JFrame implements BuscarUsuarioInterfaz {

	private static final long serialVersionUID = 8591064380823471955L;
	
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private JDateChooser dateChooserInicio;
	private JDateChooser dateChooserFin;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					PruebaFrame frame = new PruebaFrame();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public void listarUsuarios() {
		UsuariosFrame usuarios = new UsuariosFrame(this);
		usuarios.setVisible(true);
	}
	
	@Override
	public void mostrarUsuario(Usuario usuario) {
		System.out.println(usuario.getNombre());
	}
	
	/**
	 * Create the frame.
	 */
	public PruebaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		dateChooserInicio = new JDateChooser();
		dateChooserInicio.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}
		});

		dateChooserInicio.setBounds(10, 11, 100, 30);
		panel.add(dateChooserInicio);
		
		dateChooserFin = new JDateChooser();
		dateChooserFin.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {

			}
		});

		dateChooserFin.setBounds(150, 11, 100, 30);
		panel.add(dateChooserFin);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarUsuarios();
//				fechaInicio = dateChooserInicio.getCalendar();
//				System.out.println(FechasUtilidades.calendarToString(fechaInicio));
			}
		});
		btnNewButton.setBounds(104, 110, 89, 23);
		panel.add(btnNewButton);
	}
}
