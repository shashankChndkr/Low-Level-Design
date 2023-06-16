package model;

/**
 * The Ladder class represents a ladder in a game.
 */
public class Ladder {

    private int start;
    private int end;

    /**
     * Constructs a new Ladder object with the specified start and end positions.
     *
     * @param start the starting position of the ladder
     * @param end the ending position of the ladder
     */
    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the starting position of the ladder.
     *
     * @return the starting position of the ladder
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the starting position of the ladder.
     *
     * @param start the starting position of the ladder
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Returns the ending position of the ladder.
     *
     * @return the ending position of the ladder
     */
    public int getEnd() {
        return end;
    }

    /**
     * Sets the ending position of the ladder.
     *
     * @param end the ending position of the ladder
     */
    public void setEnd(int end) {
        this.end = end;
    }
}
