package com.gym.view;

import javax.swing.*;
import java.awt.*;

public class InicioPanel extends JPanel {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InicioPanel(int panelAncho, int panelAlto) {
    	
    	setPreferredSize(new Dimension(panelAncho, panelAlto));
        setBackground(Color.RED);
    }
}
