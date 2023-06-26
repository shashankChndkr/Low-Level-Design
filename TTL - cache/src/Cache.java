import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A cache implementation that stores key-value pairs with a time-to-live (TTL) policy.
 */
public class Cache {

    private Map<String, CacheEntry> cacheEntryMap;
    private PriorityQueue<CacheEntry> cacheEntryPriorityQueue;
    private long cachettl;
    private ReentrantLock lock;
    private ScheduledExecutorService scheduledExecutorService;

    /**
     * Constructs a Cache with the specified time-to-live (TTL) value.
     *
     * @param cachettl the time-to-live value in milliseconds
     */
    public Cache(long cachettl) {
        this.cachettl = cachettl;
        this.cacheEntryMap = new HashMap<>();
        this.cacheEntryPriorityQueue = new PriorityQueue<>();
        this.lock = new ReentrantLock();
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        this.startSchedulerToCleanCache();
    }

    /**
     * Starts a scheduler to clean the cache at regular intervals.
     */
    public void startSchedulerToCleanCache() {
        scheduledExecutorService.scheduleAtFixedRate(this::cleanCache, 0, this.cachettl, TimeUnit.MILLISECONDS);
    }

    /**
     * Puts a new cache entry or updates the existing entry for the given key.
     * If the key already exists, the frequency is incremented and the timestamp is updated.
     * If the key is new, a new cache entry is created.
     *
     * @param key the key for the cache entry
     */
    public void put(String key) {
        lock.lock();
        try {
            long currentTime = System.currentTimeMillis();
            CacheEntry cacheEntry = new CacheEntry(key);
            if (cacheEntryMap.containsKey(key)) {
                cacheEntryMap.get(key).incrementFreq();
                cacheEntryMap.get(key).setTimeStamp(currentTime);
                cacheEntryPriorityQueue.add(cacheEntry);
            } else {
                cacheEntryMap.put(key, cacheEntry);
                cacheEntryPriorityQueue.add(new CacheEntry(key));
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * Retrieves the cache entries that are still within the time-to-live (TTL) period.
     * Removes the expired cache entries from the map and priority queue.
     *
     * @return a map containing the keys and frequencies of the valid cache entries
     */
    public Map<String, Integer> get() {
        HashMap<String, Integer> result = new HashMap<>();
        lock.lock();
        try {
            cleanCache();
            for (String each : cacheEntryMap.keySet()) {
                if (cacheEntryMap.get(each).getFrequency() > 0) {
                    result.put(cacheEntryMap.get(each).getKey(), cacheEntryMap.get(each).getFrequency());
                }
            }
        } finally {
            lock.unlock();
        }
        return result;
    }

    /**
     * Cleans the cache by removing expired cache entries.
     */
    private void cleanCache() {
        long currentTime = System.currentTimeMillis();
        lock.lock();
        try {
            while (!cacheEntryPriorityQueue.isEmpty()) {
                CacheEntry cacheEntry = cacheEntryPriorityQueue.peek();
                if (currentTime - cacheEntry.getTimeStamp() <= this.cachettl) {
                    break;
                }
                cacheEntryMap.get(cacheEntry.getKey()).decrementFreq();
                cacheEntryPriorityQueue.poll();
            }
        } finally {
            lock.unlock();
        }
    }
}
