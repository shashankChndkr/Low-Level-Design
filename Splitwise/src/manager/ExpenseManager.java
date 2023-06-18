package manager;

import model.Balance;
import model.Expense;
import model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The ExpenseManager class manages expenses and calculates balances for users.
 */
public class ExpenseManager {

    private List<Expense> expenses;

    /**
     * Constructs an ExpenseManager object.
     * Initializes the list of expenses.
     */
    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    /**
     * Adds an expense to the manager.
     *
     * @param expense The expense to be added.
     * @return The added expense.
     * @throws NullPointerException if the expense is null.
     */
    public Expense addExpense(Expense expense) {
        if (expense == null) {
            throw new NullPointerException();
        }
        expenses.add(expense);
        return expense;
    }

    /**
     * Updates the balances for users after adding an expense.
     *
     * @param expense The expense for which to update the balances.
     * @param split   The map representing how the expense should be split among users.
     */
    public void updateBalances(Expense expense, Map<User, BigDecimal> split) {
        User giver = expense.getGiver();
        switch (expense.getExpenseType()) {
            case EQUAL -> {
                BigDecimal amount = expense.getTotalAmount().divide(BigDecimal.valueOf(expense.getTakers().size() + 1), 2, RoundingMode.HALF_DOWN);
                for (User user : expense.getTakers()) {
                    Balance balance = new Balance(null, amount);
                    expense.getTakersBalance().put(user, balance);
                    giver.setUserDebt(user, balance);
                }
            }
            case EXACT -> {
                expense.setExactSplit((HashMap<User, BigDecimal>) split);
                for (User user : split.keySet()) {
                    Balance balance = new Balance(null, split.get(user));
                    expense.getTakersBalance().put(user, balance);
                    giver.setUserDebt(user, balance);
                }
            }
            case PERCENTAGE -> {
                for (User user : split.keySet()) {
                    Balance balance = new Balance(null, expense.getTotalAmount().multiply(split.get(user)));
                    expense.getTakersBalance().put(user, balance);
                    giver.setUserDebt(user, balance);
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + expense.getExpenseType());
        }
    }
}
