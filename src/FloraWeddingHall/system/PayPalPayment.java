
package FloraWeddingHall.system;

public class PayPalPayment implements PaymentStrategy {

    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    PayPalPayment() {
    }

    @Override
    public void pay(double amount) {
//        System.out.println("Processing PayPal payment of $" + amount + " for " + email);
           System.out.println("Paid " + amount + " using PayPal.");

    
    }
}
