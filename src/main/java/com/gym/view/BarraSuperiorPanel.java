package com.gym.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import com.gym.utilidades.CircularLabel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class BarraSuperiorPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1014488060944978809L;

	public BarraSuperiorPanel() {
        setPreferredSize(new Dimension(1280, 80));
        setBackground(new Color(230, 230, 230));
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Gym");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Candara", Font.BOLD, 16));
        lblNewLabel.setBounds(50, 16, 66, 22);
        add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setIcon(new ImageIcon(BarraSuperiorPanel.class.getResource("/com/gym/resources/pesa.png")));
        lblNewLabel_1.setBounds(20, 10, 30, 30);
        add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("");
        lblNewLabel_1_1.setIcon(new ImageIcon(BarraSuperiorPanel.class.getResource("/com/gym/resources/config.png")));
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setBounds(1210, 8, 35, 35);
        add(lblNewLabel_1_1);
        
        // Crear una instancia de la imagen para poder redondearla
        BufferedImage image = null;
        
        try {
            URL imageUrl = BarraPanel.class.getResource("/com/gym/resources/user.png");
            image = ImageIO.read(imageUrl);
            
            // Operaciones con la imagen...
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        CircularLabel labelUsuario = new CircularLabel();
        labelUsuario.setImage(image);
        labelUsuario.setZoom(5);
        labelUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        labelUsuario.setBounds(1085, 8, 40, 33);
        add(labelUsuario);
        
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsuario.setForeground(Color.BLACK);
        lblUsuario.setFont(new Font("Candara", Font.BOLD, 16));
        lblUsuario.setBounds(1125, 16, 66, 22);
        add(lblUsuario);
    }
}