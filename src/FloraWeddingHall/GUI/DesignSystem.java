package FloraWeddingHall.GUI;

import javax.swing.*;
import java.awt.*;

public class DesignSystem {

    public static JPanel createBackgroundPanel(String imagePath) {
        return new BackgroundPanel(imagePath); // Assuming BackgroundPanel is a custom JPanel class.
    }

    public static void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.BLACK); // Default text color
    }

    public static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
    }

    public static JButton createStyledButton(String text, Color backgroundColor, int width, int height) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(width, height));
        button.setOpaque(true);
        button.setBorderPainted(false);
        return button;
    }

    public static void styleClickableLabel(JLabel label, Color color) {
        label.setFont(new Font("Arial", Font.PLAIN, 12));
        label.setForeground(color);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}