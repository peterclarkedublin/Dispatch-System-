/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultpackage;

import static defaultpackage.Customers.customers;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author User1
 */
public class Locations {
    
    static short locationId;
    static String latLong;
    static String locCountry;
    static String locCity;
    static String locTown;
    static String locStreet;
    static String locUnitNum;
    static String[][] locations;
    
    static public String[][] listLocations(){
        try {

            // create a mysql database connection
            Connection customerConn = Utills.openDb();

            String query1 = "SELECT * from locations";

            // create the java statement
            java.sql.Statement st = customerConn.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query1);

            int numCounter;
            for (numCounter = 0; rs.next(); numCounter++);
            locations = new String[numCounter][7];
            rs.beforeFirst();
            numCounter = 0;

            while (rs.next()) {
                int id = rs.getInt(1);
                locStreet = rs.getString(2);
                latLong = rs.getString(3);
                locCountry = rs.getString(4);
                locCity = rs.getString(5);
                locTown = rs.getString(6);

                locations[numCounter][0] = String.valueOf(id);
                locations[numCounter][1] = locStreet;
                locations[numCounter][2] = latLong;
                locations[numCounter][3] = locCountry;
                locations[numCounter][4] = locCity;
                locations[numCounter][5] = locTown;

                numCounter++;
                
            }
            
            customerConn.close();

        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return locations;
    }
    
    
    
}
