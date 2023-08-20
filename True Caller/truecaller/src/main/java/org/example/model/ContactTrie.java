package org.example.model;


public class ContactTrie {

    private final TrieNode root;

    public ContactTrie() {
        this.root = new TrieNode('/');
    }

    public void insert(String key) {
        if (key == null) {
            return;
        }
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            current = current.setChild(key.charAt(i));
        }
        current.setIsChild(true);
    }

    public boolean search(String key) {
        if (key == null) {
            return false;
        }
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            current = current.getChild(key.charAt(i));
            if (current == null) return false;
        }
        return current.isChild();
    }

    public boolean delete(String key) {
        if (key == null || !search(key)) {
            return true;
        }
        TrieNode current = root;
        for (int i = 0; i < key.length(); i++) {
            current = current.getChild(key.charAt(i));
        }
        current.setIsChild(false);
        return true;
    }


}
