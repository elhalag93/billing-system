
package webApp;

/**
 *
 * @author mo
 */
public class RatePlan {
    int ratePlane_id;
    int freeUnits;
    float monthlyFee;

    public RatePlan(int ratePlane_id, int freeUnits, float monthlyFee) {
        this.ratePlane_id = ratePlane_id;
        this.freeUnits = freeUnits;
        this.monthlyFee = monthlyFee;
    }

    RatePlan() {
        
    }

    
    
    
    public int getRatePlane_id() {
        return ratePlane_id;
    }

    public int getFreeUnits() {
        return freeUnits;
    }

    public float getMonthlyFee() {
        return monthlyFee;
    }

    
    
    
    
    public void setRatePlane_id(int ratePlane_id) {
        this.ratePlane_id = ratePlane_id;
    }

    public void setFreeUnits(int freeUnits) {
        this.freeUnits = freeUnits;
    }

    public void setMonthlyFee(float monthlyFee) {
        this.monthlyFee = monthlyFee;
    }




    
    
   
}
