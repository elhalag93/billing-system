

package classes;

/**
 *
 * @author mo
 */
public class Services {
    int serviceID;
    String serviceName,serviceType;
    float fees;

    public Services(int serviceID, String serviceName, String serviceType) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
    }

    public Services(int serviceID, String serviceName, String serviceType, float fees) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.serviceType = serviceType;
        this.fees = fees;
    }

    public float getFees() {
        return fees;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }
    

    
    
    public Services(String serviceName, String serviceType) {
        this.serviceName = serviceName;
        this.serviceType = serviceType;
    }    

    public Services() {
      
    }

 
    
    
    
    
    
    public int getServiceID() {
        return serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }    
    
    
    
    public void setServiceID(int _serviceID) {
        this.serviceID = _serviceID;
    }

    public void setServiceName(String _serviceName) {
        this.serviceName = _serviceName;
    }



    public void setServiceType(String _serviceType) {
        this.serviceType = _serviceType;
    }    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  
}


