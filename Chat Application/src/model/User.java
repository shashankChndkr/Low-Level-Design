package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a user in the messaging system.
 */
public class User implements PropertyChangeListener {

    private String username;
    private String password;
    private List<Group> groups;

    /**
     * Constructs a new User with the given username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.groups = new ArrayList<>();
    }

    /**
     * Returns the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the list of groups the user belongs to.
     *
     * @return the list of groups the user belongs to
     */
    public List<Group> getGroups() {
        return this.groups;
    }

    /**
     * Adds a group to the user's list of groups.
     *
     * @param group the group to be added
     * @return the added group
     */
    public Group addGroup(Group group) {
        groups.add(group);
        return group;
    }

    /**
     * Handles property change events.
     *
     * @param evt the property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "message" -> {
                Message msg = (Message) evt.getNewValue();
                System.out.println("Got new message " + msg.getContent() + " from user " + msg.getSender().getUsername() + " to user -" + this.getUsername());
            }
            default -> throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }
    }
}