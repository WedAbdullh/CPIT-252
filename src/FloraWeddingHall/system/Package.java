package FloraWeddingHall.system;

import java.util.ArrayList;
import java.util.List;

public abstract class Package {

    protected String packageId;
    protected String packageDescription;
    protected String packageName;
    protected double packagePrice;
    protected List <String> includedServices = new ArrayList<>();
    protected List <String> servicesPrices = new ArrayList<>();

    public Package() {
    }
    
    public abstract String getDetails();

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setPackagePrice(double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public void addService(String service, double price) {
        this.includedServices.add(service);
        this.servicesPrices.add(price + "");
        
        this.packagePrice += price;
    }
    
    public String getIncludedServices() {
        String services = "";
        for (int i = 0; i < includedServices.size() - 1; i++) {
            services += includedServices.get(i) + ", ";
        }
        services += includedServices.get(includedServices.size() - 1);
        return services;     
    }
    
    public String getServicesPrices() {
        String prices = "";
        for (int i = 0; i < servicesPrices.size() - 1; i++) {
            prices += servicesPrices.get(i) + ", ";
        }
        prices += servicesPrices.get(servicesPrices.size() - 1);
        
        return prices;     
    }

    public boolean deleteService(String service, int price) {
        packagePrice -= price;
        return this.includedServices.remove(service);
    }

    public String getPackageDescription() {
        return this.packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getPackageId() {
        return this.packageId;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public double getPackagePrice() {
        return this.packagePrice;
    }
}





