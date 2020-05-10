/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebInterface;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mo
 */
public class NewRatePlan extends HttpServlet {


    Database db=new Database();



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter(); 
        db.ratePlan.setRatePlane_id(Integer.parseInt(request.getParameter("RatePlanID")));   
        db.ratePlan.setServicePkg_id(Integer.parseInt(request.getParameter("servicePkg_id")));
        db.ratePlan.setFreeUnits(Integer.parseInt(request.getParameter("freeUnits")));
        db.ratePlan.setMonthlyFee(Float.parseFloat(request.getParameter("monthlyFee")));
        db.addNewRatePlan();
        response.sendRedirect("/BillingProject/main.jsp"+"?success=true");
    }



}
