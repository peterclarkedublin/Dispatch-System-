package defaultpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Utills {

    private static String dbIp = "127.0.0.1";
    private static String dbName = "uds";
    private static String dbUserName = "root";
    private static String dbPass = "admin";
    static String[][] vehicles = null;

    public static void setDbIp(String dbIp) {
        Utills.dbIp = dbIp;
    }

    public static void setDbName(String dbName) {
        Utills.dbName = dbName;
    }

    public static void setDbUserName(String dbUserName) {
        Utills.dbUserName = dbUserName;
    }

    public static void setDbPass(String dbPass) {
        Utills.dbPass = dbPass;
    }

    /**
     * Open a new connection to the database and return the new open connection
     * reference
     *
     * @returns sql Connection for the database or null if failed
     */
    static public Connection openDb() {
        String myDriver = "org.gjt.mm.mysql.Driver";
        String myUrl = "jdbc:mysql://" + dbIp + "/" + dbName;
        final Connection conn;
        try {
            Class.forName(myDriver);
            conn = DriverManager.getConnection(myUrl, dbUserName, dbPass);
            return conn;
        } catch (SQLException sqle) {
            System.out.println(sqle);
            return null;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    

 

}
