package model;

/**
 * The Player class represents a player in a game.
 */
public class Player {

    private String name;
    private int position;

    /**
     * Constructs a new Player object with the specified name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the player.
     *
     * @param name the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the current position of the player.
     *
     * @return the current position of the player
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the current position of the player.
     *
     * @param position the current position of the player
     */
    public void setPosition(int position) {
        this.position = position;
    }
}
