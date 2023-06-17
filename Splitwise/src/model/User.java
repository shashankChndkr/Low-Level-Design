package model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    private String mobileNumber;

    private HashMap<User, Balance> userInDebt;
    private String bio;
    private String imageURL;

    public User(String name, String email, String mobileNumber, String bio, String imageURL) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.bio = bio;
        this.imageURL = imageURL;
        this.userInDebt = new HashMap<>();
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setUserDebt(User user, Balance balance) {
        if (!userInDebt.containsKey(user)) {
            userInDebt.put(user, balance);
        } else {
            Balance balance1 = userInDebt.get(user);
            balance1.setAmount(balance1.getAmount().add(balance.getAmount()));
        }
    }

    public Map<User, Balance> getUserWhoOwe (){
        return userInDebt;
    }


}
