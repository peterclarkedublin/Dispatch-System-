/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.Date;
import javax.xml.datatype.DatatypeConstants;

/**
 *
 * @author peter
 */
public class Jobs {

    private short jobId;
    private short customerId;
    private short driverId;
    private short pickupLocId;
    private short destinationId;
    private boolean isExpedited;
    static public String[][] jobsList;

    static public void addNewJob(short customerId, short driverId, short pickupLocId, short destinationId, boolean isExpedited, String message) {

        //add job to database
        try {
            // create a mysql database connection
            Connection conn = Utills.openDb();

            // Item table mysql insert statement
            String query = " insert into jobs (customer_id, driver_id, pickup_loc_id, dest_id, is_expedited, message)"
                    + " values (?, ?, ?, ?, ?, ?)";

            // Item table mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
            preparedStmt.setShort(1, customerId);
            preparedStmt.setShort(2, driverId);
            preparedStmt.setShort(3, pickupLocId);
            preparedStmt.setShort(4, destinationId);
            preparedStmt.setBoolean(5, isExpedited);
            preparedStmt.setString(6, message);

            System.out.print(preparedStmt);

            //execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
    
    static public String[][] listJobs(){
        
        try {

            // create a mysql database connection
            Connection jobConn = Utills.openDb();

            String query1 = "SELECT jobs.is_active, jobs.job_id, job_type.name, customers.customer_name, \n"
                            + "drivers.driver_first_name, jobs.time_created, jobs.time_departed,\n"
                            + "jobs.time_eta, jobs.message\n"
                            + "FROM jobs\n"
                            + "INNER JOIN job_type\n"
                            + "ON jobs.job_type_id = job_type.id\n"
                            + "INNER JOIN customers\n"
                            + "ON jobs.customer_id = customers.customer_id\n"
                            + "INNER JOIN drivers\n"
                            + "ON jobs.driver_id = drivers.driver_id;";
            System.out.println(query1);
            // create the java statement
            java.sql.Statement st = jobConn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            int numCounter;
            for(numCounter = 0; rs.next(); numCounter++);
            jobsList = new String[numCounter][9];
            rs.beforeFirst();
            numCounter = 0;

            while (rs.next()) {
                short isActive = rs.getShort(1);
                short id = rs.getShort(2);
                String jobType = rs.getString(3);
                String custName = rs.getString(4);
                String driverName = rs.getString(5);
                Time timeCreated = rs.getTime(6);
                Time timeDeparted = rs.getTime(7);
                Time ETA = rs.getTime(8);
                String msg = rs.getString(9);
                
  
                jobsList[numCounter][0] = String.valueOf(isActive);
                jobsList[numCounter][1] = String.valueOf(id);
                jobsList[numCounter][2] = jobType;
                jobsList[numCounter][3] = custName;
                jobsList[numCounter][4] = driverName;
                jobsList[numCounter][5] = String.valueOf(timeCreated);
                jobsList[numCounter][6] = String.valueOf(timeDeparted);
                jobsList[numCounter][7] = String.valueOf(ETA);

                numCounter++;
  
            }
            
            jobConn.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return jobsList;
    }
    
    static public String[][] getJobLocations(int startLocId, int destLocId){
        
        String[][] jobLocations = null;
        
        try {

            // create a mysql database connection
            Connection jobGPSConn = Utills.openDb();

            String query1 = "SELECT lat_long, loc_street from locations where location_id = "+startLocId+"\n" +
                            "UNION\n" +
                            "SELECT lat_long, loc_street from locations where location_id = "+destLocId+"";
            System.out.println(query1);
            // create the java statement
            java.sql.Statement st = jobGPSConn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            int numCounter;
            for(numCounter = 0; rs.next(); numCounter++);
            jobLocations = new String[numCounter][2];
            rs.beforeFirst();
            numCounter = 0;

            //careful now, this array is like so [start.lat_lng][loc_street]
                                               //[end.lat_lng][loc_street]
            while (rs.next()) {
                String latLng = rs.getString(1);
                String locStreet = rs.getString(2);
                
                jobLocations[numCounter][0] = latLng;
                jobLocations[numCounter][1] = locStreet;
                
                numCounter++;
  
            }
            
            jobGPSConn.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        
        return jobLocations; 
    }
    
       

}
