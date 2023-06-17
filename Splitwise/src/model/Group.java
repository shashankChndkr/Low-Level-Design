package model;

import java.util.*;

/**
 * The Group class represents a group of users who share expenses.
 */
public class Group {

    private Set<Expense> expenses;
    private UUID groupId;
    private List<User> users;
    private String imageURI;
    private String title;
    private String description;

    /**
     * Constructs a Group object with the specified list of users, image URI, title, and description.
     *
     * @param users       The users who are part of the group.
     * @param imageURI    The image URI of the group.
     * @param title       The title of the group.
     * @param description The description of the group.
     */
    public Group(List<User> users, String imageURI, String title, String description) {
        this.groupId = UUID.randomUUID();
        this.description = description;
        this.imageURI = imageURI;
        this.title = title;
        this.users = new ArrayList<>(users);
        this.expenses = new HashSet<>();
    }

    /**
     * Retrieves the group ID.
     *
     * @return The group ID.
     */
    public UUID getGroupId() {
        return groupId;
    }

    /**
     * Sets the group ID.
     *
     * @param groupId The group ID to set.
     */
    public void setGroupId(UUID groupId) {
        this.groupId = groupId;
    }

    /**
     * Retrieves the list of users in the group.
     *
     * @return The list of users in the group.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users in the group.
     *
     * @param users The list of users to set.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    /**
     * Retrieves the image URI of the group.
     *
     * @return The image URI of the group.
     */
    public String getImageURI() {
        return imageURI;
    }

    /**
     * Sets the image URI of the group.
     *
     * @param imageURI The image URI to set.
     */
    public void setImageURI(String imageURI) {
        this.imageURI = imageURI;
    }

    /**
     * Retrieves the title of the group.
     *
     * @return The title of the group.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the group.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the description of the group.
     *
     * @return The description of the group.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the group.
     *
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Adds an expense to the group.
     *
     * @param expense The expense to add.
     */
    public void addExpense(Expense expense) {
        this.expenses.add(expense);
    }

    /**
     * Retrieves the expenses associated with the group.
     *
     * @return The expenses associated with the group.
     */
    public Set<Expense> getExpenses() {
        return expenses;
    }
}
