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
public class NewServicePack extends HttpServlet {

    Database db=new Database();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter(); 
        db.servicePackage.setServicePkg_id(Integer.parseInt(request.getParameter("servicePkg_id")));
        db.servicePackage.setServiceID(Integer.parseInt(request.getParameter("serviceID")));
        db.servicePackage.setCost_W_orgEQdest(Float.parseFloat(request.getParameter("cost_weekend_origEQdest")));
        db.servicePackage.setCost_W_orgNEQdest(Float.parseFloat(request.getParameter("cost_weekend_origNEQdest")));
        db.servicePackage.setCost_D_orgEQdest(Float.parseFloat(request.getParameter("cost_weekdays_origEQdest")));
        db.servicePackage.setCost_D_orgNEQdest(Float.parseFloat(request.getParameter("cost_weekdays_origNEQdest")));
        db.addServicePackage();
        response.sendRedirect("/BillingProject/main.jsp"+"?success=true");
        
    }


}
