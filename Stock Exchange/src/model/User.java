package model;

import java.util.UUID;

/**
 * The User class represents a user who participates in expense sharing.
 */
public class User {

    private UUID id;
    private String name;
    private String email;
    private String mobileNumber;
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


}