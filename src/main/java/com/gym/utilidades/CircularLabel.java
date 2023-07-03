package com.gym.utilidades;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

public class CircularLabel extends JLabel {
	private static final long serialVersionUID = -8392549192820123704L;

	private BufferedImage image;
	private int zoom = 0;

	public void setImage(BufferedImage image) {
	    this.image = image;
	    repaint();
	}
	
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	@Override
	protected void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D) g.create();
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

	    int diameter = Math.min(getWidth(), getHeight());
	    int x = (getWidth() - diameter) / 2;
	    int y = (getHeight() - diameter) / 2;

	    Shape clip = new Ellipse2D.Float(x, y, diameter, diameter);
	    g2.setClip(clip);

	    if (image != null) {
	        int zoomedDiameter = diameter + (2 * zoom);
	        int zoomedX = x - zoom;
	        int zoomedY = y - zoom;
	        g2.drawImage(image, zoomedX, zoomedY, zoomedDiameter, zoomedDiameter, null);
	    } else {
	        g2.setColor(new Color(217, 217, 217));
	        g2.fillOval(x, y, diameter, diameter);
	    }

	    g2.dispose();

	    super.paintComponent(g);
	}
    
}

