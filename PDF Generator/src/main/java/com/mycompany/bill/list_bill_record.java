/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bill;

import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author M Gamal
 */
public class list_bill_record {
    String phone_number;
    list_bill_record(String number){
       
    this.phone_number=number;
    
    }
        
         /**
     *
     * @return
     */
        
      public ArrayList<bill_record> get_bill() {
          Database database=new Database();
       ArrayList<bill_record> list=database.get_records(phone_number);
     
      return list;
   }
           public bill_record produce (String origin, String destination, String serviceID, String value) {
      bill_record record = new bill_record();
     record.setDestination_Number(destination);
     record.setOrignating_Number(origin);
     record.setService(serviceID);
     record.setValue(value);
//     record.setUnit(unit);

      return record;
   }

    
}
