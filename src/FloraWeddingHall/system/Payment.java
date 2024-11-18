
package FloraWeddingHall.system;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Payment {

    // Attributes
    private double price;
    private Date paymentDate;
    //private String paymentMethod;
    private PaymentStrategy paymentStrategy;
    private String paymentStatus;

    // Constructor
    public Payment(double price, Date paymentDate, PaymentStrategy paymentStrategy, String paymentStatus) {
        this.price = price;
        this.paymentDate = paymentDate;
        this.paymentStrategy = paymentStrategy;
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

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    // Methods
    //Strategy design pattern 
    public void processPayment() {
        paymentStrategy.pay(this.price);
    }
    
    public void createFinePayment(double fineAmount) {
        this.price += fineAmount;
        System.out.println("Fine added to payment. New total price: " + this.price);
    }

    public boolean validate() {
        // Validation logic, e.g., check if payment details are correct
        if (this.price > 0) {
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

    public static void generateInvoice(double amount, String accountEmail) {
//    System.out.println("--------------------------------------------------");
//    System.out.println("                 PAYMENT INVOICE                  ");
//    System.out.println("--------------------------------------------------");
//    System.out.printf("%-20s: %s%n", "Price", String.format("$%.2f", this.price));
//    System.out.printf("%-20s: %s%n", "Payment Date", this.paymentDate.toString());
//    System.out.printf("%-20s: %s%n", "Payment Status", this.paymentStatus);
//    System.out.println("--------------------------------------------------");
//    System.out.println("                   Thank you!                     ");
//    System.out.println("--------------------------------------------------");
        JFrame frame = new JFrame("Payment Confirmation");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 228, 225)); // Soft pink background

        String packageName = Database.getPackageNamesByPrice(amount);
        String packageDescription = Database.getPackageDescriptionByPrice(amount);
        String date = LocalDate.now().toString();

        JLabel titleLabel = new JLabel("Payment Confirmation", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel packageLabel = new JLabel("Package: " + packageName, SwingConstants.LEFT);
        JLabel priceLabel = new JLabel("Price: $" + amount, SwingConstants.LEFT);
        JLabel accountLabel = new JLabel("PayPal Account: " + accountEmail, SwingConstants.LEFT);
        JTextArea descriptionLabel = new JTextArea("Package Description: " + packageDescription);
        descriptionLabel.setWrapStyleWord(true);
        descriptionLabel.setLineWrap(true);
        descriptionLabel.setEditable(false);
        descriptionLabel.setOpaque(false);
        JLabel dateLabel = new JLabel("Date: " + date, SwingConstants.LEFT);

        packageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        okButton.addActionListener(e -> frame.dispose());

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(40));
        panel.add(packageLabel);
        panel.add(priceLabel);
        panel.add(accountLabel);
        //panel.add(Box.createHorizontalStrut(50));
        panel.add(descriptionLabel);
        panel.add(dateLabel);
        panel.add(Box.createVerticalStrut(168));
        panel.add(okButton);

        frame.add(panel);
        frame.setVisible(true);
    }

}
