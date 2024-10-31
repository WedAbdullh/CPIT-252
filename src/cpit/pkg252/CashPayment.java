
package cpit.pkg252;

public class CashPayment implements PaymentStrategy {

    @Override
    public void pay(double amount) {
        System.out.println("Processing cash payment of $" + amount);
    }
}
