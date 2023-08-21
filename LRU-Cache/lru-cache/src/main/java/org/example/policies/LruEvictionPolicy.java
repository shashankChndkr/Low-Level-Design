package org.example.policies;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class LruEvictionPolicy implements CacheEvicitionPolicy{

    private Queue<String> queue;
    private Set<String> mp;
    private Map<String, Integer>cnt;
    private int capacity;

    public LruEvictionPolicy(int capacity){
        queue = new LinkedList<>();
        mp = new HashSet<>();
        cnt = new HashMap<>();
        this.capacity =capacity;
    }

    @Override
    public void keyUsed(String key) {
        if(mp.contains(key)) {
            Integer occurance = cnt.getOrDefault(key, 0);
            occurance++;
            cnt.put(key, occurance);
            queue.add(key);
        }else{
            mp.add(key);
            cnt.put(key, 1);
            queue.add(key);
        }
    }

    @Override
    public String evictKey() {
        while (mp.size() > capacity){
            String key = queue.poll();
            Integer occurance = cnt.get(key);
            occurance--;
            cnt.put(key, occurance);
            if(occurance == 0){
                mp.remove(key);
                return key;
            }
        }
        return null;
    }
}
