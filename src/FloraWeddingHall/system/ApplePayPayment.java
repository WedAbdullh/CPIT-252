
package FloraWeddingHall.system;

import FloraWeddingHall.GUI.BookHallGUI;
import FloraWeddingHall.GUI.PaymentGUI;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ApplePayPayment implements PaymentStrategy {

    private String appleAccount;

    public ApplePayPayment(String appleAccount) {
        this.appleAccount = appleAccount;
    }

    ApplePayPayment() {
    }

    @Override
    public void pay(double amount) {
        
        PaymentGUI.initializeApplePayPaymentUI(amount);
    }
    
   
}
