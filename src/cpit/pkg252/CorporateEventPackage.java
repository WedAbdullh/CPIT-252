
package cpit.pkg252;

public class CorporateEventPackage extends Package {

    public CorporateEventPackage() {
        this.packageName = "Corporate Event Package";
        this.packageDescription = "Professional services for corporate events including AV setup, catering, and venue.";
        this.packagePrice = 3000; // Base price
        addService("AV Setup", 1200);
        addService("Catering Service", 800);
        addService("Venue Rental", 1000);
    }
    
    @Override
    public String getDetails() {
        return "Corporate Event Package\n"
                + "-------------------------\n"
                + "Description: " + packageDescription + "\n"
                + "Base Price: $" + packagePrice + "\n"
                + "Included Services:\n"
                + "- AV Setup ($1200)\n"
                + "- Catering Service ($800)\n"
                + "- Venue Rental ($1000)\n"
                + "Ideal for professional gatherings, this package ensures a smooth and successful corporate event.";
    }
}
