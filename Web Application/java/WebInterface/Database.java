
package webApp;

/**
 *
 * @author mo
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Database {
    private final String url = "jdbc:postgresql://localhost:5432/billing";
    private final String user = "postgres";
    private final String password = "123456";
    private Connection connection;
    private String sqlcommand;
    private PreparedStatement preparedstatement;
    private ResultSet result;    
    Services  services=new Services();
    Customer customer=new Customer();
    ServicePackage servicePackage=new ServicePackage();
    Profile profile=new Profile();
    RatePlan ratePlan=new RatePlan();
    
    
    
    private void connect(){
        
        try {
            
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to Database Succeeded");
            
        } catch (SQLException | ClassNotFoundException ex) {
            
            System.out.println("databaseconnection.dataBase.connect()error");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    private void stop(){
        
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }





    public void addNewService(){
        connect();
       
        try {
            sqlcommand = "insert into services values(?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,services.getServiceID());
            preparedstatement.setString(2,services.getServiceName());
            preparedstatement.setString(3,services.getServiceType());

            result = preparedstatement.executeQuery();
            System.out.println("Service is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        stop();
    }    










    
     
}
