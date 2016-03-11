/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rifleman.epcalendar.test;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author RiflemanSD
 */
public class NewClassGraphics extends JPanel {

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.PLAIN, 20);
        g2.setFont(font);
        
        int s = 1, h = 1, v = 0;
//        for (int i = 0; i < 5; i++) {
//            g2.drawString(i + "", i*20 + 10, 20);
//        }
        
        for (int i = 0; i < 5; i++) {
            v += 30;
            for (int j = 0; j < 7; j++) {
                if (j == 0) h = 1;
                else h = 30;
                
                g2.drawString(s + "", j*h + 10, i + v);
                s++;
            }
        }
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.getContentPane().add(new NewClassGraphics());
        f.setSize(300, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
