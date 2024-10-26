
package cpit.pkg252;

import java.util.Date;

public class Payment {

    // Attributes
    private double price;
    private Date paymentDate;
    private String paymentMethod;
    private String paymentStatus;

    // Constructor
    public Payment(double price, Date paymentDate, String paymentMethod, String paymentStatus) {
        this.price = price;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    // Getters and Setters
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Methods
    public void createFinePayment(double fineAmount) {
        this.price += fineAmount;
        System.out.println("Fine added to payment. New total price: " + this.price);
    }

    public boolean validate() {
        // Validation logic, e.g., check if payment details are correct
        if (this.paymentMethod != null && !this.paymentMethod.isEmpty() && this.price > 0) {
            System.out.println("Payment validated successfully.");
            return true;
        } else {
            System.out.println("Payment validation failed.");
            return false;
        }
    }

    public boolean confirmedPayment() {
        if (this.paymentStatus.equalsIgnoreCase("confirmed")) {
            System.out.println("Payment already confirmed.");
            return true;
        } else {
            System.out.println("Payment is not confirmed yet.");
            return false;
        }
    }

    public void updatePaymentStatus(String newStatus) {
        this.paymentStatus = newStatus;
        System.out.println("Payment status updated to: " + this.paymentStatus);
    }

    public void generatePaymentNotification() {
        System.out.println("Payment notification generated for payment of " + this.price);
    }

    public void generateInvoice() {
    System.out.println("--------------------------------------------------");
    System.out.println("                 PAYMENT INVOICE                  ");
    System.out.println("--------------------------------------------------");
    System.out.printf("%-20s: %s%n", "Price", String.format("$%.2f", this.price));
    System.out.printf("%-20s: %s%n", "Payment Date", this.paymentDate.toString());
    System.out.printf("%-20s: %s%n", "Payment Method", this.paymentMethod);
    System.out.printf("%-20s: %s%n", "Payment Status", this.paymentStatus);
    System.out.println("--------------------------------------------------");
    System.out.println("                   Thank you!                     ");
    System.out.println("--------------------------------------------------");
    }
    
}
