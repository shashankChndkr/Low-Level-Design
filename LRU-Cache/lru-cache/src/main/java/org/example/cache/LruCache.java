package org.example.cache;

import org.example.policies.LruEvictionPolicy;

import java.util.HashMap;
import java.util.Map;

public class LruCache extends LruEvictionPolicy implements Cache {

    public Map<String, String>  mapper;
    public LruCache(int capacity) {
        super(capacity);
        mapper = new HashMap<>();
    }

    @Override
    public void add(String key, String value) {
        mapper.put(key,value);
        keyUsed(key);
        if(mapper.size() > getCapacity()){
            String key1 = this.evictKey();
            System.out.println("Capacity Full key evicted - " + key1);
            mapper.remove(key1);
        }
    }

    @Override
    public void remove(String key) {
        mapper.remove(key);
    }

    @Override
    public String get(String key) {
        return mapper.get(key);
    }
}
