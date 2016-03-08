/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rifleman.epcalendar.gui;

import java.awt.Component;
import javax.swing.JPanel;

/**
 *
 * @author RiflemanSD
 */
public class MyPanelWithLayout extends JPanel {
    private int width;
    private int height;
    private int numberOfComponets;
    private int colSpace;
    private int lineSpace;
    private int startSpace;
    
    public MyPanelWithLayout(int width, int height) {
        super();
        this.width = width;
        this.height = height;
        this.lineSpace = 20;
        this.colSpace = 20;
        this.startSpace = 10;
        this.numberOfComponets = 0;
        this.setLayout(null);
    }
    
    public void myAdd(Component component) {
        System.out.println("hi");
        component.setBounds(numberOfComponets*colSpace + component.getWidth(), numberOfComponets*colSpace, component.getWidth(), component.getHeight());
        
        this.add(component);
        numberOfComponets++;
    }
}
