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
 * @author User1
 */
public class Customers {

    private short customerId;
    private String customerName;
    private short customerAccId;
    private short addressLocId;
    private String customerNote;

    static public void addNewCustomer(String customerName, short customerLocId, String note) {
        //add driver to database
        try {
            // create a mysql database connection
            Connection conn = Utills.openDb();

            // Item table mysql insert statement
            String query = " insert into customers (customer_name, customer_address_loc_id, notes)"
                    + " values (?, ?, ?)";

            // Item table mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
            preparedStmt.setString(1, customerName);
            preparedStmt.setShort(2, customerLocId);
            preparedStmt.setString(3, note);

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
