
package org.rifleman.epcalendar.classes;

import java.util.Date;

/**
 * <h1>EPCalendar</h1>
 * <h3>Class Event</h3> 
 * <p>Created: 10 Μαρ 2016, 12:01:46 πμ</p>
 *
 * <p>Copyright © 2016 | RiflemanSD | All right reserved</p>
 *
 * @author RiflemanSD
 */
public class Event {
    private int id; 
    private String title; // Τίτλος
    private String description; // Αναλυτική περιγραφή
    private Date dateTo; // Χρόνος που θα πραγματοποιήθει
    private int repaetTime; // Μετά από πόση ώρα θα επαναληυθεί ή -1 για όχι επανάληψη
    private int weight; // Βάρος ( πιο event είναι πιο σημαντικό )
    private boolean done; // Πραγματοποιήθηκε το event ?

    public Event(int id, String title, String description, Date dateTo, int repaetTime, int weight, boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTo = dateTo;
        this.repaetTime = repaetTime;
        this.weight = weight;
        this.done = done;
    }
    public Event(int id, String title, String description, Date dateTo, int repaetTime, int weight) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTo = dateTo;
        this.repaetTime = repaetTime;
        this.weight = weight;
        this.done = false;
    }
    public Event(int id, String title, String description, int repaetTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTo = null;
        this.repaetTime = repaetTime;
        this.weight = 5;
        this.done = false;
    }
    public Event(int id, String title, String description, Date dateTo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTo = dateTo;
        this.repaetTime = -1;
        this.weight = 5;
        this.done = false;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public int getRepaetTime() {
        return repaetTime;
    }

    public void setRepaetTime(int repaetTime) {
        this.repaetTime = repaetTime;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
    
}
