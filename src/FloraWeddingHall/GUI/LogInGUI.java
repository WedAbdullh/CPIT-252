package FloraWeddingHall.GUI;

import FloraWeddingHall.system.FloraFacade;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInGUI {

    public static void showLoginWindow() {
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

                if (email == null || password == null) {
                    JOptionPane.showMessageDialog(frame, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop further processing
                }

                if (!isValidEmail(email)) {
                    JOptionPane.showMessageDialog(frame, "Invalid email. Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Stop further processing
                }

                FloraFacade system = new FloraFacade();
                boolean response = system.logIn(email, password);

                if (response) {
                    if (email.contains("@FloaWeddingHall.com")) {
                        // Show manager frame (future implementation if required)
                        JOptionPane.showMessageDialog(frame, "Manager login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        ManagerDashboardGUI.showPackagesWindow();
                        // You could call a manager-specific GUI here, e.g., ManagerHomeGUI.showManagerHomePage();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Customer login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.dispose(); // Close the login window
                        HomeGUI.showHomePage(); // Show the home page for customers
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Log in failed. The email is not registered.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                frame.dispose();
            }
        });

        //Action listener for switching to signup
        switchToSignupButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.dispose();
                SignUpGUI.showSignupWindow(); // Show signup window when switching from login
            }
        });
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

}
