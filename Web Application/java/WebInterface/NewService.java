package webApp;


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

    Database db=new Database();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter(); 
        db.services.setServiceID(Integer.parseInt(request.getParameter("serviceID")));
        db.services.setServiceName(request.getParameter("serviceName"));
        db.services.setServiceType(request.getParameter("serviceType"));
        db.addNewService();
        out.print("Service is added");
        
    }



}
