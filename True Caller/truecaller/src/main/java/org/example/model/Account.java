package org.example.model;

import lombok.Getter;

import java.util.UUID;

@Getter
public abstract class Account {
    private String id;
    private String phoneNumber;
    private String userName;
    private String password;
    private ContactTrie contactTrie;

    public Account() {
        this.id = String.valueOf(UUID.randomUUID());
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.password = password;
        this.contactTrie = new ContactTrie();
    }

}
