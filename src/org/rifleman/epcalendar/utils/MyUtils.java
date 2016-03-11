/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.rifleman.epcalendar.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rifleman.epcalendar.database.EventDB;

/**
 *
 * @author user001
 */
public class MyUtils {
    public static boolean stringToBoolean(String str) {
        if (str.equals("true")) return true;
        else return false;
    }
    public static int stringToInt(String str) {
        int number = -1;
        
        try {
            number = Integer.parseInt(str);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        return number;
    }
    public static double stringToDouble(String str) {
        double number = -1;
        
        try {
            number = Double.parseDouble(str);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        return number;
    }
    
    public static String currentTime() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();        
        String reportDate = df.format(today);
        
        return reportDate;
    }
    public static String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String reportDate = df.format(date);
        
        return reportDate;
    }
    
    public static Date getDate(String sdate) {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            return format.parse(sdate);
        } catch (ParseException ex) {
            Logger.getLogger(EventDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Date();
    }
}
