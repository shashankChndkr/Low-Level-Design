package org.example;

import org.example.cache.LruCache;

public class App {
    public static void main( String[] args ) {
        LruCache lruCache = new LruCache(2);
        lruCache.add("a","1");
        lruCache.add("b","1");
        lruCache.add("b","1");

        lruCache.add("c","1"); /// removed - a
        System.out.println(lruCache.mapper.size());

        lruCache.add("d","1"); /// removed - b
        System.out.println(lruCache.mapper.size());
        lruCache.add("b","1"); /// removed - c
        System.out.println(lruCache.mapper.size());
        lruCache.add("b","1");
        lruCache.add("b","1");
        lruCache.add("d","1");
        lruCache.add("b","1");
        lruCache.add("d","1");
        lruCache.add("a","1"); /// removed - b
        System.out.println(lruCache.mapper.size());

    }
}
