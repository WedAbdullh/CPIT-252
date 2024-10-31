
package cpit.pkg252;

public class WeddingPackage extends Package {

    public WeddingPackage() {
        this.packageName = "Wedding Package";
        this.packageDescription = "Comprehensive wedding services including venue, catering, and decoration.";
        this.packagePrice = 5000; // Base price
        addService("Venue Rental", 2000);
        addService("Catering Service", 1500);
        addService("Decoration", 1000);
    }
    
    @Override
    public String getDetails() {
        return "Wedding Package\n"
                + "-----------------\n"
                + "Description: " + packageDescription + "\n"
                + "Base Price: $" + packagePrice + "\n"
                + "Included Services:\n"
                + "- Venue Rental ($2000)\n"
                + "- Catering Service ($1500)\n"
                + "- Decoration ($1000)\n"
                + "This package provides a complete wedding arrangement with top-notch services to make your day memorable.";
    }
}