package cpit.pkg252;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gkmis
 */
public interface Booking {
    int getBookingID();
    String getBookingStatus();
    double getTotalPrice();
    void viewBooking();
    void confirmBooking();
}
