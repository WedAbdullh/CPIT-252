package FloraWeddingHall.GUI;

import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.*;
import java.awt.*;

public class DesignSystem {

    // Apply consistent styling to JTextFields
    public static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.DARK_GRAY);
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    // Apply consistent styling to JLabels
    public static void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(Color.BLACK);
    }

    // Create and style buttons with consistent properties
    public static JButton createStyledButton(String text, Color backgroundColor, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    // Set up a consistent background panel
    public static JPanel createBackgroundPanel(String imagePath) {
        return new BackgroundPanel(imagePath); // Assumes BackgroundPanel class exists
    }
}
