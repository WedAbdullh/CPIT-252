package FloraWeddingHall.system;

import java.util.Date;

public interface Booking {

    int getBookingID();

    void  createBooking(int customerId, String username,String selectedPackage, String bookingDate, String paymentMethod);

}

