package manager;

import model.Expense;
import model.Group;
import model.User;

import java.util.*;

public class GroupManager {

    private Set<Group> allGroups;

    private Map<UUID, Group> uuidToGroupMap;
    private Map<User, List<UUID>> userToGroupIds;

    private Set<Expense> expenses;


    public GroupManager() {
        this.allGroups = new HashSet<>();
        this.userToGroupIds = new HashMap<>();
        this.uuidToGroupMap = new HashMap<>();
        this.expenses = new HashSet<>();
    }


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

    public List<Group> getUserGroups(User user) {

        List<UUID> groupIds = userToGroupIds.get(user);
        List<Group> groups = new ArrayList<>();

        for (UUID uuid : groupIds) {
            groups.add(uuidToGroupMap.get(uuid));
        }
        return groups;
    }

    public Expense addExpenses(Expense expense) {
        expenses.add(expense);
        return expense;
    }
}
