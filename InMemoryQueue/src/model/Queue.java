/**
 * Represents a queue of messages.
 */
package model;

import java.util.ArrayList;
import java.util.List;

public class Queue {

    private final int capacity;
    private List<Message> queue;

    /**
     * Constructs a new Queue object with the specified capacity.
     *
     * @param capacity the maximum capacity of the queue
     */
    public Queue(int capacity) {
        this.capacity = capacity;
        this.queue = new ArrayList<>(capacity);
    }

    /**
     * Adds a message to the queue.
     * If the queue is full, it waits until there is space available.
     *
     * @param message the message to enqueue
     */
    public synchronized void enqueue(Message message) {
        while (queue.size() == capacity) {
            System.out.println("Queue size is full");
        }
        queue.add(message);
    }

    /**
     * Returns the message at the front of the queue without removing it.
     *
     * @return the message at the front of the queue, or null if the queue is empty
     */
    public Message peek() {
        if (queue.isEmpty()) return null;
        return this.queue.get(0);
    }

    /**
     * Removes and returns the message at the front of the queue.
     *
     * @return the message at the front of the queue, or null if the queue is empty
     */
    public synchronized Message dequeue() {
        return queue.remove(0);
    }

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queue.size() == 0;
    }

    /**
     * Retrieves the message at the specified offset in the queue.
     *
     * @param currentOffset the offset of the message to retrieve
     * @return the message at the specified offset
     * @throws Exception if the specified offset is invalid
     */
    public Message getMessage(Integer currentOffset) throws Exception {
        if (queue.size() < currentOffset) {
            throw new Exception("Invalid offset");
        }
        return queue.get(currentOffset);
    }
}
