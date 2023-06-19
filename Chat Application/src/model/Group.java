package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Represents a group in the messaging system.
 */
public class Group {

    private final User creator;
    private String groupName;
    private Set<User> users;
    private List<Message> messages;
    private PropertyChangeSupport propertyChangeSupport;

    /**
     * Constructs a new Group with the given group name and creator.
     *
     * @param groupName the name of the group
     * @param creator   the user who created the group
     */
    public Group(String groupName, User creator) {
        this.groupName = groupName;
        this.messages = new ArrayList<>();
        this.users = new HashSet<>();
        this.creator = creator;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    /**
     * Adds a PropertyChangeListener to the group.
     *
     * @param pcl the PropertyChangeListener to be added
     */
    public void addPropertyChangeSupportListener(PropertyChangeListener pcl) {
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }

    /**
     * Returns the creator of the group.
     *
     * @return the creator of the group
     */
    public User getCreator() {
        return creator;
    }

    /**
     * Returns the name of the group.
     *
     * @return the name of the group
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the name of the group.
     *
     * @param groupName the new name for the group
     */
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /**
     * Returns the set of users in the group.
     *
     * @return the set of users in the group
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Sets the set of users in the group.
     *
     * @param users the set of users to be set in the group
     */
    public void setUsers(Set<User> users) {
        this.users = users;
    }

    /**
     * Returns the list of messages in the group.
     *
     * @return the list of messages in the group
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Adds a message to the group's list of messages and fires a property change event.
     *
     * @param message the message to be added
     */
    public void setMessages(Message message) {
        this.messages.add(message);
        propertyChangeSupport.firePropertyChange("message", null, message);
    }

    /**
     * Adds a user to the group and registers the user as a PropertyChangeListener.
     *
     * @param user the user to be added to the group
     */
    public void addUserToGroup(User user) {
        this.users.add(user);
        this.addPropertyChangeSupportListener(user);
    }
}