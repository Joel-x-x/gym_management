package com.gym.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;

import javax.swing.JPanel;

import com.gym.controller.MembresiaController;
import com.gym.model.Administrador;
import com.gym.model.Membresia;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class MembresiasPanel extends JPanel {
	private int administrador_id;
	private MembresiaController membresiaController;
	private DefaultTableModel modelo;
	
    private Calendar fechaInicio;
    private Calendar fechaFin;
    private JDateChooser dateChooserInicio;
    private JDateChooser dateChooserFin;
	
	private static final long serialVersionUID = 2171384064364316402L;
	private JTable table;
	private JTextField textBuscar;
	
	public void listar() {
		String[] cabeceras = {"Id", "Membresía", "Nombre", "Cédula", "Finalización", "Clase", "Entrenador", "No Factura", "Estado"};
		
		modelo = new DefaultTableModel(membresiaController.listar(administrador_id, textBuscar.getText()), cabeceras);
		
		table.setModel(modelo);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(4).setPreferredWidth(130);
		table.getColumnModel().getColumn(7).setPreferredWidth(25);
		table.getColumnModel().getColumn(8).setPreferredWidth(10);
		
		// Asiganar un color a la comuna estado
		table.getColumnModel().getColumn(8).setCellRenderer(renderer);
		
	}
	
	// Crea el render para asignar un color a la columna estado en base a este
    DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
		private static final long serialVersionUID = -8267339185456423635L;

		@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                       boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String estado = (String) table.getValueAt(row, 8);
            if (estado.equals("Activa")) {
                component.setBackground(new Color(112, 236, 98));
            } else if(estado.equals("Caducada")){
            	component.setBackground(new Color(241, 151, 120));
            }

            return component;
        }
    };
    
	public void buscarMembresiasFecha() {
		fechaInicio = dateChooserInicio.getCalendar();
		fechaFin = dateChooserFin.getCalendar();
		
		if(fechaInicio == null) {
			JOptionPane.showMessageDialog(null, "El campo fecha inicio no puede ir vacio");
			return;
		}
		
		if(fechaFin == null) {
			JOptionPane.showMessageDialog(null, "El campo fecha fin no puede ir vacio");
			return;
		}
		
		fechaFin.add(Calendar.DAY_OF_MONTH, 1);
		
        Date fechaInicioSQL = new Date(fechaInicio.getTimeInMillis());
        Date fechaFinSQL = new Date(fechaFin.getTimeInMillis());
		
        List<Membresia> listaMembresias = membresiaController.consultarFecha(administrador_id, fechaInicioSQL, fechaFinSQL);
        
        listaMembresias.forEach(x -> System.out.println(x.getFecha_inicio()));
try {
			
			
			String ruta = System.getProperty("user.home");
			FileOutputStream archivo = new FileOutputStream(ruta + "/Downloads/"+ fechaFinSQL +".pdf");
			Document documento = new Document();
			PdfWriter.getInstance(documento, archivo);
			documento.open();
			
			Paragraph parrafo = new Paragraph("REPORTE MEMBRESIAS");
			parrafo.setAlignment(1);
			documento.add(parrafo);
			for (int i = 0; i < listaMembresias.size(); i++) {
	            String elemento = listaMembresias.get(i).getFecha_inicio();
	            documento.add(new Paragraph(elemento));
	        }
			
			documento.close();
			
		} catch ( FileNotFoundException e1) {
			
			System.out.println(e1);
		} catch (DocumentException e1) {
			
			e1.printStackTrace();
		}
		String ruta = System.getProperty("user.home");
		File path = new File(ruta +"/Downloads/"+fechaFinSQL+ ".pdf");
		System.out.println(path);

		if (path.exists()) {
    try {
        Desktop.getDesktop().open(path);
    } catch (IOException e1) {
        e1.printStackTrace();
    }
}
        

	}

	public MembresiasPanel(int panelAncho, int panelAlto) {
		administrador_id = new Administrador().getId();
		membresiaController = new MembresiaController();
		
		setSize(1080, 800);
        setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblMembresas = new JLabel("MEMBRESÍAS");
        lblMembresas.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblMembresas.setBounds(40, 40, 170, 37);
        add(lblMembresas);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setAutoscrolls(true);
        scrollPane.setBounds(40, 134, 996, 550);
        add(scrollPane);
        
        table = new JTable();
        table.setBackground(Color.WHITE);
        scrollPane.setViewportView(table);
        
        JLabel lblNewLabel_1_4_2 = new JLabel("Buscar Nombre/Cédula");
        lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_1_4_2.setBounds(40, 104, 150, 14);
        add(lblNewLabel_1_4_2);
        
        textBuscar = new JTextField();
        textBuscar.addCaretListener(new CaretListener() {
        	public void caretUpdate(CaretEvent e) {
        		listar();
        	}
        });
        textBuscar.setColumns(10);
        textBuscar.setBounds(163, 98, 200, 25);
        add(textBuscar);
        
        JButton btnNewButton_2 = new JButton("Generar reporte");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		buscarMembresiasFecha();
        	}
        });
        btnNewButton_2.setForeground(Color.WHITE);
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnNewButton_2.setFocusPainted(false);
        btnNewButton_2.setBorder(null);
        btnNewButton_2.setBackground(new Color(46, 56, 64));
        btnNewButton_2.setBounds(877, 93, 159, 30);
        add(btnNewButton_2);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Fecha inicio");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1_1.setBounds(581, 75, 131, 14);
        add(lblNewLabel_2_1_1);
        
        dateChooserInicio = new JDateChooser();
        dateChooserInicio.setBounds(581, 93, 138, 30);
        add(dateChooserInicio);
        
        JLabel lblNewLabel_2_1_1_1 = new JLabel("Fecha fin");
        lblNewLabel_2_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblNewLabel_2_1_1_1.setBounds(729, 75, 131, 14);
        add(lblNewLabel_2_1_1_1);
        
        dateChooserFin = new JDateChooser();
        dateChooserFin.setBounds(729, 93, 138, 30);
        add(dateChooserFin);
        
        listar();
	}
}
