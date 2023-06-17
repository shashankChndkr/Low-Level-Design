package model;

import java.math.BigDecimal;

public class Balance {

    private static final String DEFAULT_CURRENCY = "INR";

    private String currency;
    private BigDecimal amount;

    public Balance(String currency, BigDecimal amount) {
        if (currency == null) {
            this.currency = DEFAULT_CURRENCY;
        } else {
            this.currency = currency;
        }
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
