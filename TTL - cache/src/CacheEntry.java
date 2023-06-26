/**
 * Represents an entry in a cache.
 * Implements the Comparable interface to enable sorting based on timestamp.
 */
public class CacheEntry implements Comparable<CacheEntry> {

    private String key;
    private Integer frequency;
    private long timeStamp;

    /**
     * Constructs a CacheEntry with the given key.
     * Initializes the frequency to 1 and the timestamp to the current system time.
     *
     * @param key the key associated with the cache entry
     */
    public CacheEntry(String key) {
        this.key = key;
        this.frequency = 1;
        this.timeStamp = System.currentTimeMillis();
    }

    /**
     * Returns the key associated with the cache entry.
     *
     * @return the key associated with the cache entry
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the frequency of the cache entry.
     *
     * @return the frequency of the cache entry
     */
    public Integer getFrequency() {
        return frequency;
    }

    /**
     * Returns the timestamp of the cache entry.
     *
     * @return the timestamp of the cache entry
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the timestamp of the cache entry.
     *
     * @param timeStamp the timestamp to set
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Increments the frequency of the cache entry by 1.
     */
    public void incrementFreq() {
        this.frequency++;
    }

    /**
     * Decrements the frequency of the cache entry by 1.
     */
    public void decrementFreq() {
        this.frequency--;
    }

    /**
     * Compares this cache entry to another cache entry based on their timestamps.
     *
     * @param o the cache entry to compare
     * @return a negative integer if this entry is older, zero if they have the same timestamp,
     *         or a positive integer if this entry is newer
     */
    @Override
    public int compareTo(CacheEntry o) {
        return (int) (this.timeStamp - o.getTimeStamp());
    }
}
