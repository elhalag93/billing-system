/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
/**
 *
 * @author M Gamal
 */
public class JavaApplication1 {
//         "C:\\Users\\M Gamal\\Documents\\NetBeansProjects\\JavaApplication1\\src\\javaapplication1\\newReport.jasper";

    
//    @SuppressWarnings("unchecked")
    /**
     * 
     * @param args the command line arguments
     */
     @SuppressWarnings("unchecked")
   public static void main(String[] args) {
      String sourceFileName = 
       "C:\\Users\\M Gamal\\Documents\\NetBeansProjects\\JavaApplication1\\src\\javaapplication1\\newReport.jasper";
      DataBeanList DataBeanList = new DataBeanList();
      ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
//         System.out.println(dataList.get(1).getCountry());
      JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
      Map parameters = new HashMap();
      try {
         JasperFillManager.fillReportToFile(sourceFileName, parameters, beanColDataSource);
      } catch (JRException e) {
         e.printStackTrace();
      }
   }
}