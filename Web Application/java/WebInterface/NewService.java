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
public class NewService extends HttpServlet {

    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter(); 
        Database db=new Database();
        db.services.setServiceID(Integer.parseInt(request.getParameter("serviceID")));
        db.services.setServiceName(request.getParameter("serviceName"));
        db.services.setServiceType(request.getParameter("serviceType"));
        if (!request.getParameter("fees").equalsIgnoreCase(""))
        {
            db.services.setFees(Float.parseFloat(request.getParameter("fees")));
        }
        db.addNewService();
        response.sendRedirect("/BillingProject/main.jsp"+"?success=true");
        
    }



}
