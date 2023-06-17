package model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * The User class represents a user who participates in expense sharing.
 */
public class User {

    private UUID id;
    private String name;
    private String email;
    private String mobileNumber;
    private HashMap<User, Balance> userInDebt;
    private String bio;
    private String imageURL;

    /**
     * Constructs a User object with the specified name, email, mobile number, bio, and image URL.
     *
     * @param name         The name of the user.
     * @param email        The email of the user.
     * @param mobileNumber The mobile number of the user.
     * @param bio          The bio of the user.
     * @param imageURL     The image URL of the user.
     */
    public User(String name, String email, String mobileNumber, String bio, String imageURL) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.bio = bio;
        this.imageURL = imageURL;
        this.userInDebt = new HashMap<>();
    }

    /**
     * Retrieves the bio of the user.
     *
     * @return The bio of the user.
     */
    public String getBio() {
        return bio;
    }

    /**
     * Sets the bio of the user.
     *
     * @param bio The bio to set.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Retrieves the image URL of the user.
     *
     * @return The image URL of the user.
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets the image URL of the user.
     *
     * @param imageURL The image URL to set.
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    /**
     * Retrieves the ID of the user.
     *
     * @return The ID of the user.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID to set.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the mobile number of the user.
     *
     * @return The mobile number of the user.
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * Sets the mobile number of the user.
     *
     * @param mobileNumber The mobile number to set.
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * Sets the debt balance of another user for the current user.
     * If the user is already in debt, the existing balance is updated by adding the new balance.
     *
     * @param user    The user who owes the debt.
     * @param balance The balance to set.
     */
    public void setUserDebt(User user, Balance balance) {
        if (!userInDebt.containsKey(user)) {
            userInDebt.put(user, balance);
        } else {
            Balance balance1 = userInDebt.get(user);
            balance1.setAmount(balance1.getAmount().add(balance.getAmount()));
        }
    }

    /**
     * Retrieves the map of users who owe debt to the current user along with their respective balances.
     *
     * @return The map of users who owe debt to the current user.
     */
    public Map<User, Balance> getUserWhoOwe() {
        return userInDebt;
    }

}
