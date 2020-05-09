/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ratingengine;

/**
 *
 * @author MohabOmar
 */
public class RatingFees {

    int ratePlane_id;
    int servicePkg_id;
    int freeUnits;
    float monthlyFee;
    int serviceID;
    float cost_W_orgEQdest; //weekend orgin=destination
    float cost_W_orgNEQdest; //weekend orgin!=destination
    float cost_D_orgEQdest; //weekday orgin=destination
    float cost_D_orgNEQdest; //weekday orgin!=destination

    public RatingFees(int ratePlane_id, int servicePkg_id, int freeUnits, float monthlyFee, int serviceID, float cost_W_orgEQdest, float cost_W_orgNEQdest, float cost_D_orgEQdest, float cost_D_orgNEQdest) {
        this.ratePlane_id = ratePlane_id;
        this.servicePkg_id = servicePkg_id;
        this.freeUnits = freeUnits;
        this.monthlyFee = monthlyFee;
        this.serviceID = serviceID;
        this.cost_W_orgEQdest = cost_W_orgEQdest;
        this.cost_W_orgNEQdest = cost_W_orgNEQdest;
        this.cost_D_orgEQdest = cost_D_orgEQdest;
        this.cost_D_orgNEQdest = cost_D_orgNEQdest;
    }

    public int getRatePlane_id() {
        return ratePlane_id;
    }

    public int getServicePkg_id() {
        return servicePkg_id;
    }

    public int getFreeUnits() {
        return freeUnits;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    public int getServiceID() {
        return serviceID;
    }

    public float getCost_W_orgEQdest() {
        return cost_W_orgEQdest;
    }

    public float getCost_W_orgNEQdest() {
        return cost_W_orgNEQdest;
    }

    public float getCost_D_orgEQdest() {
        return cost_D_orgEQdest;
    }

    public float getCost_D_orgNEQdest() {
        return cost_D_orgNEQdest;
    }
    
    

}
