package cpit.pkg252;

import static cpit.pkg252.Database.getConnection;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

public class Customer extends User {

    private String name;
    private String phone;
    private String email;
    private String password;
    private String alert;

    Database db= new Database();
        
        public  void showSignupWindow() {
        JFrame frame = new JFrame("Customer Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton signupButton = new JButton("Sign Up");
        JButton switchToLoginButton = new JButton("Already have an account? Login");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(signupButton);
        frame.add(switchToLoginButton);

        frame.setVisible(true);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Here you might call a static method in Customer or another class to validate and register the customer.
                if (Customer.validateCustomer(name, phone, email, password)) {
                    if (db.registerCustomer(name, phone, email, password)) {
                        JOptionPane.showMessageDialog(frame, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // Close the frame and optionally open another one, like a login window.
                        showLoginWindow();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Signup failed. Email might already be registered.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        switchToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the signup and open the login window.
                showLoginWindow(); // Assuming there is also a method to show the login window.
            }
        });
    }


    public void showLoginWindow() {
        JFrame frame = new JFrame("Customer Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton switchToSignupButton = new JButton("Don't have an account? Sign Up");

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(switchToSignupButton);

        frame.add(panel);
        frame.setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                // Attempt to login
                if (db.emailExists(email) && db.loginCustomer(email, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Proceed to the main application or dashboard
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid email or password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        switchToSignupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeGUI();
            }
        });

    }

    

    public static boolean validateCustomer(String name, String phone, String email, String password) {
        if (name == null || name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return false;
        }
        if (!isValidPhone(phone)) {
            System.out.println("Invalid phone number. It should be 10 digits long.");
            return false;
        }
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format.");
            return false;
        }
        if (!isValidPassword(password)) {
            System.out.println("Password must be at least 8 characters long and contain at least one digit.");
            return false;
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    private static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");  // Assuming a 10-digit phone number format
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 8 && password.matches(".*\\d.*");  // At least 8 characters and one digit
    }
}

//
//    public boolean registerCustomer() {
//        // Check if the email is already registered
//        if (findCustomerByEmail(email) != null) {
//            System.out.println("Email is already registered. Please log in.");
//            return false;
//        } else {
//            try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
//                    "INSERT INTO Customer (name, phone, email, password, alerts) VALUES (?, ?, ?, ?, ?)")) {
//                stmt.setString(1, name);
//                stmt.setString(2, phone);
//                stmt.setString(3, email);
//                stmt.setString(4, password);
//                stmt.setString(5, alert); // Use the alert value (empty string initially)
//                stmt.executeUpdate();
//                return true;
//            } catch (SQLException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//    }
//
//    // Login Customer
//    public static Customer loginCustomer(Scanner scanner) {
//        System.out.print("Enter your email: ");
//        String email = scanner.nextLine();
//        System.out.print("Enter your password: ");
//        String password = scanner.nextLine();
//
//        // Validate login from database
//        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
//                "SELECT * FROM Customer WHERE email = ? AND password = ?")) {
//            stmt.setString(1, email);
//            stmt.setString(2, password);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Customer customer = new Customer(
//                        rs.getString("name"),
//                        rs.getString("phone"),
//                        rs.getString("email"),
//                        rs.getString("password")
//                );
//                System.out.println("Customer login successful!");
//                return customer;
//            } else {
//                System.out.println("Invalid email or password.");
//                return null;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // Find Customer by Email
//    private static Customer findCustomerByEmail(String email) {
//        try (Connection conn = Database.getConnection(); PreparedStatement stmt = conn.prepareStatement(
//                "SELECT * FROM Customer WHERE email = ?")) {
//            stmt.setString(1, email);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return new Customer(
//                        rs.getString("name"),
//                        rs.getString("phone"),
//                        rs.getString("email"),
//                        rs.getString("password")
//                );
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    // Getters and Setters
//    @Override
//    public String getEmail() {
//        return email;
//    }
//     // Email Validation (Simple regex for email format)
//    public static boolean isValidEmail(String email) {
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(email);
//        return matcher.matches();
//    }
//
//    // Phone Validation (Only digits and length of 10)
//    public static boolean isValidPhone(String phone) {
//        return phone.matches("\\d{10}");
//    }
//
//    // Password Validation (At least 8 characters, contains at least one digit)
//    public static boolean isValidPassword(String password) {
//        return password.length() >= 8 && password.matches(".*\\d.*");
//    }
//
//    // Validation method
//    public static boolean validateCustomer(String name, String phone, String email, String password) {
//        if (name == null || name.isEmpty()) {
//            System.out.println("Name cannot be empty.");
//            return false;
//        }
//        if (!isValidPhone(phone)) {
//            System.out.println("Invalid phone number. It should be 10 digits long.");
//            return false;
//        }
//        if (!isValidEmail(email)) {
//            System.out.println("Invalid email format.");
//            return false;
//        }
//        if (!isValidPassword(password)) {
//            System.out.println("Password must be at least 8 characters long and contain at least one digit.");
//            return false;
//        }
//        return true;
//    }
  

