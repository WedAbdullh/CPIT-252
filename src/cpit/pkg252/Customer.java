package cpit.pkg252;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    Database db = new Database();

    public void showSignupWindow() {

        JFrame frame = new JFrame("Customer Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1383, 768);
        frame.setLayout(null); // Disable layout manager

        //Background Image
        BackgroundPanel backgroundPanel = new BackgroundPanel("SignUp.jpg");
        frame.setContentPane(backgroundPanel);

        // Create labels and text fields with appropriate styling and positioning
        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone");
        JTextField phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();

        // Set positions and dimensions directly
        // Assuming the bottom right margin space and components dimensions
        int xPosition = 870; // Starting x position for labels
        int yStart = 250; // Starting y position for the first label and field

        nameLabel.setBounds(xPosition, yStart, 80, 30);
        nameField.setBounds(xPosition + 85, yStart, 200, 30);

        phoneLabel.setBounds(xPosition, yStart + 40, 80, 30);
        phoneField.setBounds(xPosition + 85, yStart + 40, 200, 30);

        emailLabel.setBounds(xPosition, yStart + 80, 80, 30);
        emailField.setBounds(xPosition + 85, yStart + 80, 200, 30);

        passwordLabel.setBounds(xPosition, yStart + 120, 80, 30);
        passwordField.setBounds(xPosition + 85, yStart + 120, 200, 30);

        // Add components to the frame
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);

        // Ensure components are visible and appropriately sized
        styleTextField(nameField);
        styleTextField(phoneField);
        styleTextField(emailField);
        styleTextField(passwordField);

        // Define reddish-brown color
        Color reddishBrown = new Color(139, 69, 19);

        // Create sign-up button and apply reddish-brown color
        JButton signupButton = new JButton("Sign Up");
        JLabel switchToLoginButton = new JLabel("Already have an account? Login");

        signupButton.setBackground(reddishBrown);
        signupButton.setForeground(Color.WHITE); // Set text color to white for better readability
        signupButton.setFont(new Font("Arial", Font.BOLD, 14));
        signupButton.setBounds(940, 450, 200, 40); // Set position and size

        switchToLoginButton.setForeground(reddishBrown); // Change color to indicate it's clickable
        switchToLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovered
        switchToLoginButton.setBounds(945, 490, 250, 50); // Position under the "Sign Up" button
        frame.add(switchToLoginButton);

        // Ensure the button text is visible and not hidden by the focus paint
        signupButton.setOpaque(true);
        signupButton.setBorderPainted(false); // Optional: remove border if you want the color to fill the button completely

        // Add button to the frame
        frame.add(signupButton);

        frame.setVisible(true);
        // Example action listener for the sign-up button
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (validateCustomer(name, phone, email, password)) {
                    if (db.registerCustomer(name, phone, email, password)) {
                        JOptionPane.showMessageDialog(frame, "Signup successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose();
                        showLoginWindow();  // Transition to the login window after successful signup
                    } else {
                        JOptionPane.showMessageDialog(frame, "Signup failed. Email might already be registered.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid data. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        switchToLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                showLoginWindow(); // Show login window when switching from sign up
            }
        });

    }

    private void addComponent(JFrame frame, Component component, GridBagConstraints gbc) {
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(component, gbc);
    }

    private void styleTextField(JTextField textField) {
        textField.setBorder(BorderFactory.createCompoundBorder(
                textField.getBorder(),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
    }

    private void styleButton(JButton button) {
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setBackground(new Color(255, 255, 255, 200)); // Semi-transparent white
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    public void showLoginWindow() {
        JFrame frame = new JFrame("Customer Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1383, 768);
        frame.setLayout(null); // Disable layout manager

        // Background Image
        BackgroundPanel backgroundPanel = new BackgroundPanel("Login.jpg");
        frame.setContentPane(backgroundPanel);

        // Create labels and text fields with appropriate styling and positioning
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        // Define positions
        int xPosition = 870; // Starting x position for labels
        int yStart = 300; // Starting y position for the first label and field

        emailLabel.setBounds(xPosition, yStart, 80, 30);
        emailField.setBounds(xPosition + 85, yStart, 200, 30);

        passwordLabel.setBounds(xPosition, yStart + 40, 80, 30);
        passwordField.setBounds(xPosition + 85, yStart + 40, 200, 30);

        // Add components to the frame
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);

        // Define reddish-brown color
        Color reddishBrown = new Color(139, 69, 19);

        // Create login button and apply styling
        JButton loginButton = new JButton("Login");
        JLabel switchToSignupButton = new JLabel("Don't have an account? Sign Up");

        loginButton.setBackground(reddishBrown);
        loginButton.setForeground(Color.WHITE); // Set text color to white for better readability
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(940, 420, 200, 40); // Set position and size

        switchToSignupButton.setForeground(reddishBrown); // Change color to indicate it's clickable
        switchToSignupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovered
        switchToSignupButton.setBounds(950, 470, 250, 50); // Position under the "Login" button
        frame.add(switchToSignupButton);

        // Ensure the button text is visible and not hidden by the focus paint
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false); // Optional: remove border if you want the color to fill the button completely

        // Add button to the frame
        frame.add(loginButton);

        frame.setVisible(true);

        // Action listener for the login button
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

        // Action listener for switching to signup
        switchToSignupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                showSignupWindow(); // Show signup window when switching from login
            }
        });
    }

    public static boolean validateCustomer(String name, String phone, String email, String password) {
        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Name cannot be empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidPhone(phone)) {
            JOptionPane.showMessageDialog(null, "Invalid phone number. It should be 10 digits long.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long and contain at least one digit.", "Validation Error", JOptionPane.ERROR_MESSAGE);
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
