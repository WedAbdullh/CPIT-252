package FloraWeddingHall.system;

class PackageFactory {

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
