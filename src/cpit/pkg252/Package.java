package cpit.pkg252;

import java.util.ArrayList;
import java.util.List;

public abstract class Package {

    protected String packageId;
    protected String packageDescription;
    protected String packageName;
    protected double packagePrice;
    protected List<String> includedServices = new ArrayList<>();

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
        StringBuilder s = new StringBuilder();
        s.append(packageName).append("; \n");
        s.append(packageDescription).append(" \n");
        s.append("Price: ").append(packagePrice).append(" \n");
        s.append("Included Services: \n");

        for (String service : includedServices) {
            s.append(service).append(" \n");
        }

        return s.toString();
    }
}





