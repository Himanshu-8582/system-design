package PaymentGatewayLLD.BankingSystem;

import java.util.Random;

public class PaytmBankingSystem implements BankingSystem {
    Random random = new Random();

    public PaytmBankingSystem() {
    }


    @Override
    public boolean processPayment(double amount) {
        // Simulate 20% success
        int r = random.nextInt(100);
        return r < 80;
    }
}
