package com.gym.pruebas;

import java.io.FileNotFoundException;
import java.io.*;

import com.gym.controller.FacturaController;
import com.gym.dao.FacturaDAO;
import com.gym.factory.ConnectionFactory;
import com.gym.model.Factura;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


public class PruebaPDF {
	
	public FacturaController facturaControlller;
	public FacturaDAO facturaDAO;
	public Factura factura ;
	
	public PruebaPDF() {
		facturaDAO = new FacturaDAO(ConnectionFactory.conectar());
	}
	
	FacturaController facturaController = new FacturaController();
	

}
