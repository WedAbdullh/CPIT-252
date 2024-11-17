package FloraWeddingHall.system;

import java.util.*;

public class RealBooking implements Booking {

    private int bookingID;
    private String customerID; // Representing customer association
    private String packageID;  // Representing package association
    private Date bookingDate;

    private static List<Date> bookedDates = new ArrayList<>();


    RealBooking() {
    }

    @Override
    public int getBookingID() {
        return bookingID;
    }


    @Override
    public void viewBooking() {
        System.out.println("Booking ID: " + bookingID);
        System.out.println("Customer ID: " + customerID);
        System.out.println("Package ID: " + packageID);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Status: " + bookingStatus);
        System.out.println("Total Price: " + totalPrice);
    }

    @Override
   public void createBooking(int customerId, String username,String selectedPackage, String bookingDate, String paymentMethod) {
        System.out.println("Booking created successfully for:");
        System.out.println("User: " + username);
        System.out.println("Package: " + selectedPackage + ", Date: " + bookingDate + ", payment method: " + paymentMethod);
  
   }

    public static boolean isDateAvailable(Date date) {
        return !bookedDates.contains(date);
    }
}
