package defaultpackage;


import defaultpackage.Vehicle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author peter
 */
public class Driver {
    
    short driverId;
    private short vehicleId;
    private String driverFirstName;
    private String driverLastName;
    private String taxiPlateNum = "PLATE# YET NOT SET";
    private boolean isOnJob;

    
    public Driver(short driverId, short vehicleId, String driverFirstName, String DriverLastName ){
        this.driverId = driverId;
        this.vehicleId = vehicleId;
        this.driverFirstName = driverFirstName;
        this.driverLastName = driverLastName;
        
        //insert DB code to update tables
 
    }
    
    //create list drivers DB calls
    
}
