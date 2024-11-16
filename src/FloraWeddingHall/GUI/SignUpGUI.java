package FloraWeddingHall.GUI;

import FloraWeddingHall.system.FloraFacade;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class SignUpGUI {

    public static void showSignupWindow() {
        JFrame frame = new JFrame("Customer Signup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1383, 768);
        frame.setLayout(null);

        // Set up the background using DesignSystem
        JPanel backgroundPanel = DesignSystem.createBackgroundPanel("SignUp.jpg");
        frame.setContentPane(backgroundPanel);

        // Create and style labels and text fields
        JLabel nameLabel = new JLabel("Name");
        DesignSystem.styleLabel(nameLabel);
        JTextField nameField = new JTextField();
        DesignSystem.styleTextField(nameField);

        JLabel phoneLabel = new JLabel("Phone");
        DesignSystem.styleLabel(phoneLabel);
        JTextField phoneField = new JTextField();
        DesignSystem.styleTextField(phoneField);

        JLabel emailLabel = new JLabel("Email");
        DesignSystem.styleLabel(emailLabel);
        JTextField emailField = new JTextField();
        DesignSystem.styleTextField(emailField);

        JLabel passwordLabel = new JLabel("Password");
        DesignSystem.styleLabel(passwordLabel);
        JPasswordField passwordField = new JPasswordField();
        DesignSystem.styleTextField(passwordField);

        // Position components
        int xPosition = 870, yStart = 250;
        nameLabel.setBounds(xPosition, yStart, 80, 30);
        nameField.setBounds(xPosition + 85, yStart, 200, 30);
        phoneLabel.setBounds(xPosition, yStart + 40, 80, 30);
        phoneField.setBounds(xPosition + 85, yStart + 40, 200, 30);
        emailLabel.setBounds(xPosition, yStart + 80, 80, 30);
        emailField.setBounds(xPosition + 85, yStart + 80, 200, 30);
        passwordLabel.setBounds(xPosition, yStart + 120, 80, 30);
        passwordField.setBounds(xPosition + 85, yStart + 120, 200, 30);

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);

        // Create and style the sign-up button
        JButton signupButton = DesignSystem.createStyledButton("Sign Up", new Color(139, 69, 19), 200, 40);
        signupButton.setBounds(940, 450, 200, 40);
        frame.add(signupButton);

        // Create and style the "Switch to Login" label
        JLabel switchToLoginButton = new JLabel("Already have an account? Login");
        DesignSystem.styleLabel(switchToLoginButton);
        switchToLoginButton.setForeground(new Color(139, 69, 19));
        switchToLoginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        switchToLoginButton.setBounds(945, 490, 250, 50);
        frame.add(switchToLoginButton);

        frame.setVisible(true);

        signupButton.addActionListener(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (!validateCustomer(name, phone, email, password)) {
                return;
            }

            FloraFacade system = new FloraFacade();
            boolean response = system.signUp(name, phone, email, password);

            if (response) {
                JOptionPane.showMessageDialog(frame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                // Navigate to the home page
                HomeGUI.showHomePage();
            } else {
                JOptionPane.showMessageDialog(frame, "Registration failed. This email is already in use. Please try another one.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        switchToLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                LogInGUI.showLoginWindow();
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
