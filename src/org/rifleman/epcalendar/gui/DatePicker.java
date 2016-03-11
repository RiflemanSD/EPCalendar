package org.rifleman.epcalendar.gui;

import java.awt.BorderLayout;
import java.util.Date;
import org.jdesktop.swingx.JXDatePicker;


/** <h1>﻿DatePicker</h1>
 * 
 * <p></p>
 * 
 * <p>Last Update: 01/02/2016</p>
 * <p>Author: <a href=https://github.com/RiflemanSD>RiflemanSD</a></p>
 * 
 * <p>Copyright © 2016 Sotiris Doudis | All rights reserved</p>
 * 
 * @version 1.0.7
 * @author RiflemanSD
 */
public class DatePicker extends javax.swing.JPanel {
    private JXDatePicker picker;
    /**
     * Creates new form DatePicker
     */
    public DatePicker() {
        initComponents();
        
        picker = new JXDatePicker(new Date());
        this.add(picker, BorderLayout.CENTER);
    }
    public Date getDate() {
        return this.picker.getDate();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}