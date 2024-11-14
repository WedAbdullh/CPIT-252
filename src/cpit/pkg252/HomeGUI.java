package cpit.pkg252;

import cpit.pkg252.Customer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeGUI {

    private JFrame frame;

    public HomeGUI() {
        initializeHomePage();
    }

    private void initializeHomePage() {
        frame = new JFrame("Home Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(3, 1));

        JButton viewPackagesButton = new JButton("View Packages");
        JButton bookHallButton = new JButton("Book Hall");
        JButton logoutButton = new JButton("Log Out");

        frame.add(viewPackagesButton);
        frame.add(bookHallButton);
        frame.add(logoutButton);

        // Setting action listeners
        viewPackagesButton.addActionListener(new ViewPackagesAction());
        bookHallButton.addActionListener(new BookHallAction());
        logoutButton.addActionListener(new LogoutAction());

        frame.setVisible(true);
    }

    // ActionListener for viewing packages
    private class ViewPackagesAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
          //new PackageViewerGUI();
        }
    }

    // ActionListener for booking hall
    private class BookHallAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            bookHall();
        }
    }

    // ActionListener for logout
    private class LogoutAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.dispose(); // Close the home page
            System.out.println("Logged out successfully.");
            new Customer().showLoginWindow(); // Optionally, redirect to login page
        }
    }

    private void viewPackages() {
        System.out.println("Package viewing functionality will be implemented here.");
    }

    private void bookHall() {
        System.out.println("Hall booking functionality will be implemented here.");
    }
}