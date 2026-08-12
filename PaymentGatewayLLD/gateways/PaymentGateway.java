package PaymentGatewayLLD.gateways;


import PaymentGatewayLLD.BankingSystem.BankingSystem;
import PaymentGatewayLLD.models.PaymentRequest;

public abstract class PaymentGateway {

    protected BankingSystem bankingSystem;

    public boolean processPayment(PaymentRequest request) {
        if (!validatePayment(request)) {
            System.out.println("[PaymentGateway] Validation failed for " + request.sender + ".");
            return false;
        }
        if (!initiatePayment(request)) {
            System.out.println("[PaymentGateway] Initiation failed for " + request.sender + ".");
            return false;
        }
        if (!confirmPayment(request)) {
            System.out.println("[PaymentGateway] Confirmation failed for " + request.sender + ".");
            return false;
        }
        return true;
    }
    
    // Steps to be implemented by concrete gateways
    public abstract boolean validatePayment(PaymentRequest request);

    public abstract boolean initiatePayment(PaymentRequest request);

    public abstract boolean confirmPayment(PaymentRequest request);
}
