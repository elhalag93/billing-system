/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdr;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

/**
 *
 * @author MohabOmar
 */
public class CDRUploadHandler extends HttpServlet {
    
    private final String uploadDir = "D:\\ITI\\Billing\\CDRParser\\CDRs";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if(ServletFileUpload.isMultipartContent(req)){
           try {
               List<FileItem> multiParts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
               for(FileItem item : multiParts)
               {
                   if(!item.isFormField())
                   {
                       String name = new File(item.getName()).getName();
                       item.write(new File(uploadDir + File.separator + name));
                       resp.sendRedirect("/BillingProject/CDRUpload.html");
                       System.out.println("####File uploaded successfully####");
                   }
               }
           } catch (FileUploadException ex) {
               Logger.getLogger(CDRUploadHandler.class.getName()).log(Level.SEVERE, null, ex);
           } catch (Exception ex) {
               Logger.getLogger(CDRUploadHandler.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }
}
