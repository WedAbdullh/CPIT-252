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
  public void createBooking(int customerId, String username,String PackageID, String bookingDate, String paymentMethod) {
        // Access control to ensure only the logged-in users can create bookings. 
         if (!username.equals(this.authorizedUser)) {
            System.out.println("Access denied: You can only create bookings for yourself.");
            System.out.println(this.authorizedUser);
            return;
        }
       //Delegates the actual database operation to RealBooking.
        if (realBooking == null) {
            realBooking = new RealBooking();
            System.out.println("booking db");
        }
        realBooking.createBooking(customerId, username, PackageID, bookingDate, paymentMethod);
        } 
}
