package PaymentGatewayLLD.models;


public class PaymentRequest {
    public String sender;
    public String receiver;
    public String currency;
    public double amount;

    public PaymentRequest(String sender, String reciever, double amt, String curr) {
        this.sender = sender;
        this.receiver = reciever;
        this.amount = amt;
        this.currency = curr;
    }
}