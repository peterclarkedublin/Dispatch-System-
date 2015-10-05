package defaultpackage;

import static defaultpackage.Utills.vehicles;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author peter
 */
public class Vehicle {

    private short driverId;
    private short vehicleId;
    private String make;
    private String model;
    private short yom;
    private int mileage;
    private Date dateLastServiced;
    private Date dateDueService;
    private Date dateLastCertified; //eg. road worthyness test (DOE | MOT ...)
    private Date dateDueCertification;

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
                Date yom = rs.getDate(4);
                int mileage = rs.getInt(5);
                Date lastServiced = rs.getDate(6);
                Date dueDateService = rs.getDate(7);
                Date dateLastCert = rs.getDate(8);
                Date dateDueCert = rs.getDate(9);

                vehicles[numCounter][0] = String.valueOf(id);
                vehicles[numCounter][1] = make;
                vehicles[numCounter][2] = model;
                vehicles[numCounter][3] = String.valueOf(yom);
                vehicles[numCounter][4] = String.valueOf(mileage);
                vehicles[numCounter][5] = String.valueOf(lastServiced);
                vehicles[numCounter][6] = String.valueOf(dueDateService);
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

        String[] ids = null;

        for (int i = 0; i < vehicles.length; ++i) {

            ids[i] = vehicles[i][0];

        }

        return ids;
    }
}
