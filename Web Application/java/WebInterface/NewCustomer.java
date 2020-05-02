/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;  
/**
 *
 * @author mo
 */
public class NewCustomer extends HttpServlet {

    Database db=new Database();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter(); 
        db.customer.setCustomerName(request.getParameter("customerName"));
        db.customer.setMsisdn(request.getParameter("msisdn"));
        db.customer.setEmail(request.getParameter("email"));
        db.customer.setBirthDate(Date.valueOf(request.getParameter("birthDate")));
        db.customer.setAddress(request.getParameter("address"));
        db.customer.setJob(request.getParameter("job"));
        db.customer.setNationalID(request.getParameter("nationalID"));
        db.customer.setRatePlane_id(Integer.parseInt(request.getParameter("RatePlanID")));
        
        db.addNewCustomer();
        
        
        
        
        
        
        
    }

}
