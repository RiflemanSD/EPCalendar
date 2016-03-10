/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rifleman.epcalendar.gui;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author user01
 */
public class TimePicker extends JPanel {
    JSpinner timeSpinner;
    
    public TimePicker() {
        this.setLayout(new BorderLayout());
        
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        
        this.add(timeSpinner, BorderLayout.CENTER);
    }
    public TimePicker(String type) {
        this.setLayout(new BorderLayout());
        
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, type);
        timeSpinner.setEditor(timeEditor);
        timeSpinner.setValue(new Date());
        
        this.add(timeSpinner, BorderLayout.CENTER);
    }
    public TimePicker(String type, Date date) {
        this.setLayout(new BorderLayout());
        
        timeSpinner = new JSpinner( new SpinnerDateModel() );
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, type);
        timeSpinner.setEditor(timeEditor);
        
        if (date == null) timeSpinner.setValue(new Date());
        else timeSpinner.setValue(date);
        
        this.add(timeSpinner, BorderLayout.CENTER);
    }
    
    public String getTime() {
        return ((Date)this.timeSpinner.getValue()).toString();
    }
    public void setTime(String value) {
        this.timeSpinner.setValue(new Date(value));
    }
}
