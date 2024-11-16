
package FloraWeddingHall.system;

public class BirthdayPartyPackage extends Package {

    public BirthdayPartyPackage() {
        this.packageName = "Birthday Party Package";
        this.packageDescription = "Fun and exciting services for birthday parties including entertainment and catering.";
        this.packagePrice = 27000; // Base price
        addService("Entertainment", 800);
        addService("Catering Service", 700);
        addService("Venue Rental", 500);
    }
    
    @Override
    public String getDetails() {
        return "Birthday Party Package\n"
                + "------------------------\n"
                + "Description: " + packageDescription + "\n"
                + "Base Price: SAR" + packagePrice + "\n"
                + "Included Services:\n"
                + "- Entertainment (SAR800)\n"
                + "- Catering Service (SAR700)\n"
                + "- Venue Rental (SAR500)\n"
                + "This package provides everything you need for a fun and memorable birthday celebration.";
    }
}