/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package defaultpackage;

/**
 *
 * @author peter
 */
public class Jobs {
    
    private short jobId;
    private short customerId;
    private short driverId;
    private short destId;
    private boolean isExpedited;
    
    public Jobs(short customerId, short driverId, short destId, boolean isExpedited){
        this.jobId = jobId;
        this.customerId = customerId;
        this.driverId = driverId;
        this.destId = destId;
        this.isExpedited = isExpedited;
        
        //insert code to update DB
        
    }
            
    //create list jobs DB calls
    
}
