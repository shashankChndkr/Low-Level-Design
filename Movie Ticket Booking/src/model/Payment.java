package model;

import java.util.UUID;

public class Payment {


    private PaymentStatus status;
    private String message;

    private UUID id;

    public Payment(PaymentStatus status, String message) {
        this.status = status;
        this.message = message;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
