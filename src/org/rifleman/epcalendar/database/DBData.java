package org.rifleman.epcalendar.database;

/** <h1>﻿DBData</h1>
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
public class DBData {
    public static final int NO_ERROR = 1; 
    public static final int ERROR_CONNECTION = -1;
    public static final int ERROR_CLOSE_CONNECTION = -2;
    public static final int ERROR_COMMIT = -3;
    public static final int ERROR_Statement = -4;
    public static final int ERROR_4 = -5;
    public static final int ERROR_5 = -6;
    
    /*
    java.sql.SQLException: UNIQUE constraint failed: group1.ID
    java.sql.SQLException: no such column: 'ide'
    java.sql.SQLException: [SQLITE_ERROR] SQL error or missing database (near "SELECTe": syntax error)
    java.sql.SQLException: [SQLITE_ERROR] SQL error or missing database (no such table: ma)
    */
    
        /*
    SQL Injection Prevention
    https://www.owasp.org/index.php/SQL_Injection_Prevention_Cheat_Sheet
    
     String custname = request.getParameter("customerName"); // This should REALLY be validated too
 // perform input validation to detect attacks
 String query = "SELECT account_balance FROM user_data WHERE user_name = ? ";
 
 PreparedStatement pstmt = connection.prepareStatement( query );
 pstmt.setString( 1, custname); 
 ResultSet results = pstmt.executeQuery( );
    
    
     String custname = request.getParameter("customerName"); // This should REALLY be validated
 try {
 	CallableStatement cs = connection.prepareCall("{call sp_getAccountBalance(?)}");
 	cs.setString(1, custname);
 	ResultSet results = cs.executeQuery();		
 	// … result set handling 
 } catch (SQLException se) {			
 	// … logging and error handling
 }
    
    
    Codec ORACLE_CODEC = new OracleCodec();
 String query = "SELECT user_id FROM user_data WHERE user_name = '" + 
   ESAPI.encoder().encodeForSQL( ORACLE_CODEC, req.getParameter("userID")) + "' and user_password = '"
   + ESAPI.encoder().encodeForSQL( ORACLE_CODEC, req.getParameter("pwd")) +"'";
    */
}
