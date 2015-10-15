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

    static private short jobId;
    private short customerId;
    private short driverId;
    private short pickupLocId;
    private short destinationId;
    private boolean isExpedited;
    static public String[][] jobsList;
    static private String startLoc;
    static private String destLoc;
    static private String pickupStreet;
    static private String destStreet;

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

            //something I need to address here - logic is wrong, so I must do latlng call
            //db seperateley as each row will contain startLoc & endLoc which are different rows in the same locations
            //table, alas, SQL joins won't work here #possibleLogicFailInMyBrain

            String query1 = "SELECT jobs.is_active, jobs.job_id, job_type.name, customers.customer_name, \n"
                            + "drivers.driver_first_name, jobs.time_created, jobs.time_departed,\n"
                            + "jobs.time_eta, jobs.message, jobs.pickup_loc_id, jobs.dest_id, jobs.customer_id, vehicle.current_lat_long\n"
                            + "FROM jobs\n"
                            + "INNER JOIN job_type\n"
                            + "ON jobs.job_type_id = job_type.id\n"
                            + "INNER JOIN customers\n"
                            + "ON jobs.customer_id = customers.customer_id\n"
                            + "INNER JOIN drivers\n"
                            + "ON jobs.driver_id = drivers.driver_id\n"
                            + "INNER JOIN vehicle\n"
                            + "ON drivers.vehicle_id = vehicle.vehicle_id";
 
            // create the java statement
            java.sql.Statement st = jobConn.createStatement();//main loop
            java.sql.Statement st2 = jobConn.createStatement();//inner startLoc loop
            java.sql.Statement st3 = jobConn.createStatement();//inner destLoc loop

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            int numCounter;
            for(numCounter = 0; rs.next(); numCounter++);
            jobsList = new String[numCounter][14];
            rs.beforeFirst();
            numCounter = 0;

            while (rs.next()) {
                short isActive = rs.getShort(1);
                jobId = rs.getShort(2);
                String jobType = rs.getString(3);
                String custName = rs.getString(4);
                String driverName = rs.getString(5);
                Time timeCreated = rs.getTime(6);
                Time timeDeparted = rs.getTime(7);
                Time ETA = rs.getTime(8);
                String msg = rs.getString(9);
                String pickupLocId = rs.getString(10);
                String destLocId = rs.getString(11);
                String customerId = rs.getString(12);
                String vehCurrLatLng = rs.getString(13);
 
                //do inner latlng db calls which are added to end of jobList array
                String queryStartLoc = "SELECT locations.lat_long, locations.loc_street \n" +
                                        "FROM jobs\n" +
                                        "INNER JOIN locations\n" +
                                        "ON jobs.pickup_loc_id = locations.location_id WHERE jobs.customer_id = " +customerId + 
                                        " AND jobs.job_id = " + String.valueOf(jobId);
                
                String queryDestLoc = "SELECT locations.lat_long, locations.loc_street \n" +
                                        "FROM jobs\n" +
                                        "INNER JOIN locations\n" +
                                        "ON jobs.dest_id = locations.location_id WHERE jobs.customer_id = " +customerId + 
                                        " AND jobs.job_id = " + String.valueOf(jobId);
                
                
                ResultSet rs2 = st2.executeQuery(queryStartLoc);
                ResultSet rs3 = st3.executeQuery(queryDestLoc);
                
                while(rs2.next()){
                startLoc = rs2.getString(1);
                pickupStreet = rs2.getString(2);
                }
                while(rs3.next()){
                destLoc = rs3.getString(1);
                destStreet = rs3.getString(2);
                }
                
                jobsList[numCounter][0] = String.valueOf(isActive);
                jobsList[numCounter][1] = String.valueOf(jobId);
                jobsList[numCounter][2] = jobType;
                jobsList[numCounter][3] = custName;
                jobsList[numCounter][4] = pickupStreet;
                jobsList[numCounter][5] = destStreet;
                jobsList[numCounter][6] = driverName;
                jobsList[numCounter][7] = String.valueOf(timeCreated);
                jobsList[numCounter][8] = String.valueOf(timeDeparted);
                jobsList[numCounter][9] = String.valueOf(ETA);
                jobsList[numCounter][10] = String.valueOf(msg);
                jobsList[numCounter][11] = startLoc;
                jobsList[numCounter][12] = destLoc;
                jobsList[numCounter][13] = vehCurrLatLng;

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

}
