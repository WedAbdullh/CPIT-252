//package FloraWeddingHall.system;
//
//import java.util.*;
//
//public class RealBooking implements Booking{
//
//    // Attributes
//    private int bookingID;
//    private Customer customerID; // Association with Customer class
//    private Package packageID;    // Association with Package class
//    private Date bookingDate;
//    private String bookingStatus;
//    private double totalPrice;
//    private Payment payment;    // Association with Payment class
//
//    // List of booked dates
//    private static List<Date> bookedDates = new ArrayList<>(); // Keeps track of all booked dates
//  
//// Constructor loads full booking details
//    public RealBooking(int bookingID) {
//        this.bookingID = bookingID;
//        loadBookingFromDatabase();
//    }
//    
//     // Simulates loading booking details from database
//    private void loadBookingFromDatabase() {
//        // Load data from database based on bookingID
//        // Here we assume placeholders for example purposes
//        this.bookingStatus = "Pending";
//        this.totalPrice = 1000.00;
//        // Load customerID, packageID, bookingDate, and payment details
//    }
//    
//    // Constructor
//    public RealBooking(int bookingID, Customer customerID, Package packageID, Date bookingDate, String bookingStatus, double totalPrice) {
//        this.bookingID = bookingID;
//        this.customerID = customerID;
//        this.packageID = packageID;
//        this.bookingDate = bookingDate;
//        this.bookingStatus = bookingStatus;
//        this.totalPrice = totalPrice;
//    }
//
//    // replace addbooking method to constructor
//    public RealBooking(int bookingID, Customer customer, Package pkg, Date bookingDate) throws Exception {
//        // Check availability before creating the booking
//        if (checkAvailability(bookingDate)) {
//            this.bookingID = bookingID;
//            this.customerID = customer;
//            this.packageID = pkg;
//            this.bookingDate = bookingDate;
//            this.bookingStatus = "Pending"; // Default status when booking is created
//            this.totalPrice = pkg.getPackagePrice(); // Ii should be added in the package class and implement it
//
//            // Add the booking date to the bookedDates list
//            bookedDates.add(bookingDate);
//            System.out.println("Booking created successfully.");
//        } else {
//            throw new Exception("The selected date is not available.");
//        }
//    }
//
//
//    // Getters and Setters
//
//    public void setBookingID(int bookingID) {
//        this.bookingID = bookingID;
//    }
//  
//
//    public Date getBookingDate() {
//        return bookingDate;
//    }
//
//    public void setBookingDate(Date bookingDate) {
//        this.bookingDate = bookingDate;
//    }
//
//
//    public void setBookingStatus(String bookingStatus) {
//        this.bookingStatus = bookingStatus;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//// Implement methods from the BookingInterface
//    public int getBookingID() { return bookingID; }
//    public String getBookingStatus() { return bookingStatus; }
//    public double getTotalPrice() { return totalPrice; }
//
//
//    // Methods
//    // View booking details
//    public void viewBooking() {
//        System.out.println("Booking ID: " + bookingID);
//        System.out.println("Customer ID: " + customerID.getId());
//        System.out.println("Package: " + packageID.getPackageDescription()); // should be implement 
//        System.out.println("Booking Date: " + bookingDate);
//        System.out.println("Booking Status: " + bookingStatus);
//        System.out.println("Total Price: " + totalPrice);
//        System.out.println("Payment Status: " + payment.getPaymentStatus()); // should be implement //implemented
//
//    }
//
//    // Cancel booking
//    public void cancelBooking() {
//        if (!this.bookingStatus.equals("Confirmed")) {
//            this.bookingStatus = "Cancelled";
//            System.out.println("Booking has been cancelled.");
//        } else {
//            System.out.println("Booking cannot be cancelled because it is confirmed.");
//        }
//    }
//
//    // Confirm booking if available and process payment
//    public void confirmBooking() {
//        if (this.bookingStatus.equals("Pending")) {
//            if (checkAvailability(this.bookingDate)) {
//                this.bookingStatus = "Confirmed";
//                System.out.println("Booking has been confirmed.");
//
//                // Process payment after confirmation
//                if (this.payment != null) {
//                    payment.confirmedPayment();
//                } else {
//                    System.out.println("Payment has not been processed yet.");
//                }
//
//                // Add the booked date to the bookedDates list after confirmation
//                bookedDates.add(this.bookingDate);
//            } else {
//                System.out.println("The selected date is not available.");
//            }
//        } else {
//            System.out.println("Booking is already confirmed or cancelled.");
//        }
//    }
//
////    // Calculate total price
////    public void calculateTotalPrice() {
////        this.totalPrice = packageID.getPackagePrice();
////        System.out.println("Total price calculated: " + totalPrice);
////
////    }
//    // Check if the booking date is available
//
//    public boolean checkAvailability(Date desiredDate) {
//        for (Date bookedDate : bookedDates) {
//            if (bookedDate.equals(desiredDate)) {
//                return false; // Date is already booked
//            }
//        }
//        return true; // Date is available
//    }
//
//}
