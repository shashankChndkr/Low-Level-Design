package org.example;

import org.example.model.SpamContact;
import org.example.model.User;

public class TrueCaller {
    public static void main(String args[]) {
        User user = new User("172", "NAME", "pass");
        SpamContact spamContact = new SpamContact();
        user.addContact("abc", "987");
        user.addContact("defabc", "645");
        user.addContact("qweabc", "258");
        spamContact.addSpam("defabc");
        spamContact.addSpam("645");
        System.out.println("Search result for abc - " + user.getContactTrie().search("abc"));
        System.out.println("Search result for xyz - " + user.getContactTrie().search("xyz"));
        System.out.println("Search result for opi - " + user.getContactTrie().search("opi"));
        System.out.println("Search result for 645 - " + user.getContactTrie().search("645"));
        System.out.println("Search result for 987 - " + user.getContactTrie().search("987"));
        System.out.println("Search result for 7777 - " + user.getContactTrie().search("7777"));
        System.out.println("Search result for 8888 - " + user.getContactTrie().search("8888"));
        user.addContact("abcde", "7777");
        System.out.println("Search result for abcde - " + user.getContactTrie().search("abcde"));
        System.out.println("Search result for 7777 - " + user.getContactTrie().search("7777"));
        user.deleteContact("abcde", "7777");
        System.out.println("Search result for abcde - " + user.getContactTrie().search("abcde"));
        System.out.println("Search result for 7777 - " + user.getContactTrie().search("7777"));
        System.out.println("Is spam defabc " + spamContact.isSpam("defabc"));
        System.out.println("Is spam 645 " + spamContact.isSpam("645"));

    }
}
