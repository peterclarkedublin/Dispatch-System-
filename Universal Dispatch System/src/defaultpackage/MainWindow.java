/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultpackage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author peter
 */
public class MainWindow implements Initializable {

    //settings tab
    @FXML
    private Button refreshDriversList;
    @FXML
    private ListView driverList;
    @FXML
    private TextField addressTxtField;
    @FXML
    private TextField dbNameTxtField;
    @FXML
    private TextField userNameTxtField;
    @FXML
    private PasswordField dbPassField;
    @FXML
    private Button sbSaveBtn;

    //drivers tab
    @FXML
    private TableView driversTabTbl;
    @FXML
    private TextField newDriverFname;
    @FXML
    private TextField newDriverSname;
    @FXML
    private TextField newDriverPlateNum;
    @FXML
    private ComboBox newDriverVehId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void updateDriversList() {
        System.out.println("pressed!!!!!!!!!");

    }

    public void updateDbSettings() {

        //add driver to database
        try {
            // create a mysql database connection
            Connection conn = Utills.openDb();

            // Item table mysql insert statement
            String query = " insert into drivers (driver_first_name, driver_last_name, vehicle_id, taxi_plate_num)"
                    + " values (?, ?, ?, ?)";

            // Item table mysql insert preparedstatement
            PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
            preparedStmt.setString(1, newDriverFname.getText());
            preparedStmt.setString(2, newDriverSname.getText());
            preparedStmt.setString(3, newDriverPlateNum.getText());
            preparedStmt.setString(4, newDriverSname.getText());

            //execute the preparedstatement
            preparedStmt.execute();

            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }
    
    Utills
    

    
}
    


