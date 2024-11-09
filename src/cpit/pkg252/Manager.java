package cpit.pkg252;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Manager extends User {

    private String name;
    private String phone;
    private String email;
    private String password;

    // Static variable to hold the single instance of Manager
    private static Manager instance;

    // Private constructor to prevent instantiation from outside
    private Manager(String name, String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // Static method to provide access to the singleton instance
    public static Manager getInstance(Scanner scanner) {
        // Check if the instance is null, and create it if necessary
        if (instance == null) {
            System.out.println("Please login first to access the Manager.");
            instance = loginManager(scanner);  // Log in and create the singleton instance
        }
        return instance;
    }

    // Login Manager from Database
    public static Manager loginManager(Scanner scanner) {
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM Manager WHERE email = ? AND password = ?")) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Only create the manager instance if login is successful
                Manager manager = new Manager(
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password")
                );
                System.out.println("Manager login successful!");
                return manager;
            } else {
                System.out.println("Invalid email or password.");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static void addNewPackage(String packageName, String packageDescription, double packagePrice) throws SQLException {
        // Input validation
        if (packageName == null || packageName.trim().isEmpty()) {
            throw new IllegalArgumentException("Package name cannot be empty.");
        }
        if (packageName.length() > 100) {
            throw new IllegalArgumentException("Package name cannot exceed 100 characters.");
        }
        if (packageDescription == null || packageDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Package description cannot be empty.");
        }
        if (packagePrice < 0) {
            throw new IllegalArgumentException("Package price cannot be negative.");
        }

        // Check if the package name is not already exists
        if (isPackageNameExists(packageName)) {
            throw new IllegalArgumentException("A package with this name already exists.");
        }

        // Insert new package into the database
        try {
            String insertRecordsQuery = "INSERT INTO Package (name, description, price) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(insertRecordsQuery);
            preparedStatement.setString(1, packageName);
            preparedStatement.setString(2, packageDescription);
            preparedStatement.setDouble(3, packagePrice);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to insert package: " + e.getMessage());
            throw e;
        }
    }

    // Method to check if a package name already exists in the database
    private static boolean isPackageNameExists(String packageName) throws SQLException {
        String query = "SELECT COUNT(*) FROM Package WHERE name = ?";
        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
            stmt.setString(1, packageName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }

    // Getters and Setters
    @Override
    public String getEmail() {
        return email;
    }

    // Method to send an alert to all customers
    public void sendAlert(AlertSystem alertSystem, Scanner scanner) {
        System.out.print("Enter alert message: ");
        String alertMessage = scanner.nextLine();
        
        // Send the alert using the alert system
        alertSystem.sendAlertToAllCustomers(alertMessage);  // Use AlertSystem class to send alerts to customers
        System.out.println("Alert sent to all customers!");
    }
}