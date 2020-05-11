package WebInterface;

/**
 *
 * @author mo
 */
import billing.Bills;
import cdrparser.CDR;
import classes.Customer;
import classes.OnetimeService;
import classes.Profile;
import classes.RatePlan;
import classes.RecurringService;
import classes.ServicePackage;
import classes.Services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    Services services = new Services();
    ServicePackage servicePackage = new ServicePackage();
    Customer customer = new Customer();
    Profile profile = new Profile();
    RatePlan ratePlan = new RatePlan();

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

    public void addNewService() {
        connect();

        try {
            sqlcommand = "insert into services values(?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, services.getServiceID());
            preparedstatement.setString(2, services.getServiceName());
            preparedstatement.setString(3, services.getServiceType());
            preparedstatement.setFloat(4, services.getFees());

            result = preparedstatement.executeQuery();
            System.out.println("Service is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        stop();
    }

    public void addServicePackage() {
        connect();

        try {
            sqlcommand = "insert into service_package values(?,?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, servicePackage.getServicePkg_id());
            preparedstatement.setInt(2, servicePackage.getServiceID());
            preparedstatement.setFloat(3, servicePackage.getCost_W_orgEQdest());
            preparedstatement.setFloat(4, servicePackage.getCost_W_orgNEQdest());
            preparedstatement.setFloat(5, servicePackage.getCost_D_orgEQdest());
            preparedstatement.setFloat(6, servicePackage.getCost_D_orgNEQdest());
            result = preparedstatement.executeQuery();
            System.out.println("ServicePackage is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        stop();
    }

    public void addNewRatePlan() {
        connect();

        try {
            sqlcommand = "insert into rateplan values(?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, ratePlan.getRatePlane_id());
            preparedstatement.setInt(2, ratePlan.getServicePkg_id());
            preparedstatement.setInt(3, ratePlan.getFreeUnits());
            preparedstatement.setFloat(4, ratePlan.getMonthlyFee());

            result = preparedstatement.executeQuery();
            System.out.println("rateplan is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        stop();
    }

    public void addNewProfil() {
        connect();

        try {
            sqlcommand = "insert into profile (customer_id, rateplan_id)  values(?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);

            preparedstatement.setInt(1, profile.getCustomerID());
            preparedstatement.setInt(2, profile.getRatePlanID());
            result = preparedstatement.executeQuery();
            System.out.println("Profile is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        stop();
    }

    public boolean addNewCustomer() {
        connect();

        try {
            sqlcommand = "insert into customers (customer_name, msisdn, email, birthdate, address, job, national_id, rateplan_id) values(?,?,?,?,?,?,?,?)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1, customer.getCustomerName());
            preparedstatement.setString(2, customer.getMsisdn());
            preparedstatement.setString(3, customer.getEmail());
            preparedstatement.setDate(4, customer.getBirthDate());
            preparedstatement.setString(5, customer.getAddress());
            preparedstatement.setString(6, customer.getJob());
            preparedstatement.setString(7, customer.getNationalID());
            preparedstatement.setInt(8, customer.getRatePlane_id());
            result = preparedstatement.executeQuery();
            System.out.println("Customer is added Successfully" + result);
            return true;
        } catch (SQLException ex) {

            return false;
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            stop();
        }
    }

    public Customer getCustomer(String msisdn) {
        try {
            connect();
            sqlcommand = "SELECT * FROM customers WHERE msisdn LIKE ?";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setString(1, "%" + msisdn);
            result = preparedstatement.executeQuery();

            while (result.next()) {
                customer.setCustomerID(result.getInt(1));
                customer.setCustomerName(result.getString(2));
                customer.setMsisdn(result.getString(3));
                customer.setEmail(result.getString(4));
                customer.setBirthDate(result.getDate(5));
                customer.setAddress(result.getString(6));
                customer.setJob(result.getString(7));
                customer.setNationalID(result.getString(8));
                customer.setRatePlane_id(result.getInt(9));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            stop();
            return customer;
        }
    }

    public Vector<Services> getServices() {
        Vector<Services> services = new Vector();
        try {
            connect();
            sqlcommand = " select * from services WHERE service_type like 'recurring' OR service_type like 'one time'";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                services.add(new Services(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getFloat(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return services;
        }
    }

    public Services getService(int sid) {
        Services service = null;
        try {
            connect();
            sqlcommand = " select * from services WHERE service_id =" + sid;
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                service = new Services(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getFloat(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return service;
        }
    }

    public void addRecurringService(int serviceID, int customerID, float fees) {
        connect();

        try {
            sqlcommand = "select * from recurring_services where service_id=" + serviceID + "and customer_id=" + customerID + "and isactive= false";
            preparedstatement = connection.prepareStatement(sqlcommand);
            Statement stmt = connection.createStatement();
            result = preparedstatement.executeQuery();
            if (result.next()) {
                stmt.executeUpdate("update recurring_services set isactive= true where service_id=" + serviceID + " and customer_id=" + customerID);
            } else {
                sqlcommand = "select * from recurring_services where service_id=" + serviceID + "and customer_id=" + customerID + "and isactive= true";
                preparedstatement = connection.prepareStatement(sqlcommand);
                result = preparedstatement.executeQuery();
                if (result.next()) {
                    System.out.println("Service is already added" + result);
                    stop();
                } else {
                    sqlcommand = "insert into recurring_services values(?,?,?,true)";
                    preparedstatement = connection.prepareStatement(sqlcommand);
                    preparedstatement.setInt(1, serviceID);
                    preparedstatement.setInt(2, customerID);
                    preparedstatement.setFloat(3, fees);
                    preparedstatement.executeQuery();
                    System.out.println("Service is added Successfully" + result);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        stop();
    }

    public void stopRecurringService(int serviceID, int customerID, float fees) {
        connect();

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("update recurring_services set isactive= false where service_id=" + serviceID + " and customer_id=" + customerID);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        stop();
    }

    public void addOnetimeService(int serviceID, int customerID, float fees) {
        connect();

        try {
            System.out.println("#########" + customerID);
            sqlcommand = "insert into onetime_services values(?,?,?,'now',false)";
            preparedstatement = connection.prepareStatement(sqlcommand);
            preparedstatement.setInt(1, serviceID);
            preparedstatement.setInt(2, customerID);
            preparedstatement.setFloat(3, fees);

            result = preparedstatement.executeQuery();
            System.out.println("Service is added Successfully" + result);
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        stop();
    }

    public Vector<RecurringService> getRecurringServices(int cid) {
        Vector<RecurringService> services = new Vector();
        try {
            connect();
            sqlcommand = " select * from recurring_services WHERE customer_id = " + cid + " AND isactive = true";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                services.add(new RecurringService(result.getInt(1),
                        result.getInt(2),
                        result.getFloat(3),
                        result.getBoolean(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return services;
        }
    }

    public Vector<OnetimeService> getOnetimeServices(int cid) {
        Vector<OnetimeService> services = new Vector();
        try {
            connect();
            sqlcommand = " select * from onetime_services WHERE customer_id = " + cid;
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                services.add(new OnetimeService(result.getInt(1),
                        result.getInt(2),
                        result.getFloat(3),
                        result.getTimestamp(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return services;
        }
    }

    public Vector<Bills> getBills(int cid) {
        Vector<Bills> bills = new Vector();
        try {
            connect();
            sqlcommand = " select * from bills WHERE customer_id = " + cid;
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                bills.add(new Bills(result.getInt(1),
                        result.getInt(2),
                        result.getFloat(3),
                        result.getDate(4),
                        result.getBoolean(5),
                        result.getFloat(6),
                        result.getFloat(7),
                        result.getFloat(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return bills;
        }
    }

    public Vector<RatePlan> getRateplans() {
        Vector<RatePlan> ratePlans = new Vector();
        try {
            connect();
            sqlcommand = " select * from rateplan";
            preparedstatement = connection.prepareStatement(sqlcommand);
            result = preparedstatement.executeQuery();
            while (result.next()) {
                ratePlans.add(new RatePlan(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getFloat(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            stop();
            return ratePlans;
        }
    }

    public void addcustomerMonthlyUnits(int customerID, int rid, int units) {
        connect();

        try {
            sqlcommand = "select * from customer_units where customer_id=" + customerID + "and rateplan_id=" + rid;
            preparedstatement = connection.prepareStatement(sqlcommand);
            Statement stmt = connection.createStatement();
            result = preparedstatement.executeQuery();
            if (result.next()) {
                stmt.executeUpdate("update customer_units set remaining_units=" + units);
            } else {
                sqlcommand = "insert into customer_units values(?,?,?)";
                preparedstatement = connection.prepareStatement(sqlcommand);
                preparedstatement.setInt(1, customerID);
                preparedstatement.setInt(2, rid);
                preparedstatement.setInt(3, units);
                preparedstatement.execute();
                System.out.println("Units added Successfully" + result);
            }
        } catch (SQLException ex) {
            System.out.println("Something wrong happened");
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }

        stop();
    }

    public int getRateplanUnits(int rid) {
        int units = 0;
        try {
            connect();
            sqlcommand = " select * from rateplan WHERE rateplan_id =" + rid;
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
    
    

}
