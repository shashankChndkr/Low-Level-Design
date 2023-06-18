/**
 * The UserManager class manages the creation and retrieval of User objects.
 * It maintains a list of users and provides methods for managing user data.
 */
package manager;

import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private List<User> users;

    /**
     * Constructs a new UserManager object.
     */
    public UserManager() {
        this.users = new ArrayList<>();
    }

    /**
     * Returns the list of users.
     *
     * @return the list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Creates a new user with the specified details and adds it to the user list.
     *
     * @param name         the name of the user
     * @param email        the email address of the user
     * @param mobileNumber the mobile number of the user
     * @param bio          the biography of the user
     * @param imageURL     the URL of the user's profile image
     * @return the created User object
     */
    public User createUser(String name, String email, String mobileNumber, String bio, String imageURL) {
        User user = new User(name, email, mobileNumber, bio, imageURL);
        users.add(user);
        return user;
    }
}
