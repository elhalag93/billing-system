package webApp;
/**
 *
 * @author mo
 */
public class ServicePackage {
    int servicePkg_id;
    float cost_W_orgEQdest; //weekend orgin=destination
    float cost_W_orgNEQdest; //weekend orgin!=destination
    float cost_D_orgEQdest; //weekday orgin=destination
    float cost_D_orgNEQdest; //weekday orgin!=destination

    public ServicePackage(int servicePkg_id, float cost_W_orgEQdest, float cost_W_orgNEQdest, float cost_D_orgEQdest, float cost_D_orgNEQdest) {
        this.servicePkg_id = servicePkg_id;
        this.cost_W_orgEQdest = cost_W_orgEQdest;
        this.cost_W_orgNEQdest = cost_W_orgNEQdest;
        this.cost_D_orgEQdest = cost_D_orgEQdest;
        this.cost_D_orgNEQdest = cost_D_orgNEQdest;
    }



    ServicePackage() {
       
    }

    public int getServicePkg_id() {
        return servicePkg_id;
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

    
    
    
    
    
    public void setServicePkg_id(int servicePkg_id) {
        this.servicePkg_id = servicePkg_id;
    }

    public void setCost_W_orgEQdest(float cost_W_orgEQdest) {
        this.cost_W_orgEQdest = cost_W_orgEQdest;
    }

    public void setCost_W_orgNEQdest(float cost_W_orgNEQdest) {
        this.cost_W_orgNEQdest = cost_W_orgNEQdest;
    }

    public void setCost_D_orgEQdest(float cost_D_orgEQdest) {
        this.cost_D_orgEQdest = cost_D_orgEQdest;
    }

    public void setCost_D_orgNEQdest(float cost_D_orgNEQdest) {
        this.cost_D_orgNEQdest = cost_D_orgNEQdest;
    }
    
    
}

