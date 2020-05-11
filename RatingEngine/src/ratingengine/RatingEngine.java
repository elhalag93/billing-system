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
    private static Database database = new Database();

    /**
     *
     * @param c
     * @param cdr
     */
    private static void rateData (Customer c , CDR cdr)
    {
        float externalRating = cdr.getExternalRating();
        if (database.getRemainingUnits(c.getCustomerID()) > externalRating)
        {
            database.reduceRemainingUnits(c.getCustomerID(), Math.round(externalRating));
        } else {
            database.updateInternalRating(cdr.getCdrID(), externalRating);        
        }
    }
    
    
    
    

    
    public static void main(String[] args) {
        
        
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
            if (unratedCdr.getServiceID() == 3)
            {
                rateData(customer, unratedCdr);
            }
        }
        
    }
    
}
