/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;

import java.sql.Date;

/**
 *
 * @author mohab
 */
public class Bills {
    private int bill_id;
    private int customer_id;
    private float amount_due;
    private Date bill_date;
    private boolean generated;
    private float totalRecurring;
    private float totalOnetime;
    private float rateplanFees;

    public Bills(int bill_id, int customer_id, float amount_due, Date bill_date, boolean generated) {
        this.bill_id = bill_id;
        this.customer_id = customer_id;
        this.amount_due = amount_due;
        this.bill_date = bill_date;
        this.generated = generated;
    }

    public Bills(int customer_id, float amount_due, Date bill_date, boolean generated) {
        this.customer_id = customer_id;
        this.amount_due = amount_due;
        this.bill_date = bill_date;
        this.generated = generated;
    }

    public Bills(int bill_id, int customer_id, float amount_due, Date bill_date, boolean generated, float totalRecurring, float totalOnetime, float rateplanFees) {
        this.bill_id = bill_id;
        this.customer_id = customer_id;
        this.amount_due = amount_due;
        this.bill_date = bill_date;
        this.generated = generated;
        this.totalRecurring = totalRecurring;
        this.totalOnetime = totalOnetime;
        this.rateplanFees = rateplanFees;
    }

    public float getTotalRecurring() {
        return totalRecurring;
    }

    public float getTotalOnetime() {
        return totalOnetime;
    }

    public float getRateplanFees() {
        return rateplanFees;
    }

    public void setTotalRecurring(float totalRecurring) {
        this.totalRecurring = totalRecurring;
    }

    public void setTotalOnetime(float totalOnetime) {
        this.totalOnetime = totalOnetime;
    }

    public void setRateplanFees(float rateplanFees) {
        this.rateplanFees = rateplanFees;
    }
    
    
    
    

    public int getBill_id() {
        return bill_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public float getAmount_due() {
        return amount_due;
    }

    public Date getBill_date() {
        return bill_date;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setAmount_due(float amount_due) {
        this.amount_due = amount_due;
    }

    public void setBill_date(Date bill_date) {
        this.bill_date = bill_date;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }
    
    
    
    
}
