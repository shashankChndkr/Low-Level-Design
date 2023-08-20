package org.example.model;

public class User extends Account {

    public User(String phoneNumber, String userName, String password) {
    }

    public void addContact(String name, String phoneNumber) {
        getContactTrie().insert(name);
        getContactTrie().insert(phoneNumber);
    }

    public void deleteContact(String name, String phoneNumber){
        getContactTrie().delete(name);
        getContactTrie().delete(phoneNumber);
    }
}
