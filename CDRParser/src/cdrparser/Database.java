/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdrparser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MohabOmar
 */
public class Database {

    private final String url = "jdbc:postgresql://localhost:5432/billing";
    private final String user = "postgres";
    private final String password = "postgres";

    private Connection connection = null;
    private PreparedStatement preparedStatment = null;
    private ResultSet result = null;
    private String sqlCommand;
    boolean operation = false;

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection done successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean addCdr(CDR cdr) {
        try {
            connect();
            sqlCommand = "INSERT INTO cdr VALUES (?,?,?,?,?,?,?,?,?)";
            preparedStatment = connection.prepareStatement(sqlCommand);
            preparedStatment.setLong(1, cdr.getOrigin());
            preparedStatment.setLong(2, cdr.getDestination());
            preparedStatment.setInt(3, cdr.getServiceID());
            preparedStatment.setInt(4, cdr.getDuration_message_volume());
            preparedStatment.setString(5, cdr.getStartDate());
            preparedStatment.setTime(6, cdr.getStartTime());
            preparedStatment.setFloat(7, cdr.getExternalRating());
            preparedStatment.setFloat(8, cdr.getInternalRating());
            preparedStatment.setBoolean(9, cdr.isRated());
            preparedStatment.execute();
            operation = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            operation = false;
        } finally {
            stop();
            return operation;
        }
    }

    private void stop() {
        try {
            connection.close();
            System.out.println("Database is stopped");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
