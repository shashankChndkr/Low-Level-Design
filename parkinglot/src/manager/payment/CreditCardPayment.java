package manager.payment;

import java.util.UUID;

public class CreditCardPayment implements IPaymentStrategy {
    private double totalamount;

    @Override
    public UUID processPayment(double amount) {
        this.totalamount += amount;
        return UUID.randomUUID();
    }
}
