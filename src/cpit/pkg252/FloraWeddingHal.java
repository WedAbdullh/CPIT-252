
package cpit.pkg252;

import java.util.Date;
import java.util.Scanner;

public class FloraWeddingHal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       // PaymentMethod(scanner, 13.03);
       
        
        Customer customerManager = new Customer(0, "defaultPassword", "defaultName", "defaultEmail", "defaultPhone");
        Package packages = new Package();

        while (true) {
            System.out.println("Welcome to Flora Wedding Hall");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. View Packages");
            System.out.println("4. Book a Hall");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    customerManager.registerCustomer(scanner);
                    break;
                case 2:
                    customerManager.loginCustomer(scanner);
                    break;
                case 3:
                    packages.toString();
                    break;
                case 4:
                    //bookingManager.makeBooking(scanner, customerManager);
                    break;
                case 5:
                    System.out.println("Thank you for visiting Flora Wedding Hall!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    
    // PaymentStrategy method
    public static void PaymentMethod(Scanner s, double amount){
        // Ask the user to choose a payment method
        System.out.println("Choose a payment method: ");
        System.out.println("1. PayPal");
        System.out.println("2. Apple Pay");
        System.out.println("3. Cash");

        int choice = s.nextInt();
        s.nextLine(); // Consume the newline character

        PaymentStrategy paymentStrategy = null;

        // Depending on the user's choice, create the corresponding payment strategy
        switch (choice) {
            case 1:
                System.out.println("Enter your PayPal email: ");
                String email = s.nextLine();
                paymentStrategy = new PayPalPayment(email);
                break;
            case 2:
                System.out.println("Enter your Apple Pay account: ");
                String appleAccount = s.nextLine();
                paymentStrategy = new ApplePayPayment(appleAccount);
                break;
            case 3:
                paymentStrategy = new CashPayment();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }

        // Create the payment object with the chosen payment method
        Payment payment = new Payment(amount, new Date(), paymentStrategy, "pending");

        // Process the payment
        payment.processPayment();

        // Generate an invoice
        payment.generateInvoice();
        
        // Close the scanner
        s.close();
        
        
    }
    
}
