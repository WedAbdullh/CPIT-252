package cpit.pkg252;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private static Connection connection = null;
    private static Statement statement;
    
    public static Connection getConnection() throws SQLException {
        return connection;
    }
    
    public static Statement getStatement() {
        return statement;
    }
    
    public static void setupDatabase() throws SQLException {
        
        String ConnectionURL = "jdbc:mysql://localhost:3306/";

        connection = DriverManager.getConnection(ConnectionURL, "root", "01082003");

        statement = connection.createStatement();
        statement.executeUpdate("CREATE DATABASE IF NOT EXISTS FloraWeddingHallDB");

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/FloraWeddingHallDB", "root", "01082003");
        statement = connection.createStatement();

        //Create Customer table
        createCustomerTable();

        //Create Manager table
        createManagerTable();
        insertManagerRecord();
        
        //Create Package table
        createPackageTable();
        insertPackageRecord();
    }
    
    public static void createCustomerTable() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Customer" + " ("
                + "name VARCHAR(100) NOT NULL, "
                + "phone VARCHAR(20) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "password VARCHAR(100) NOT NULL)";
        statement.executeUpdate(createTableQuery);
    }
    
    public static void createManagerTable() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Manager" + " ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "phone VARCHAR(20) NOT NULL, "
                + "email VARCHAR(100) UNIQUE NOT NULL, "
                + "password VARCHAR(100) NOT NULL)";
        statement.executeUpdate(createTableQuery);
    }
    
    public static void createPackageTable() throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Package" + " ("
                + "id INT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(100) NOT NULL, "
                + "description TEXT NOT NULL, "
                + "price DOUBLE NOT NULL)";
        statement.executeUpdate(createTableQuery);
    }
    
    public static void insertManagerRecord() throws SQLException {
        String insertRecordsQuery = "INSERT INTO Manager (name, phone, email, password) VALUES "
                + "('Ali Al-Qahtani', '0501234567', 'ali.qahtani@gmail.com', 'password123'), "
                + "('Fatima Al-Salem', '0503456789', 'fatima.salem@gmail.com', 'password456'), "
                + "('Abdullah Al-Ghamdi', '0506789012', 'abdullah.ghamdi@gmail.com', 'password789')";
        statement.executeUpdate(insertRecordsQuery);
    }
    
    public static void insertPackageRecord() throws SQLException {
        //Creating packages using the factory
        Package weddingPackage = PackageFactory.createPackage("Wedding");
        Package corporatePackage = PackageFactory.createPackage("Corporate");
        Package birthdayPackage = PackageFactory.createPackage("Birthday");

        // Insert packages into the database
        String insertRecordsQuery = "INSERT INTO Package (name, description, price) VALUES "
                + "('" + weddingPackage.getPackageName() + "', '" + weddingPackage.getPackageDescription() + "', " + weddingPackage.getPackagePrice() + "), "
                + "('" + corporatePackage.getPackageName() + "', '" + corporatePackage.getPackageDescription() + "', " + corporatePackage.getPackagePrice() + "), "
                + "('" + birthdayPackage.getPackageName() + "', '" + birthdayPackage.getPackageDescription() + "', " + birthdayPackage.getPackagePrice() + ")";
        statement.executeUpdate(insertRecordsQuery);
    }
}