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
public class CustomerUnits {
    
    private int customerID;
    private int rateplanID;
    private int remainingUnits;

    public CustomerUnits(int customerID, int rateplanID, int remainingUnits) {
        this.customerID = customerID;
        this.rateplanID = rateplanID;
        this.remainingUnits = remainingUnits;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getRateplanID() {
        return rateplanID;
    }

    public int getRemainingUnits() {
        return remainingUnits;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setRateplanID(int rateplanID) {
        this.rateplanID = rateplanID;
    }

    public void setRemainingUnits(int remainingUnits) {
        this.remainingUnits = remainingUnits;
    }
    
    
}
