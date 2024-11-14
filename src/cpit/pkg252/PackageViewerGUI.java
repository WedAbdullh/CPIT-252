package cpit.pkg252;

import static cpit.pkg252.Customer.validateCustomer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PackageViewerGUI {

    String packageType = "";

    public PackageViewerGUI(String packageType) {
        this.packageType = packageType;
    }

    private void showPackageWindow() {
        String[] details = packageDetails();
        JFrame frame = new JFrame("View Packages");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1383, 768);

        // Create a layered pane to manage layers
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null); // Disable layout manager for custom positioning

        // Background panel for the image
        BackgroundPanel backgroundPanel = new BackgroundPanel("Packeges.jpg");
        backgroundPanel.setBounds(0, 0, 1383, 768); // Set size to fill the frame
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER); // Add to bottom layer

        // Custom color #4B2727
        Color customTextColor = new Color(0x4B2727);

        // Details panel for the main package information
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setOpaque(false); // Make it transparent so the background shows through
        detailsPanel.setBounds(336, 185, 711, 186); // Position within the layered pane

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 24, 0); // Add spacing around components
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel packageNameLabel = new JLabel(details[0]);
        packageNameLabel.setFont(new Font("Arial", Font.BOLD, 22));
        packageNameLabel.setForeground(customTextColor); // Set custom text color
        packageNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 0;
        detailsPanel.add(packageNameLabel, gbc);

        JLabel packageDescriptionLabel = new JLabel(details[1]);
        packageDescriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        packageDescriptionLabel.setForeground(customTextColor); // Set custom text color
        packageDescriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 1;
        detailsPanel.add(packageDescriptionLabel, gbc);

        JLabel packagePriceLabel = new JLabel(details[2] + " Riyals");
        packagePriceLabel.setFont(new Font("Arial", Font.BOLD, 22));
        packagePriceLabel.setForeground(customTextColor); // Set custom text color
        packagePriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridy = 2;
        detailsPanel.add(packagePriceLabel, gbc);

        // Add the details panel to the layered pane at a higher layer
        layeredPane.add(detailsPanel, JLayeredPane.PALETTE_LAYER);

        // Panel for Included Services
        JPanel includedServicesPanel = new JPanel(new BorderLayout());
        includedServicesPanel.setOpaque(false); // Make it transparent
        includedServicesPanel.setBounds(365, 461, 679, 146); // Position it below the details panel

        // Separate panels for services and prices
        JPanel servicePanel = new JPanel(new GridLayout(0, 1, -10, 5)); // Single-column grid for service names
        servicePanel.setOpaque(false);
        JPanel pricePanel = new JPanel(new GridLayout(0, 1, -10, 5));   // Single-column grid for prices
        pricePanel.setOpaque(false);

        // Populate service and price panels
        String[] services = details[3].split(",");
        String[] prices = details[4].split(",");

        for (int i = 0; i < services.length; i++) {
            // Service label
            JLabel serviceLabel = new JLabel(services[i]);
            serviceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            serviceLabel.setForeground(customTextColor); // Set custom text color
            serviceLabel.setHorizontalAlignment(SwingConstants.LEFT);
            servicePanel.add(serviceLabel);

            // Price label
            JLabel priceLabel = new JLabel(prices[i] + " Riyals");
            priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
            priceLabel.setForeground(customTextColor); // Set custom text color
            priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            pricePanel.add(priceLabel);
        }

        // Add servicePanel and pricePanel to includedServicesPanel
        includedServicesPanel.add(servicePanel, BorderLayout.WEST);
        includedServicesPanel.add(pricePanel, BorderLayout.EAST);

        // Add the included services panel to the layered pane
        layeredPane.add(includedServicesPanel, JLayeredPane.PALETTE_LAYER);

        // Add the layered pane to the frame
        frame.add(layeredPane);
        frame.setLocationRelativeTo(null); // Center on screen
        frame.setVisible(true);
    }

    private String[] packageDetails() {
        // Simulating fetching a package based on type
        Package selectedPackage = PackageFactory.createPackage(packageType);
        String[] details = new String[5];
        if (selectedPackage != null) {
            details[0] = selectedPackage.getPackageName();
            details[1] = selectedPackage.getPackageDescription();
            details[2] = selectedPackage.getPackagePrice() + "";
            details[3] = selectedPackage.getIncludedServices();
            details[4] = selectedPackage.getServicesPrices();
        } else {
            // Handle case where no package is found (this should not happen unless there's an error in code)
            JOptionPane.showMessageDialog(null, "No details found for the selected package type.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return details;
    }

}
