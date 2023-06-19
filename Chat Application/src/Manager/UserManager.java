package Manager;

import model.Group;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private HashMap<String, User> users;


    private GroupManager groupManager;

    public UserManager(GroupManager groupManager) {
        this.users = new HashMap<>();
        this.groupManager = groupManager;
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users.put(users.getUsername(), users);
    }

    public void createUser(String userName, String password) throws Exception {
        if (users.containsKey(userName)) {
            throw new Exception();
        }
        User user = new User(userName, password);
        this.setUsers(user);
    }

    public Boolean login(String userName, String password) {
        if (!users.containsKey(userName)) {
            return false;
        }
        return users.get(userName).equals(password);
    }


    public void createGroup(String groupName, User user) throws Exception {
        Group group = groupManager.createGroup(groupName, user);
        user.addGroup(group);
    }

    public void addUserToGroup(Group group, User creator, User user) throws Exception {
        groupManager.addUserToGroup(group, creator, user);
    }

    public void leaveGroup(Group group, User user) {
        groupManager.leaveGroup(group, user);
    }

    public void removeUser(Group group, User creator, User user) throws Exception {
        groupManager.removeUser(group, creator, user);
    }

}
