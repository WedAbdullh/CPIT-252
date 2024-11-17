package FloraWeddingHall.system;

import java.util.Date;

public class BookingProxy implements Booking {

    private RealBooking realBooking;
    private String authorizedUser;

    public BookingProxy(String authorizedUser) {
        this.authorizedUser = authorizedUser;
        this.realBooking = new RealBooking(); // Instantiate the real booking object
    }

    public BookingProxy() {
    }

    @Override
    public int getBookingID() {
        if (realBooking != null) {
            return realBooking.getBookingID();
        }
        throw new IllegalStateException("Booking not initialized.");
    }


    @Override
    public void viewBooking() {
        if (realBooking != null) {
            realBooking.viewBooking();
        } else {
            System.out.println("No booking to view.");
        }
    }

 
  @Override
  public void createBooking(int customerId, String username,String selectedPackage, String bookingDate, String paymentMethod) {
        // Access control: Only allow the logged-in user to create bookings
        if (authorizedUser == null || !authorizedUser.equals(username)) {
            System.out.println("Access denied: Only the logged-in user can create bookings.");
        }else{
            // Logic to validate and add booking
    System.out.println("Creating booking for: " + customerId);
    System.out.println("Package: " + selectedPackage);
    System.out.println("Date: " + bookingDate);
    System.out.println("Payment Method: " + paymentMethod);


    // Simulate saving to database or further processing
     System.out.println("Access granted for user: " + username);
    realBooking.createBooking(customerId, username, selectedPackage, bookingDate, paymentMethod); // Delegate to RealBooking
        
        }
        }
       
}
