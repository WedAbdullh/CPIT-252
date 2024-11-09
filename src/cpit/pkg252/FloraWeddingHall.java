package cpit.pkg252;

import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FloraWeddingHall {

    // Scanner object for user input
    private static final Scanner scanner = new Scanner(System.in);
    private static AlertSystem alertSystem = new AlertSystem();

    // Method to display the initial entry menu for user actions
    private static void showEntryMenu() {
        while (true) {
            // Display menu options
            System.out.println("\n1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            try {
                // Get user choice
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                // Execute the corresponding action based on user choice
                switch (choice) {
                    case 1:
                        registerNewCustomer();  // Register a new customer
                        break;
                    case 2:
                        handleLogin();  // Handle user login
                        break;
                    case 3:
                        // Exit the application
                        System.out.println("Thank you for visiting Flora Wedding Hall!");
                        System.exit(0);
                    default:
                        // Handle invalid option
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle non-integer input
                System.out.println("Please enter a valid integer option.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    private static void registerNewCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Validate the customer input
        if (Customer.validateCustomer(name, phone, email, password)) {
            // Create a new Customer object with provided values
            Customer newCustomer = new Customer(name, phone, email, password);

            // Attempt to register the new customer and show the main menu if successful
            if (newCustomer.registerCustomer()) {
                alertSystem.attach(newCustomer); // Attach customer to alert system
                System.out.println("Registration successful!"); // Optional: Show success message
                showMainMenu();
            } else {
                System.out.println("Registration failed. Please try again.");
            }
        } else {
            System.out.println("Invalid input. Please check your details and try again.");
        }
    }


// Method to handle customer or manager login
private static void handleLogin() {
        // Ask user to choose their type (Customer or Manager)
        String userType = chooseUserType();

        // Handle login based on the selected user type
        if ("Customer".equals(userType)) {
            // Login as a Customer
            Customer existingCustomer = new Customer("", "", "", "");
            Customer loggedInCustomer = existingCustomer.loginCustomer(scanner);

            if (loggedInCustomer == null) {
                System.out.println("Login failed. Returning to the main menu.");
            } else {
                // Attach customer to alert system and retrieve alerts from the database
                alertSystem.attach(loggedInCustomer);

                // Retrieve and display alerts for the logged-in customer
                String alerts = Database.getCustomerAlerts(loggedInCustomer.getEmail());
                if (alerts != null && !alerts.isEmpty()) {
                    System.out.println("Alerts for you: " + alerts);
                } else {
                    System.out.println("No new alerts.");
                }

                showMainMenu();  // Show the main menu if login is successful
            }
        } else if ("Manager".equals(userType)) {
            // Login as a Manager
            Manager loggedInManager = Manager.loginManager(scanner);

            if (loggedInManager == null) {
                System.out.println("Login failed. Returning to the main menu.");
            } else {
                showManagerMenu();  // Show the manager menu if login is successful
            }
        }
    }

    // Method to prompt the user to select their type for login
    private static String chooseUserType() {
        while (true) {
            // Display options for user type
            System.out.println("Login as:");
            System.out.println("1. Customer");
            System.out.println("2. Manager");
            System.out.print("Select an option: ");

            try {
                int loginChoice = scanner.nextInt();
                scanner.nextLine();  // Consume newline
                switch (loginChoice) {
                    case 1:
                        return "Customer";  // Return "Customer" if selected
                    case 2:
                        return "Manager";  // Return "Manager" if selected
                    default:
                        // Handle invalid choice
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle non-integer input
                System.out.println("Please enter a valid integer.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    // Method to display the main menu for customers
    private static void showMainMenu() {
        while (true) {
            // Display main menu options
            System.out.println("\n1. View Packages");
            System.out.println("2. Book a Hall");
            System.out.println("3. Logout");
            System.out.print("Select an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                // Execute corresponding action based on choice
                switch (choice) {
                    case 1:
                        viewPackages();  // View available packages
                        break;
                    case 2:
                        bookHall();  // Book a hall (functionality to be implemented)
                        break;
                    case 3:
                        // Logout and return to the entry menu
                        System.out.println("Logging out...");
                        return;  // Return to entry menu
                    default:
                        // Handle invalid option
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle non-integer input
                System.out.println("Please enter a valid integer.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }
// Method to display the manager menu

    private static void showManagerMenu() {
        AlertSystem alertSystem = new AlertSystem(); // Create the alert system

//        // Get the singleton instance of Manager (login is handled here)
//        Manager loggedInManager = Manager.getInstance(scanner);  // Retrieve the singleton instance, or use the logged-in manager
//
//        // Check if login was successful
//        if (loggedInManager == null) {
//            System.out.println("Login failed. Returning to the main menu.");
//            return;  // Return to the main menu if login fails
//        }
        // Proceed with the menu if login is successful
        while (true) {
            // Display manager menu options
            System.out.println("\n1. View Packages");
            System.out.println("2. View Customers");
            System.out.println("3. View Bookings");
            System.out.println("4. Add New Package");
            System.out.println("5. Send Alert");
            System.out.println("6. Logout");
            System.out.print("Select an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                // Execute corresponding action based on choice
                switch (choice) {
                    case 1:
                        viewPackages();  // View available packages
                        break;
                    case 2:
                        viewCustomers();  // View customer details
                        break;
                    case 3:
                        viewBookings();  // View booking details
                        break;
                    case 4:
                        addNewPackage();  // Add a new package (functionality to be implemented)
                        break;
                    case 5:
                        // Send alert using the Singleton instance of Manager
                       // loggedInManager.sendAlert(alertSystem, scanner);  // Send alert to all customers
                        break;
                    case 6:
                        // Logout and return to the entry menu
                        System.out.println("Logging out...");
                        return;  // Return to entry menu
                    default:
                        // Handle invalid option
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                // Handle non-integer input
                System.out.println("Please enter a valid integer.");
                scanner.nextLine();  // Clear invalid input
            }
        }
    }

    // Method to view available packages
    private static void viewPackages() {
        while (true) {
            // Display available package options
            System.out.println("\nAvailable Packages:");
            System.out.println("1. Wedding");
            System.out.println("2. Corporate");
            System.out.println("3. Birthday");
            System.out.println("4. Cancel");
            System.out.print("Type the package name to view details: ");

            String packageType = scanner.nextLine().trim();  // Get user input

            // Check if user wants to cancel
            if ("Cancel".equalsIgnoreCase(packageType)) {
                return;  // Return to previous menu
            }

            if (packageType.equalsIgnoreCase("Wedding") | packageType.equalsIgnoreCase("Corporate") | packageType.equalsIgnoreCase("Birthday")) {
                // Attempt to create a package based on user input
                Package selectedPackage = PackageFactory.createPackage(packageType);
                System.out.println(selectedPackage.getDetails());  // Display package details
                break;  // Exit the loop after successful package view
            } else {
                // Handle invalid package type input
                System.out.println("Invalid package type. Please enter a valid package name.");
            }
        }
    }

    // Placeholder method for hall booking functionality
    private static void bookHall() {
        System.out.println("Booking functionality goes here.");  // Placeholder for method implementation
    }

    // Payment method using Strategy Pattern
    public static void processPayment(double amount) {
        System.out.println("\nChoose a payment method: ");
        System.out.println("1. PayPal");
        System.out.println("2. Apple Pay");
        System.out.println("3. Cash");
        System.out.println("4. Cancel");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            PaymentStrategy paymentStrategy = null;  // Variable to hold the chosen payment strategy
            switch (choice) {
                case 1:
                    // Get PayPal email from user
                    System.out.print("Enter your PayPal email: ");
                    paymentStrategy = new PayPalPayment(scanner.nextLine());
                    break;
                case 2:
                    // Get Apple Pay account from user
                    System.out.print("Enter your Apple Pay account: ");
                    paymentStrategy = new ApplePayPayment(scanner.nextLine());
                    break;
                case 3:
                    // Use cash payment method
                    paymentStrategy = new CashPayment();
                    break;
                case 4:
                    // User chose to cancel payment
                    System.out.println("Payment canceled.");
                    return;
                default:
                    // Handle invalid choice
                    System.out.println("Invalid choice. Please try again.");
                    processPayment(amount);  // Retry payment processing
            }

            // Process payment if a valid payment strategy is selected
            if (paymentStrategy != null) {
                Payment payment = new Payment(amount, new Date(), paymentStrategy, "pending");
                payment.processPayment();  // Execute payment processing
                payment.generateInvoice();  // Generate invoice for the transaction
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid integer option.");
            scanner.nextLine();  // Clear invalid input
            processPayment(amount);
        }
    }

    public static void main(String[] args) throws SQLException {
        try {
            Database.setupDatabase();
        } catch (SQLException e) {
            System.out.println("Error setting up the database.");
        }

        System.out.println("Welcome to Flora Wedding Hall!");

        // Display the initial entry menu
        showEntryMenu();
    }

    private static void viewCustomers() {
        System.out.println("Viewing all registered customers...");
        Database.viewCustomers();  // Calls the viewCustomers method from the Database class
    }

    private static void viewBookings() {
        System.out.println("Viewing bookings...");
        // Proxy instance with only basic booking data
        BookingProxy proxy = new BookingProxy(101, "Pending", 1200.00);

        // Accessing basic info without triggering full database load
        System.out.println("Booking Status: " + proxy.getBookingStatus());
        System.out.println("Total Price: " + proxy.getTotalPrice());

        // Accessing full details, which triggers lazy loading
        proxy.viewBooking();
    }

    private static void addNewPackage() {
        System.out.println("Adding new package...");
    }
}