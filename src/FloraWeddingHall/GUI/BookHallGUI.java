package FloraWeddingHall.GUI;

import FloraWeddingHall.system.BookingProxy;
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
        frame.setSize(1383, 780);
        frame.setLayout(null);

        
         // Add the background image
        JLabel background = new JLabel(new ImageIcon("Book page.jpg")); // Make sure the image path is correct
        background.setBounds(0, 0, 1383, 768); // Same dimensions as the frame
        frame.setContentPane(background);
        frame.setLayout(null);


        JComboBox<String> packageDropdown = new JComboBox<>(new String[]{"Wedding Package", "Birthday Package", "Corporate Package"});
        packageDropdown.setBounds(270, 170, 200, 30); // Positioned below the label
        packageDropdown.setFont(new Font("Arial", Font.PLAIN, 16)); // Match font style
        packageDropdown.setForeground(Color.WHITE);
        packageDropdown.setBackground(Color.decode("#543927")); // Match dropdown background color to design
        frame.add(packageDropdown);
        

        // Date Selector
        JComboBox<String> dateDropdown = new JComboBox<>(new String[]{"2024-11-20", "2024-11-21", "2024-11-22", "2024-11-23"});
        dateDropdown.setBounds(270, 250, 200, 30);
        frame.add(dateDropdown);


        // Add Price Label Directly in the Background Box
JLabel priceLabel = new JLabel(" "); // Initial text for the price label
priceLabel.setBounds(335, 588, 200, 30); // Positioned inside the price box
priceLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Adjust font size and style
priceLabel.setForeground(Color.decode("#543927")); // Match the text color to the design
frame.add(priceLabel);



        // Radio Buttons
// Payment Method Radio Buttons (moved further downward)
JRadioButton cashButton = new JRadioButton();
cashButton.setBounds(780, 492, 25, 25); // Further downward for "Cash"
cashButton.setOpaque(false); // Transparent to match background
cashButton.setFocusPainted(false); // Remove focus highlight
frame.add(cashButton);

JRadioButton paypalButton = new JRadioButton();
paypalButton.setBounds(780, 545, 25, 25); // Further downward for "PayPal"
paypalButton.setOpaque(false);
paypalButton.setFocusPainted(false);
frame.add(paypalButton);

JRadioButton applePayButton = new JRadioButton();
applePayButton.setBounds(780, 600, 25, 25); // Further downward for "Apple Pay"
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
hiddenBookButton.setBounds(270, 680, 150, 50); // Position over the "Book" area in the image
hiddenBookButton.setOpaque(false); // Make the button invisible
hiddenBookButton.setContentAreaFilled(false); // Remove background
hiddenBookButton.setBorderPainted(false); // Remove border
hiddenBookButton.setFocusPainted(false); // Remove focus highlight
frame.add(hiddenBookButton);




        // Event Listener for Package and Date
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

             FloraFacade paymentfacade = new FloraFacade();

        // Process payment
         boolean isPaymentSuccessful = paymentfacade.processPayment(paymentMethod, 200.0); // Example amount
    
            try {
            // Use the Facade to create the booking
               FloraFacade bookingfacade = new FloraFacade();
                boolean isBooked = bookingfacade.createBooking(2,"user123", "wedding", "2024-12-15", "PayPal");

            
                // proxy logic 
                BookingProxy bookingProxy = new BookingProxy("user12345"); // Example user
                Date bookingDate = new SimpleDateFormat("yyyy-MM-dd").parse(selectedDate);
                double price = getPriceForPackage(selectedPackage);
 
        
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
            JOptionPane.showMessageDialog(frame, "Invoice functionality coming soon.");
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
