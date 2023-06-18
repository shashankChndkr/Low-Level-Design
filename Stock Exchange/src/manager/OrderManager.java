/**
 * The OrderManager class manages the creation, modification, and cancellation of orders.
 * It also provides methods to retrieve executed and active orders for a specific stock symbol.
 */
package manager;

import Constants.OrderStatus;
import Constants.TradeType;
import model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class OrderManager {

    private final ConcurrentMap<UUID, Order> uuidOrderMap;
    private final ConcurrentMap<String, CopyOnWriteArrayList<UUID>> orderBook;
    private final ConcurrentMap<String, CopyOnWriteArrayList<UUID>> activeOrders;
    private final ConcurrentMap<String, List<UUID>> executedOrder;

    /**
     * Constructs a new OrderManager object.
     * Initializes the internal data structures for managing orders.
     */
    public OrderManager() {
        this.uuidOrderMap = new ConcurrentHashMap<>();
        this.orderBook = new ConcurrentHashMap<>();
        this.activeOrders = new ConcurrentHashMap<>();
        this.executedOrder = new ConcurrentHashMap<>();
    }

    /**
     * Returns a list of executed order IDs for the specified stock symbol.
     *
     * @param stockSymbol the symbol of the stock
     * @return a list of executed order IDs
     */
    public List<UUID> getExecutedOrder(String stockSymbol) {
        return executedOrder.get(stockSymbol);
    }

    /**
     * Returns a list of active order IDs for the specified stock symbol.
     *
     * @param stockSymbol the symbol of the stock
     * @return a list of active order IDs
     */
    public List<UUID> getActiveOrders(String stockSymbol) {
        return activeOrders.get(stockSymbol);
    }

    /**
     * Creates a new order with the specified user ID, trade type, stock symbol, quantity, and price.
     * The order is added to the order book and active orders for the corresponding stock symbol.
     *
     * @param userId      the ID of the user placing the order
     * @param tradeType   the type of trade (buy or sell)
     * @param stockSymbol the symbol of the stock being traded
     * @param quantity    the quantity of stocks being traded
     * @param price       the price per stock
     * @return the created Order object
     */
    public Order createOrder(UUID userId, TradeType tradeType, String stockSymbol, Integer quantity, BigDecimal price) {
        Order order = new Order(userId, tradeType, stockSymbol, quantity, price);
        synchronized (orderBook) {
            orderBook.putIfAbsent(stockSymbol, new CopyOnWriteArrayList<>());
            orderBook.get(stockSymbol).add(order.getOrderId());
        }
        if (!activeOrders.containsKey(stockSymbol)) {
            activeOrders.put(stockSymbol, new CopyOnWriteArrayList<>());
        }
        activeOrders.get(stockSymbol).add(order.getOrderId());
        uuidOrderMap.put(order.getOrderId(), order);
        System.out.println("Order created with orderId " + order.getOrderId() + " stock " + order.getStockSymbol());
        return order;
    }

    /**
     * Modifies the specified order with the new stock symbol, quantity, and price.
     * The order must be an active order for the corresponding stock symbol.
     *
     * @param orderId     the ID of the order to be modified
     * @param stockSymbol the new symbol of the stock
     * @param quantity    the new quantity
     * @param price       the new price
     * @return the modified Order object
     * @throws Exception if the order is not an active order for the specified stock symbol
     */


    public Order modifyOrder(UUID orderId, String stockSymbol, Integer quantity, BigDecimal price) throws Exception {
        if (!activeOrders.containsKey(stockSymbol) || !activeOrders.get(stockSymbol).contains(orderId)) {
            throw new Exception("Order is not an active order for the specified stock symbol");
        }

        Order order = uuidOrderMap.get(orderId);

        order.setPrice(price);
        order.setQuantity(quantity);
        return order;
    }

    /**
     * Cancels the specified order by setting its order status to CANCELLED.
     *
     * @param orderId the ID of the order to be cancelled
     * @return the cancelled Order object
     */
    public Order cancelOrder(UUID orderId) {
        Order order = uuidOrderMap.get(orderId);
        order.setOrderStatus(OrderStatus.CANCELLED);
        return order;
    }

    /**
     * Returns the Order object associated with the specified UUID.
     *
     * @param uuid the UUID of the order
     * @return the Order object
     */
    public Order getOrderFromUUID(UUID uuid) {
        return uuidOrderMap.get(uuid);
    }

    /**
     * Adds a new stock to the order book and initializes its corresponding data structures.
     *
     * @param stock the symbol of the new stock
     */
    public void addNewStock(String stock) {
        this.orderBook.putIfAbsent(stock, new CopyOnWriteArrayList<>());
        this.executedOrder.put(stock, new ArrayList<>());
        this.activeOrders.putIfAbsent(stock, new CopyOnWriteArrayList<>());
    }

}
