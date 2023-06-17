import constants.SplitType;
import manager.ExpenseManager;
import manager.GroupManager;
import manager.UserManager;
import model.Balance;
import model.Expense;
import model.Group;
import model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * The Main class demonstrates the usage of the expense management system.
 */
public class Main {
    public static void main(String[] args) {

        UserManager userManager = new UserManager();
        GroupManager groupManager = new GroupManager();
        ExpenseManager expenseManager = new ExpenseManager();

        // Create users
        for (int i = 0; i < 20; i++) {
            userManager.createUser("name-" + i, "email-" + i, "mob-" + i, "bio-" + i, "imageUrl-" + i);
        }

        Set<User> users = userManager.getUsers();

        // Print user names
        for (User user : users) {
            System.out.println(user.getName());
        }

        // Create a group with all users
        List<User> groupUser = new ArrayList<>(users);
        Group group = groupManager.createGroup(groupUser, "group", "group-description", "www.groupimage.com");

        BigDecimal totalAmt = BigDecimal.ZERO;

        // Add expenses to the group
        for (int i = 0; i < group.getUsers().size(); i++) {
            BigDecimal amount = BigDecimal.valueOf(2000.00);
            List<User> taker = new ArrayList<>(groupUser);
            taker.remove(group.getUsers().get(i));
            Expense expense = new Expense(group.getUsers().get(i), amount, taker, SplitType.EQUAL, group.getGroupId());
            expenseManager.updateBalances(expense, new HashMap<>());
            group.addExpense(expense);
            totalAmt = totalAmt.add(amount).subtract(amount.divide(BigDecimal.valueOf(group.getUsers().size()), 2, RoundingMode.HALF_DOWN));
        }

        BigDecimal totalAmt1 = BigDecimal.ZERO;

        // Calculate user balances
        for (User user : users) {
            HashMap<User, Balance> userBalanceHashMap = (HashMap<User, Balance>) user.getUserWhoOwe();
            for (User user1 : userBalanceHashMap.keySet()) {
                totalAmt1 = totalAmt1.add(userBalanceHashMap.get(user1).getAmount());
                System.out.println(user1.getName() + " owes - " + userBalanceHashMap.get(user1).getAmount() + " to user " + user.getName());
            }
        }

        // Check if the total calculated amount matches the expected amount
        if (totalAmt1.equals(totalAmt)) {
            System.out.println("Exact Split works correctly");
        }
    }
}
