package model;

/**
 * The Snake class represents a snake in a game.
 */
public class Snake {

    private int head;
    private int tail;

    /**
     * Constructs a new Snake object with the specified head and tail positions.
     *
     * @param head the position of the snake's head
     * @param tail the position of the snake's tail
     */
    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    /**
     * Returns the position of the snake's head.
     *
     * @return the position of the snake's head
     */
    public int getHead() {
        return head;
    }

    /**
     * Sets the position of the snake's head.
     *
     * @param head the position of the snake's head
     */
    public void setHead(int head) {
        this.head = head;
    }

    /**
     * Returns the position of the snake's tail.
     *
     * @return the position of the snake's tail
     */
    public int getTail() {
        return tail;
    }

    /**
     * Sets the position of the snake's tail.
     *
     * @param tail the position of the snake's tail
     */
    public void setTail(int tail) {
        this.tail = tail;
    }
}
