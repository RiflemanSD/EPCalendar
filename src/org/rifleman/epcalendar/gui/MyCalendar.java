/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rifleman.epcalendar.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author RiflemanSD
 */
public class MyCalendar extends JFrame {
    
    public MyCalendar() {
        this.rootPane.setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(5,7));
        
        for (int i = 1; i <= 35; i++) {
            p.add(new JButton("" + i));
        }
        //MyPanelWithLayout mp = new MyPanelWithLayout(200, 200);
        //JPanel jp = new JPanel();
        //jp.setLayout(null);
        
        //JButton b = new JButton("B");
        //b.setBounds(10, 10, b.getWidth(), b.getHeight());
        //jp.add(b);
        
        //mp.myAdd(b);
        //mp.add(b);
        //p.add(jp);
        //p.add(b);
        
        //this.rootPane.add(p, BorderLayout.CENTER);
        this.rootPane.add(new NewJPanel1(), BorderLayout.CENTER);
        this.rootPane.add(new EventList(), BorderLayout.PAGE_END);
        this.rootPane.add(new InsertEvent(), BorderLayout.PAGE_START);
        
        this.setBounds(100, 100, 300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
}
