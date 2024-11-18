
package FloraWeddingHall.system;

import FloraWeddingHall.GUI.PaymentGUI;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
//        System.out.println("Processing cash payment of $" + amount);
        PaymentGUI.initializeCashPaymentUI(amount);
        System.out.println("Paid " + amount + " in cash.");
      
    
    }
}
