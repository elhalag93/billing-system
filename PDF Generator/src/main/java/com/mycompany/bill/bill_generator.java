package com.mycompany.bill;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author M Gamal
 */
public class bill_generator {
       @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        
//        String phone_number = args[0];
//        String customer_id = args[1];
        String phone_number="00201001234567";
         String sourceFileName = 
"E:\\iti\\Billing\\bill\\src\\main\\java\\com\\mycompany\\bill\\newReport.jasper";
      list_bill_record bill_data = new list_bill_record(phone_number);
      
      ArrayList<bill_record> dataList = bill_data.get_bill();
      
      JRBeanCollectionDataSource beanColDataSource = new 
         JRBeanCollectionDataSource(dataList,false);
      
      Map parameters = new HashMap();
      parameters.put("Total","10000");
      parameters.put("customer_id","099898998");
      
      String para="Total" +"customer_id" +"msisdn" +"national_id"+"customer_name"+"profile_id"+
              "total_ontime"+"total_recuring"+"rate_plan_fees" +"services";
      try {
    	  String printFileName = JasperFillManager.fillReportToFile( 
            sourceFileName, parameters, beanColDataSource);
         if(printFileName != null){
             String path="E:\\iti\\Billing\\bill\\src\\main\\java\\com\\mycompany\\bill";
             JasperExportManager.exportReportToPdfFile(printFileName,path + "\\Report.pdf");

//            JasperPrintManager.printReport( printFileName, true);
           
         }
      } catch (JRException e) {
         e.printStackTrace();
      }
   }
    
    
}
