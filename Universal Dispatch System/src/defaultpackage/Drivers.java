package defaultpackage;


import static defaultpackage.Utills.vehicles;
import defaultpackage.Vehicles;
import static defaultpackage.Vehicles.currLatLong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author peter
 */
public class Drivers {
    
    short driverId;
    static private short vehicleId;
    static private String driverFirstName;
    static private String driverLastName;
    static private String taxiPlateNum = "PLATE# YET NOT SET";
    static private boolean isOnJob;
    static private short jobId;
    static public String[][] drivers;

   
    //add new driver to DB
    
        static public void addNewDriver(String newDriverFname, String newDriverSname, String newDriverPlateNum) {

        //add driver to database
        try {
            // create a mysql database connection
            Connection conn = Utills.openDb();

            // Item table mysql insert statement
            String query = " insert into drivers (driver_first_name, driver_last_name, taxi_plate_num)"
                    + " values (?, ?, ?)";

            // Item table mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
            preparedStmt.setString(1, newDriverFname);
            preparedStmt.setString(2, newDriverSname);
            preparedStmt.setString(3, newDriverPlateNum);
            
            System.out.print(preparedStmt);
            
            //execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
        
        static public String[][] listDrivers(){
            
            try {

            // create a mysql database connection
            Connection driverConn = Utills.openDb();

            String query1 = "SELECT * from drivers";

            // create the java statement
            java.sql.Statement st = driverConn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            int numCounter;
            for (numCounter = 0; rs.next(); numCounter++);
            drivers = new String[numCounter][6];
            rs.beforeFirst();
            numCounter = 0;

            while (rs.next()) {
                int id = rs.getInt(1);
                driverFirstName = rs.getString(2);
                driverLastName = rs.getString(3);
                vehicleId = rs.getShort(4);
                taxiPlateNum = rs.getString(5);
                jobId = rs.getShort(6);
                

                drivers[numCounter][0] = String.valueOf(id);
                drivers[numCounter][1] = driverFirstName;
                drivers[numCounter][2] = driverLastName;
                drivers[numCounter][3] = String.valueOf(vehicleId);
                drivers[numCounter][4] = taxiPlateNum;
                drivers[numCounter][5] = String.valueOf(jobId);

                numCounter++;
                
            }
            
            driverConn.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return drivers;
            
            
        }
    
}
