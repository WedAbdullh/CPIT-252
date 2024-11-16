package FloraWeddingHall.system;

import java.util.List;

public class FloraFacade {

    //private BookingProxy bookingProxy;
    private Database database;
    private PackageFactory packageFactory;
    private PaymentStrategy paymentStrategy;
    private Customer customer;
    private SingletonManager manager;

    public FloraFacade() {
        this.database = new Database();
        this.packageFactory = new PackageFactory();
    }

    public boolean logIn(String email, String password) {
        // Check if the user is a Manager
        if (email.contains("@FloaWeddingHall.com")) {
            if(SingletonManager.logInManager(email, password)){
                manager = SingletonManager.getInstance();
                return true;
            } else {
                return false;
            }
        }
        
        // Check if the user is a Customer
        if (Database.loginCustomer(email, password)) {
            // Fetch customer details from the database
            customer = Database.getCustomerByEmail(email); 
            return true;
        }
        
        return false;
    }

    // Customer sign-up process
    public boolean signUp(String name, String phone, String email, String password) {
        if(Database.getCustomerByEmail(email) != null){
            return false;
        }
        
        // Register customer in the database
        boolean isRegistered = Database.registerCustomer(name, phone, email, password);
        return isRegistered;
    }
    
    public String[] getPackageDetails(String packageType) {
        Package selectedPackage = PackageFactory.createPackage(packageType);

        if (selectedPackage == null) {
            return null; // Return null if no package is found
        }

        // Prepare package details as an array
        String[] details = new String[5];
        details[0] = selectedPackage.getPackageName();
        details[1] = selectedPackage.getPackageDescription();
        details[2] = String.valueOf(selectedPackage.getPackagePrice());
        details[3] = selectedPackage.getIncludedServices();
        details[4] = selectedPackage.getServicesPrices();

        return details;
    }
    
    public List<String> getBookings() {
        return Database.viewBookings();
    }
}

//
//    public FloraFacade() {
//        this.bookingProxy = new BookingProxy();
//        this.bookingDatabase = new BookingDatabase();
//        this.packageFactory = new PackageFactory();
//    }
//
//    // Method to create a new booking
//    public boolean createBooking(String customerId, String packageType, String paymentMethod) {
//        Package selectedPackage = packageFactory.createPackage(packageType);
//        if (selectedPackage == null) {
//            System.out.println("Package type not found.");
//            return false;
//        }
//
//        RealBooking booking = new RealBooking(customerId, selectedPackage);
//        boolean isBooked = bookingProxy.addBooking(booking);
//        if (!isBooked) {
//            System.out.println("Booking could not be completed.");
//            return false;
//        }
//        
//        Payment payment = createPayment(paymentMethod);
//        if (payment != null) {
//            paymentStrategy = new PaymentStrategy(payment);
//            paymentStrategy.pay(booking.getPackage().getPackagePrice());
//        } else {
//            System.out.println("Invalid payment method.");
//            return false;
//        }
//
//        System.out.println("Booking and payment successful!");
//        return true;
//    }
//
//    public List<String> getAvailablePackages() {
//        return packageFactory.getAvailablePackageTypes();
//    }
//
//    public List<RealBooking> getBookings(String customerId) {
//        return bookingDatabase.getBookingsForUser(customerId);
//    }
//
//    private Payment createPayment(String paymentType) {
//        switch (paymentType.toLowerCase()) {
//            case "cash":
//                return new CashPayment();
//            case "applepay":
//                return new ApplePayPayment();
//            case "paypal":
//                return new PayPalPayment();
//            default:
//                return null;
//        }
//    }

