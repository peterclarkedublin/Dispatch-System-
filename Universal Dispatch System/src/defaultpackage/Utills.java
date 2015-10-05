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
    

    static public String[][] listVehicles() {

        try {

            // create a mysql database connection
            Connection vehConn = Utills.openDb();

            String query1 = "SELECT * from vehicle";

            // create the java statement
            java.sql.Statement st = vehConn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            int numCounter;
            for (numCounter = 0; rs.next(); numCounter++);
            vehicles = new String[numCounter][9];
            rs.beforeFirst();
            numCounter = 0;

            while (rs.next()) {
                int id = rs.getInt(1);
                String make = rs.getString(2);
                String model = rs.getString(3);
                Date yom = rs.getDate(4);
                int mileage = rs.getInt(5);
                Date lastServiced = rs.getDate(6);
                Date dueDateService = rs.getDate(7);
                Date dateLastCert = rs.getDate(8);
                Date dateDueCert = rs.getDate(9);

                vehicles[numCounter][0] = String.valueOf(id);
                vehicles[numCounter][1] = make;
                vehicles[numCounter][2] = model;
                vehicles[numCounter][3] = String.valueOf(yom);
                vehicles[numCounter][4] = String.valueOf(mileage);
                vehicles[numCounter][5] = String.valueOf(lastServiced);
                vehicles[numCounter][6] = String.valueOf(dueDateService);
                vehicles[numCounter][7] = String.valueOf(dateLastCert);
                vehicles[numCounter][8] = String.valueOf(dateDueCert);
                numCounter++;

            }

            vehConn.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return vehicles;
    }

    public static String[] listVehicleIds() {

        String[] ids = null;

        for (int i = 0; i < vehicles.length; ++i) {

            ids[i] = vehicles[i][0];

        }

        return ids;
    }

}
