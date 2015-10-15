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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.util.Callback;

/**
 *
 * @author peter
 */
public class MainWindow implements Initializable {

    @FXML
    private AnchorPane mainAnchorPane;
    
    //home tab
    @FXML
    private Tab homeTab;
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
    private TableView homeJobsList;
    
    //home tab / driver locations map
    @FXML
    private TableView activeJobsAndDrivers;
    @FXML
    private Tab activeJobsTab;
    @FXML
    private Tab jobsQue;

    
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
    private Tab driversTab;
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
    private Tab jobsTab;
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
    @FXML
    private Button pickupLoc;
    @FXML
    private TextField pickupLocTxtFld;
    
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

    
    //maps
    @FXML
    Button changeConductorButton;

    @FXML
    ComboBox<String> conductorComboBox;

    @FXML
    WebView mapaWebView;

    WebEngine engine;
    //AIzaSyBM7o4RXMLVk9N9Y2fe4VYrbardP2D3qLs
    double lat;
    double lng;
    
    //for switching map between tabs
    private String mapviewFile = "maps_directions.html";
    
    @FXML 
    private void setHomeMapView(){
    this.mapviewFile = "maps_directions.html";
    
    }
    
    @FXML 
    private void setDriverMapView(){
    //this.mapviewFile = "drivers_mapa.html";
    engine.reload();
    listAvtiveJobs();
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        listJobsQue();
        
        fillComboBoxConductor();
        final URL urlGoogleMaps = getClass().getResource(mapviewFile);
        engine = mapaWebView.getEngine();
        lat = 53.349064;
        lng = -6.266736;
        engine.getLoadWorker().stateProperty().addListener(new ChangeListener<State>() {
                    public void changed(ObservableValue ov, State oldState, State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
//                            engine.executeScript(""
//                                    + "window.lat = " + lat + ";"
//                                    + "window.lon = " + lng + ";"
//                                    + "document.goToLocation(window.lat, window.lon);"
//                            );
                        }
                    }
                });

        engine.setJavaScriptEnabled(true);
        engine.load(urlGoogleMaps.toExternalForm());
    }

    private void fillComboBoxConductor() {
    //code to fill ComboBox
    }

    private String driverMarkerGPS;
    private String driverMarkerName;//the below method doesnt like constructors. . . 
    @FXML
    public void addMapMarker() {
        String lat = "53.311128";
        String lng =  "-6.999673";
        engine = mapaWebView.getEngine();
        //engine.executeScript("addMarker(" +lat+"," + lng + ");");
        //engine.executeScript("addMarker(" + driverMarkerGPS +");");
        engine.executeScript("addMarker();");
        System.out.println("added marker");
 
    }
    
    @FXML
    private void setDirCoords(String startLoc, String destLoc) {
//        String start = "53.34481274192986, -6.26495361328125";
//        String end = "53.32349126597425, -6.3480377197265625";
        engine = mapaWebView.getEngine();
        engine.executeScript("initMap(\"" + startLoc + "\", \" " + destLoc + "\");");

    }
    
    
    @FXML
    public void showAllConductores() {
        for (int i = 0; i < 6; i++) {
            lat = lat + 5;
            lng = lng + 5;
            engine.executeScript("arrayConductores.push({x:5, y:3});");

        }
        engine.executeScript("addAllMarkers();");

    }

    
    public void addNewDriver() {
        Drivers.addNewDriver(newDriverFname.getText(),newDriverSname.getText(),newDriverPlateNum.getText());

    }
    
    //class list for refresh button calls
    private ObservableList<String[]> driverData;
    public void listDrivers() {
        
         driversTabTbl.getColumns().clear();  

        String[][] driverArray = Drivers.listDrivers();
        String[] colnames = {"ID", "First Name", "Last Name", "Vehicle ID", "Plate Number", "Job ID"};
        driverData = FXCollections.observableArrayList();
        ObservableList<String[]> cols = FXCollections.observableArrayList();
        cols.addAll(colnames);
        driverData.addAll(Arrays.asList(driverArray));

        for (int i = 0; i < driverArray[0].length; i++) {
            
            TableColumn tc = new TableColumn(colnames[i]);
            
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                }
            });

            tc.setPrefWidth(120);
            driversTabTbl.getColumns().add(tc);
        }

        driversTabTbl.setItems(driverData);
        
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

        String[][] numArray;
        String[][] locationsArray;
        short customerLocationId = 0;
        short customerId = 0; 
        
        //check if array has been made
        if(Customers.customers == null){
        numArray = Customers.listCustomers();
        locationsArray = Locations.listLocations();
        }else{
            numArray = Customers.customers;
            locationsArray = Locations.locations;
        }
        
        if (homeCustomerPhone.getCharacters().length() >= 4) {
            //loop through numArray check for number match
            for (int i = 0; i < numArray.length; ++i) {
                if(numArray[i][5].contains(homeCustomerPhone.getText())){
                    homeCustomerPhoneSearch.setText(numArray[i][5]);
                    homeCustomerName.setText(numArray[i][1]);
                    homeCustomerNameSearch.clear();
                    homeCustomerAddressSearch.clear();
                    System.out.println(numArray[i][5]);
                    
                    customerLocationId = Short.valueOf(numArray[i][3]);
                    customerId = Short.valueOf(numArray[i][0]);

                }else{
                    if (homeCustomerPhone.getText() != numArray[i][5] && homeCustomerPhone.getCharacters().length() > 7) {
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
                    //System.out.print(locationsArray[i][0]);
                    homeCustomerAddress.setText(locationsArray[i][1]);
                    
                }
            }
        }
        
        homeCustomerPhone.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {

                homeCustomerPhoneSearch.clear();
                homeCustomerName.clear();
                homeCustomerAddress.clear();

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
                        Short.valueOf(pickupLocTxtFld.getText()), Short.valueOf(selectDestinationField.getText()), isExpedited, driverMessage.getText());
        
        
    }
    
    private ObservableList<String[]> jobData;
    public void listJobsQue(){

        if(homeTab.isSelected()){
            homeJobsList.getColumns().clear();
        }else{
            if(jobsTab.isSelected()){
                jobsTbl.getColumns().clear();
            }
        }

        String[][] jobArray = Jobs.listJobs();
        String[] colnames = {"Active" , "ID", "Type", "Customer" , "From", "To", "Driver", "Created",
                            "Departed", "ETA", "Message", "Start", "End", "Vehicle Current GPS"};
        ObservableList<String[]> custData = FXCollections.observableArrayList();
        ObservableList<String[]> cols = FXCollections.observableArrayList();
        cols.addAll(colnames);
        custData.addAll(Arrays.asList(jobArray));
        
        for (int i = 0; i < jobArray[0].length; i++) {
            TableColumn tc = new TableColumn(colnames[i]);
 
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                    
                }
            });
               
            tc.setPrefWidth(80);
            
            if (homeTab.isSelected()) {
                homeJobsList.getColumns().add(tc);
            } else {
                if (jobsTab.isSelected()) {
                    jobsTbl.getColumns().add(tc);
                }
                
            };
        }
        
        if(homeTab.isSelected()){
            homeJobsList.setItems(custData);
        }else{
            if(jobsTab.isSelected()){
                jobsTbl.setItems(custData);
            }
        }
        homeJobsList.getColumns().remove(0);
        homeJobsList.getColumns().remove(5);
        homeJobsList.getColumns().remove(6);///not part of active list
        homeJobsList.getColumns().remove(10);
        
    }
    
    @FXML
    public void listAvtiveJobs(){

        activeJobsAndDrivers.getColumns().clear();

        String[][] jobArray = Jobs.listJobs();
        String[] colnames = {"Active" , "ID", "Type", "Customer" , "From", "To", "Driver", "Created",
                            "Departed", "ETA", "Message", "Start", "End", "Vehicle Current GPS"};
        ObservableList<String[]> custData = FXCollections.observableArrayList();
        ObservableList<String[]> cols = FXCollections.observableArrayList();
        cols.addAll(colnames);
        custData.addAll(Arrays.asList(jobArray));
        
        for (int i = 0; i < jobArray[0].length; i++) {
            TableColumn tc = new TableColumn(colnames[i]);
 
            final int colNo = i;
            tc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                    return new SimpleStringProperty((p.getValue()[colNo]));
                    
                }
            });
               
            tc.setPrefWidth(80);
            activeJobsAndDrivers.getColumns().add(tc);

        }
        
        activeJobsAndDrivers.setItems(custData);
        activeJobsAndDrivers.getColumns().remove(0);
    }
    
    @FXML
    public void updateDirsOnMap(){
        
        //grabs the startLoc and endLoc from the homJobsList and passes em to updateMap method
        String startLoc = ((String[])homeJobsList.getSelectionModel().getSelectedItem())[11];
        String destLoc  = ((String[])homeJobsList.getSelectionModel().getSelectedItem())[12];
        
        setDirCoords(startLoc, destLoc);
    }
    
    @FXML
    public void putDriverMarkerMap(){
        
        //grabs the startLoc and endLoc from the homJobsList and passes em to updateMap method
        driverMarkerGPS = ((String[])activeJobsAndDrivers.getSelectionModel().getSelectedItem())[13];
        driverMarkerName = ((String[])activeJobsAndDrivers.getSelectionModel().getSelectedItem())[8];
        System.out.print(driverMarkerGPS);
        addMapMarker();
    }
  
}
    


