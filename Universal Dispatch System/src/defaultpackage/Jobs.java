/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    private short destinationId;
    private boolean isExpedited;
    static public String[][] jobsList;

    static public void addNewJob(short customerId, short driverId, short destinationId, boolean isExpedited, String message) {

        //add job to database
        try {
            // create a mysql database connection
            Connection conn = Utills.openDb();

            // Item table mysql insert statement
            String query = " insert into jobs (customer_id, driver_id, dest_id, is_expedited, message )"
                    + " values (?, ?, ?, ?, ?)";

            // Item table mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
            preparedStmt.setShort(1, customerId);
            preparedStmt.setShort(2, driverId);
            preparedStmt.setShort(3, destinationId);
            preparedStmt.setBoolean(4, isExpedited);
            preparedStmt.setString(5, message);

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

            String query1 = "SELECT * from jobs";

            // create the java statement
            java.sql.Statement st = jobConn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            int numCounter;
            for(numCounter = 0; rs.next(); numCounter++);
            jobsList = new String[numCounter][10];
            rs.beforeFirst();
            numCounter = 0;

            while (rs.next()) {
                int id = rs.getInt(1);
                short jobType = rs.getShort(2);
                short custId = rs.getShort(3);
                short driverId = rs.getShort(4);
                short destId = rs.getShort(5);
                String departed = rs.getString(6);
                String eta = rs.getString(7);
                byte isExpedited = rs.getByte(8);
                String msg = rs.getString(9);
                byte isActive = rs.getByte(10);
        
                
                

                jobsList[numCounter][0] = String.valueOf(id);
                jobsList[numCounter][1] = String.valueOf(jobType);
                jobsList[numCounter][2] = String.valueOf(custId);
                jobsList[numCounter][3] = String.valueOf(driverId);
                jobsList[numCounter][4] = String.valueOf(destId);
                jobsList[numCounter][5] = departed;
                jobsList[numCounter][6] = eta;
                jobsList[numCounter][7] = String.valueOf(isExpedited);
                jobsList[numCounter][8] = msg;
                jobsList[numCounter][9] = String.valueOf(isActive);

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
