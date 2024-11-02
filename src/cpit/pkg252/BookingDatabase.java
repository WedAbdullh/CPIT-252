package cpit.pkg252;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookingDatabase { }
//
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "your_password";
//
//    // Establish MySQL connection
//    private Connection connect() throws SQLException {
//        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//    }
//
//    // Constructor to set up the table if not exists
//    public BookingDatabase() {
//        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
//            String createTableSQL = """
//                CREATE TABLE IF NOT EXISTS Bookings (
//                    bookingId INT PRIMARY KEY,
//                    customerName VARCHAR(100),
//                    packageName VARCHAR(100),
//                    status VARCHAR(20),
//                    bookingDate DATE
//                );
//            """;
//            stmt.execute(createTableSQL);
//            System.out.println("Bookings table is ready.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to save a booking
//    public void saveBooking(Booking booking) {
//        String query = "INSERT INTO Bookings (bookingId, customerName, packageName, status, bookingDate) VALUES (?, ?, ?, ?, ?)";
//        
//        try (Connection conn = connect();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            
//            stmt.setInt(1, booking.getBookingId());
//            stmt.setString(2, booking.getCustomerName());
//            stmt.setString(3, booking.getPackageName());
//            stmt.setString(4, booking.getStatus());
//            stmt.setDate(5, java.sql.Date.valueOf(booking.getBookingDate()));
//            
//            stmt.executeUpdate();
//            System.out.println("Booking saved to database: " + booking);
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to fetch a booking by ID
//    public Booking fetchBooking(int bookingId) {
//        String query = "SELECT * FROM Bookings WHERE bookingId = ?";
//        
//        try (Connection conn = connect();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            
//            stmt.setInt(1, bookingId);
//            ResultSet rs = stmt.executeQuery();
//            
//            if (rs.next()) {
//                String customerName = rs.getString("customerName");
//                String packageName = rs.getString("packageName");
//                String status = rs.getString("status");
//                String bookingDate = rs.getDate("bookingDate").toString();
//
//                return new Booking(bookingId, customerName, packageName, status, bookingDate);
//            }
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        
//        return null;
//    }
//
//    // Method to update a booking
//    public void updateBooking(Booking booking) {
//        String query = "UPDATE Bookings SET customerName = ?, packageName = ?, status = ?, bookingDate = ? WHERE bookingId = ?";
//        
//        try (Connection conn = connect();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            
//            stmt.setString(1, booking.getCustomerName());
//            stmt.setString(2, booking.getPackageName());
//            stmt.setString(3, booking.getStatus());
//            stmt.setDate(4, java.sql.Date.valueOf(booking.getBookingDate()));
//            stmt.setInt(5, booking.getBookingId());
//
//            stmt.executeUpdate();
//            System.out.println("Booking updated in database: " + booking);
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to delete a booking
//    public void deleteBooking(int bookingId) {
//        String query = "DELETE FROM Bookings WHERE bookingId = ?";
//        
//        try (Connection conn = connect();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            
//            stmt.setInt(1, bookingId);
//            stmt.executeUpdate();
//            System.out.println("Booking deleted from database with ID: " + bookingId);
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
