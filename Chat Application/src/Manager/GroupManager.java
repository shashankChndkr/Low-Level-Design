package Manager;

import Constants.MessageType;
import model.Group;
import model.Message;
import model.User;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Manages the operations related to groups in the messaging system.
 */
public class GroupManager {

    private HashMap<String, Group> groups;
    private ExecutorService executorService;

    /**
     * Constructs a new GroupManager.
     */
    public GroupManager() {
        this.groups = new HashMap<>();
        this.executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Creates a new group with the given name and user as the creator.
     *
     * @param groupName the name of the group
     * @param user      the user who creates the group
     * @return the created group
     * @throws Exception if the user is null or the group name is already taken
     */
    public Group createGroup(String groupName, User user) throws Exception {
        if (user == null) {
            throw new Exception("user is null");
        }
        if (groups.containsKey(groupName)) {
            throw new Exception("Group name is already taken");
        }

        Group group = new Group(groupName, user);
        groups.put(groupName, group);
        user.addGroup(group);
        return group;
    }

    /**
     * Adds a user to a group.
     *
     * @param group   the group to which the user is added
     * @param creator the creator of the group
     * @param user    the user to be added
     * @throws Exception if the group doesn't exist or the creator is not valid
     */
    public void addUserToGroup(Group group, User creator, User user) throws Exception {
        if (!groups.containsKey(group.getGroupName()) || !group.getCreator().equals(creator)) {
            throw new Exception("Invalid group or creator");
        }

        Set<User> users = group.getUsers();
        users.add(user);
        user.getGroups().add(group);
    }

    /**
     * Removes a user from a group.
     *
     * @param group   the group from which the user is removed
     * @param creator the creator of the group
     * @param user    the user to be removed
     * @throws Exception if the creator is not valid
     */
    public void removeUser(Group group, User creator, User user) throws Exception {
        if (!group.getCreator().equals(creator)) {
            throw new Exception("Invalid creator");
        }
        group.getUsers().remove(user);
    }

    /**
     * Allows a user to leave a group.
     *
     * @param group the group to leave
     * @param user  the user leaving the group
     */
    public void leaveGroup(Group group, User user) {
        group.getUsers().remove(user);
    }

    /**
     * Sends a message from a user to a group.
     *
     * @param user         the user sending the message
     * @param group        the group to which the message is sent
     * @param content      the content of the message
     * @param messageType the type of the message
     * @throws Exception if the user is not a member of the group
     */
    public void sendMessage(User user, Group group, String content, MessageType messageType) throws Exception {
        if (!group.getUsers().contains(user)) {
            throw new Exception("User is not a member of the group");
        }
        executorService.submit(() -> {
            group.setMessages(new Message(content, user, messageType));
        });
    }

    /**
     * Shuts down the executor service.
     */


    public void shutDownExecutor() {
        executorService.shutdown();
    }

}