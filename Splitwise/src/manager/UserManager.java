package manager;

import model.User;

import java.util.HashSet;
import java.util.Set;

/**
 * The UserManager class manages the creation and retrieval of users.
 */
public class UserManager {

    private Set<User> users;

    /**
     * Constructs a UserManager object.
     * Initializes the set of users.
     */
    public UserManager() {
        this.users = new HashSet<>();
    }

    /**
     * Retrieves the set of users.
     *
     * @return The set of users.
     */
    public Set<User> getUsers() {
        return users;
    }

    /**
     * Creates a new user with the specified details and adds it to the set of users.
     *
     * @param name         The name of the user.
     * @param email        The email address of the user.
     * @param mobileNumber The mobile number of the user.
     * @param bio          The bio or description of the user.
     * @param imageURL     The image URL of the user.
     * @return The created user.
     */
    public User createUser(String name, String email, String mobileNumber, String bio, String imageURL) {
        User user = new User(name, email, mobileNumber, bio, imageURL);
        users.add(user);
        return user;
    }
}
