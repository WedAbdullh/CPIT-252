
package FloraWeddingHall.system;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
//        System.out.println("Processing cash payment of $" + amount);
      System.out.println("Paid " + amount + " in cash.");
    
    }
}
