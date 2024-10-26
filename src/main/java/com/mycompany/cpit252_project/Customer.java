
package com.mycompany.cpit252_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {

    private String customerName;
    private String customerEmail;
    private String customerPhone;

    private static List<Customer> customerList = new ArrayList<>(); // Stores all customers

    // Constructor
    public Customer(String userID, String password, String name, String email, String phone) {
        super(userID, password);  // Call the parent constructor to set userID and password
        this.customerName = name;
        this.customerEmail = email;
        this.customerPhone = phone;
    }

    // Method to register a new customer
    public static void registerCustomer(Scanner scanner) {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter a user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // Check if the email is already registered
        if (findCustomerByEmail(email) != null) {
            System.out.println("Email is already registered. Please log in.");
        } else {
            // Create a new customer and add to the list
            Customer newCustomer = new Customer(userID, password, name, email, phone);
            customerList.add(newCustomer);
            System.out.println("Registration successful!");
        }
    }

    // Override the login method if needed, otherwise it will inherit from User class
    public static boolean loginCustomer(Scanner scanner) {
        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Find the customer by userID
        Customer customer = findCustomerByUserID(userID);

        if (customer != null && customer.login(userID, password)) {  // Calling the parent login method
            return true;
        } else {
            return false;
        }
    }

    // Method to update customer profile
    public void updateProfile(Scanner scanner) {
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String newPhone = scanner.nextLine();

        // Update customer information
        this.customerEmail = newEmail;
        this.customerPhone = newPhone;

        System.out.println("Profile updated: New Email: " + newEmail + ", New Phone: " + newPhone);
    }

    // Method to view customer profile
    public void viewProfile() {
        System.out.println("Customer Profile:");
        System.out.println("Name: " + customerName);
        System.out.println("Email: " + customerEmail);
        System.out.println("Phone: " + customerPhone);
    }

    // Method to delete customer profile
    public static void deleteCustomerProfile(Scanner scanner) {
        System.out.print("Enter your email to delete profile: ");
        String email = scanner.nextLine();

        // Find the customer by email
        Customer customer = findCustomerByEmail(email);

        if (customer != null) {
            customerList.remove(customer);
            System.out.println("Customer profile deleted successfully.");
        } else {
            System.out.println("Customer not found.");
        }
    }

    // Helper methods
    private static Customer findCustomerByEmail(String email) {
        for (Customer customer : customerList) {
            if (customer.customerEmail.equals(email)) {
                return customer;
            }
        }
        return null;
    }

    private static Customer findCustomerByUserID(String userID) {
        for (Customer customer : customerList) {
            if (customer.getUserID().equals(userID)) {  // getUserID() inherited from User class
                return customer;
            }
        }
        return null;
    }

    // Getters for UserID and other inherited methods
    public String getUserID() {
        return super.userID; // Access the userID from the parent class
    }
}