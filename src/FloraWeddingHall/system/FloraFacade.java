package FloraWeddingHall.system;

import java.sql.SQLException;

public class FloraFacade {

    private Database database;
    private PackageFactory packageFactory;
    private PaymentStrategy paymentStrategy;
    private Customer customer;
    private SingletonManager manager;
    private BookingProxy bookingProxy;

    public FloraFacade() {
        this.database = new Database();
        this.packageFactory = new PackageFactory();
        this.bookingProxy = new BookingProxy();
    }

    // Authentication Methods
    public boolean logIn(String email, String password) {
        if (email.contains("@FloaWeddingHall.com")) {
            return SingletonManager.logInManager(email, password);
        }
        return Database.loginCustomer(email, password);
    }

//    public boolean logIn(String email, String password) {
//        // Check if the user is a Manager
//        if (email.contains("@FloaWeddingHall.com")) {
//            if (SingletonManager.logInManager(email, password)) {
//                manager = SingletonManager.getInstance();
//                return true;
//            } else {
//                return false;
//            }
//        }
//
//        // Check if the user is a Customer
//        if (Database.loginCustomer(email, password)) {
//            // Fetch customer details from the database
//            customer = Database.getCustomerByEmail(email);
//            return true;
//        }
//
//        return false;
//    }
    // Customer sign-up process
    public boolean signUp(String name, String phone, String email, String password) {
        return Database.registerCustomer(name, phone, email, password);

//        if (Database.getCustomerByEmail(email) != null) {
//            return false;
//        }
//
//        // Register customer in the database
//        boolean isRegistered = Database.registerCustomer(name, phone, email, password);
//        return isRegistered;
    }

    // --------- Package Management Methods -------- 
//    this acts as an interface for the GUI to get package details without exposing the Package object directly.
    public String[] fetchPackageDetails(String packageType) {
        return getPackageDetails(packageType);
    }

    public String[] getPackageDetails(String packageType) {
        System.out.println("Requested package type: " + packageType); // Debugging line

        Package selectedPackage = PackageFactory.createPackage(packageType);

        if (selectedPackage == null) {
            System.out.println("Package not found for: " + packageType); // Debugging line
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

    // --------- Booking Management Methods -------- 
    public boolean createBooking(int customerId, String username, String selectedPackage, String bookingDate, String paymentMethod) {

// Call the existing createBooking method in BookingProxy (or equivalent class)
        bookingProxy.createBooking(customerId, username, selectedPackage, bookingDate, paymentMethod);
        return true;
    }

    // --------- Payment Management Methods -------- 
    // Payment Processing
    public boolean processPayment(String paymentMethod, double amount) {
        PaymentStrategy paymentStrategy;

        // Determine the payment strategy
        switch (paymentMethod.toLowerCase()) {
            case "cash":
                paymentStrategy = new CashPayment();
                break;
            case "paypal":
                paymentStrategy = new PayPalPayment();
                break;
            case "applepay":
                paymentStrategy = new ApplePayPayment();
                break;
            default:
                System.out.println("Invalid payment method.");
                return false; // Payment failed
        }

        // Execute the payment
        paymentStrategy.pay(amount);
        return true; // Payment successful
    }

    public boolean addPackageToDatabase(String name, String description, String services, String servicesPrices, double totalPrice) {
        try {
            database.insertNewPackage(name, description, services, servicesPrices, totalPrice);
            return true;
        } catch (SQLException ex) {
            ex.getMessage();
            return false;
        }
    }

    public Object[][] getAllPackages() {
        try {
            return database.getAllPackages(); // Call the database method
        } catch (SQLException ex) {
            System.out.println("Error fetching packages: " + ex.getMessage());
            return new Object[0][0]; // Return an empty array on error
        }
    }

    public Object[][] getAllBookings() {
        try {
            return database.getAllBookings(); // Call the database method
        } catch (SQLException ex) {
            System.out.println("Error fetching bookings: " + ex.getMessage());
            return new Object[0][0]; // Return an empty array on error
        }
    }

    public Object[][] getAllCustomers() {
        try {
            return database.getAllCustomers(); // Call the database method
        } catch (SQLException ex) {
            System.out.println("Error fetching customers: " + ex.getMessage());
            return new Object[0][0]; // Return an empty array on error
        }
    }

}
