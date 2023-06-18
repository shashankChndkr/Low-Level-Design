/**
 * The Main class is the entry point of the application. It demonstrates the usage
 * of the order matching system by creating users, adding stock symbols, and generating
 * random buy and sell orders for trade matching.
 */
import Constants.TradeType;
import manager.OrderManager;
import manager.TradeManager;
import manager.UserManager;
import model.User;

import java.math.BigDecimal;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {

        // Create instances of managers
        UserManager userManager = new UserManager();
        OrderManager orderManager = new OrderManager();
        TradeManager tradeManager = new TradeManager(orderManager);

        // Add stock symbols
        for (int i = 0; i < 10; i++) {
            tradeManager.addStockSymbol("stockSymbol-" + i);
        }
        System.out.println("Stocks added");

        // Create users
        for (int i = 0; i < 20; i++) {
            userManager.createUser("name-" + i, "email-" + i, "mob-" + i, "bio-" + i, "imageUrl-" + i);
        }
        System.out.println("Users created");

        // Get users for trade execution
        User user1 = userManager.getUsers().get(0);
        User user2 = userManager.getUsers().get(1);

        System.out.println("Trade matching scheduler started");

        // Start the trade matching task
        tradeManager.startTradeMatchingTask();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int rand = random.nextInt(0, 9);
            orderManager.createOrder(user1.getId(), TradeType.BUY, "stockSymbol-" + rand, 10, BigDecimal.TEN);
            orderManager.createOrder(user2.getId(), TradeType.SELL, "stockSymbol-" + rand, 10, BigDecimal.TEN);
            Thread.sleep(1000);
        }

//        tradeManager.stopTradeMatchingTask();

    }
}