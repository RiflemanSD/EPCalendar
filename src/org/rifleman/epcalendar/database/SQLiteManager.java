package org.rifleman.epcalendar.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** <h1>SQLite database</h1>
 * <p>This class use sqlite-jdbc library to
 * haddle a SQLite database</p>
 * 
 * <p>You need to have last version of sqlite-jdbc,
 * donwload here: <a href="https://bitbucket.org/xerial/sqlite-jdbc/downloads">sqlite-jdbc</a>
 * </p>
 * 
 * <div>
 * <ul>
 * <li>Created: 20-02-2015</li>
 * <li>Last Update: 19-09-2015</li>
 * </ul>
 * </div>
 * 
 * <p>Author: <a href="https://github.com/RiflemanSD">RiflemanSD</a></p>
 * 
 * @version 1.0.7
 * @author rifleman
 */
public class SQLiteManager {
    private Connection c;
    private String driver;
    private String dbUrl;
    
    /**
     * 
     * @param dbName database name
     */
    public SQLiteManager(String dbName) {
        this.driver = "org.sqlite.JDBC";
        this.dbUrl = "jdbc:sqlite:";
        
        this.openDB(dbName, true);
    }
    
    /**
     * 
     * @param dbName database name
     * @param autoCommit
     */
    public SQLiteManager(String dbName, boolean autoCommit) {
        this.driver = "org.sqlite.JDBC";
        this.dbUrl = "jdbc:sqlite:";
        
        this.openDB(dbName, autoCommit);
        
    }
    
    private int openDB(String db, boolean autoCommit) {
        c = null;
        try {
            Class.forName(this.driver);
            c = DriverManager.getConnection(this.dbUrl + db + ".db");
            
            c.setAutoCommit(autoCommit);
            
            return DBData.NO_ERROR;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            return DBData.ERROR_CONNECTION;
        }
    }
    
    public int close() {
        try {
            c.close();
            
            return DBData.NO_ERROR;
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            return DBData.ERROR_CLOSE_CONNECTION;
        }
    }

    public int commit() {
        try {
            c.commit();
            return DBData.NO_ERROR;
        } catch (SQLException ex) {
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
            
            return DBData.ERROR_COMMIT;
        }
    }
    
    private String validTable(String tableName) {
        //if (tableName.equals("default")) tableName = "_default";
        //if (tableName.contains("-")) tableName = tableName.replace("-", "_");
        
        return "[" + tableName + "]";
    }
    
    /**
     * @param querys - INSERT, UPDATE, DELETE
     * @return  0 if no error, &lt; 0 otherwise
     */
    public int executeUpdates(String... querys) {
        
        for (String query: querys) {
            try {
                Statement stmt = null;
                stmt = c.createStatement();
                stmt.executeUpdate(query);

                stmt.close();

            } catch (SQLException ex) {
                Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);

                //return DBData.ERROR_Statement;
            }   
        }
        return DBData.NO_ERROR;
    }
    
    /**
     * 
     * @param query
     * @param numberOfColumns
     * @return - the resault, at format: 1,name,80 /n 2,name,...
     */
    public String executeQuery(String query, int numberOfColumns) {
        int count = 0;
        String result = "";
        try {
            Statement stmt = null;
            stmt = c.createStatement();
            
            //System.out.println(stmt.getMaxRows() + " " + stmt.getMaxFieldSize());
            ResultSet rs = stmt.executeQuery(query);
            Object temp = null;
            
            while (rs.next()) {
                if (count == numberOfColumns-1) {
                    result += "\n";
                    count = 0;
                }
                
                temp = rs.getObject(1);
                result += "" + temp;
                
                for (int i = 2; i <= numberOfColumns; i++) {
                    temp = rs.getObject(i);
                    
                    result += "," + temp;
                    count++;
                }
            }
            rs.close();
            stmt.close();
            
            //return DBData.NO_ERROR;
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
            
            //return DBData.ERROR_Statement;
        }
        //if (result.length() < 1) return null;
        //System.out.println("re " + result + " re");
        return result;
    }
    
    public int createTable(String tableName, String sql) {
        tableName = this.validTable(tableName);
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            
            return DBData.NO_ERROR;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            return DBData.ERROR_Statement;
        }
    }
    
    public int createTable(String tableName, String... columns) {
        tableName = this.validTable(tableName);
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE if not exists " + tableName
                    + " (" + columns[0] + " INT PRIMARY KEY NOT NULL,"
                    + " " + columns[1] + " TEXT NOT NULL, "
                    + " " + columns[2] + " REAL NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            
            return DBData.NO_ERROR;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            
            return DBData.ERROR_Statement;
        }
    }

    /**
     * 
     * @param tableName table name
     * @param columns columns, in format: id,name,balance
     * @param values values depend on columns, 10,lakis,100
     * @return  0 if no error, &lt; 0 otherwise
     */
    public int insert(String tableName, String columns, Object... values) {
        tableName = this.validTable(tableName);
        // 1, 'rifleman', 'world', 30.5, 0
        String sValues = "";
        for (int i = 0; i < values.length; i++) {
            String t = "" + values[i];
            if (values[i] instanceof String) {
                t = "'" + t + "'";
            }
            if (i + 1 != values.length) t = t + ", ";
            
            sValues += t;
        }
        
        String insert =  "INSERT INTO " + tableName + " (" + columns + ") " + "VALUES (" + sValues + ");";
        //System.out.println(insert);
        
        this.executeUpdates(insert);
        return 0;
    }
    
    /**
     * Convert a vaule object from java type to sqlite datatypes
     * This used for create table
     * 
     * @param value the value to convert
     * @param notNull if the value must be not null
     * @param primaryKey if it is primary key
     * @return the converted value
     */
    public String convertForQuery(Object value, boolean notNull,  boolean primaryKey) {
        
        String q = "";
        String n = "";
        String pkey = "";
        
        if (notNull) {
            n = " NOT NULL";
        }
        if (primaryKey) {
            pkey = " PRIMARY KEY";
        }
        //System.out.println(value.getClass().getName());
        if (value instanceof Integer) {
            q = "INT";
        }
        else if (value instanceof String) {
            q = "TEXT";
        }
        else if (value instanceof Double || value instanceof Float) {
            q = "REAL";
        }
        else return null;
        
        
        return q + pkey + n;
    }
    
    /**
     * The 1st column here, we will be the PrimaryKey
     *  If you want to have not null values
     *  number &lt; 0 (NULL) number &gt;= 0 (NOT NULL)
     *  string = "null" (NULL) else (NOT NULL)
     * {@link String} - link to javadoc
     * http://docs.oracle.com/cd/E50453_01/doc.80/e50452/working_nbeans.htm#NBDAG2405
     * 
     * @param tableName table name
     * @param columns columns, in format: id,name,balance
     * @param dataTypes all values (to determinate the datatype)
     * @return 0 if no error, &lt; 0 otherwise
     */
    public int createTable(String tableName, String columns, Object... dataTypes) {
        tableName = this.validTable(tableName);
        String[] c = columns.split(",");
        String cfq = "";
        cfq = this.convertForQuery(dataTypes[0], false, true);
        if (cfq == null) return -1;
        
        String sValues = c[0] + " " + cfq.replace("INT", "INTEGER") + " AUTOINCREMENT, ";
        
        for (int i = 1; i < dataTypes.length; i++) {
            if (dataTypes[i] instanceof Integer ) {
                if ((int)dataTypes[i] >= 0) {
                    cfq = this.convertForQuery(dataTypes[i], true, false);
                }
                else cfq = this.convertForQuery(dataTypes[i], false, false);
            }
            else if (dataTypes[i] instanceof Double || dataTypes[i] instanceof Float) {
                if ((double)dataTypes[i] >= 0) {
                    cfq = this.convertForQuery(dataTypes[i], true, false);
                }
                else cfq = this.convertForQuery(dataTypes[i], false, false);
            }
            else if (dataTypes[i] instanceof String) {
                if (!((String)dataTypes[i]).equals("null")) {
                    cfq = this.convertForQuery(dataTypes[i], true, false);
                }
                else cfq = this.convertForQuery(dataTypes[i], false, false);
            }
            if (cfq == null) return -1;
            
            String t = c[i] + " " + cfq;
            if (i + 1 != dataTypes.length) t = t + ", ";
            
            sValues += t;
        }
        
        String sql = "CREATE TABLE if not exists " + tableName + " (" + sValues + ")";
        //System.out.println(sql);
        
        this.executeUpdates(sql);
        return 0;
    }
    
    /**
     * <p>The where must be something like</p>
     * <p>AGE >= 25 AND SALARY >= 65000
     * AGE >= 25 OR SALARY >= 65000
     * AGE >= 25 OR SALARY >= 65000 AND ...</p>
     * 
     * @param tableName table name
     * @param where - in format above
     * @return 0 if no error, &lt; 0 otherwise
     */
    public int delete(String tableName, String where) {
        tableName = this.validTable(tableName);
        //SELECT * FROM COMPANY WHERE AGE >= 25 AND SALARY >= 65000;
        //SELECT * FROM COMPANY WHERE AGE >= 25 OR SALARY >= 65000;
        String delete = "DELETE FROM " + tableName + " WHERE " + where + ";";
        
        //System.out.println(delete);
        
        this.executeUpdates(delete);
        return 0;
    }
    
    /**
     * 
     * @param tableName table name
     * @param columns columns, in format: id,name,location,balance,acc - or * for all columns
     * @param where name='playerName' AND AGE >= 25 AND SALARY >= 65000
     * @param numberOfColumns
     * @return - the resault, at format: 1,name,80 /n 2,name,...
     */
    public String select(String tableName, String columns, String where, int numberOfColumns) {
        tableName = this.validTable(tableName);
        String select = "SELECT " + columns + " FROM " + tableName + " WHERE " + where + ";";
        
        if (where.equals("")) select = "SELECT " + columns + " FROM " + tableName + ";";
        //System.out.println(select);
        
        return this.executeQuery(select, numberOfColumns);
    }
    
    /**
     * UPDATE table_name
     * SET column1 = value1, column2 = value2...., columnN = valueN
     * WHERE [condition];
     * 
     * @param tableName table name
     * @param set
     * @param where 
     * @return 0 if no error, &lt; 0 otherwise
     */
    public int update(String tableName, String set, String where) {
        System.out.println(set);
        System.out.println(where);
        tableName = this.validTable(tableName);
        String update =  "UPDATE " + tableName + " SET " + set + " where " + where + ";";
        
        //System.out.println(update);
        
        this.executeUpdates(update);
        return 0;
    }

    public void getTableNames() throws SQLException {
        DatabaseMetaData md = c.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
          System.out.println(rs.getString(3));
        }
    }
    
    public String[] getColumnNameArray(String tableName) {
        String[] sArray = null;
        try {
            Statement stmt = null;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            
            ResultSetMetaData rm = rs.getMetaData();
            sArray = new String[rm.getColumnCount()];
            for (int ctr = 1; ctr <= sArray.length; ctr++) {
              String s = rm.getColumnName(ctr);
              sArray[ctr - 1] = s;
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sArray;
    }
    public static void main(String[] args) {
        TimeCalc tmain = new TimeCalc();
        tmain.start();
        
        SQLiteManager db = new SQLiteManager("lakis");
        
        db.createTable("user", "id,name,address,phone,time", 1, "null", "null", "null", "null");
        
        /*for (int i = 0; i < 100; i++) {
            db.insert("user", "name,address,phone", "marios papazisis", "papanikolaou 32", "2310358359");
            db.insert("user", "name,address,phone,time", "xristos mplazis", "papanikolaou 32", "2310358359", "27/5/15");
            db.insert("user", "name,address,phone", "Λαζόπουλος Κώστας", "πηραιά 32", "2310358359");
            db.insert("user", "name,time", "Παπαμανωλάκης Άλεξανδρος", "27/5/15");
            db.insert("user", "address", "περιστέρι 65");
        }*/
        
        //String re = db.select("customer", "*", "name like '%ης%'", 4);
        String re = db.select("user", "*", "", 5);
        
        System.out.println(re);
        String[] ss = db.getColumnNameArray("user");
        for (String s: ss) {
            System.out.println(s);
        }
        
        tmain.end();
        
        System.out.println("Time: " + tmain.getMillis());
    }
}
