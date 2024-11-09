package cpit.pkg252;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/FloraWeddingHallDB";
    private static final String USER = "root";
    private static final String PASSWORD = "lougin12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD); // Open a new connection each time
    }

    public static void setupDatabase() throws SQLException {
        String connectionURL = "jdbc:mysql://localhost:3306/";

        try (Connection connection = DriverManager.getConnection(connectionURL, USER, PASSWORD); Statement statement = connection.createStatement()) {

            // Create the database
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS FloraWeddingHallDB");
        }

        // Now connect to the created database and set up tables
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            // Create Customer, Manager, and Package tables
            createCustomerTable(statement);
            createManagerTable(statement);
            createPackageTable(statement);

            // Insert initial records
            insertManagerRecord(statement);
            insertPackageRecord(statement);
        }
    }

    public static void createCustomerTable(Statement statement) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Customer"
                + " (name VARCHAR(100) NOT NULL, "
                + "phone VARCHAR(20) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "password VARCHAR(100) NOT NULL, "
                + "alerts TEXT NOT NULL)";
        statement.executeUpdate(createTableQuery);
    }

    public static void createManagerTable(Statement statement) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Manager"
                + " (id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "phone VARCHAR(20) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "password VARCHAR(100) NOT NULL)";
        statement.executeUpdate(createTableQuery);
    }

    public static void createPackageTable(Statement statement) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Package"
                + " (id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "description TEXT NOT NULL, "
                + "price DOUBLE NOT NULL)";
        statement.executeUpdate(createTableQuery);
    }

    public static void insertManagerRecord(Statement statement) throws SQLException {
        String checkManagerQuery = "SELECT COUNT(*) FROM Manager";
        ResultSet resultSet = statement.executeQuery(checkManagerQuery);
        resultSet.next();

        if (resultSet.getInt(1) == 0) {  // No manager record exists
            String insertRecordsQuery = "INSERT INTO Manager (name, phone, email, password) VALUES "
                    + "('Ali Al-Qahtani', '0501234567', 'ali.qahtani@gmail.com', 'password123')";
            statement.executeUpdate(insertRecordsQuery);
            System.out.println("Manager record inserted successfully.");
        } else {
            System.out.println("A manager already exists. Insertion skipped.");
        }
    }

    public static void insertPackageRecord(Statement statement) throws SQLException {
        Package weddingPackage = PackageFactory.createPackage("Wedding");
        Package corporatePackage = PackageFactory.createPackage("Corporate");
        Package birthdayPackage = PackageFactory.createPackage("Birthday");

        String insertRecordsQuery = "INSERT INTO Package (name, description, price) VALUES "
                + "('" + weddingPackage.getPackageName() + "', '"
                + weddingPackage.getPackageDescription() + "', " + weddingPackage.getPackagePrice() + "), "
                + "('" + corporatePackage.getPackageName() + "', '"
                + corporatePackage.getPackageDescription() + "', " + corporatePackage.getPackagePrice() + "), "
                + "('" + birthdayPackage.getPackageName() + "', '"
                + birthdayPackage.getPackageDescription() + "', " + birthdayPackage.getPackagePrice() + ")";
        statement.executeUpdate(insertRecordsQuery);
    }

    // Register a new customer
    public static boolean registerCustomer(String name, String phone, String email, String password) {
        String insertQuery = "INSERT INTO Customer (name, phone, email, password) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void viewCustomers() {
        String selectQuery = "SELECT name, phone, email FROM Customer"; // Query to get customer details

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(selectQuery)) {

            System.out.println("Customer List:");
            System.out.println("----------------------------------------------------");
            System.out.printf("%-20s %-15s %-30s%n", "Name", "Phone", "Email");
            System.out.println("----------------------------------------------------");

            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                System.out.printf("%-20s %-15s %-30s%n", name, phone, email);
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving customer information.");
            e.printStackTrace();
        }
    }

    public static String getCustomerAlerts(String email) {
        String selectQuery = "SELECT alerts FROM Customer WHERE email = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("alerts");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateCustomerAlert(String email, String alertMessage) {
        String updateQuery = "UPDATE Customer SET alerts = ? WHERE email = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(updateQuery)) {
            // Get current alerts and append the new alert
            String existingAlerts = getCustomerAlerts(email);
            String updatedAlerts = existingAlerts == null ? alertMessage : existingAlerts + "; " + alertMessage;

            pstmt.setString(1, updatedAlerts);
            pstmt.setString(2, email);

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Check if an email already exists
    public static boolean emailExists(String email) {
        String checkQuery = "SELECT email FROM Customer WHERE email = ?";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(checkQuery)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
