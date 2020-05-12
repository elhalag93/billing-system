/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratingengine;

import cdrparser.CDR;
import java.util.Vector;
import java.util.regex.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author MohabOmar
 */
public class RatingEngine {

    public static Vector<CDR> unratedCdrs = new Vector();
    private static Database database = new Database();
    private static RatingFees ratingFees;

    /**
     *
     * @param c
     * @param cdr
     */
    private static void rateData(Customer c, CDR cdr) {
        float externalRating = cdr.getExternalRating();
        if (database.getRemainingUnits(c.getCustomerID()) > externalRating) {
            database.reduceRemainingUnits(c.getCustomerID(), Math.round(externalRating));
        } else {
            database.updateInternalRating(cdr.getCdrID(), externalRating);
        }
    }

    private static void rateVoice(Customer c, CDR cdr) throws ParseException {
        float cost;
        int cost_in_felix;
        //in the same network
        if (is_same_network(cdr.getDestination())) {
            System.out.println("1");
            //in week end
            if (is_weekend(cdr.getStartDate())) {
                System.out.println("2");
                cost = Math.round(cdr.getDuration_message_volume()) * ratingFees.cost_W_orgEQdest;
                cost_in_felix = Math.round(cdr.getDuration_message_volume()) * 1;
                //update
                if (database.getRemainingUnits(c.getCustomerID()) > Math.round(cost_in_felix)) {
                    database.reduceRemainingUnits(c.getCustomerID(), Math.round(cost_in_felix));

                } else {
                    System.out.println("3");
                    int balance = database.getRemainingUnits(c.getCustomerID());
                    int reminder = cost_in_felix - database.getRemainingUnits(c.getCustomerID());
                    database.reduceRemainingUnits(c.getCustomerID(), balance);
                    database.updateInternalRating(cdr.getCdrID(), reminder * ratingFees.cost_W_orgEQdest);

                }

            } //in week day
            else {
                System.out.println("4");
                cost = Math.round(cdr.getDuration_message_volume()) * ratingFees.cost_D_orgEQdest;
                cost_in_felix = Math.round(cdr.getDuration_message_volume()) * 1;
                //update
                if (database.getRemainingUnits(c.getCustomerID()) > Math.round(cost_in_felix)) {
                    System.out.println("5");
                    database.reduceRemainingUnits(c.getCustomerID(), Math.round(cost_in_felix));

                } else {
                    int balance = database.getRemainingUnits(c.getCustomerID());
                    int reminder = cost_in_felix - database.getRemainingUnits(c.getCustomerID());
                    database.reduceRemainingUnits(c.getCustomerID(), balance);
                    database.updateInternalRating(cdr.getCdrID(), reminder * ratingFees.cost_D_orgEQdest);
                    System.out.println("6");
                }
            }
        } // in different network
        else {
            //in week end
            if (is_weekend(cdr.getStartDate())) {
                System.out.println("7");
                cost = Math.round(cdr.getDuration_message_volume()) * ratingFees.cost_W_orgNEQdest;
                cost_in_felix = Math.round(cdr.getDuration_message_volume()) * 5;
                //update
                if (database.getRemainingUnits(c.getCustomerID()) > Math.round(cost_in_felix)) {
                    System.out.println("8");
                    database.reduceRemainingUnits(c.getCustomerID(), Math.round(cost_in_felix));

                } else {
                    System.out.println("9");
                    int balance = database.getRemainingUnits(c.getCustomerID());
                    int reminder = cost_in_felix - database.getRemainingUnits(c.getCustomerID());
                    database.reduceRemainingUnits(c.getCustomerID(), balance);
                    database.updateInternalRating(cdr.getCdrID(), reminder * ratingFees.cost_W_orgNEQdest);

                }

            } //in week day
            else {
                System.out.println("10");
                cost = Math.round(cdr.getDuration_message_volume()) * ratingFees.cost_D_orgNEQdest;
                cost_in_felix = Math.round(cdr.getDuration_message_volume()) * 5;
                //update
                if (database.getRemainingUnits(c.getCustomerID()) > Math.round(cost_in_felix)) {
                    System.out.println("11");
                    database.reduceRemainingUnits(c.getCustomerID(), Math.round(cost_in_felix));

                } else {
                    System.out.println("12");
                    int balance = database.getRemainingUnits(c.getCustomerID());
                    int reminder = cost_in_felix - database.getRemainingUnits(c.getCustomerID());
                    database.reduceRemainingUnits(c.getCustomerID(), balance);
                    database.updateInternalRating(cdr.getCdrID(), reminder * ratingFees.cost_D_orgNEQdest);

                }
            }
        }
        /////////up date in data base

    }

    private static void rateSms(Customer c, CDR cdr) {

        float cost;
        float cost_in_felix = 0;
        if (is_same_network(cdr.getDestination())) {
            cost = cdr.getDuration_message_volume() * 1;
            //update
            if (database.getRemainingUnits(c.getCustomerID()) > Math.round(cost_in_felix)) {
                System.out.println("13");
                database.reduceRemainingUnits(c.getCustomerID(), Math.round(cost_in_felix));

            } else {
                System.out.println("14");
                int balance = database.getRemainingUnits(c.getCustomerID());
                int reminder = (int) (cost_in_felix - database.getRemainingUnits(c.getCustomerID()));
                database.reduceRemainingUnits(c.getCustomerID(), balance);
                database.updateInternalRating(cdr.getCdrID(), reminder * ratingFees.cost_D_orgEQdest);

            }

        } else {
            cost = cdr.getDuration_message_volume() * 5;
            //update
            if (database.getRemainingUnits(c.getCustomerID()) > Math.round(cost_in_felix)) {
                database.reduceRemainingUnits(c.getCustomerID(), Math.round(cost_in_felix));

            } else {
                int balance = database.getRemainingUnits(c.getCustomerID());
                int reminder = (int) (cost_in_felix - database.getRemainingUnits(c.getCustomerID()));
                database.reduceRemainingUnits(c.getCustomerID(), balance);
                database.updateInternalRating(cdr.getCdrID(), reminder * ratingFees.cost_D_orgNEQdest);

            }
        }
    }

    public static void main(String[] args) throws ParseException {

        unratedCdrs = database.getUnratedCdrs();
        System.out.println(unratedCdrs.size());
        for (CDR unratedCdr : unratedCdrs) {
            String origin = unratedCdr.getOrigin() + "";
            String serviceID = unratedCdr.getServiceID() + "";
            Customer customer = database.getCustomer(origin);
            System.out.println(customer.getRatePlane_id());
            String customerRateplan = customer.getRatePlane_id() + "";
            switch (unratedCdr.getServiceID()) {
                case 1:
                    ratingFees = database.getRatingFees(serviceID, customerRateplan);
                    System.out.println("voice");
                    rateVoice(customer, unratedCdr);
                    System.out.println(ratingFees.getCost_D_orgEQdest());
                    System.out.println(ratingFees.getCost_D_orgNEQdest());
                    System.out.println(ratingFees.getCost_W_orgEQdest());
                    System.out.println(ratingFees.getCost_W_orgNEQdest());
                    break;
                case 2:
                    System.out.println("message");
                    rateSms(customer, unratedCdr);
                    break;
                case 3:
                    rateData(customer, unratedCdr);
                    break;
                default:
                    break;
            }
        }

    }

    static boolean is_same_network(String des) {

        Pattern pattern = Pattern.compile("...012........");
        //number to check des
        Matcher matcher = pattern.matcher(des);
        boolean matches = matcher.matches();
        return matches;

    }

    static boolean is_weekend(String day) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(day);
        String which_day = new SimpleDateFormat("EEE").format(date);
        boolean flag;
        if (!(which_day.equals("Fri") || which_day.equals("Sat"))) {

            flag = false;
        } else {
            flag = true;
        }

        return flag;
    }

}
