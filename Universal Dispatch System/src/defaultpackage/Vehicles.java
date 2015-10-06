package defaultpackage;

import static defaultpackage.Utills.vehicles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author peter
 */
public class Vehicles {

    static private short driverId;
    static private short vehicleId;
    static private String make;
    static private String model;
    static private short yom;
    static private int mileage;
    static private Date dateLastServiced;
    static private Date dateDueService;
    static private Date dateLastCert; //eg. road worthyness test (DOE | MOT ...)
    static private Date dateDueCert;

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
                yom = rs.getShort(4);
                mileage = rs.getInt(5);
                dateLastServiced = rs.getDate(6);
                dateDueService = rs.getDate(7);
                dateLastCert = rs.getDate(8);
                dateDueCert = rs.getDate(9);

                vehicles[numCounter][0] = String.valueOf(id);
                vehicles[numCounter][1] = make;
                vehicles[numCounter][2] = model;
                vehicles[numCounter][3] = String.valueOf(yom);
                vehicles[numCounter][4] = String.valueOf(mileage);
                vehicles[numCounter][5] = String.valueOf(dateLastServiced);
                vehicles[numCounter][6] = String.valueOf(dateDueService);
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

    static public String[] listVehicleIds() {
            //todo: this should be a sql call
        String[] ids = null;

        for (int i = 0; i < vehicles.length; ++i) {

            ids[i] = vehicles[i][0];

        }

        return ids;
    }
    
    static public void addNewVehicle(String reg, String make, String model, short yom ){
        
         //add vehicle to database
        try {
            // create a mysql database connection
            Connection conn = Utills.openDb();

            // Item table mysql insert statement
            String query = " insert into vehicle (reg_num, make, model, yom)"
                    + " values (?, ?, ?, ?)";

            // Item table mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
            preparedStmt.setString(1, reg);
            preparedStmt.setString(2, make);
            preparedStmt.setString(3, model);
            preparedStmt.setShort(4, yom);
            
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
