package model;

import constants.SplitType;

import java.math.BigDecimal;
import java.util.*;

/**
 * The Expense class represents an expense made by a giver and shared among multiple takers.
 */
public class Expense {

    private User giver;
    private BigDecimal totalAmount;
    private List<User> takers;
    private SplitType splitType;
    private Map<User, Balance> takersBalance;
    private UUID groupId;
    private HashMap<User, BigDecimal> percentageSplit;
    private HashMap<User, BigDecimal> exactSplit;

    /**
     * Constructs an Expense object with the specified giver, total amount, takers, split type, and group ID.
     *
     * @param giver       The user who made the expense.
     * @param totalAmount The total amount of the expense.
     * @param takers      The users who shared the expense.
     * @param splitType   The split type of the expense.
     * @param groupId     The ID of the group associated with the expense.
     */
    public Expense(User giver, BigDecimal totalAmount, List<User> takers, SplitType splitType, UUID groupId) {
        this.giver = giver;
        this.totalAmount = totalAmount;
        this.takers = new ArrayList<>(takers);
        this.splitType = splitType;
        this.takersBalance = new HashMap<>(takers.size());
        this.percentageSplit = new HashMap<>();
        this.exactSplit = new HashMap<>();
        this.groupId = groupId;
    }

    /**
     * Retrieves the split type of the expense.
     *
     * @return The split type of the expense.
     */
    public SplitType getSplitType() {
        return splitType;
    }

    /**
     * Sets the split type of the expense.
     *
     * @param splitType The split type to set.
     */
    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    /**
     * Retrieves the percentage split of the expense.
     *
     * @return The percentage split of the expense.
     */
    public Map<User, BigDecimal> getPercentageSplit() {
        return percentageSplit;
    }

    /**
     * Sets the percentage split of the expense.
     *
     * @param percentageSplit The percentage split to set.
     */
    public void setPercentageSplit(Map<User, BigDecimal> percentageSplit) {
        this.percentageSplit = (HashMap<User, BigDecimal>) percentageSplit;
    }

    /**
     * Retrieves the exact split of the expense.
     *
     * @return The exact split of the expense.
     */
    public Map<User, BigDecimal> getExactSplit() {
        return exactSplit;
    }

    /**
     * Sets the exact split of the expense.
     *
     * @param exactSplit The exact split to set.
     */
    public void setExactSplit(Map<User, BigDecimal> exactSplit) {
        this.exactSplit = (HashMap<User, BigDecimal>) exactSplit;
    }

    /**
     * Retrieves the group ID associated with the expense.
     *
     * @return The group ID associated with the expense.
     */
    public UUID getGroupId() {
        return groupId;
    }

    /**
     * Sets the group ID associated with the expense.
     *
     * @param groupId The group ID to set.
     */
    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    /**
     * Retrieves the giver of the expense.
     *
     * @return The giver of the expense.
     */
    public User getGiver() {
        return giver;
    }

    /**
     * Sets the giver of the expense.
     *
     * @param giver The giver to set.
     */
    public void setGiver(User giver) {
        this.giver = giver;
    }

    /**
     * Retrieves the total amount of the expense.
     *
     * @return The total amount of the expense.
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the total amount of the expense.
     *
     * @param totalAmount The total amount to set.
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * Retrieves the takers of the expense.
     *
     * @return The takers of the expense.
     */
    public List<User> getTakers() {
        return takers;
    }

    /**
     * Sets the takers of the expense.
     *
     * @param takers The takers to set.
     */
    public void setTakers(List<User> takers) {
        this.takers = takers;
    }

    /**
     * Retrieves the split type of the expense.
     *
     * @return The split type of the expense.
     */
    public SplitType getExpenseType() {
        return splitType;
    }

    /**
     * Sets the split type of the expense.
     *
     * @param splitType The split type to set.
     */
    public void setExpenseType(SplitType splitType) {
        this.splitType = splitType;
    }

    /**
     * Retrieves the balances for each taker of the expense.
     *
     * @return The balances for each taker of the expense.
     */
    public Map<User, Balance> getTakersBalance() {
        return takersBalance;
    }

    /**
     * Sets the balances for each taker of the expense.
     *
     * @param takersBalance The balances for each taker to set.
     */
    public void setTakersBalance(Map<User, Balance> takersBalance) {
        this.takersBalance = takersBalance;
    }
}
