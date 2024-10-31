package cpit.pkg252;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer extends User {

    private String name;
    private String phone;
    private String email;
    private String password;

    // Constructor
    public Customer(String name, String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // Register Customer in Database
    public boolean registerCustomer(Scanner scanner) {
        System.out.print("Enter your name: ");
        this.name = scanner.nextLine();
        System.out.print("Enter your email: ");
        this.email = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        this.phone = scanner.nextLine();
        System.out.print("Enter a password: ");
        this.password = scanner.nextLine();

        // Check if the email is already registered
        if (findCustomerByEmail(email) != null) {
            System.out.println("Email is already registered. Please log in.");
            return false;
        } else {
            // Insert into database
            try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Customer (name, phone, email, password) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, name);
                stmt.setString(2, phone);
                stmt.setString(3, email);
                stmt.setString(4, password);
                stmt.executeUpdate();
                System.out.println("Registration successful!");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Login Customer
    public static Customer loginCustomer(Scanner scanner) {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Validate login from database
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Customer WHERE email = ? AND password = ?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Customer customer = new Customer(
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                System.out.println("Customer login successful!");
                return customer;
            } else {
                System.out.println("Invalid email or password.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Find Customer by Email
    private static Customer findCustomerByEmail(String email) {
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Customer WHERE email = ?")) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Getters and Setters
    @Override
    public String getEmail() {
        return email;
    }
}
