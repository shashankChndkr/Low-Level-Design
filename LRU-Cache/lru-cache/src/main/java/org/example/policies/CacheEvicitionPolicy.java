package org.example.policies;

public interface CacheEvicitionPolicy {

    public void keyUsed(String key);
    public String evictKey();
}
