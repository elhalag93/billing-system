/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratingengine;


import cdrparser.CDR;
import java.util.Vector;


/**
 *
 * @author MohabOmar
 */
public class RatingEngine {
    
    public static Vector<CDR> unratedCdrs = new Vector();

    
    public static void main(String[] args) {
        
        Database database = new Database();
        unratedCdrs = database.getUnratedCdrs();
        System.out.println(unratedCdrs.size());
        for (CDR unratedCdr : unratedCdrs)
        {
            String origin = unratedCdr.getOrigin()+"";
            String serviceID = unratedCdr.getServiceID()+"";
            Customer customer = database.getCustomer(origin);
            System.out.println(customer.getRatePlane_id());
            String customerRateplan = customer.getRatePlane_id()+"";
            RatingFees ratingFees = database.getRatingFees(serviceID, customerRateplan);
            System.out.println(ratingFees.freeUnits);
            
            
            
            
            
        }
        
    }
    
}
