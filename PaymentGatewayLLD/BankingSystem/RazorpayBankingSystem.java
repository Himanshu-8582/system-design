package PaymentGatewayLLD.BankingSystem;

import java.util.Random;

public class RazorpayBankingSystem implements BankingSystem {
    Random random = new Random();

    @Override
    public boolean processPayment(double amount) {
        System.out.println("[BankingSystem-Razorpay] Processing payment of " + amount + "...");
        // Simulate 90% success
        int r = random.nextInt(100);
        return r < 90;
    }
}
