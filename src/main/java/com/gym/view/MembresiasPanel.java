package com.gym.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MembresiasPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6442770399461125032L;
	private JTextField txt_nombre_plan_membresias;
	private JTextField txt_valor_extra_membresias;
	private JTextField txt_nombre_clase_membresias;
	private JTextField txt_buscar_usuarios_membresias;
	private JTable tabla_membresias_membresias;
	private JTable tabla_usuarios_membresias;
	private JLabel lbl_valor_total;
	private JButton btn_buscar_usuarios_membresias;
	private JButton btn_agregar_membresias;
	private JButton btn_modificar_membresias;
	private JButton btn_eliminar_membresias;
	
    public MembresiasPanel(int panelAncho, int panelAlto) {
    	
    	setPreferredSize(new Dimension(1080, 800));

        setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("MEMBRESIAS");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 11, 121, 33);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("Plan");
        lblNewLabel_1.setBounds(10, 55, 46, 14);
        add(lblNewLabel_1);
        
        txt_nombre_plan_membresias = new JTextField();
        txt_nombre_plan_membresias.setBounds(10, 80, 235, 20);
        add(txt_nombre_plan_membresias);
        txt_nombre_plan_membresias.setColumns(10);
        
        txt_valor_extra_membresias = new JTextField();
        txt_valor_extra_membresias.setBounds(10, 142, 235, 20);
        add(txt_valor_extra_membresias);
        txt_valor_extra_membresias.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("Valor Extra");
        lblNewLabel_2.setBounds(10, 117, 72, 14);
        add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("Clase");
        lblNewLabel_3.setBounds(284, 55, 46, 14);
        add(lblNewLabel_3);
        
        txt_nombre_clase_membresias = new JTextField();
        txt_nombre_clase_membresias.setBounds(284, 80, 218, 20);
        add(txt_nombre_clase_membresias);
        txt_nombre_clase_membresias.setColumns(10);
        
        JLabel lblNewLabel_4 = new JLabel("VALOR TOTAL");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(178, 169, 164, 64);
        add(lblNewLabel_4);
        
        lbl_valor_total = new JLabel("0");
        lbl_valor_total.setFont(new Font("Tahoma", Font.PLAIN, 24));
        lbl_valor_total.setBounds(188, 223, 147, 56);
        add(lbl_valor_total);
        
        btn_agregar_membresias = new JButton("Agregar");
        btn_agregar_membresias.setForeground(Color.WHITE);
        btn_agregar_membresias.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_agregar_membresias.setBorder(null);
        btn_agregar_membresias.setBackground(new Color(46, 56, 64));
        btn_agregar_membresias.setBounds(10, 290, 150, 30);
        add(btn_agregar_membresias);
        
        btn_modificar_membresias = new JButton("Modificar");
        btn_modificar_membresias.setForeground(Color.WHITE);
        btn_modificar_membresias.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_modificar_membresias.setBorder(null);
        btn_modificar_membresias.setBackground(new Color(46, 56, 64));
        btn_modificar_membresias.setBounds(178, 290, 150, 30);
        add(btn_modificar_membresias);
        
        btn_eliminar_membresias = new JButton("Eliminar");
        btn_eliminar_membresias.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btn_eliminar_membresias.setForeground(Color.WHITE);
        btn_eliminar_membresias.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_eliminar_membresias.setBorder(null);
        btn_eliminar_membresias.setBackground(new Color(46, 56, 64));
        btn_eliminar_membresias.setBounds(350, 290, 150, 30);
        add(btn_eliminar_membresias);
        
        JLabel lblNewLabel_5 = new JLabel("Buscar");
        lblNewLabel_5.setBounds(10, 349, 46, 14);
        add(lblNewLabel_5);
        
        txt_buscar_usuarios_membresias = new JTextField();
        txt_buscar_usuarios_membresias.setBounds(10, 374, 213, 20);
        add(txt_buscar_usuarios_membresias);
        txt_buscar_usuarios_membresias.setColumns(10);
        
        JScrollPane scrollPane_membresias_membresias = new JScrollPane();
        scrollPane_membresias_membresias.setBounds(512, 22, 468, 233);
        add(scrollPane_membresias_membresias);
        
        tabla_membresias_membresias = new JTable();
        scrollPane_membresias_membresias.setViewportView(tabla_membresias_membresias);
        
        JScrollPane scrollPane_usuarios_membresias = new JScrollPane();
        scrollPane_usuarios_membresias.setBounds(10, 405, 970, 292);
        add(scrollPane_usuarios_membresias);
        
        tabla_usuarios_membresias = new JTable();
        scrollPane_usuarios_membresias.setViewportView(tabla_usuarios_membresias);
        
        btn_buscar_usuarios_membresias = new JButton("Buscar");
        btn_buscar_usuarios_membresias.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        	}
        });
        btn_buscar_usuarios_membresias.setForeground(Color.WHITE);
        btn_buscar_usuarios_membresias.setFont(new Font("Tahoma", Font.BOLD, 11));
        btn_buscar_usuarios_membresias.setBorder(null);
        btn_buscar_usuarios_membresias.setBackground(new Color(46, 56, 64));
        btn_buscar_usuarios_membresias.setBounds(233, 364, 150, 30);
        add(btn_buscar_usuarios_membresias);
    }
}
