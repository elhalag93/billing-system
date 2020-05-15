package com.mycompany.bill;

/**
 *
 * @author mo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    private final String url = "jdbc:postgresql://localhost:5544/billing";
    private final String user = "postgres";
    private final String password = "mohamed";
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

   

   
    public ArrayList<bill_record> get_records(String phone_number) {
        ArrayList<bill_record> list=new ArrayList<bill_record>();
        try {
            connect();
            
            sqlcommand = "SELECT * FROM pdf WHERE orignating_number LIKE ?";
            
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1,phone_number);
            result = preparedstatement.executeQuery();

            while (result.next()) {
                bill_record record = new bill_record();
                record.setOrignating_Number(result.getString(1));
                record.setDestination_Number(result.getString(2));
                record.setService(result.getInt(3)+"");
                record.setValue(result.getString(4));
                list.add(record);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return list;
        }
    }

}