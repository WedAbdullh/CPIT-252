package FloraWeddingHall.GUI;

import FloraWeddingHall.system.FloraFacade;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class LogInGUI {

    private static FloraFacade system = new FloraFacade();
    
    public static void showLoginWindow() {
        JFrame frame = new JFrame("Customer Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1383, 768);
        frame.setLayout(null);

        // Set up the background
        JPanel backgroundPanel = DesignSystem.createBackgroundPanel("Login.jpg");
        frame.setContentPane(backgroundPanel);

        // Create and style labels and fields
        JLabel emailLabel = new JLabel("Email");
        DesignSystem.styleLabel(emailLabel);
        JTextField emailField = new JTextField();
        DesignSystem.styleTextField(emailField);

        JLabel passwordLabel = new JLabel("Password");
        DesignSystem.styleLabel(passwordLabel);
        JPasswordField passwordField = new JPasswordField();
        DesignSystem.styleTextField(passwordField);

        // Position components
        int xPosition = 870, yStart = 300;
        emailLabel.setBounds(xPosition, yStart, 80, 30);
        emailField.setBounds(xPosition + 85, yStart, 200, 30);
        passwordLabel.setBounds(xPosition, yStart + 40, 80, 30);
        passwordField.setBounds(xPosition + 85, yStart + 40, 200, 30);

        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(passwordLabel);
        frame.add(passwordField);

        // Create and add the login button
        JButton loginButton = DesignSystem.createStyledButton("Login", new Color(139, 69, 19), 200, 40);
        loginButton.setBounds(940, 420, 200, 40);
        frame.add(loginButton);

        // Create and add the "Switch to Sign Up" label
        JLabel switchToSignupButton = new JLabel("Don't have an account? Sign Up");
        DesignSystem.styleClickableLabel(switchToSignupButton, new Color(139, 69, 19));
        switchToSignupButton.setBounds(945, 470, 250, 50);
        frame.add(switchToSignupButton);

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

                boolean response = system.logIn(email, password);

                if (response) {
                    if (email.contains("@FloaWeddingHall.com")) {
                        JOptionPane.showMessageDialog(frame, "Manager login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        ManagerDashboardGUI.showPackagesWindow();
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
