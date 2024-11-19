
package FloraWeddingHall.system;

import FloraWeddingHall.GUI.PaymentGUI;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        PaymentGUI.initializeCashPaymentUI(amount);
        System.out.println("Paid " + amount + " in cash.");
      
    
    }
}
