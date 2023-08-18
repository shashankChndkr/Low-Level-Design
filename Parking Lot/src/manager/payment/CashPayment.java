package manager.payment;

import java.util.UUID;

public class CashPayment implements IPaymentStrategy {

    private double totalAmount;

    @Override
    public UUID processPayment(double amount) {
        totalAmount += amount;
        return UUID.randomUUID();
    }
}
