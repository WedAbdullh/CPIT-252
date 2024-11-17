package FloraWeddingHall.system;

public class PackageFactory {
/* The PackageFactory is responsible for package creation
    and details management. It contains the logic to create package 
    objects and fetch specific details. */
    
    public static Package createPackage(String type) {

        if (type.equalsIgnoreCase("Wedding")) {
            return new WeddingPackage();
        } else if (type.equalsIgnoreCase("Corporate")) {
            return new CorporateEventPackage();
        } else if (type.equalsIgnoreCase("Birthday")) {
            return new BirthdayPartyPackage();
        } else {
            return null;
        }

    }
    

      
}
