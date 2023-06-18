/**
 * The TradeManager class handles the matching and execution of trades.
 * It manages a set of stock symbols and a trade book to keep track of executed trades.
 * The TradeManager works in conjunction with the OrderManager to retrieve and process orders.
 */
package manager;

import Constants.OrderStatus;
import model.Order;
import model.Trade;

import java.util.*;
import java.util.concurrent.*;

public class TradeManager {

    private Set<String> stockSymbol;
    private ScheduledExecutorService executorService;
    private ConcurrentMap<String, Trade> tradeBook;

    private OrderManager orderManager;

    /**
     * Constructs a new TradeManager object with the specified OrderManager.
     *
     * @param orderManager the OrderManager instance to be used
     */
    public TradeManager(OrderManager orderManager) {
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        this.tradeBook = new ConcurrentHashMap<>();
        this.stockSymbol = new HashSet<>();
        this.orderManager = orderManager;
    }

    /**
     * Adds a new stock symbol to the TradeManager.
     *
     * @param symbol the symbol of the stock to be added
     * @throws Exception if the symbol is empty
     */
    public void addStockSymbol(String symbol) throws Exception {
        if (symbol.isEmpty()) {
            throw new Exception("Stock symbol cannot be empty");
        }
        stockSymbol.add(symbol);
        orderManager.addNewStock(symbol);
    }

    /**
     * Starts the trade matching task for all registered stock symbols.
     * The trade matching task executes at fixed intervals to match and execute trades.
     */
    public void startTradeMatchingTask() {
        Iterator iterator = stockSymbol.iterator();
        while (iterator.hasNext()) {
            String stock = iterator.next().toString();
            System.out.println("Trade matching started for stock " + stock);
            executorService.scheduleAtFixedRate(() -> {
                matchTradeForStock(stock);
            }, 0, 1, TimeUnit.SECONDS);
        }
    }

    /**
     * Matches and executes trades for the specified stock symbol.
     * It checks for matching orders and executes trades if conditions are met.
     *
     * @param stock the symbol of the stock to match trades for
     */
    private void matchTradeForStock(String stock) {
        List<UUID> openOrdersUUID = orderManager.getActiveOrders(stock);
        if (!openOrdersUUID.isEmpty()) {
            for (int i = 0; i < openOrdersUUID.size(); i++) {
                for (int j = 0; j < openOrdersUUID.size(); j++) {
                    if (i == j) continue;
                    Order order1 = orderManager.getOrderFromUUID(openOrdersUUID.get(i));
                    Order order2 = orderManager.getOrderFromUUID(openOrdersUUID.get(j));
                    if (!order1.getTradeType().equals(order2.getTradeType()) &&
                            order1.getPrice().equals(order2.getPrice()) &&
                            order1.getQuantity().equals(order2.getQuantity())) {
                        executeTrade(order1, order2);
                    }
                }
            }
        }
    }

    /**
     * Executes a trade between two matching orders.
     * The trade is recorded in the trade book and the orders are marked as executed.
     *
     * @param order1 the first order to be executed
     * @param order2 the second order to be executed
     */
    private void executeTrade(Order order1, Order order2) {
        Trade trade = new Trade(order1.getOrderId(), order2.getOrderId(), order1.getStockSymbol(), order1.getQuantity(), order1.getPrice());
        tradeBook.put(order1.getStockSymbol(), trade);
        order1.setOrderStatus(OrderStatus.EXECUTED);
        order2.setOrderStatus(OrderStatus.EXECUTED);
        orderManager.getExecutedOrder(order2.getStockSymbol()).add(order2.getOrderId());
        orderManager.getActiveOrders(order1.getStockSymbol()).remove(order1.getOrderId());
        orderManager.getActiveOrders(order1.getStockSymbol()).remove(order2.getOrderId());

        System.out.println("Order 1 " + order1.getOrderId() + " order 2 " + order2.getOrderId() + " executed for stock symbol " + order1.getStockSymbol());
    }

    /**
     * Stops the trade matching task and shuts down the executor service.
     */
    public void stopTradeMatchingTask() {
        executorService.shutdown();
    }
}
