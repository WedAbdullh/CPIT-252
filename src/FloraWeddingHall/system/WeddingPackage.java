
package FloraWeddingHall.system;

public class WeddingPackage extends Package {

    public WeddingPackage() {
        this.setPackageName("Wedding Package"); 
        this.setPackageDescription("Comprehensive wedding services including venue, catering, and decoration.");
        this.setPackagePrice(35000);
        addService("Venue Rental", 2000);
        addService("Catering Service", 1500);
        addService("Decoration", 1000);
    }
    
    @Override
    public String getDetails() {
        return "Wedding Package\n"
                + "-----------------\n"
                + "Description: " + packageDescription + "\n"
                + "Base Price: SAR" + packagePrice + "\n"
                + "Included Services:\n"
                + "- Venue Rental (SAR2000)\n"
                + "- Catering Service (SAR1500)\n"
                + "- Decoration (SAR1000)\n"
                + "This package provides a complete wedding arrangement with top-notch services to make your day memorable.";
    }
}