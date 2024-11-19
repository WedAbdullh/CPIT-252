package FloraWeddingHall.GUI;

import FloraWeddingHall.system.FloraFacade;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeGUI {

    FloraFacade facade = new FloraFacade();

    public static void showHomePage() {
        JFrame frame = new JFrame("Flora Wedding Hall - Homepage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1383, 768);
        frame.setLayout(null);

        // Set up the background using DesignSystem
        JPanel backgroundPanel = DesignSystem.createBackgroundPanel("Home.jpg");
        frame.setContentPane(backgroundPanel);

        // Add "Logout" label (right-aligned at the top)
        JLabel logoutLabel = createLabel("Logout", Color.BLACK, 18);
        logoutLabel.setBounds(50, 10, 100, 30); // Position independently
        frame.add(logoutLabel);

        // Add "Packages" label (middle-right at the top)
        JLabel packagesLabel = createLabel("Packages", Color.BLACK, 18);
        packagesLabel.setBounds(400, 10, 150, 30); // Position independently
        frame.add(packagesLabel);

        // Add "Booking" label (middle-left at the top)
        JLabel bookingLabel = createLabel("Book", Color.BLACK, 18);
        bookingLabel.setBounds(900, 10, 150, 30); // Position independently
        frame.add(bookingLabel);

        // Add the "Wedding Package" button
        JButton weddingPackageButton = createButton("Wedding Package", Color.WHITE, 20);
        weddingPackageButton.setBounds(30, 590, 600, 50); // Adjust vertical position slightly upward
        frame.add(weddingPackageButton);

        // Add the "Birthday Package" button
        JButton birthdayPackageButton = createButton("Birthday Package", Color.WHITE, 20);
        birthdayPackageButton.setBounds(440, 590, 500, 50); // Adjust horizontal spacing
        frame.add(birthdayPackageButton);

        // Add the "Corporate Package" button
        JButton corporatePackageButton = createButton("Corporate Package", Color.WHITE, 20);
        corporatePackageButton.setBounds(850, 590, 400, 50); // Adjust horizontal spacing
        frame.add(corporatePackageButton);

        frame.setVisible(true);

        // Add listeners for labels
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
            }
        });

        packagesLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the Packages Viewer for the desired package type
                frame.dispose(); // Ensure this line is being executed
                PackageViewerGUI packageViewer = new PackageViewerGUI("Wedding"); // Or dynamically determine the package type
                packageViewer.showPackageWindow();

            }
        });

        bookingLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose(); // Ensure this line is being executed
                BookHallGUI.showBookingPage(); // Open the booking page
            }
        });

        // Add listeners for package buttons to show package details
        weddingPackageButton.addActionListener(createPackageButtonListener("Wedding"));
        birthdayPackageButton.addActionListener(createPackageButtonListener("Birthday"));
        corporatePackageButton.addActionListener(createPackageButtonListener("Corporate"));
    }

    // This method creates an ActionListener for each package button
    private static ActionListener createPackageButtonListener(String packageType) {
        return e -> {
            // Open the corresponding package details page
            PackageViewerGUI packageViewer = new PackageViewerGUI(packageType);
            packageViewer.showPackageWindow();
        };
    }

    private static JLabel createLabel(String text, Color color, int fontSize) {
        JLabel label = new JLabel(text);
        label.setForeground(color);
        label.setFont(new Font("Arial", Font.BOLD, fontSize));
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return label;
    }

    private static JButton createButton(String text, Color color, int fontSize) {
        JButton button = new JButton(text);
        button.setForeground(color);
        button.setFont(new Font("Arial", Font.PLAIN, fontSize));
        button.setContentAreaFilled(false); // Makes the button transparent
        button.setBorderPainted(false); // Removes the border
        button.setFocusPainted(false); // Removes focus highlighting
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}
