/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bill;

/**
 *
 * @author M Gamal
 */
import java.sql.Time;
import java.util.ArrayList;
import java.math.BigInteger; 

/**
 *
 * @author M Gamal
 */
public class CDRList {
    ArrayList<CDR> CDRList;

    /**
     *
     * @return
     */
    public ArrayList<CDR> getDataBeanList() {
       
      CDRList = new ArrayList<CDR>();

      CDRList.add(produce("201221234567" , "00201001234567" , 1, 100 ,"20190301" , Time.valueOf("10:03:20"), 0 , 0,true ));
      CDRList.add(produce("201221234567" , "00201001234567" , 1, 500 ,"20190301" , Time.valueOf("11:03:20"), 0 , 0,true ));
      CDRList.add(produce("201221234567" , "00201001234567" , 1, 498 ,"20190301" , Time.valueOf("11:30:20"), 0 , 0,true ));
      return CDRList;
   }

   /**
    * This method returns a DataBean object,
    * with name and country set in it. 
    */
     private String origin;
    private String destination;
    private int serviceID;
    private int duration_message_volume;
    private String startDate;
    private Time startTime;
    private float externalRating;
    private float internalRating;
    private boolean isRated;
    
   public CDR produce (String origin , String destination , int serviceID , int duration_message_volume , String startDate , Time startTime, float externalRating , float internalRating , boolean isRated) {
      CDR cdr = new CDR();
      cdr.setDestination(destination);
      cdr.setDuration_message_volume(duration_message_volume);
      cdr.setExternalRating(externalRating);
      cdr.setInternalRating(internalRating);
      cdr.setIsRated(isRated);
      cdr.setOrigin(origin);
      cdr.setServiceID(serviceID);
      cdr.setStartDate(startDate);
      cdr.setStartTime(startTime);
            
      return cdr;
   }
}