package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrieNode {

    public static final int TOTAL_CHARACTER = 256;

    private char character;
    private boolean isChild;
    private TrieNode[] children;

    public TrieNode(char character) {
        this.character = character;
        this.children = new TrieNode[TOTAL_CHARACTER];
        this.isChild = false;
    }


    public TrieNode setChild(char ch) {
        int value = ch;
        if (this.children[value] != null) {
            return this.children[value];
        }
        this.children[value] = new TrieNode(ch);
        return this.children[value];
    }

    public TrieNode getChild(char ch) {
        int value = ch;
        return this.children[value];
    }

    public void setIsChild(boolean b) {
        this.isChild = b;
    }
}
