
package FloraWeddingHall.system;

public class ApplePayPayment implements PaymentStrategy {

    private String appleAccount;

    public ApplePayPayment(String appleAccount) {
        this.appleAccount = appleAccount;
    }

    ApplePayPayment() {
    }

    @Override
    public void pay(double amount) {
//        System.out.println("Processing Apple Pay payment of $" + amount + " for account " + appleAccount);
          System.out.println("Paid " + amount + " using Apple Pay.");

    }
}
