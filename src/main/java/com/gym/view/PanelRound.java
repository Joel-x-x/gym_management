package com.gym.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class PanelRound {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo de Panel Redondeado");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        
        Pan panel = new Pan();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.RED);
        
        JLabel label = new JLabel("Etiqueta Redondeada");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setOpaque(false);  // Hacer que el fondo del JLabel sea transparente
        panel.add(label, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setVisible(true);
    }
}

class Pan extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        // Redondear el borde del panel
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Shape roundedRect = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
        g2.setColor(Color.GRAY);
        g2.fill(roundedRect);
        super.paintComponent(g);
    }
}
