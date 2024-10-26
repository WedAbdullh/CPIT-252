
package cpit.pkg252;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {

    private Map<String, String> customers = new HashMap<>(); // Stores email and password
    private int id;
    private String name;
    private String phone;
    private String password;    
    private String email;

    // AtomicInteger to generate unique IDs for each customer
    private AtomicInteger idGenerator = new AtomicInteger(1000); // Start ID from 1000


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
        if (customers.containsKey(email)) {
            System.out.println("Email is already registered. Please log in.");
        } else {
            // Generate a unique ID for the customer
            id = idGenerator.getAndIncrement();
            
            customers.put(email, password); // Register the customer
            System.out.println("Registration successful! Please log in to book the hall.");
        }
    }

    public boolean loginCustomer(Scanner scanner) {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (customers.containsKey(email) && customers.get(email).equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or password.");
            return false;
        }
    }
    
    
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
