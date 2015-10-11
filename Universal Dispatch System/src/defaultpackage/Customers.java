/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultpackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
    private String customerPhone;
    static String[][] customers;

    static public void addNewCustomer(String customerName, String customerPhone,  short customerLocId, String note) {
        //add driver to database
        try {
            // create a mysql database connection
            Connection conn = Utills.openDb();

            // Item table mysql insert statement
            String query = " insert into customers (customer_name, customer_phone, customer_address_loc_id, notes)"
                    + " values (?, ?, ?, ?)";

            // Item table mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
            preparedStmt.setString(1, customerName);
            preparedStmt.setString(2, customerPhone);
            preparedStmt.setShort(3, customerLocId);
            preparedStmt.setString(4, note);

            System.out.print(preparedStmt);

            //execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
    
    static public String[][] listCustomers(){
        try {

            // create a mysql database connection
            Connection customerConn = Utills.openDb();

            String query1 = "SELECT * from customers";

            // create the java statement
            java.sql.Statement st = customerConn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            int numCounter;
            for (numCounter = 0; rs.next(); numCounter++);
            customers = new String[numCounter][6];
            rs.beforeFirst();
            numCounter = 0;

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                short accId = rs.getShort(3);
                short addressLocId = rs.getShort(4);
                String msg = rs.getString(5);
                String custNum = rs.getString(6);

                customers[numCounter][0] = String.valueOf(id);
                customers[numCounter][1] = name;
                customers[numCounter][2] = String.valueOf(accId);
                customers[numCounter][3] = String.valueOf(addressLocId);
                customers[numCounter][4] = msg;
                customers[numCounter][5] = custNum;

                numCounter++;
                
            }
            
            customerConn.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return customers;
    }
    
 
}
