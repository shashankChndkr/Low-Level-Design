package model;

import Constants.MessageType;

public class Message {


    private String content;
    private User sender;

    private MessageType messageType;

    public Message(String content, User sender, MessageType messageType) {
        this.content = content;
        this.sender = sender;
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
