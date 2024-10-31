
package cpit.pkg252;

public class ApplePayPayment implements PaymentStrategy {

    private String appleAccount;

    public ApplePayPayment(String appleAccount) {
        this.appleAccount = appleAccount;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Processing Apple Pay payment of $" + amount + " for account " + appleAccount);
    }
}
