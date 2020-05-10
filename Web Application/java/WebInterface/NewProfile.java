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
public class NewProfile extends HttpServlet {

    Database db=new Database();
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();    
        db.profile.setCustomerID(Integer.parseInt(request.getParameter("customerID")));
        db.profile.setRatePlanID(Integer.parseInt(request.getParameter("RatePlanID")));
        db.addNewProfil();
        response.sendRedirect("/BillingProject/main.jsp"+"?success=true");
    }

  

}
