package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.prefs.PreferenceChangeListener;

public class Group {

    private String groupName;
    private Set<User> users;

    private final User creator;
    private List<Message> messages;

    private PropertyChangeSupport propertyChangeSupport ;

    public Group(String groupName, User creator) {
        this.groupName = groupName;
        this.messages = new ArrayList<>();
        this.users = new HashSet<>();
        this.creator = creator;
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeSupportListener(PropertyChangeListener pcl) {
        propertyChangeSupport.addPropertyChangeListener(pcl);
    }


    public User getCreator() {
        return creator;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(Message message) {
        this.messages.add(message);
        propertyChangeSupport.firePropertyChange("message", null, message);
    }

    public void addUserToGroup(User user) {
        this.users.add(user);
        this.addPropertyChangeSupportListener(user);
    }

}
