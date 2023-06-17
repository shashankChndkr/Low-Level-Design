package model;

import java.math.BigDecimal;

/**
 * The Balance class represents a monetary balance with a specific currency and amount.
 */
public class Balance {

    private static final String DEFAULT_CURRENCY = "INR";

    private String currency;
    private BigDecimal amount;

    /**
     * Constructs a Balance object with the specified currency and amount.
     * If the currency is null, it is set to the default currency (INR).
     *
     * @param currency The currency of the balance.
     * @param amount   The amount of the balance.
     */
    public Balance(String currency, BigDecimal amount) {
        if (currency == null) {
            this.currency = DEFAULT_CURRENCY;
        } else {
            this.currency = currency;
        }
        this.amount = amount;
    }

    /**
     * Retrieves the currency of the balance.
     *
     * @return The currency of the balance.
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the currency of the balance.
     *
     * @param currency The currency to set.
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Retrieves the amount of the balance.
     *
     * @return The amount of the balance.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Sets the amount of the balance.
     *
     * @param amount The amount to set.
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

}
