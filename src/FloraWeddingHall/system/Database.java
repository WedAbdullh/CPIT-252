package FloraWeddingHall.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final String URL = "jdbc:mysql://localhost:3306/FloraWeddingHallDB";
    private static final String USER = "root";
    private static final String PASSWORD = "01082003";

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
            insertPackageRecord();
        }
    }

    public static void createCustomerTable(Statement statement) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Customer"
                + " (id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "phone VARCHAR(20) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "password VARCHAR(100) NOT NULL)";
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

    public static void createPackageTable(Statement statement) {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Package"
                + " (id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) UNIQUE NOT NULL, "
                + "description TEXT NOT NULL, "
                + "includedServices TEXT NOT NULL, "
                + "servicesPrices TEXT NOT NULL, "
                + "totalPrice DOUBLE NOT NULL)";
        try {
            statement.executeUpdate(createTableQuery);
        } catch (SQLException ex) {
            System.out.println("Exception in createPackageTable");
        }
    }

    public static void createBookingTable(Statement statement) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Bookings"
                + " (id INT PRIMARY KEY AUTO_INCREMENT, "
                + "customerId INT NOT NULL, "
                + "packageId INT NOT NULL, "
                + "bookingDate DATE NOT NULL, "
                + "paymentStatus VARCHAR(20) NOT NULL, "
                + "FOREIGN KEY (customerId) REFERENCES Customer(id), "
                + "FOREIGN KEY (packageId) REFERENCES Package(id))";
        try {
            statement.executeUpdate(createTableSQL);
        } catch (SQLException ex) {
            System.out.println("Exception in createBookingTable");
        }
    }

    public static void insertManagerRecord(Statement statement) {
        String checkManagerQuery = "SELECT COUNT(*) FROM Manager";
        ResultSet resultSet;
        try {
            resultSet = statement.executeQuery(checkManagerQuery);

            resultSet.next();

            if (resultSet.getInt(1) == 0) {  // No manager record exists
                String insertRecordsQuery = "INSERT INTO Manager (name, phone, email, password) VALUES "
                        + "('Ali Al-Qahtani', '0501234567', 'ali.qahtani@FloaWeddingHall.com', 'password123')";
                statement.executeUpdate(insertRecordsQuery);
            } else {
                System.out.println("A manager already exists. Insertion skipped.");
            }
        } catch (SQLException ex) {
            System.out.println("Exception in insertManagerRecord");
        }
    }

    public static void insertPackageRecord() {
        String insertQuery = "INSERT INTO Package (name, description, includedServices, servicesPrices, totalPrice) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertQuery)) {
            addPackageToBatch(preparedStatement, PackageFactory.createPackage("Wedding"));
            addPackageToBatch(preparedStatement, PackageFactory.createPackage("Corporate"));
            addPackageToBatch(preparedStatement, PackageFactory.createPackage("Birthday"));

            preparedStatement.executeBatch();
        } catch (SQLException ex) {
            System.out.println("Exception in insertPackageRecord: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static void addPackageToBatch(PreparedStatement preparedStatement, Package pack) throws SQLException {
        if (pack != null) {
            preparedStatement.setString(1, pack.getPackageName());
            preparedStatement.setString(2, pack.getPackageDescription());
            preparedStatement.setString(3, pack.getIncludedServices());
            preparedStatement.setString(4, pack.getServicesPrices());
            preparedStatement.setDouble(5, pack.getPackagePrice());
            preparedStatement.addBatch();
        } else {
            System.out.println("Package is null and will not be added.");
        }
    }

    public static void insertNewPackage(String name, String description, String services, String servicesPrices, double totalPrice) throws SQLException {
        String query = "INSERT INTO Package (name, description, price) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setString(3, services);
            pstmt.setString(4, servicesPrices);
            pstmt.setDouble(5, totalPrice);
            pstmt.executeUpdate();
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

    public static List<String> viewBookings() {
        List<String> bookingsList = new ArrayList<>();
        String query = "SELECT b.id AS bookingId, "
                + "c.name AS customerName, "
                + "p.name AS packageName, "
                + "b.bookingDate, "
                + "b.paymentStatus "
                + "FROM Bookings b "
                + "JOIN Customer c ON b.customerId = c.id "
                + "JOIN Package p ON b.packageId = p.id";

        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int bookingId = rs.getInt("bookingId");
                String customerName = rs.getString("customerName");
                String packageName = rs.getString("packageName");
                String bookingDate = rs.getDate("bookingDate").toString();
                String paymentStatus = rs.getString("paymentStatus");

                // Concatenate the fields with commas
                String booking = bookingId + "," + customerName + "," + packageName + "," + bookingDate + "," + paymentStatus;
                bookingsList.add(booking);
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while retrieving bookings.");
            e.printStackTrace();
        }

        return bookingsList;
    }

    public static boolean deleteBooking(int bookingId) throws SQLException {
        // SQL query to delete a booking by ID
        String deleteSQL = "DELETE FROM Bookings WHERE id = " + bookingId;
        Statement statement = getConnection().createStatement();

        // Execute the delete query
        int rowsAffected = statement.executeUpdate(deleteSQL);

        // Print the result
        return rowsAffected > 0;
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

    public static boolean loginCustomer(String email, String password) {
        String loginQuery = "SELECT email, password FROM Customer WHERE email = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(loginQuery)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // If a record is found, login is successful
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

    public static Customer getCustomerByEmail(String email) {
        String query = "SELECT * FROM Customer WHERE email = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if the customer is not found
    }

    public static String getLoggedInUser() {
        String loggedInUser = null;
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT customer_id FROM sessions WHERE session_active = 1");

            if (rs.next()) {
                loggedInUser = rs.getString("customer_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loggedInUser;
    }

    public Object[][] getAllPackages() throws SQLException {
        String query = "SELECT id, name, description, includedServices, servicesPrices, totalPrice FROM Package";
        ArrayList<Object[]> packageList = new ArrayList<>();

        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String includedServices = resultSet.getString("includedServices");
                String servicesPrices = resultSet.getString("servicesPrices");
                double totalPrice = resultSet.getDouble("totalPrice");

                packageList.add(new Object[]{id, name, description, includedServices, servicesPrices, totalPrice});
            }
        }

        return convertListTo2DArray(packageList);

    }

    public Object[][] getAllBookings() throws SQLException {
        String query = "SELECT b.id, c.name AS customerName, p.name AS packageName, b.bookingDate, b.paymentStatus "
                + "FROM Bookings b "
                + "JOIN Customer c ON b.customerId = c.id "
                + "JOIN Package p ON b.packageId = p.id";

        ArrayList<Object[]> bookingsList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String customerName = resultSet.getString("customerName");
                String packageName = resultSet.getString("packageName");
                String bookingDate = resultSet.getString("bookingDate");
                String paymentStatus = resultSet.getString("paymentStatus");

                bookingsList.add(new Object[]{id, customerName, packageName, bookingDate, paymentStatus});
            }
        }

        return convertListTo2DArray(bookingsList);
    }

    public Object[][] getAllCustomers() throws SQLException {
        String query = "SELECT id, name, email, phone FROM Customer";
        ArrayList<Object[]> customersList = new ArrayList<>();
        try (Statement statement = getConnection().createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");

                customersList.add(new Object[]{id, name, email, phone});
            }
        }

        return convertListTo2DArray(customersList);
    }

    private Object[][] convertListTo2DArray(ArrayList<Object[]> list) {
        Object[][] array = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

}
