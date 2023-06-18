/**
 * The Order class represents an order placed by a user for buying or selling stocks.
 */
package model;

import Constants.OrderStatus;
import Constants.TradeType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Order {

    private UUID userId;
    private UUID orderId;
    private TradeType tradeType;
    private String stockSymbol;
    private Integer quantity;
    private BigDecimal price;
    private LocalDateTime localDateTime;
    private OrderStatus orderStatus;

    /**
     * Constructs a new Order object with the specified user ID, trade type, stock symbol, quantity, and price.
     * The order ID is generated randomly, and the order status is set to ACCEPTED by default.
     *
     * @param userId      the ID of the user placing the order
     * @param tradeType   the type of trade (buy or sell)
     * @param stockSymbol the symbol of the stock being traded
     * @param quantity    the quantity of stocks being traded
     * @param price       the price per stock
     */
    public Order(UUID userId, TradeType tradeType, String stockSymbol, Integer quantity, BigDecimal price) {
        this.userId = userId;
        this.orderId = UUID.randomUUID();
        this.tradeType = tradeType;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.localDateTime = LocalDateTime.now();
        this.orderStatus = OrderStatus.ACCEPTED;
    }

    /**
     * Returns the ID of the user placing the order.
     *
     * @return the user ID
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Returns the ID of the order.
     *
     * @return the order ID
     */
    public UUID getOrderId() {
        return orderId;
    }

    /**
     * Returns the type of trade (buy or sell) for the order.
     *
     * @return the trade type
     */
    public TradeType getTradeType() {
        return tradeType;
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
     * Sets the quantity of stocks being traded.
     *
     * @param quantity the new quantity value
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
     * Sets the price per stock.
     *
     * @param price the new price value
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Returns the date and time when the order was placed.
     *
     * @return the date and time
     */
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    /**
     * Returns the current status of the order.
     *
     * @return the order status
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Sets the status of the order.
     *
     * @param orderStatus the new order status
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
