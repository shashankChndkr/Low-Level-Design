import Constants.MessageType;
import Manager.GroupManager;
import Manager.UserManager;
import model.Group;
import model.User;

import java.util.HashMap;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println("Chat application for groups");
        GroupManager groupManager = new GroupManager();
        UserManager userManager = new UserManager(groupManager);


        for (int i = 0; i < 20; i++) {
            userManager.createUser("user-" + i, "password" + i);
        }

        HashMap<String, User> userHashMap = (HashMap<String, User>) userManager.getUsers();

        for (String username : userHashMap.keySet()) {
            groupManager.createGroup("group_" + username, userHashMap.get(username));
        }

        for (int i = 0; i < 20; i++) {
            User user = userManager.getUsers().get("user-" + i);
            Group group = user.getGroups().get(0);
            for (int j = 0; j < 20; j++) {
                group.addUserToGroup(userManager.getUsers().get("user-" + j));
            }
        }

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(0, 19);
            User user = userManager.getUsers().get("user-" + x);
            Group group = user.getGroups().get(0);
            groupManager.sendMessage(user, group, "new message-" + x, MessageType.TEXT);
        }


        groupManager.shutDownExecutor();

    }
}