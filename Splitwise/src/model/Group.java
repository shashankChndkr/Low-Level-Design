package model;

import java.util.*;

public class Group {

    private Set<Expense> expenses;

    private UUID groupId;

    private List<User> users;

    private String imageURI;

    private String title;

    private String description;

    public Group(List<User> users, String imageURI, String title, String description) {
        this.groupId = UUID.randomUUID();
        this.description = description;
        this.imageURI = imageURI;
        this.title = title;
        this.users = new ArrayList<>(users);
        this.expenses = new HashSet<>();
    }


    public UUID getGroupId() {
        return groupId;
    }

    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getImageURI() {
        return imageURI;
    }

    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    public Set<Expense> getExpenses() {
        return expenses;
    }
}