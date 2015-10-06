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
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    
    @FXML
    private Button addNewDriver;

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
    
    //Fleet tab
    @FXML
    private TextField vehicleReg;
    @FXML
    private TextField vehicleMake;
    @FXML
    private TextField vehicleModel;
    @FXML
    private TextField vehicleYom;
    @FXML
    private Button saveNewVeh;
    
    //jobs tab
    @FXML
    private Button selectCustomer;
    @FXML
    private TextField selectCustomerField;
    @FXML
    private Button selectDriver;
    @FXML
    private TextField selectDriverField;
    @FXML
    private Button selectDestination;
    @FXML
    private TextField selectDestinationField;
    @FXML
    private TextArea driverMessage;
    @FXML
    private CheckBox expedite;
    
    //customers tab
    @FXML
    private TextField customerName;
    @FXML
    private TextField customerLoc;
    @FXML
    private Button saveNewCustomer;
   
    @FXML
    private TextArea customerNote;
    @FXML
    private RadioButton enableAddNewCustomer;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void addNewDriver() {
        Drivers.addNewDriver(newDriverFname.getText(),newDriverSname.getText(),newDriverPlateNum.getText());

    }

    public void addNewVehicle(){
        
        Vehicles.addNewVehicle(vehicleReg.getText(), vehicleMake.getText(),  vehicleModel.getText(), Short.valueOf(vehicleYom.getText()));
        
    }
    
    public void addNewCustomer(){
        
        Customers.addNewCustomer(customerName.getText(),Short.valueOf(customerLoc.getText()),customerNote.getText());
    }
    
    public void enableAddNewCustomerFields(){
        
        //enable save btn
        if(!saveNewCustomer.isVisible()){
            saveNewCustomer.setVisible(true);
        }else{
            saveNewCustomer.setVisible(false);
        }
        //enable fields
        if(!customerName.isEditable()){
            customerName.setEditable(true);
            customerName.clear();
        }else{
            customerName.setEditable(false);
        }
        if (!customerLoc.isEditable()) {
            customerLoc.setEditable(true);
            customerLoc.clear();
        } else {
            customerLoc.setEditable(false);
        }
        if(!customerNote.isEditable()){
            customerNote.setEditable(true);
        }else{
            customerNote.setEditable(false);
        }
        

    }
    
//    public void addNewJob(){
//        
//        boolean isExpedited;
//        
//        Jobs.addNewJob(selectCustomerField.getText(), selectDriverField.getText(), Short.valueOf(selectDestinationField.getText()), true, null);
//    }

    
   
    
    
    
    
}
    


