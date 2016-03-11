/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rifleman.epcalendar.database;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.rifleman.epcalendar.classes.Event;

/**
 *
 * @author RiflemanSD
 */
public class EventDB {
    private SQLiteManager manager;
    
    public EventDB() {
        manager = new SQLiteManager("eventDB");
        
        manager.createTable("event", "id,title,desc,timeTo,repeatTime,weight,done,duration", 1, "s", "s", "NULL", -1, 1, true, 1);
        
        //Settings
        manager.createTable("settings", "id,name,value", 1, "s", "s");
    }
    
    public void saveEvent(Event event) {
        String title = event.getTitle();
        String desc = event.getDescription();
        String timeTo = "NULL";
        if (event.getDateTo() != null) timeTo = this.dateToString(event.getDateTo());
        int rTime = event.getRepaetTime();
        int w = event.getWeight();
        boolean d = event.isDone();
        int duration = event.getDuration();
        
        manager.insert("event", "title,desc,timeTo,repeatTime,weight,done,duration", title, desc, timeTo, rTime, w, d, duration);
    }
    public String getEvents() {
        String r = manager.select("event", "*", "", 7);
        return r;
    }
    public Event getEvent(String title) {
        String[] r = manager.select("event", "id,title,desc,timeTo,repeatTime,weight,done,duration", "title = '" + title + "'", 8).split(",");
        
        int id = stringToInt(r[0]);
        String desc = r[2];
        Date timeTo = getDate(r[3]);
        int rTime = stringToInt(r[4]);
        int w = stringToInt(r[5]);
        boolean d = stringToBoolean(r[6]);
        int duration = stringToInt(r[7]);
        
        return new Event(id, title, desc, timeTo, rTime, w, d, duration);
    }
    public Event getEvent(int id) {
        String[] r = manager.select("event", "id,title,desc,timeTo,repeatTime,weight,done,duration", "id = '" + id + "'", 8).split(",");
        
        String title = r[1];
        String desc = r[2];
        Date timeTo = getDate(r[3]);
        int rTime = stringToInt(r[4]);
        int w = stringToInt(r[5]);
        boolean d = stringToBoolean(r[6]);
        int duration = stringToInt(r[7]);
        
        return new Event(id, title, desc, timeTo, rTime, w, d, duration);
    }
    
    public void saveSetting(String name, String value) {
        manager.insert("settings", "name,value", name, value);
    }
    public void updateSetting(String name, String value) {
        System.out.println(value);
        manager.update("settings", "value = '" + value + "'", "name = '" + name + "'");
    }
    public String getSetting(String name) {
        String result = manager.select("settings", "value", "name = '" + name + "'", 1);
        if (result.isEmpty()) return null;
        
        //System.out.println(result.replace("\n", ""));
        
        return result.replace("\n", "").replace(" ", "");
    }
    
    public boolean stringToBoolean(String str) {
        if (str.equals("true")) return true;
        else return false;
    }
    public int stringToInt(String str) {
        int number = -1;
        
        try {
            number = Integer.parseInt(str);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        return number;
    }
    public double stringToDouble(String str) {
        double number = -1;
        
        try {
            number = Double.parseDouble(str);
        }
        catch (Exception e) {
            System.out.println(e);
        }
        
        return number;
    }
    
    public String currentTime() {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date today = Calendar.getInstance().getTime();        
        String reportDate = df.format(today);
        
        return reportDate;
    }
    public String dateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String reportDate = df.format(date);
        
        return reportDate;
    }
    
    public Date getDate(String sdate) {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        try {
            return format.parse(sdate);
        } catch (ParseException ex) {
            Logger.getLogger(EventDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new Date();
    }
}
