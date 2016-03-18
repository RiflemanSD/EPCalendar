
package org.rifleman.epcalendar;

import org.rifleman.epcalendar.database.EventDB;
import org.rifleman.epcalendar.gui.MyCalendar;

/**
 * <h1>EPCalendar</h1>
 * <h3>Class EPCalendar<h3> 
 * <p>Created: 8 Μαρ 2016, 12:04:06 πμ</p></br>
 *
 * <p>Copyright © 2015 | RiflemanSD | All right reserved</p>
 *
 * @author RiflemanSD
 */
public class EPCalendar {
    public static EventDB database;
    
    public EPCalendar() {
        database = new EventDB();
        
        System.out.println("hey");
        MyCalendar c = new MyCalendar();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new EPCalendar();
    }

}
