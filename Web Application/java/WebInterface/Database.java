
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
    ServicePackage servicePackage=new ServicePackage();
    Customer customer=new Customer();
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


        public void addServicePackage(){
        connect();
       
        try {
            sqlcommand = "insert into service_package values(?,?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,servicePackage.getServicePkg_id());
            preparedstatement.setInt(2,servicePackage.getServiceID());
            preparedstatement.setFloat(3,servicePackage.getCost_W_orgEQdest());
            preparedstatement.setFloat(4,servicePackage.getCost_W_orgNEQdest());
            preparedstatement.setFloat(5,servicePackage.getCost_D_orgEQdest());
            preparedstatement.setFloat(6,servicePackage.getCost_D_orgNEQdest());            
            result = preparedstatement.executeQuery();
            System.out.println("ServicePackage is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        stop();
    }


    public void addNewRatePlan(){
        connect();
       
        try {
            sqlcommand = "insert into rateplan values(?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1,ratePlan.getRatePlane_id());
            preparedstatement.setInt(2,ratePlan.getServicePkg_id());
            preparedstatement.setInt(3,ratePlan.getFreeUnits());
            preparedstatement.setFloat(4,ratePlan.getMonthlyFee());

            result = preparedstatement.executeQuery();
            System.out.println("rateplan is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        stop();
    } 




    public void addNewProfil(){
        connect();
       
        try {
            sqlcommand = "insert into profile (customer_id, rateplan_id)  values(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            
            preparedstatement.setInt(1,profile.getCustomerID());
            preparedstatement.setInt(2,profile.getRatePlanID());
            result = preparedstatement.executeQuery();
            System.out.println("Profile is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        stop();
    } 



    public void addNewCustomer(){
        connect();
       
        try {
            sqlcommand = "insert into customers (customer_name, msisdn, email, birthdate, address, job, national_id, rateplan_id) values(?,?,?,?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,customer.getCustomerName());
            preparedstatement.setString(2,customer.getMsisdn());
            preparedstatement.setString(3,customer.getEmail());
            preparedstatement.setDate(4,customer.getBirthDate());
            preparedstatement.setString(5,customer.getAddress());
            preparedstatement.setString(6,customer.getJob());
            preparedstatement.setString(7,customer.getNationalID());
            preparedstatement.setInt(8,customer.getRatePlane_id());
            result = preparedstatement.executeQuery();
            System.out.println("Customer is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        stop();
    }    
    
     
}
