package model;

import Constants.MessageType;

/**
 * Represents a message in the messaging system.
 */
public class Message {

    private String content;
    private User sender;
    private MessageType messageType;

    /**
     * Constructs a new Message with the given content, sender, and message type.
     *
     * @param content      the content of the message
     * @param sender       the user who sent the message
     * @param messageType the type of the message
     */
    public Message(String content, User sender, MessageType messageType) {
        this.content = content;
        this.sender = sender;
        this.messageType = messageType;
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
     * Sets the content of the message.
     *
     * @param content the new content for the message
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the sender of the message.
     *
     * @return the sender of the message
     */
    public User getSender() {
        return sender;
    }

    /**
     * Sets the sender of the message.
     *
     * @param sender the new sender for the message
     */
    public void setSender(User sender) {
        this.sender = sender;
    }

    /**
     * Returns the type of the message.
     *
     * @return the type of the message
     */
    public MessageType getMessageType() {
        return messageType;
    }

    /**
     * Sets the type of the message.
     *
     * @param messageType the new message type
     */
    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}