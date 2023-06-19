package Manager;

import Constants.MessageType;
import model.Group;
import model.Message;
import model.User;

import java.util.HashMap;
import java.util.Set;

public class GroupManager {

    HashMap<String, Group> groups;

    public GroupManager() {
        this.groups = new HashMap<>();
    }

    public Group createGroup(String groupName, User user) throws Exception {
        if(user == null){
            throw new Exception("user is null");
        }
        if (groups.containsKey(groupName)) {
            throw new Exception();
        }

        Group group = new Group(groupName, user);
        groups.put(groupName, group);
        user.addGroup(group);
        return group;
    }


    public void addUserToGroup(Group group, User creator, User user) throws Exception {
        if (!groups.containsKey(group.getGroupName()) && group.getCreator().equals(creator)) {
            throw new Exception();
        }

        Set<User> users = group.getUsers();
        users.add(user);
        user.getGroups().add(group);
    }

    public void removeUser(Group group, User creator, User user) throws Exception {
        if (!group.getCreator().equals(creator)) {
            throw new Exception();
        }
        group.getUsers().remove(user);
    }


    public void leaveGroup(Group group, User user) {
        group.getUsers().remove(user);
    }

    public void sendMessage(User user, Group group, String content, MessageType messageType) throws Exception {
        if (!group.getUsers().contains(user)){
            throw new Exception();
        }
        group.setMessages(new Message(content, user, messageType));
    }
}
