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
        // Check if the user is a Manager
        if (email.contains("@FloaWeddingHall.com")) {
            if (SingletonManager.logInManager(email, password)) {
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

        if (Database.getCustomerByEmail(email) != null) {
            return false;
        }

        // Register customer in the database
        return  Database.registerCustomer(name, phone, email, password);
         
    }

    // --------- Package Management Methods -------- 
//    this acts as an interface for the GUI to get package details without exposing the Package object directly.
    public String[] fetchPackageDetails(String packageType) {
        return getPackageDetails(packageType);
    }

    public String[] getPackageDetails(String packageType) {
//        System.out.println("Requested package type: " + packageType); // Debugging line

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
    public void createBooking(int customerId, String username, String packageid, String bookingDate, String paymentMethod) {
       try{
     // Call the existing createBooking method in BookingProxy
        bookingProxy.createBooking(customerId, username, packageid, bookingDate, paymentMethod);
        System.out.println("Booking successfully created for user: " + username);

       }catch (Exception e) {
            System.err.println("Error creating booking: " + e.getMessage());
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

    // --------- Payment Management Methods -------- 

        public boolean processPayment(String paymentMethod, double amount) {
        PaymentStrategy paymentStrategy = null;

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

 // --------- Packages Management Methods -------- 

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

   

 

}
