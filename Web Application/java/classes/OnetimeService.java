/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author mohab
 */
public class OnetimeService {

    private int serviceID;
    private int customerID;
    private float fees;
    private Timestamp date;
    private boolean isCharged;
           

    public OnetimeService(int serviceID, int customerID, float fees, Timestamp date) {
        this.serviceID = serviceID;
        this.customerID = customerID;
        this.fees = fees;
        this.date = date;
    }

    public OnetimeService(int serviceID, int customerID, float fees, Timestamp date, boolean isCharged) {
        this.serviceID = serviceID;
        this.customerID = customerID;
        this.fees = fees;
        this.date = date;
        this.isCharged = isCharged;
    }

    public boolean isIsCharged() {
        return isCharged;
    }

    public void setIsCharged(boolean isCharged) {
        this.isCharged = isCharged;
    }
    

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
    

    public int getServiceID() {
        return serviceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public float getFees() {
        return fees;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }
    
    

}
