/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultpackage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

/**
 *
 * @author peter
 */
public class MainWindow implements Initializable {

    //home tab
    @FXML
    private Button refreshDriversList;
    @FXML
    private ListView driverList;
    @FXML
    private TableView activeJobs;
    @FXML
    private TextField homeCustomerPhone;
    @FXML
    private TextField homeCustomerName;
    @FXML
    private TextField homeCustomerAddress;
    @FXML
    private TextField homeCustomerPhoneSearch;
    @FXML
    private TextField homeCustomerNameSearch;
    @FXML
    private TextField homeCustomerAddressSearch;
    @FXML
    private TextField homeCustomerNotes;
    @FXML
    private Label homeCustomerNotesLbl;
    @FXML
    private WebView mapBrowser;
    
    //settings tab
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
    @FXML
    private Button refreshDriversTbl;
    
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
    @FXML
    private Button refreshFleetList;
    //fleet tab observable, filterable table
    @FXML
    private TextField fleetFilterfield;
    @FXML
    //private TableView tvFleetList;
    private TableView<String[]> tvFleetList = new TableView<>();

    //jobs tab
    @FXML
    private TableView jobsTbl;
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
    @FXML
    private Button saveJob;
    @FXML
    private Button refreshJobTbl;
    
    //customers tab
    @FXML
    private TableView tvCustomers;
    @FXML
    private TextField customerName;
    @FXML
    private TextField customerLoc;
    @FXML
    private Button saveNewCustomer;
    @FXML
    private TextField customerPhone;
   
    @FXML
    private TextArea customerNote;
    @FXML
    private RadioButton enableAddNewCustomer;
    @FXML
    private Button refreshCustTbl;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void addNewDriver() {
        Drivers.addNewDriver(newDriverFname.getText(),newDriverSname.getText(),newDriverPlateNum.getText());

    }
    
    //class list for refresh button calls
    private ObservableList<String[]> fleetData;
    public void listDrivers() {
        
        driversTabTbl.getColumns().clear();
        
        String[][] driverArray = Drivers.listDrivers();
        String[] colnames = {"ID", "First Name", "Last Name", "Vehicle ID", "Plate Number", "Job ID"};
        fleetData = FXCollections.observableArrayList();
        ObservableList<String[]> cols = FXCollections.observableArrayList();
        cols.addAll(colnames);
        fleetData.addAll(Arrays.asList(driverArray));
     

        
        for (int i = 0; i < driverArray[0].length; i++) {
            
            TableColumn tc = new TableColumn(colnames[i]);
            
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });

            tc.setPrefWidth(90);
            driversTabTbl.getColumns().add(tc);
        }

        driversTabTbl.setItems(fleetData);
        
    }
    

    public void addNewVehicle(){
        
        Vehicles.addNewVehicle(vehicleReg.getText(), vehicleMake.getText(), 
              vehicleModel.getText(), Short.valueOf(vehicleYom.getText()));
        
    }
    
    public void listFleet(){
        
        tvFleetList.getColumns().clear();
        
        String[][] fleetArray = Vehicles.listVehicles();
        String[] colnames = {"ID" , "Reg#", "Make", "Model" , "YOM", "Mileage(km)",
                              "Last Serviced", "Date Due Service", "Date Last Cert.",
                               "Date Due Cert.", "Current Location"};
        ObservableList<String[]> data = FXCollections.observableArrayList();
        ObservableList<String[]> cols = FXCollections.observableArrayList();

        cols.addAll(colnames);
        data.addAll(Arrays.asList(fleetArray));
        //data.remove(0);//remove titles from data
                
        for (int i = 0; i < fleetArray[0].length; i++) {
            TableColumn tc = new TableColumn(colnames[i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
               
            tc.setPrefWidth(90);
            tvFleetList.getColumns().add(tc);
        }

        tvFleetList.setItems(data);

    }
    
    public void addNewCustomer(){
        
        Customers.addNewCustomer(customerName.getText(),customerPhone.getText(), Short.valueOf(customerLoc.getText()),customerNote.getText());
    }
    
    private ObservableList<String[]> custData;
    public void listCustomers(){
        
        tvCustomers.getColumns().clear();
        
        String[][] fleetArray = Customers.listCustomers();
        String[] colnames = {"ID" , "Customer Name", "Accounts ID", "Address ID" , "Notes", "Phone", "Dest. ID 1", "Dest. ID 2", "Dest. ID 3"};
        ObservableList<String[]> custData = FXCollections.observableArrayList();
        ObservableList<String[]> cols = FXCollections.observableArrayList();

        cols.addAll(colnames);
        custData.addAll(Arrays.asList(fleetArray));
        //data.remove(0);//remove titles from data
                
        for (int i = 0; i < fleetArray[0].length; i++) {
            TableColumn tc = new TableColumn(colnames[i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
               
            tc.setPrefWidth(90);
            tvCustomers.getColumns().add(tc);
        }

        tvCustomers.setItems(custData);
    }
    
    //counter for textfield to fire off .contains in the array
    static short numCounter;
    public void homeSearchCustomer(){

        mapBrowser = new WebView();
        WebEngine webEngine = mapBrowser.getEngine();
        webEngine.load("http://www.google.com");
        
        String[][] numArray;
        String[][] locationsArray;
        short customerLocationId = 0;
        short customerId = 0;
 
        numCounter++; 
        
        //check if array has been made
        if(Customers.customers == null){
        numArray = Customers.listCustomers();
        locationsArray = Locations.listLocations();
        }else{
            numArray = Customers.customers;
            locationsArray = Locations.locations;
        }
        
        if (numCounter >= 4) {
            //loop through numArray check for number match
            for (int i = 0; i < numArray.length; ++i) {
                if(numArray[i][5].contains(homeCustomerPhone.getText())){
                    homeCustomerPhoneSearch.setText(numArray[i][5]);
                    homeCustomerName.setText(numArray[i][1]);
                    homeCustomerNameSearch.clear();
                    homeCustomerAddressSearch.clear();
                    
                    customerLocationId = Short.valueOf(numArray[i][3]);
                    customerId = Short.valueOf(numArray[i][0]);

                }else{
                    if (homeCustomerPhone.getText() != numArray[i][5]) {
                        homeCustomerName.clear();
                        homeCustomerAddress.clear();
                        homeCustomerPhoneSearch.setText("New number");
                        homeCustomerNameSearch.setText("New name");
                        homeCustomerAddressSearch.setText("New Address");

                    }
                }
              
            }
            
            //loop through address array and pull out via customer ID
            
            for(int i = 0; i < locationsArray.length; ++i){
                if(Short.valueOf(locationsArray[i][0])==customerLocationId){
                    System.out.print(locationsArray[i][0]);
                    homeCustomerAddress.setText(locationsArray[i][1]);
                    
                }
            }
        }
        
        homeCustomerPhone.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {

                homeCustomerPhoneSearch.clear();
                homeCustomerName.clear();
                homeCustomerAddress.clear();
                numCounter--;

            }
        });
   
    }
    
    static short addressCounter = 0;
    public void listCustomerAddress(){
        
        short customerId;
        
        String[][] addressArray;
 
        addressCounter++; 
        
        //check if array has been made
        if(Locations.locations == null){
        addressArray = Locations.locations;
        }else{
            addressArray = Locations.locations;
        }
        
        if (addressCounter >= 4) {
            //loop through numArray check for number match
            for (int i = 0; i < addressArray.length; ++i) {
                if(addressArray[i][5].contains(homeCustomerAddress.getText())){
                    homeCustomerAddressSearch.setText(addressArray[i][5]);
                    System.out.print(addressArray[i][5]);
                }else{
                    homeCustomerAddressSearch.setText("New place press save");
                }
              
            }
        }
        
        
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
        if(!customerPhone.isEditable()){
            customerPhone.setEditable(true);
        }else{
            customerPhone.setEditable(false);
        }
        

    }
    
    public void addNewJob(){
        
        boolean isExpedited = false;
        if(expedite.isSelected()){
            isExpedited = true;
        }
        
        Jobs.addNewJob(Short.valueOf(selectCustomerField.getText()), Short.valueOf(selectDriverField.getText()), 
                        Short.valueOf(selectDestinationField.getText()), isExpedited, driverMessage.getText());
        
        
    }
    
    private ObservableList<String[]> jobData;
    public void listJobs(){
        
        jobsTbl.getColumns().clear();
        
        String[][] jobArray = Jobs.listJobs();
        String[] colnames = {"ID" , "Job Type ID", "Customer ID", "Driver ID" , "Dest. ID",
                            "Departed", "ETA", "Is Expedited?", "Message", "Is Active?"};
        ObservableList<String[]> custData = FXCollections.observableArrayList();
        ObservableList<String[]> cols = FXCollections.observableArrayList();

        cols.addAll(colnames);
        custData.addAll(Arrays.asList(jobArray));
        //data.remove(0);//remove titles from data
                
        for (int i = 0; i < jobArray[0].length; i++) {
            TableColumn tc = new TableColumn(colnames[i]);
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });
               
            tc.setPrefWidth(90);
            jobsTbl.getColumns().add(tc);
        }

        jobsTbl.setItems(custData);
        
    }

    
   

    
    
    
}
    


