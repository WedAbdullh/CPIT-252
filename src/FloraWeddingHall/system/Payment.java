
package FloraWeddingHall.system;

import java.awt.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagLayout;
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

        JFrame frame = new JFrame("Payment Confirmation");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(255, 228, 225)); // Soft pink background

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10); // General padding

        // Fetch package details based on the price
        String packageName = Database.getPackageNamesByPrice(amount);
        String packageDescription = Database.getPackageDescriptionByPrice(amount);
        String date = LocalDate.now().toString();

        JLabel titleLabel = new JLabel("Reservation Summary", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(titleLabel, c);

        JLabel packageLabel = new JLabel("Package: " + packageName);
        packageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        c.gridy++;
        c.gridwidth = 1;
        c.insets = new Insets(10, 5, 10, 10); // Reduced left inset
        panel.add(packageLabel, c);

        JLabel priceLabel = new JLabel("Price: $" + amount);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        c.gridy++;
        panel.add(priceLabel, c);

        JLabel accountLabel = new JLabel("PayPal Account: " + accountEmail);
        accountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        c.gridy++;
        panel.add(accountLabel, c);

        JTextArea descriptionLabel = new JTextArea("Package Description: " + packageDescription);
        descriptionLabel.setWrapStyleWord(true);
        descriptionLabel.setLineWrap(true);
        descriptionLabel.setEditable(false);
        descriptionLabel.setOpaque(false);
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        c.gridy++;
        c.gridheight = 2; // Allow more space for description
        panel.add(descriptionLabel, c);
        c.gridheight = 1;

        JLabel dateLabel = new JLabel("Date: " + date);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        c.gridy += 2; // Adjust for multi-line description
        panel.add(dateLabel, c);

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.addActionListener(e -> frame.dispose());
        c.gridy++;
        c.gridx = 0;
        c.gridwidth = 2; // Center the OK button
        c.fill = GridBagConstraints.CENTER;
        panel.add(okButton, c);

        frame.add(panel);
        frame.setVisible(true);
    }

}
