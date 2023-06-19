package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class User implements PropertyChangeListener {

    private String username;
    private String password;

    private List<Group> groups;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.groups = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }


    public List<Group> getGroups() {
        return this.groups;
    }

    public Group addGroup(Group group) {
        groups.add(group);
        return group;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case "message" -> {
                Message msg = (Message) evt.getNewValue();
                System.out.println("Got new message "  +  msg.getContent() + " from user " + msg.getSender().getUsername() + " to user -" + this.getUsername());
            }
            default -> throw new IllegalStateException("Unexpected value: " + evt.getPropertyName());
        }
    }
}
