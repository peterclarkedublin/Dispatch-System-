package defaultpackage;


import java.util.Date;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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


    public Vehicle(short driverId){
        this.driverId = driverId;
        
    }
}
