package FloraWeddingHall.GUI;

import FloraWeddingHall.system.FloraFacade;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ManagerDashboardGUI {

    private static JTable packagesTable;
    private static DefaultTableModel tableModel;
    private static FloraFacade system = new FloraFacade();

    public static void showPackagesWindow() {
        JFrame frame = createMainFrame("Manager Dashboard - Packages", "packagesDashboard.jpg", null);

        // Main area
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(273, 115, 1064, 570);
        mainPanel.setOpaque(false);

        String[] columnNames = {"ID", "Name", "Description", "Services", "Services Prices", "Total Price"};
        JTable table = createTable(columnNames);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();

        Object[][] packagesData = system.getAllPackages(); // Fetch package data
        populateTable(tableModel, packagesData);

        JScrollPane tableScrollPane = new JScrollPane(table);

        JButton addPackageButton = DesignSystem.createStyledButton("Add Package", new Color(139, 69, 19), 150, 30);
        addPackageButton.addActionListener(e -> showAddPackageDialog(frame, tableModel));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setOpaque(false);
        topPanel.add(addPackageButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        JLayeredPane layeredPane = (JLayeredPane) frame.getContentPane();
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        frame.setVisible(true);
    }

    public static void showCustomersWindow() {
        JFrame frame = createMainFrame("Manager Dashboard - Customers", "customersDashboard.jpg", null);

        // Main area
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(273, 115, 1064, 570);
        mainPanel.setOpaque(false);

        // Table for customers
        String[] columnNames = {"Customer ID", "Name", "Email", "Phone"};
        JTable table = createTable(columnNames);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        Object[][] customersData = system.getAllCustomers(); // Retrieve customer data
        populateTable(tableModel, customersData);

        JScrollPane tableScrollPane = new JScrollPane(table);

        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        JLayeredPane layeredPane = (JLayeredPane) frame.getContentPane();
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        frame.setVisible(true);
    }

    public static void showBookingsWindow() {
        JFrame frame = createMainFrame("Manager Dashboard - Bookings", "bookingsDashboard.jpg", null);

        // Main area
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBounds(273, 115, 1064, 570);
        mainPanel.setOpaque(false);

        // Table for bookings
         String[] columnNames = {"Customer ID", "Customer Name", "Package Name", "Date", "Payment Method"};

//        String[] columnNames = {"Booking ID", "Customer ID", "Package ID", "Date", "Payment Status"};
        JTable table = createTable(columnNames);
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        // Fetch bookings data dynamically
        Object[][] bookingsData = system.getAllBookings(); // Retrieve bookings data
        populateTable(tableModel, bookingsData);

        JScrollPane tableScrollPane = new JScrollPane(table);

        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        JLayeredPane layeredPane = (JLayeredPane) frame.getContentPane();
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        frame.setVisible(true);
    }

    private static JFrame createMainFrame(String title, String backgroundImage, JFrame currentFrame) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1383, 768);

        // Main layered pane
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(1383, 768));
        frame.setContentPane(layeredPane);

        // Background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setBounds(0, 0, 1383, 768);
        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);

        // Sidebar
        JPanel sidebar = createSidebar(frame);
        layeredPane.add(sidebar, JLayeredPane.PALETTE_LAYER);

        frame.setLocationRelativeTo(null);
        return frame;
    }

    private static JPanel createSidebar(JFrame currentFrame) {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1, 0, 10));
        sidebar.setBounds(50, 200, 210, 370);
        sidebar.setOpaque(false);

        // Buttons for different windows
        JButton packagesButton = createStyledButton("Packages", new Color(0x4B2727));
        JButton customersButton = createStyledButton("Customers", new Color(0x4B2727));
        JButton bookingsButton = createStyledButton("Bookings", new Color(0x4B2727));
        JButton logoutButton = createStyledButton("Log Out", new Color(0x4B2727));

        // Add action listeners to the buttons
        packagesButton.addActionListener(e -> {
            currentFrame.dispose(); // Close current frame
            showPackagesWindow();  // Open the Packages window
        });

        customersButton.addActionListener(e -> {
            currentFrame.dispose(); // Close current frame
            showCustomersWindow(); // Open the Customers window
        });

        bookingsButton.addActionListener(e -> {
            currentFrame.dispose(); // Close current frame
            showBookingsWindow();  // Open the Bookings window
        });

        logoutButton.addActionListener(e -> {
            currentFrame.dispose(); // Close current frame
            System.exit(0);         // Terminate the program
        });

        // Add buttons to the sidebar
        sidebar.add(packagesButton);
        sidebar.add(customersButton);
        sidebar.add(bookingsButton);
        sidebar.add(logoutButton);

        return sidebar;
    }

    private static void showAddPackageDialog(JFrame frame, DefaultTableModel tableModel) {
        JDialog addDialog = new JDialog(frame, "Add Package", true);
        addDialog.setSize(400, 300);
        addDialog.setLayout(null);

        // Input fields for package details
        JLabel nameLabel = new JLabel("Package Name");
        JTextField nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Description");
        JTextField descriptionField = new JTextField();
        JLabel servicesLabel = new JLabel("Included Services");
        JTextField servicesField = new JTextField();
        JLabel servicesPriceLabel = new JLabel("Services Prices");
        JTextField servicesPriceField = new JTextField();
        JLabel totalPriceLabel = new JLabel("Total Package Price");
        JTextField totalPriceField = new JTextField();
        JButton saveButton = DesignSystem.createStyledButton("Save", new Color(139, 69, 19), 150, 30);

        // Position components
        nameLabel.setBounds(20, 20, 150, 30);
        nameField.setBounds(180, 20, 200, 30);
        descriptionLabel.setBounds(20, 60, 150, 30);
        descriptionField.setBounds(180, 60, 200, 30);
        servicesLabel.setBounds(20, 100, 150, 30);
        servicesField.setBounds(180, 100, 200, 30);
        servicesPriceLabel.setBounds(20, 140, 150, 30);
        servicesPriceField.setBounds(180, 140, 200, 30);
        totalPriceLabel.setBounds(20, 180, 150, 30);
        totalPriceField.setBounds(180, 180, 200, 30);
        saveButton.setBounds(150, 230, 100, 30);

        DesignSystem.styleLabel(nameLabel);
        DesignSystem.styleLabel(descriptionLabel);
        DesignSystem.styleLabel(servicesLabel);
        DesignSystem.styleLabel(servicesPriceLabel);
        DesignSystem.styleLabel(totalPriceLabel);

        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String services = servicesField.getText();
            String servicesPrice = servicesPriceField.getText();
            String totalPrice = totalPriceField.getText();

            if (name.isEmpty() || description.isEmpty() || services.isEmpty() || servicesPrice.isEmpty() || totalPrice.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!areValidPrices(servicesPrice)) {
                JOptionPane.showMessageDialog(frame, "All service prices must be valid numbers separated by commas.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (!isNumeric(totalPrice)) {
                JOptionPane.showMessageDialog(frame, "Total price must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                tableModel.addRow(new Object[]{null, name, description, services, servicesPrice, totalPrice});
            }
            addDialog.dispose();
        });

        // Add components to dialog
        addDialog.add(nameLabel);
        addDialog.add(nameField);
        addDialog.add(descriptionLabel);
        addDialog.add(descriptionField);
        addDialog.add(servicesLabel);
        addDialog.add(servicesField);
        addDialog.add(servicesPriceLabel);
        addDialog.add(servicesPriceField);
        addDialog.add(totalPriceLabel);
        addDialog.add(totalPriceField);
        addDialog.add(saveButton);

        addDialog.setLocationRelativeTo(frame);
        addDialog.setVisible(true);
    }

    private static boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean areValidPrices(String prices) {
        if (prices == null || prices.trim().isEmpty()) {
            return false;
        }
        String[] priceArray = prices.split(",");
        for (String price : priceArray) {
            if (!isNumeric(price.trim())) {
                return false;
            }
        }
        return true;
    }

    private static JTable createTable(String[] columnNames) {
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        return table;
    }

    private static void populateTable(DefaultTableModel tableModel, Object[][] data) {
        for (Object[] row : data) {
            tableModel.addRow(row);
        }
    }

    private static JButton createStyledButton(String text, Color textColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14)); // Bold font
        button.setForeground(textColor); // Custom text color
        button.setBackground(new Color(0, 0, 0, 0)); // Transparent background
        button.setOpaque(false);
        button.setBorderPainted(false); // Remove default border
        button.setFocusPainted(false); // Remove focus outline
        button.setContentAreaFilled(false); // Ensure transparency
        return button;
    }

}
