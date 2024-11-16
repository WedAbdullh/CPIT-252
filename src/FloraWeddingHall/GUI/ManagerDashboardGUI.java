package FloraWeddingHall.GUI;

import javax.swing.JFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerDashboardGUI {

    private static JTable packagesTable;
    private static DefaultTableModel tableModel;

    public static void showPackagesWindow() {
        JFrame frame = new JFrame("Manager Dashboard - Packages");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1383, 768);
        frame.setLayout(new BorderLayout());

        // Background Image
        BackgroundPanel backgroundPanel = new BackgroundPanel("packagesDashboard.jpg");
        frame.setContentPane(backgroundPanel);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(5, 1));

        JButton packagesButton = new JButton("Packages");
        JButton customersButton = new JButton("Customers");
        JButton bookingsButton = new JButton("Bookings");
        JButton logoutButton = new JButton("Log Out");

        sidebar.add(packagesButton);
        sidebar.add(customersButton);
        sidebar.add(bookingsButton);
        sidebar.add(logoutButton);

        frame.add(sidebar, BorderLayout.WEST);

        // Main area
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Table to display packages
        String[] columnNames = {"ID", "Name", "Description", "Services", "Services Prices", "Total Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        packagesTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(packagesTable);

        // Add Package button
        JButton addPackageButton = new JButton("Add Package");
        addPackageButton.addActionListener(e -> showAddPackageDialog(frame));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.add(addPackageButton);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void showAddPackageDialog(JFrame frame) {
        JDialog addDialog = new JDialog(frame, "Add Package", true);
        addDialog.setSize(400, 300);
        addDialog.setLayout(new GridLayout(5, 2));

        // Input fields for package details
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel descriptionLabel = new JLabel("Description:");
        JTextField descriptionField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String description = descriptionField.getText();
            String price = priceField.getText();

            if (name.isEmpty() || description.isEmpty() || price.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Add to database and table
            //addPackageToDatabase(name, description, Double.parseDouble(price));
           // tableModel.addRow(new Object[]{null, name, description, price});
            addDialog.dispose();
        });

        addDialog.add(nameLabel);
        addDialog.add(nameField);
        addDialog.add(descriptionLabel);
        addDialog.add(descriptionField);
        addDialog.add(priceLabel);
        addDialog.add(priceField);
        addDialog.add(new JLabel());
        addDialog.add(saveButton);

        addDialog.setLocationRelativeTo(frame);
        addDialog.setVisible(true);
    }

//    private void addPackageToDatabase(String name, String description, double price) {
//        String query = "INSERT INTO Package (name, description, price) VALUES (?, ?, ?)";
//
//        try (Connection conn = Database.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, name);
//            stmt.setString(2, description);
//            stmt.setDouble(3, price);
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Failed to save package to the database.", "Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
//    }
}

//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class DashboardGUI extends JFrame {
//
//    private JTable packagesTable;
//    private DefaultTableModel tableModel;
//
//    public DashboardGUI() {
//        // Frame settings
//        setTitle("Dashboard");
//        setSize(1000, 600);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // Sidebar
//        JPanel sidebar = new JPanel();
//        sidebar.setLayout(new GridLayout(5, 1));
//        sidebar.setBackground(new Color(0xF7F7F7));
//
//        JButton packagesButton = new JButton("Packages");
//        JButton customersButton = new JButton("Customers");
//        JButton bookingsButton = new JButton("Bookings");
//        JButton logoutButton = new JButton("Log Out");
//
//        sidebar.add(packagesButton);
//        sidebar.add(customersButton);
//        sidebar.add(bookingsButton);
//        sidebar.add(logoutButton);
//
//        add(sidebar, BorderLayout.WEST);
//
//        // Main area
//        JPanel mainPanel = new JPanel(new BorderLayout());
//
//        // Table to display packages
//        String[] columnNames = {"ID", "Name", "Description", "Price"};
//        tableModel = new DefaultTableModel(columnNames, 0);
//        packagesTable = new JTable(tableModel);
//        JScrollPane tableScrollPane = new JScrollPane(packagesTable);
//
//        // Add Package button
//        JButton addPackageButton = new JButton("Add Package");
//        addPackageButton.addActionListener(e -> showAddPackageDialog());
//
//        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
//        topPanel.add(addPackageButton);
//
//        mainPanel.add(topPanel, BorderLayout.NORTH);
//        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
//
//        add(mainPanel, BorderLayout.CENTER);
//
//        setLocationRelativeTo(null);
//        setVisible(true);
//    }
//
//    private void showAddPackageDialog() {
//        JDialog addDialog = new JDialog(this, "Add Package", true);
//        addDialog.setSize(400, 300);
//        addDialog.setLayout(new GridLayout(5, 2));
//
//        // Input fields for package details
//        JLabel nameLabel = new JLabel("Name:");
//        JTextField nameField = new JTextField();
//
//        JLabel descriptionLabel = new JLabel("Description:");
//        JTextField descriptionField = new JTextField();
//
//        JLabel priceLabel = new JLabel("Price:");
//        JTextField priceField = new JTextField();
//
//        JButton saveButton = new JButton("Save");
//        saveButton.addActionListener(e -> {
//            String name = nameField.getText();
//            String description = descriptionField.getText();
//            String price = priceField.getText();
//
//            if (name.isEmpty() || description.isEmpty() || price.isEmpty()) {
//                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Add to database and table
//            addPackageToDatabase(name, description, Double.parseDouble(price));
//            tableModel.addRow(new Object[]{null, name, description, price});
//            addDialog.dispose();
//        });
//
//        addDialog.add(nameLabel);
//        addDialog.add(nameField);
//        addDialog.add(descriptionLabel);
//        addDialog.add(descriptionField);
//        addDialog.add(priceLabel);
//        addDialog.add(priceField);
//        addDialog.add(new JLabel());
//        addDialog.add(saveButton);
//
//        addDialog.setLocationRelativeTo(this);
//        addDialog.setVisible(true);
//    }
//
//    private void addPackageToDatabase(String name, String description, double price) {
//        String query = "INSERT INTO Package (name, description, price) VALUES (?, ?, ?)";
//
//        try (Connection conn = Database.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, name);
//            stmt.setString(2, description);
//            stmt.setDouble(3, price);
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Failed to save package to the database.", "Error", JOptionPane.ERROR_MESSAGE);
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(DashboardGUI::new);
//    }
//}
