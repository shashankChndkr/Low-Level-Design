package manager;

import model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserManager {

    public Set<User> getUsers() {
        return users;
    }

    private Set<User> users;


    public UserManager(){
        this.users = new HashSet<>();
    }

    public User createUser(String name, String email, String mobileNumber, String bio, String imageURL){
        User user = new User(name, email, mobileNumber, bio, imageURL);
        users.add(user);
        return user;
    }
}
