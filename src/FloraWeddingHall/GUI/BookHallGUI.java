package FloraWeddingHall.GUI;

import FloraWeddingHall.system.BookingProxy;
import FloraWeddingHall.system.Database;
import FloraWeddingHall.system.FloraFacade;
import FloraWeddingHall.system.PackageFactory;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookHallGUI {

    public static void showBookingPage() {
        JFrame frame = new JFrame("Book a Hall");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of the frame to match the image dimensions
         frame.setSize(1383, 768);
         frame.setLayout(null);

        // Set up the background using DesignSystem
        JPanel backgroundPanel = DesignSystem.createBackgroundPanel("Book page2.jpg");
        frame.setContentPane(backgroundPanel);
        
     

        JComboBox<String> packageDropdown = new JComboBox<>(new String[]{"Wedding Package", "Birthday Package", "Corporate Package"});
        packageDropdown.setBounds(370, 250, 200, 30); // Positioned 
        packageDropdown.setFont(new Font("Arial", Font.PLAIN, 16)); // Match font style
        packageDropdown.setForeground(Color.WHITE);
        packageDropdown.setBackground(Color.decode("#543927")); // Match dropdown background color to design
        frame.add(packageDropdown);
        

        // Date Selector
        JComboBox<String> dateDropdown = new JComboBox<>(new String[]{"2024-11-20", "2024-11-21", "2024-11-22", "2024-11-23"});
        dateDropdown.setBounds(820, 250, 200, 30);
        frame.add(dateDropdown);


        // Add Price Label Directly in the Background Box
        JLabel priceLabel = new JLabel(" "); // Initial text for the price label
        priceLabel.setBounds(450, 412, 200, 30); // Positioned inside the price box
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Adjust font size and style
        priceLabel.setForeground(Color.decode("#543927")); // Match the text color to the design
        frame.add(priceLabel);



        // Payment Method Radio Buttons (moved further downward)
        JRadioButton cashButton = new JRadioButton();
        cashButton.setBounds(840, 440, 25, 25); // Further downward for "Cash"
        cashButton.setOpaque(false); // Transparent to match background
        cashButton.setFocusPainted(false); // Remove focus highlight
        frame.add(cashButton);

        JRadioButton paypalButton = new JRadioButton();
        paypalButton.setBounds(840, 485, 25, 25); // Further downward for "PayPal"
        paypalButton.setOpaque(false);
        paypalButton.setFocusPainted(false);
        frame.add(paypalButton);

        JRadioButton applePayButton = new JRadioButton();
        applePayButton.setBounds(840, 532, 25, 25); // Further downward for "Apple Pay"
        applePayButton.setOpaque(false);
        applePayButton.setFocusPainted(false);
        frame.add(applePayButton);
        // Group the Radio Buttons to ensure only one is selectable
        ButtonGroup paymentGroup = new ButtonGroup();
        paymentGroup.add(cashButton);
        paymentGroup.add(paypalButton);
        paymentGroup.add(applePayButton);
        // Add Radio Buttons to Frame
        frame.add(cashButton);
        frame.add(paypalButton);
        frame.add(applePayButton);

        // Hidden Button for "Book"
        JButton hiddenBookButton = new JButton();
        hiddenBookButton.setBounds(380, 510, 90, 30); // Position over the "Book" area in the image
        hiddenBookButton.setOpaque(false); // Make the button invisible
        hiddenBookButton.setContentAreaFilled(false); // Remove background
        hiddenBookButton.setBorderPainted(false); // Remove border
        hiddenBookButton.setFocusPainted(false); // Remove focus highlight
        frame.add(hiddenBookButton);


        // Event Listener for Package 
        packageDropdown.addActionListener(e -> {
            String selectedPackage = (String) packageDropdown.getSelectedItem();
            double price = getPriceForPackage(selectedPackage);
            priceLabel.setText(String.valueOf(price));
        });

        // Event Listener for Book Button
        hiddenBookButton.addActionListener(e -> {
            String selectedPackage = (String) packageDropdown.getSelectedItem();
            String selectedDate = (String) dateDropdown.getSelectedItem();
            String paymentMethod = cashButton.isSelected() ? "Cash" :
                                   applePayButton.isSelected() ? "Apple pay" :
                                   paypalButton.isSelected() ? "PayPal" : "None";

            if (paymentMethod.equals("None")) {
                JOptionPane.showMessageDialog(frame, "Please select a payment method.");
                return;
            }

            try {
            // Use the Facade to create the booking
            FloraFacade bookingfacade = new FloraFacade();
            bookingfacade.createBooking(2, "Rafal@gmail.com", selectedPackage, selectedDate, paymentMethod); // customerId = 1 (example)

       
        
             // Show a custom confirmation message with options
             Object[] options = {"View Invoice", "Ok"};
             int choice = JOptionPane.showOptionDialog(
             frame,
             "Reservation Confirmed",
             "Confirmation",
             JOptionPane.YES_NO_OPTION,
             JOptionPane.INFORMATION_MESSAGE,
             null,
             options,
             options[1]
        );

        // Handle button actions
        if (choice == JOptionPane.YES_OPTION) {
            // View Invoice functionality
//     JOptionPane.showMessageDialog(frame, "Invoice functionality coming soon.");
 // Show the payment UI instead of a static message
                
// Modify the button's action listener to retrieve package details from the database when the user clicks "View Invoice."
 double price = getPriceForPackage(selectedPackage);
                    PaymentGUI.showPaymentUI(paymentMethod, price);
//         String packageName = Database.getPackageNamesByPrice(price);
//        if (packageName != null) {
//                PaymentGUI.showPaymentUI(paymentMethod, price); // Use database-retrieved package name
//            } else {
//                JOptionPane.showMessageDialog(frame, "Package details could not be retrieved.", "Error", JOptionPane.ERROR_MESSAGE);
//            }
        }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
            }
        });
        frame.setVisible(true);
    }
    //changed to public
    public static double getPriceForPackage(String packageName) {
        PackageFactory factory = new PackageFactory();
        switch (packageName) {
            case "Wedding Package":
                return factory.createPackage("Wedding").getPackagePrice();
            case "Birthday Package":
                return factory.createPackage("Birthday").getPackagePrice();
            case "Corporate Package":
                return factory.createPackage("Corporate").getPackagePrice();
            default:
                return 0.00;
        }
    }
}
