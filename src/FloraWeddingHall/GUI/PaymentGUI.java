/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FloraWeddingHall.GUI;

/**
 *
 * @author wedalotibi
 */

import FloraWeddingHall.system.Database;
import FloraWeddingHall.system.FloraFacade;
import FloraWeddingHall.system.Payment;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.border.EmptyBorder;


public class PaymentGUI {
    private JFrame frame;

    public PaymentGUI() {
        // Placeholder to start the GUI; actual method calls will be added later
       
    }
//   Ensure PaymentGUI methods (initializeCashPaymentUI, initializePayPalPaymentUI, etc.) depend on the database for package and price retrieval.

    public static void initializeCashPaymentUI(Double amount) {
        JFrame frame = new JFrame("Cash Payment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Center on screen


        // Create a panel with BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(255, 228, 225));  // Soft pink background color

//         Fetch package name from the database
        String packageName = Database.getPackageNamesByPrice(amount);
     
        JLabel packageLabel = new JLabel("Package: " + packageName, SwingConstants.CENTER);
        packageLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        JLabel priceLabel = new JLabel("Price: $" + amount, SwingConstants.CENTER);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 18));
  
        // Label for the package and price
        packageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center align
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // Center align
        packageLabel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));  // Add some space around the label

        // Styling the button
        JButton payButton = new JButton("Confirm Cash Payment");
        payButton.setForeground(Color.BLACK);
        payButton.setBackground(Color.decode("#6A4E42"));  // Darker shade for button
        payButton.setFont(new Font("Arial", Font.BOLD, 14));
        payButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        payButton.addActionListener(e -> {
            if (packageName != null) { //  && !packageName.isEmpty()
                JOptionPane.showMessageDialog(null, "Payment confirmed for " + packageName, "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Close the frame on successful action
                
            } else {
                JOptionPane.showMessageDialog(frame, "No package found to process payment.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(packageLabel);
        panel.add(priceLabel);
        panel.add(Box.createVerticalStrut(50));  // Spacer
        panel.add(payButton);

        // Add background panel to frame
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void initializePayPalPaymentUI(Double amount) {
      JFrame frame = new JFrame("PayPal Payment");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 300);
    frame.setLocationRelativeTo(null); // Center on screen

    // Use BorderLayout to manage overall content
    frame.setLayout(new BorderLayout());

    // Center panel that holds all content
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    centerPanel.setBackground(new Color(255, 228, 225)); // Soft pink background
    centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100)); // Adjust padding to better align contents

    // Labels for the package and price
    String packageName = Database.getPackageNamesByPrice(amount);
    JLabel packageLabel = new JLabel("Package: " + packageName);
    JLabel priceLabel = new JLabel("Price: $" + amount);
    packageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    packageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Label and text field for PayPal email
    JLabel emailLabel = new JLabel("Enter PayPal Email:");
    emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    JTextField emailField = new JTextField(20);
    emailLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    emailField.setMaximumSize(new Dimension(300, 30)); // Control the width of the text field
    emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Confirm button
    JButton payButton = new JButton("Confirm PayPal Payment");
    payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    payButton.setFont(new Font("Arial", Font.PLAIN, 16));
    payButton.setForeground(Color.BLACK);
    payButton.setBackground(Color.decode("#6A4E42"));
    payButton.addActionListener(e -> {
            if (packageName != null && !packageName.isEmpty()) {
                Payment.generateInvoice( amount,  emailField.getText());
                //JOptionPane.showMessageDialog(null, "Payment confirmed for " + packageName, "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Close the frame on successful action
                
            } else {
                JOptionPane.showMessageDialog(frame, "No package found to process payment.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    // Add components to the center panel
    centerPanel.add(packageLabel);
    centerPanel.add(priceLabel);
    centerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Adds space between price label and email label
    centerPanel.add(emailLabel);
    centerPanel.add(emailField);
    centerPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Adds space between email field and button
    centerPanel.add(payButton);

    // Add the center panel to the frame
    frame.add(centerPanel, BorderLayout.CENTER);

    frame.setVisible(true);

    }

    public static void initializeApplePayPaymentUI(Double amount) {
        JFrame frame = new JFrame("ApplePay Payment");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 300);
    frame.setLocationRelativeTo(null); // Center on screen

    // Set up the main panel with BoxLayout for vertical alignment
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(new Color(255, 228, 225)); // Soft pink background
    mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100)); // Padding around the panel

    // Package and price information
    String packageName = Database.getPackageNamesByPrice(amount);
    JLabel packageLabel = new JLabel("Package: " + packageName);
    JLabel priceLabel = new JLabel("Price: $" + amount);
    packageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    packageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Fields for ApplePay details
    JLabel accountLabel = new JLabel("Enter Apple Account:");
    accountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    JTextField accountField = new JTextField(20);
    accountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
    accountField.setMaximumSize(new Dimension(300, 30)); // Control the width to fit the layout
    accountField.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Button to confirm payment
    JButton payButton = new JButton("Confirm ApplePay Payment");
    payButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    payButton.setFont(new Font("Arial", Font.PLAIN, 16));
    payButton.setForeground(Color.BLACK);
    payButton.setBackground(Color.decode("#6A4E42"));  // A dark shade for better visibility
    payButton.addActionListener(e -> {
            if (packageName != null && !packageName.isEmpty()) {
                Payment.generateInvoice( amount,  accountField.getText());
                //JOptionPane.showMessageDialog(null, "Payment confirmed for " + packageName, "Payment Successful", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose(); // Close the frame on successful action
                
            } else {
                JOptionPane.showMessageDialog(frame, "No package found to process payment.", "Payment Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    // Adding components to main panel
    mainPanel.add(packageLabel);
    mainPanel.add(priceLabel);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Space between price and account input
    mainPanel.add(accountLabel);
    mainPanel.add(accountField);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Space before the button
    mainPanel.add(payButton);

    // Add main panel to the frame and display it
    frame.add(mainPanel, BorderLayout.CENTER);
    frame.setVisible(true);
    }

    public static void showPaymentUI(String paymentMethod, double amount) {
    switch (paymentMethod.toLowerCase()) {
        case "cash":
            initializeCashPaymentUI(amount);
            break;
        case "paypal":
            initializePayPalPaymentUI(amount);
            break;
        case "applepay":
            initializeApplePayPaymentUI(amount);
            break;
        default:
            JOptionPane.showMessageDialog(null, "Invalid payment method selected!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
//
//    public void showConfirmationPage( double amount, String accountEmail) {
//     JFrame frame = new JFrame("Payment Confirmation");
//    frame.setSize(600, 400);
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.setLocationRelativeTo(null);
//
//    JPanel panel = new JPanel();
//    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//    panel.setBackground(new Color(255, 228, 225)); // Soft pink background
//
//    String packageName = Database.getPackageNamesByPrice(amount);
//    String packageDescription = Database.getPackageDescriptionByPrice(amount);
//    String date = LocalDate.now().toString();
//
//    JLabel titleLabel = new JLabel("Payment Confirmation", SwingConstants.CENTER);
//    titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//
//    JLabel packageLabel = new JLabel("Package: " + packageName, SwingConstants.LEFT);
//    JLabel priceLabel = new JLabel("Price: $" + amount, SwingConstants.LEFT);
//    JLabel accountLabel = new JLabel("PayPal Account: " + accountEmail, SwingConstants.LEFT);
//    JTextArea descriptionLabel = new JTextArea("Package Description: " + packageDescription);
//    descriptionLabel.setWrapStyleWord(true);
//    descriptionLabel.setLineWrap(true);
//    descriptionLabel.setEditable(false);
//    descriptionLabel.setOpaque(false);
//    JLabel dateLabel = new JLabel("Date: " + date, SwingConstants.LEFT);
//
//    packageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//    priceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//    accountLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//    descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//    dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
//
//    JButton okButton = new JButton("OK");
//    okButton.setFont(new Font("Arial", Font.BOLD, 16));
//    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//    okButton.addActionListener(e -> frame.dispose());
//
//    panel.add(titleLabel);
//    panel.add(Box.createVerticalStrut(40));
//    panel.add(packageLabel);
//    panel.add(priceLabel);
//    panel.add(accountLabel);
//    //panel.add(Box.createHorizontalStrut(50));
//    panel.add(descriptionLabel);
//    panel.add(dateLabel);
//    panel.add(Box.createVerticalStrut(168));
//    panel.add(okButton);
//
//    frame.add(panel);
//    frame.setVisible(true);
//    }
//    
//    
    
}
