package Manager;

import model.Group;
import model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the operations related to users in the messaging system.
 */
public class UserManager {
    private HashMap<String, User> users;
    private GroupManager groupManager;

    /**
     * Constructs a new UserManager with the given GroupManager.
     *
     * @param groupManager the GroupManager instance
     */
    public UserManager(GroupManager groupManager) {
        this.users = new HashMap<>();
        this.groupManager = groupManager;
    }

    /**
     * Returns the map of users.
     *
     * @return the map of users
     */
    public Map<String, User> getUsers() {
        return users;
    }

    /**
     * Sets a user in the map of users.
     *
     * @param user the user to be set
     */
    public void setUsers(User user) {
        this.users.put(user.getUsername(), user);
    }

    /**
     * Creates a new user with the given username and password.
     *
     * @param userName the username of the user
     * @param password the password of the user
     * @throws Exception if the username is already taken
     */
    public void createUser(String userName, String password) throws Exception {
        if (users.containsKey(userName)) {
            throw new Exception("Username is already taken");
        }
        User user = new User(userName, password);
        this.setUsers(user);
    }

    /**
     * Authenticates a user with the given username and password.
     *
     * @param userName the username of the user
     * @param password the password of the user
     * @return true if the user is authenticated, false otherwise
     */
    public boolean login(String userName, String password) {
        if (!users.containsKey(userName)) {
            return false;
        }
        return users.get(userName).equals(password);
    }

    /**
     * Creates a new group with the given name and user as the creator.
     *
     * @param groupName the name of the group
     * @param user      the user who creates the group
     * @throws Exception if the group creation fails
     */
    public void createGroup(String groupName, User user) throws Exception {
        groupManager.createGroup(groupName, user);
    }

    /**
     * Adds a user to a group.
     *
     * @param group   the group to which the user is added
     * @param creator the creator of the group
     * @param user    the user to be added
     * @throws Exception if the user addition fails
     */
    public void addUserToGroup(Group group, User creator, User user) throws Exception {
        groupManager.addUserToGroup(group, creator, user);
    }

    /**
     * Allows a user to leave a group.
     *
     * @param group the group to leave
     * @param user  the user leaving the group
     */
    public void leaveGroup(Group group, User user) {
        groupManager.leaveGroup(group, user);
    }

    /**
     * Removes a user from a group.
     *
     * @param group   the group from which the user is removed
     * @param creator the creator of the group
     * @param user    the user to be removed
     * @throws Exception if the user removal fails
     */
    public void removeUser(Group group, User creator, User user) throws Exception {
        groupManager.removeUser(group, creator, user);
    }
}