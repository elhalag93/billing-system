/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;

import java.util.Vector;
import ratingengine.Customer;

/**
 *
 * @author mohab
 */
public class Billing {

    /**
     * @param args the command line arguments
     */
    
    public static Vector<Customer> customers = new Vector();
    
    
    public static void main(String[] args) {
         Database database = new Database();
         customers = database.getAllCustomers();
         for (Customer c : customers)
         {
             float totalRecurring = database.getCustomerTotalRecurringServices(c.getCustomerID());
             float totalOnetime = database.getCustomerTotalOnetimeServices(c.getCustomerID());
             float monthlyFees = database.getCustomerRateplanFees(c.getRatePlane_id());
             float totalAmount = totalOnetime + totalRecurring + monthlyFees;
             database.createBill(c.getCustomerID(), totalAmount, totalRecurring, totalOnetime, monthlyFees);
             database.markOnetimeCharged(c.getCustomerID());
             System.out.println(totalRecurring);
             System.out.println(monthlyFees);
             System.out.println(totalOnetime);
         }
              
    }
    
}
