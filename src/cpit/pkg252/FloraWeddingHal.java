
package cpit.pkg252;

import java.util.Scanner;

public class FloraWeddingHal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Customer customerManager = new Customer(0, "defaultPassword", "defaultName", "defaultEmail", "defaultPhone");
        //Customer customerManager = new Customer();
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
}
