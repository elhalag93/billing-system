package com.mycompany.bill;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrintManager;
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
         String sourceFileName = 
"E:\\iti\\Billing\\bill\\src\\main\\java\\com\\mycompany\\bill\\newReport.jasper";
              String printFileName = null;
      DataBeanList DataBeanList = new DataBeanList();
      ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();

      JRBeanCollectionDataSource beanColDataSource = new 
         JRBeanCollectionDataSource(dataList);

      Map parameters = new HashMap();
      try {
    	   printFileName = JasperFillManager.fillReportToFile( 
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
