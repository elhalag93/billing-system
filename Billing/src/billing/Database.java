/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package billing;

import cdrparser.CDR;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import ratingengine.Customer;

/**
 *
 * @author mohab
 */
public class Database {

    private final String url = "jdbc:postgresql://localhost:5432/billing";
    private final String user = "postgres";
    private final String password = "postgres";
    private Connection connection;
    private String sqlcommand;
    private PreparedStatement preparedstatement;
    private ResultSet result;
    boolean operation = false;

    private void connect() {

        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to Database Succeeded");

        } catch (SQLException | ClassNotFoundException ex) {

            System.out.println("databaseconnection.dataBase.connect()error");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void stop() {

        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Customer getCustomer(String msisdn) {
        Customer customer = null;
        try {
            connect();
            sqlcommand = "SELECT * FROM customers WHERE msisdn LIKE ?";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1, "%" + msisdn);
            result = preparedstatement.executeQuery();

            while (result.next()) {
                customer = new Customer(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getDate(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8),
                        result.getInt(9));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            stop();
            return customer;
        }
    }

    public Vector<Customer> getAllCustomers() {
        Vector<Customer> customers = new Vector();
        try {
            connect();
            sqlcommand = "select * from customers";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                customers.add(new Customer(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getDate(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8),
                        result.getInt(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return customers;
        }
    }

    public float getCustomerTotalRecurringServices(int cid) {
        float total = 0;
        try {
            connect();
            sqlcommand = "SELECT * FROM recurring_services WHERE customer_id = " + cid + "AND isactive = true";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();

            while (result.next()) {

                total += result.getFloat(3);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            stop();
            return total;
        }
    }

    public float getCustomerTotalOnetimeServices(int cid) {
        float total = 0;
        try {
            connect();
            sqlcommand = "SELECT * FROM onetime_services WHERE customer_id = " + cid + "AND ischarged = false";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();

            while (result.next()) {

                total += result.getFloat(3);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            stop();
            return total;
        }
    }

    public float getCustomerRateplanFees(int rid) {
        float monthlyFees = 0;
        try {
            connect();
            sqlcommand = "SELECT * FROM rateplan WHERE rateplan_id = " + rid;
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();

            while (result.next()) {

                monthlyFees = result.getFloat(4);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            stop();
            return monthlyFees;
        }
    }

    public boolean createBill(int cid, float amountDue, float tR, float tO, float monthlyFees) {
        try {
            connect();
            sqlcommand = "INSERT INTO bills (customer_id, amount_due, bill_date, generated, total_recurring, total_onetime, rateplan_fees) VALUES (?,?,'now',false,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, cid);
            preparedstatement.setFloat(2, amountDue);
            preparedstatement.setFloat(3, tR);
            preparedstatement.setFloat(4, tO);
            preparedstatement.setFloat(5, monthlyFees);
            preparedstatement.execute();
            operation = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            operation = false;
        } finally {
            stop();
            return operation;
        }
    }
    
        public boolean markOnetimeCharged(int cid) {
        try {
            connect();
            sqlcommand = "SELECT * FROM onetime_services WHERE customer_id = " + cid + "AND ischarged = false";
            preparedstatement = connection.prepareStatement(sqlcommand);
            Statement stmt = connection.createStatement();
            result = preparedstatement.executeQuery();
            while (result.next())
            {
             stmt.executeUpdate("update onetime_services set ischarged= true where customer_id = " + cid + "AND ischarged = false");   
            }
            operation = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            operation = false;
        } finally {
            stop();
            return operation;
        }
    }

}
