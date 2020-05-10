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
 * @author mohab
 */
public class AddService extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Database db = new Database();
        if (req.getParameter("stype").equalsIgnoreCase("recurring")) {
            db.addRecurringService(Integer.parseInt(req.getParameter("sid")), Integer.parseInt(req.getParameter("cid")), Float.parseFloat(req.getParameter("fees")));
        }
        if (req.getParameter("stype").equalsIgnoreCase("one time")) {
            db.addOnetimeService(Integer.parseInt(req.getParameter("sid")), Integer.parseInt(req.getParameter("cid")), Float.parseFloat(req.getParameter("fees")));
        }

        resp.sendRedirect("/BillingProject/services.jsp" + "?success=true");
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
}
