/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author mohab
 */
public class RecurringService {
    private int serviceID;
    private int customerID;
    private float fees;
    private boolean isActive;

    public RecurringService(int serviceID, int customerID, float fees, boolean isActive) {
        this.serviceID = serviceID;
        this.customerID = customerID;
        this.fees = fees;
        this.isActive = isActive;
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

    public boolean isIsActive() {
        return isActive;
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

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
    
    
    
}
