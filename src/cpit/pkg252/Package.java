
package cpit.pkg252;

import java.util.ArrayList;
import java.util.List;

public class Package {

    private String packageId;
    private String packageDescription;
    private String packageName;
    private double packagePrice;
    private List <String> includedServices = new ArrayList <String> ();

    public Package() {
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public void addService(String service, int price) {
        includedServices.add(service);
        packagePrice += price;
    }

    public List<String> getIncludedServices() {
        return includedServices;
    }

    public boolean deleteService(String service, int price) {
        packagePrice -= price;
        return includedServices.remove(service);
    }
    
    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public double getPackagePrice() {
        return packagePrice;
    }
    
    @Override
    public String toString() {
        String s = "";
        s = s + packageName + "; \n";
        s = s + packageDescription + " \n";
        s = s + "Price: " + packagePrice + " \n";
        s = s + "Included Services: \n";
        
        for (String service: includedServices){
            s = s + service + " \n";
        }
        
        return s;
    }
}

