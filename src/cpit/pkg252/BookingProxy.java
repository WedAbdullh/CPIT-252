/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cpit.pkg252;

/**
 *
 * @author gkmis
 */
public class BookingProxy implements Booking{
     private int bookingID;
    private String bookingStatus;
    private double totalPrice;
    private RealBooking realbooking;

    public BookingProxy(int bookingID, String bookingStatus, double totalPrice) {
        this.bookingID = bookingID;
        this.bookingStatus = bookingStatus;
        this.totalPrice = totalPrice;
        this.realbooking = null; // Load real booking only if needed
    }

    public int getBookingID() { return bookingID; }
    public String getBookingStatus() { return bookingStatus; }
    public double getTotalPrice() { return totalPrice; }

    // Lazy-load full booking details
    private void loadFullBooking() {
        if (realbooking == null) {
            realbooking = new RealBooking(bookingID);
        }
    }

    public void viewBooking() {
        loadFullBooking();
        realbooking.viewBooking();
    }

    public void confirmBooking() {
        loadFullBooking();
        realbooking.confirmBooking();
    }
}

