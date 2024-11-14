
package cpit.pkg252;

import java.util.List;

public class FloraFacade {
//    private BookingProxy bookingProxy;
//    private BookingDatabase bookingDatabase;
//    private PackageFactory packageFactory;
//    private PaymentStrategy paymentStrategy;
//
//    public FloraFacade() {
//        this.bookingProxy = new BookingProxy();
//        this.bookingDatabase = new BookingDatabase();
//        this.packageFactory = new PackageFactory();
//    }
//
//    // Method to create a new booking
//    public boolean createBooking(String customerId, String packageType, String paymentMethod) {
//        Package selectedPackage = packageFactory.createPackage(packageType);
//        if (selectedPackage == null) {
//            System.out.println("Package type not found.");
//            return false;
//        }
//
//        RealBooking booking = new RealBooking(customerId, selectedPackage);
//        boolean isBooked = bookingProxy.addBooking(booking);
//        if (!isBooked) {
//            System.out.println("Booking could not be completed.");
//            return false;
//        }
//        
//        Payment payment = createPayment(paymentMethod);
//        if (payment != null) {
//            paymentStrategy = new PaymentStrategy(payment);
//            paymentStrategy.pay(booking.getPackage().getPackagePrice());
//        } else {
//            System.out.println("Invalid payment method.");
//            return false;
//        }
//
//        System.out.println("Booking and payment successful!");
//        return true;
//    }
//
//    public List<String> getAvailablePackages() {
//        return packageFactory.getAvailablePackageTypes();
//    }
//
//    public List<RealBooking> getBookings(String customerId) {
//        return bookingDatabase.getBookingsForUser(customerId);
//    }
//
//    private Payment createPayment(String paymentType) {
//        switch (paymentType.toLowerCase()) {
//            case "cash":
//                return new CashPayment();
//            case "applepay":
//                return new ApplePayPayment();
//            case "paypal":
//                return new PayPalPayment();
//            default:
//                return null;
//        }
//    }
}
