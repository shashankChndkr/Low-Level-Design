package manager.payment;

import java.util.UUID;

public interface IPaymentStrategy {
    public UUID processPayment(double amount);
}
