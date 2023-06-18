/**
 * The Trade class represents a trade executed between a buy order and a sell order.
 */
package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Trade {

    private UUID tradeId;
    private UUID buyOrderId;
    private UUID sellOrderId;
    private String stockSymbol;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime tradeTimeStamp;

    /**
     * Constructs a new Trade object with the specified buy order ID, sell order ID, stock symbol, quantity, and price.
     * The trade ID is generated randomly, and the trade timestamp is set to the current date and time.
     *
     * @param buyOrderId  the ID of the buy order
     * @param sellOrderId the ID of the sell order
     * @param stockSymbol the symbol of the stock being traded
     * @param quantity    the quantity of stocks being traded
     * @param price       the price per stock
     */
    public Trade(UUID buyOrderId, UUID sellOrderId, String stockSymbol, Integer quantity, BigDecimal price) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeId = UUID.randomUUID();
        this.tradeTimeStamp = LocalDateTime.now();
    }

    /**
     * Returns the ID of the trade.
     *
     * @return the trade ID
     */
    public UUID getTradeId() {
        return tradeId;
    }

    /**
     * Returns the ID of the buy order associated with the trade.
     *
     * @return the buy order ID
     */
    public UUID getBuyOrderId() {
        return buyOrderId;
    }

    /**
     * Returns the ID of the sell order associated with the trade.
     *
     * @return the sell order ID
     */
    public UUID getSellOrderId() {
        return sellOrderId;
    }

    /**
     * Returns the symbol of the stock being traded.
     *
     * @return the stock symbol
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     * Returns the quantity of stocks being traded.
     *
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * Returns the price per stock.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Returns the timestamp when the trade was executed.
     *
     * @return the trade timestamp
     */
    public LocalDateTime getTradeTimeStamp() {
        return tradeTimeStamp;
    }

    /**
     * Sets the timestamp of the trade.
     *
     * @param tradeTimeStamp the new trade timestamp
     */
    public void setTradeTimeStamp(LocalDateTime tradeTimeStamp) {
        this.tradeTimeStamp = tradeTimeStamp;
    }
}
