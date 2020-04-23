/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdrparser;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author MohabOmar
 */
public class CDR {
    
    private long origin;
    private long destination;
    private int serviceID;
    private int duration_message_volume;
    private String startDate;
    private Time startTime;
    private float externalRating;
    private float internalRating;
    private boolean isRated;

    public CDR(long origin, long destination, int serviceID, int duration_message_volume, String startDate, Time startTime, float externalRating, float internalRating, boolean isRated) {
        this.origin = origin;
        this.destination = destination;
        this.serviceID = serviceID;
        this.duration_message_volume = duration_message_volume;
        this.startDate = startDate;
        this.startTime = startTime;
        this.externalRating = externalRating;
        this.internalRating = internalRating;
        this.isRated = isRated;
    }


    public long getOrigin() {
        return origin;
    }

    public long getDestination() {
        return destination;
    }

    public int getServiceID() {
        return serviceID;
    }

    public int getDuration_message_volume() {
        return duration_message_volume;
    }

    public String getStartDate() {
        return startDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public float getExternalRating() {
        return externalRating;
    }

    public float getInternalRating() {
        return internalRating;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setOrigin(long origin) {
        this.origin = origin;
    }

    public void setDestination(long destination) {
        this.destination = destination;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public void setDuration_message_volume(int duration_message_volume) {
        this.duration_message_volume = duration_message_volume;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setExternalRating(float externalRating) {
        this.externalRating = externalRating;
    }

    public void setInternalRating(float internalRating) {
        this.internalRating = internalRating;
    }

    public void setIsRated(boolean isRated) {
        this.isRated = isRated;
    }
    
    
    
}
