package FloraWeddingHall.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SingletonManager {

    private String name;
    private String email;
    private String phone;
    private String password;
    private static SingletonManager instance = null;

    private SingletonManager(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public static SingletonManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("Manager is not logged in.");
        }
        return instance;
    }

    public static boolean logInManager(String email, String password) {
        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Manager WHERE email = ? AND password = ?")) {

            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                instance = new SingletonManager(rs.getString("name"), email, rs.getString("phone"), password);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addNewPackage(String name, String description, String services, String servicesPrices,double totalPrice) throws SQLException {
        Database.insertNewPackage(name, description, services, servicesPrices, totalPrice);
    }
}

//package FloraWeddingHall.system;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class SingletonManager extends User {
//
//    private String name;
//    private String phone;
//    private String email;
//    private String password;
//
//    // Static variable to hold the single instance of Manager
//    private static SingletonManager instance;
//
//    // Private constructor to prevent instantiation from outside
//    private SingletonManager(String name, String phone, String email, String password) {
//        super(name, email, phone, password);
//    }
//
//    // Static method to provide access to the singleton instance
//    public static SingletonManager getInstance(Scanner scanner) {
//        // Check if the instance is null, and create it if necessary
//        if (instance == null) {
//            System.out.println("Please login first to access the Manager.");
//            instance = loginManager(); 
//        }
//        return instance;
//    }
//
//    private static SingletonManager loginManager() {
//
//        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
//                "SELECT * FROM Manager WHERE email = ? AND password = ?")) {
//
//            stmt.setString(1, email);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                // Only create the manager instance if login is successful
//                instance = new SingletonManager(
//                        rs.getString("name"),
//                        rs.getString("phone"),
//                        rs.getString("email"),
//                        rs.getString("password")
//                );
//                System.out.println("Manager login successful!");
//            } else {
//                System.out.println("Invalid email or password.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return instance;
//    }
//
//    private static void addNewPackage(String packageName, String packageDescription, double packagePrice) throws SQLException {
//        // Input validation
//        if (packageName == null || packageName.trim().isEmpty()) {
//            throw new IllegalArgumentException("Package name cannot be empty.");
//        }
//        if (packageName.length() > 100) {
//            throw new IllegalArgumentException("Package name cannot exceed 100 characters.");
//        }
//        if (packageDescription == null || packageDescription.trim().isEmpty()) {
//            throw new IllegalArgumentException("Package description cannot be empty.");
//        }
//        if (packagePrice < 0) {
//            throw new IllegalArgumentException("Package price cannot be negative.");
//        }
//
//        // Check if the package name is not already exists
//        if (isPackageNameExists(packageName)) {
//            throw new IllegalArgumentException("A package with this name already exists.");
//        }
//
//        // Insert new package into the database
//        try {
//            String insertRecordsQuery = "INSERT INTO Package (name, description, price) VALUES (?, ?, ?)";
//            PreparedStatement preparedStatement = Database.getConnection().prepareStatement(insertRecordsQuery);
//            preparedStatement.setString(1, packageName);
//            preparedStatement.setString(2, packageDescription);
//            preparedStatement.setDouble(3, packagePrice);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Failed to insert package: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    // Method to check if a package name already exists in the database
//    private static boolean isPackageNameExists(String packageName) throws SQLException {
//        String query = "SELECT COUNT(*) FROM Package WHERE name = ?";
//        try (PreparedStatement stmt = Database.getConnection().prepareStatement(query)) {
//            stmt.setString(1, packageName);
//            ResultSet rs = stmt.executeQuery();
//            if (rs.next()) {
//                return rs.getInt(1) > 0;
//            }
//        }
//        return false;
//    }
//
//    // Getters and Setters
//    @Override
//    public String getEmail() {
//        return email;
//    }
//
//    @Override
//    public boolean logInProcess(String email, String password) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    @Override
//    public boolean signUpProcess(String name, String phone, String email, String password) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//}
