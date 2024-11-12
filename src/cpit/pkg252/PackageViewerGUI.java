/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cpit.pkg252;

/**
 *
 * @author wedalotibi
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PackageViewerGUI {

    public PackageViewerGUI() {
        initializePackageViewer();
    }

    private void initializePackageViewer() {
        JFrame frame = new JFrame("View Packages");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);

        // Panel to hold buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1)); // Grid layout to display package options

        // Buttons for each package type
        JButton weddingButton = new JButton("Wedding");
        JButton corporateButton = new JButton("Corporate");
        JButton birthdayButton = new JButton("Birthday");
        JButton cancelButton = new JButton("Cancel");

        panel.add(weddingButton);
        panel.add(corporateButton);
        panel.add(birthdayButton);
        panel.add(cancelButton);

        // Adding the panel to the frame
        frame.add(panel);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);

        // Action Listeners for buttons
        weddingButton.addActionListener(e -> displayPackageDetails("Wedding"));
        corporateButton.addActionListener(e -> displayPackageDetails("Corporate"));
        birthdayButton.addActionListener(e -> displayPackageDetails("Birthday"));
        cancelButton.addActionListener(e -> {
                frame.dispose();// Close the package viewer
                new HomeGUI();} ); // Launch a new instance of the home page
    }

    private void displayPackageDetails(String packageType) {
        // Simulating fetching a package based on type
        Package selectedPackage = PackageFactory.createPackage(packageType);
        if (selectedPackage != null) {
            // Display package details in a new dialog
            JOptionPane.showMessageDialog(null, selectedPackage.getDetails(), packageType + " Package Details", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Handle case where no package is found (this should not happen unless there's an error in code)
            JOptionPane.showMessageDialog(null, "No details found for the selected package type.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

   
}