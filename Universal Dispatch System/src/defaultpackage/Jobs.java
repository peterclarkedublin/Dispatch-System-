/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author peter
 */
public class Jobs {

    private short jobId;
    private short customerId;
    private short driverId;
    private short destId;
    private boolean isExpedited;

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

}
