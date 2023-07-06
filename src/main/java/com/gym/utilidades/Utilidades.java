package com.gym.utilidades;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Utilidades {
	public boolean toBoolean(int numero) {
		if(numero > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public void copiar(String string) {
		
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		StringSelection selection = new StringSelection(string);
		
		clipboard.setContents(selection, null);
		
	}
	
	public BufferedImage resizeImage(BufferedImage image, int width, int height) {
	    Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

	    BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImage.createGraphics();
	    g2.drawImage(scaledImage, 0, 0, null);
	    g2.dispose();

	    return resizedImage;
	}
	
	public static BufferedImage obtenerBufferedImage(byte[] imageData) {
	    try {
	        ByteArrayInputStream inputStream = new ByteArrayInputStream(imageData);
	        return ImageIO.read(inputStream);
	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	public static String codigoToMensajeEliminar(int error, String mensajeRestriccion) {
		
		switch (error) {
		case 0:
			return "No se puedo eliminar";
		case 1:
			return "Eliminado con exito!";
		case 1451:
			return mensajeRestriccion;
		default:
			return "No se puedo eliminar";
		}

	}
}
