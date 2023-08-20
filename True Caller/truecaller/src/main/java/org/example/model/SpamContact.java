package org.example.model;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;

import static org.example.model.Contants.SPAM_FALSE_POSITIVE_PROB;
import static org.example.model.Contants.TOTAL_SPAM;

public class SpamContact {
    private static volatile SpamContact instance;
    public BloomFilter<String> filter;

    public SpamContact() {
        filter = BloomFilter.create(
                Funnels.stringFunnel(Charset.forName("UTF-8")),
                TOTAL_SPAM,
                SPAM_FALSE_POSITIVE_PROB);
    }

    public static SpamContact getInstance() {
        if (instance == null) {
            synchronized (SpamContact.class) {
                if (instance == null) {
                    instance = new SpamContact();
                }
            }
        }
        return instance;
    }

    public void addSpam(String key) {
        filter.put(key);
    }

    public boolean isSpam(String key) {
        return filter.mightContain(key);
    }

}
