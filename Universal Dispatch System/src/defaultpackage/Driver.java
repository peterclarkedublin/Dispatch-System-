package defaultpackage;


import defaultpackage.Vehicle;
import java.sql.Connection;
import java.sql.PreparedStatement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author peter
 */
public class Driver {
    
    short driverId;
    private short vehicleId;
    private String driverFirstName;
    private String driverLastName;
    private String taxiPlateNum = "PLATE# YET NOT SET";
    private boolean isOnJob;

   
    //create list drivers DB calls
    
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
    
}
