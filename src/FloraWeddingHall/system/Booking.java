package FloraWeddingHall.system;

public interface Booking {

    int getBookingID();

    String getBookingStatus();

    double getTotalPrice();

    void viewBooking();

    void confirmBooking();
}
