package strategies;

public class CreditCardStrategy implements PaymentStrategy{
    private String cardNo;

    public CreditCardStrategy(String cardNumber) {
        this.cardNo = cardNumber;
    }

    public void pay(double amount) {
        System.out.println("Paid Rs: " + amount + " using Card ( " + cardNo + " )");
    }
}
