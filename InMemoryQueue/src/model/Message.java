/**
 * Represents a message with content and timestamp.
 */
package model;

import java.time.LocalDateTime;

public class Message {

    private String content;
    private LocalDateTime timeStamp;

    private LocalDateTime ttl;

    /**
     * Constructs a new Message object with the specified content and time-to-live (TTL).
     *
     * @param content the content of the message
     * @param ttl     the time-to-live (in hours) of the message
     */
    public Message(String content, long ttl) {
        this.content = content;
        this.timeStamp = LocalDateTime.now();
        this.ttl = LocalDateTime.now().plusHours(ttl);
    }

    /**
     * Returns the content of the message.
     *
     * @return the content of the message
     */
    public String getContent() {
        return content;
    }

    /**
     * Returns the timestamp when the message was created.
     *
     * @return the timestamp of the message
     */
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    /**
     * Returns the time-to-live (TTL) of the message.
     *
     * @return the time-to-live of the message
     */
    public LocalDateTime getTtl() {
        return ttl;
    }

    /**
     * Checks if the message has expired.
     *
     * @return true if the message has expired, false otherwise
     */
    public boolean isExpired() {
        return !this.ttl.isAfter(LocalDateTime.now());
    }
}
