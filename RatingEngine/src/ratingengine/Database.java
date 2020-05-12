package ratingengine;

/**
 *
 * @author mo
 */
import cdrparser.CDR;
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

    public boolean addCdr(CDR cdr) {
        try {
            connect();
            sqlcommand = "INSERT INTO cdr VALUES (?,?,?,?,?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setLong(1, cdr.getOrigin());
            preparedstatement.setString(2, cdr.getDestination());
            preparedstatement.setInt(3, cdr.getServiceID());
            preparedstatement.setInt(4, cdr.getDuration_message_volume());
            preparedstatement.setString(5, cdr.getStartDate());
            preparedstatement.setTime(6, cdr.getStartTime());
            preparedstatement.setFloat(7, cdr.getExternalRating());
            preparedstatement.setFloat(8, cdr.getInternalRating());
            preparedstatement.setBoolean(9, cdr.isRated());
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

    public Vector<CDR> getUnratedCdrs() {
        Vector<CDR> unratedCdrs = new Vector();
        try {
            connect();
            sqlcommand = "select * from cdr where israted = false";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                unratedCdrs.add(new CDR(result.getLong(1),
                        result.getString(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getString(5),
                        result.getTime(6),
                        result.getFloat(7),
                        result.getFloat(8),
                        result.getBoolean(9),
                        result.getInt(10)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return unratedCdrs;
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

    public RatingFees getRatingFees(String serviceID, String customerRateplan) {
        RatingFees fees = null;
        try {
            connect();
            sqlcommand = "select * from rateplan r inner join service_package s on r.servicepkg_id=s.servicepkg_id where s.service_id =" + serviceID + "and r.rateplan_id =" + customerRateplan;
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();

            while (result.next()) {
                fees = new RatingFees(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getFloat(4),
                        result.getInt(6),
                        result.getFloat(7),
                        result.getFloat(8),
                        result.getFloat(9),
                        result.getFloat(10));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            stop();
            return fees;
        }
    }

    public int getRemainingUnits(int cid) {
        int units = 0;
        try {
            connect();
            sqlcommand = " select * from customer_units WHERE customer_id =" + cid;
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                units = result.getInt(3);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            stop();
            return units;
        }
    }

    public void reduceRemainingUnits(int cid, int units) {
        try {
            connect();
            sqlcommand = " update customer_units set remaining_units = remaining_units - " + units + " WHERE customer_id =" + cid;
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            stop();
        }
    }

    public void updateInternalRating(int cdrID, float rating) {
        try {
            connect();
            sqlcommand = " update cdr set internalrating =" + rating + ", israted= true, isbilled= false WHERE cdr_id =" + cdrID;
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            stop();
        }
    }
}
