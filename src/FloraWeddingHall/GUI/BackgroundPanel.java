
package FloraWeddingHall.GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

// A panel that shows a background image
class BackgroundPanel extends JPanel {

    private Image backgroundImage;  // The background image

    // Constructor that loads the image from the given path
    public BackgroundPanel(String imagePath) {
        // Load the image
        try {
        backgroundImage = ImageIO.read(new File(imagePath));
        setLayout(null);  // Allows manual positioning of components
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading background image: " + e.getMessage());
        }
    }

    // This method draws the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Paint the panel as usual
        // Draw the image to cover the whole panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}