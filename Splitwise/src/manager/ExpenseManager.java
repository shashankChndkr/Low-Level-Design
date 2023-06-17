package manager;

import constants.SplitType;
import model.Balance;
import model.Expense;
import model.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseManager {

    private List<Expense> expenses;

    public ExpenseManager() {
        this.expenses = new ArrayList<>();
    }

    public Expense addExpense(Expense expense) {
        if (expense == null) {
            throw new NullPointerException();
        }
        expenses.add(expense);
        return expense;
    }

    public void updateBalances(Expense expense, Map<User, BigDecimal> split) {
        User giver = expense.getGiver();
        switch (expense.getExpenseType()) {
            case EQUAL -> {
                BigDecimal amount = expense.getTotalAmount().divide(BigDecimal.valueOf(expense.getTakers().size() + 1) , 2 , RoundingMode.HALF_DOWN);
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
