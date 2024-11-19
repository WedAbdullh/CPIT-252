
package FloraWeddingHall.system;

import FloraWeddingHall.GUI.PaymentGUI;

public class PayPalPayment implements PaymentStrategy {

    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    PayPalPayment() {
    }

    @Override
    public void pay(double amount) {
           PaymentGUI.initializePayPalPaymentUI(amount);
           System.out.println("Paid " + amount + " using PayPal.");

    
    }
}
