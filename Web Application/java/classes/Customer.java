
package classes;

import java.sql.Date;



/**
 *
 * @author mo
 */
public class Customer {
    int customerID;
    String customerName;
    String msisdn;
    String email;
    String address;
    Date birthDate;
    String job;
    String nationalID;
    int ratePlane_id;

    public Customer(int customerID, String customerName, String msisdn, String email, String address, Date birthDate, String job, String nationalID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.msisdn = msisdn;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.job = job;
        this.nationalID = nationalID;
    }



    public Customer() {
     
    }

    

    
    
    
    
    public int getCustomerID() {
        return customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getJob() {
        return job;
    }

    public String getNationalID() {
        return nationalID;
    }

    public int getRatePlane_id() {
        return ratePlane_id;
    }

    
    
    
    
    
    
    
    
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }

    public void setRatePlane_id(int ratePlane_id) {
        this.ratePlane_id = ratePlane_id;
    }
    
    
    
    
    
    
    
    
}
