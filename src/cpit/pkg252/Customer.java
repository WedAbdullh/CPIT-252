
package cpit.pkg252;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer extends User{
    private static List<Customer> customerList = new ArrayList<>();
    private int id;
    private String name;
    private String phone;
    private String password;    
    private String email;
    
   
    // AtomicInteger to generate unique IDs for each customer
    private AtomicInteger idGenerator = new AtomicInteger(1000); // Start ID from 1000
    
    public Customer(int id, String password, String name, String email, String phone) {
        super(id, password);  // Call the parent constructor to set userID and password
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

   

    public void registerCustomer(Scanner scanner) {
        System.out.print("Enter your name: ");
        name = scanner.nextLine();
        System.out.print("Enter your email: ");
        email = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        phone = scanner.nextLine();
        System.out.print("Enter a password: ");
        password = scanner.nextLine();

        // Check if the email is already registered
        if (findCustomerByEmail(email) != null) {
            System.out.println("Email is already registered. Please log in.");
        } else {
            // Generate a unique ID for the customer
            id = idGenerator.getAndIncrement();
            
            Customer newCustomer = new Customer(id, password, name, email, phone);
            customerList.add(newCustomer);
            System.out.println("Registration successful! Please log in to book the hall.");
        }
    }

    public boolean loginCustomer(Scanner scanner) {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        // Find the customer by userID
        Customer customer = findCustomerByUserID(id);

        if (customer != null && customer.login(id, password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or password.");
            return false;
        }
    }
    
    // Helper methods
    private static Customer findCustomerByEmail(String email) {
        for (Customer customer : customerList) {
            if (customer.email.equals(email)) {
                return customer;
            }
        }
        return null;
    }

    private static Customer findCustomerByUserID(int userID) {
        for (Customer customer : customerList) {
            if (customer.getId()==userID) {  // getUserID() inherited from User class
                return customer;
            }
        }
        return null;
    }

    // Getters for UserID and other inherited methods
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
