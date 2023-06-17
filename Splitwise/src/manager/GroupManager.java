package manager;

import model.Expense;
import model.Group;
import model.User;

import java.util.*;

/**
 * The GroupManager class manages groups and expenses associated with them.
 */
public class GroupManager {

    private Set<Group> allGroups;
    private Map<UUID, Group> uuidToGroupMap;
    private Map<User, List<UUID>> userToGroupIds;
    private Set<Expense> expenses;

    /**
     * Constructs a GroupManager object.
     * Initializes the sets and maps used for managing groups and expenses.
     */
    public GroupManager() {
        this.allGroups = new HashSet<>();
        this.userToGroupIds = new HashMap<>();
        this.uuidToGroupMap = new HashMap<>();
        this.expenses = new HashSet<>();
    }

    /**
     * Creates a new group with the specified users, title, description, and image URL.
     *
     * @param users       The list of users in the group.
     * @param title       The title of the group.
     * @param description The description of the group.
     * @param imageURL    The image URL of the group.
     * @return The created group.
     */
    public Group createGroup(List<User> users, String title, String description, String imageURL) {
        List<User> newUsers = new ArrayList<>(users);
        Group group = new Group(newUsers, title, description, imageURL);
        uuidToGroupMap.put(group.getGroupId(), group);
        for (User user : users) {
            if (!userToGroupIds.containsKey(user)) {
                userToGroupIds.put(user, new ArrayList<>());
            }
            userToGroupIds.get(user).add(group.getGroupId());
        }
        return group;
    }

    /**
     * Retrieves a list of groups in which the specified user is a member.
     *
     * @param user The user for whom to retrieve the groups.
     * @return A list of groups in which the user is a member.
     */
    public List<Group> getUserGroups(User user) {
        List<UUID> groupIds = userToGroupIds.get(user);
        List<Group> groups = new ArrayList<>();
        for (UUID uuid : groupIds) {
            groups.add(uuidToGroupMap.get(uuid));
        }
        return groups;
    }

    /**
     * Adds an expense to the manager.
     *
     * @param expense The expense to be added.
     * @return The added expense.
     */
    public Expense addExpenses(Expense expense) {
        expenses.add(expense);
        return expense;
    }
}
