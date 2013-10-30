/*
 * This class is responssible to connect to the desired database. It return
 * the Connection pointer to the caller.
 */


package webapp.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Nina
 */
public class MySQLConnection
{
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//

    /** The DB driver string */
    private static final String DB_DRIVER_STR = "com.mysql.jdbc.Driver";

    /** The DB driver string */
    private static final String DB_URL =
              "jdbc:mysql://localhost/booksea"
            + "?useUnicode=true&characterEncoding=UTF-8&"
            + "strictUpdates=false";
    //private static final String DB_URL = "jdbc:mysql://localhost/";

// ................. G L O B A L   P R I V A T E   V A R S ...................//

    /** The user ID */
    private String uid;

    /** The password */
    private String pwd;

    /** hold a pointer to the database connection. */
    private Connection con = null;

// ........................ C O N S T R U C T O R S ..........................//
    MySQLConnection(String dbName)
    {
        uid = "root";
        pwd = "root";
        con = login2DB();
    }

// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//
    /**
     * make a connection to the desired database.
     * @return connection object to the caller
     */
    private Connection login2DB()
    {
        try {
        	com.mysql.jdbc.Driver driver=null;
            Class.forName(DB_DRIVER_STR);
            con = DriverManager.getConnection(DB_URL, uid, pwd);
            System.out.println("Connected to DB!");

            return con;
        
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

        catch (ClassNotFoundException ex) {
            System.err.println(ex.getMessage());
            return null;
        }


    } //end of the method

    /**
     * disconnect from the desired database.
     * @param the connection object to the database
     */
    public void disconnectFromDB() {
        try {
            con.close();
            System.out.println("Disconnected from DB!");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

    } //end of method

    /**
     * disconnect from the desired database.
     * @param the connection object to the database
     */
    public Connection getDBConnection() {
        return con;
    } //end of method


	/**
	 */
// ........................ M A I N   M E T H O D ............................//
    /**
     * @param args
     */
    public static void main(String[] args)
    {
//        Messages msg = new Messages();
        String dbName = "cookingmonsters";
        MySQLConnection dbc = new MySQLConnection(dbName);

        dbc.disconnectFromDB();


    } // end of the main method
} // end of the class

