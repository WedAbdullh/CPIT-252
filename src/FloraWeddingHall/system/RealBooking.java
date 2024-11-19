package FloraWeddingHall.system;

import java.sql.SQLException;
import java.util.*;

public class RealBooking implements Booking {

    private int bookingID;
    private Customer customerID; // Representing customer association
    private Package packageID;  // Representing package association
    private Date bookingDate;


    RealBooking() {
    }

    @Override
    public int getBookingID() {
        return bookingID;
    }

    @Override
    public void createBooking(int customerId, String username, String packageId, String bookingDate, String paymentMethod) {
        try {
       //Calls the Database class by insert method to insert booking details into the database.
            Database.insertBooking(customerId, username, packageId, bookingDate, paymentMethod);
            System.out.println("Booking successfully created for: " + username);
        } catch (SQLException e) {
            System.err.println("Error creating booking: " + e.getMessage());
        }
    }

   
}
