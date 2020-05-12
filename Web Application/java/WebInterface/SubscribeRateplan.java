/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebInterface;

import classes.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohab
 */
public class SubscribeRateplan extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database database = new Database();
        if (req.getParameter("currentrateplan") != null) {
            float currentRateplanFees = database.getRateplanFees(Integer.parseInt(req.getParameter("currentrateplan")));
            database.insertPreviousRateplan(Integer.parseInt(req.getParameter("cid")), Integer.parseInt(req.getParameter("currentrateplan")), currentRateplanFees);
        }
        if (req.getParameter("rid") != null && req.getParameter("cid") != null) {
            int newRateplanUnits = database.getRateplanUnits(Integer.parseInt(req.getParameter("rid")));
            database.updateCustomerRateplan(Integer.parseInt(req.getParameter("cid")), Integer.parseInt(req.getParameter("rid")));
            database.updateRemainingUnits(newRateplanUnits, Integer.parseInt(req.getParameter("cid")));
        }

        resp.sendRedirect("/BillingProject/customerInfo.jsp" + "?success=true");
    }

}
