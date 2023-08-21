package org.example.cache;

public interface Cache {

    public void add(String key, String value);
    public void remove(String key);
    public String get(String key);
}
