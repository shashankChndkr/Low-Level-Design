package model;

import constants.SplitType;

import java.math.BigDecimal;
import java.util.*;

public class Expense {

    private User giver;

    private BigDecimal totalAmount;

    private List<User> takers;

    private SplitType splitType;

    private Map<User, Balance> takersBalance;
    private UUID groupId;
    private HashMap<User, BigDecimal> percentageSplit;
    private HashMap<User, BigDecimal> exactSplit;

    public Expense(User giver, BigDecimal totalAmount, List<User> takers, SplitType splitType, UUID groupId) {
        this.giver = giver;
        this.groupId = groupId;
        this.totalAmount = totalAmount;
        this.takers = new ArrayList<>(takers);
        this.splitType = splitType;
        this.takersBalance = new HashMap<>(takers.size());
        this.percentageSplit = new HashMap<>();
        this.exactSplit = new HashMap<>();
    }

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public Map<User, BigDecimal> getPercentageSplit() {
        return percentageSplit;
    }

    public void setPercentageSplit(Map<User, BigDecimal> percentageSplit) {
        this.percentageSplit = (HashMap<User, BigDecimal>) percentageSplit;
    }

    public Map<User, BigDecimal> getExactSplit() {
        return exactSplit;
    }

    public void setExactSplit(Map<User, BigDecimal> exactSplit) {
        this.exactSplit = (HashMap<User, BigDecimal>) exactSplit;
    }

    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public User getGiver() {
        return giver;
    }

    public void setGiver(User giver) {
        this.giver = giver;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<User> getTakers() {
        return takers;
    }

    public void setTakers(List<User> takers) {
        this.takers = takers;
    }

    public SplitType getExpenseType() {
        return splitType;
    }

    public void setExpenseType(SplitType splitType) {
        this.splitType = splitType;
    }


    public Map<User, Balance> getTakersBalance() {
        return takersBalance;
    }

    public void setTakersBalance(Map<User, Balance> takersBalance) {
        this.takersBalance = takersBalance;
    }


}
