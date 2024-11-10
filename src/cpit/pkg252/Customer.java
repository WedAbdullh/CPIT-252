package cpit.pkg252;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer extends User {

    private String name;
    private String phone;
    private String email;
    private String password;
    private String alert;

    public Customer(String name, String phone, String email, String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.alert = ""; // Initialize alert as empty
    }

    public boolean registerCustomer() {
        // Check if the email is already registered
        if (findCustomerByEmail(email) != null) {
            System.out.println("Email is already registered. Please log in.");
            return false;
        } else {
            try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
                    "INSERT INTO Customer (name, phone, email, password, alerts) VALUES (?, ?, ?, ?, ?)")) {
                stmt.setString(1, name);
                stmt.setString(2, phone);
                stmt.setString(3, email);
                stmt.setString(4, password);
                stmt.setString(5, alert); // Use the alert value (empty string initially)
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
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
     // Email Validation (Simple regex for email format)
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Phone Validation (Only digits and length of 10)
    public static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    // Password Validation (At least 8 characters, contains at least one digit)
    public static boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*");
    }

    // Validation method
    public static boolean validateCustomer(String name, String phone, String email, String password) {
        if (name == null || name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return false;
        }
        if (!isValidPhone(phone)) {
            System.out.println("Invalid phone number. It should be 10 digits long.");
            return false;
        }
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }
        if (!isValidPassword(password)) {
            System.out.println("Password must be at least 8 characters long and contain at least one digit.");
            return false;
        }
        return true;
    }


    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }
}


