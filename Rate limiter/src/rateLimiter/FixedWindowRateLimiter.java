package rateLimiter;

/**
 * The FixedWindowRateLimiter class implements the RateLimiter interface and provides a fixed window rate limiting mechanism.
 * It allows a fixed number of requests within a specific time window.
 */
public class FixedWindowRateLimiter implements RateLimiter {

    private final Integer rateLimit; // Maximum number of requests allowed within the window
    private final long windowSizeInMilliSeconds; // Time window size in milliseconds
    private final Object lock; // Object used for synchronization
    private int requestCount; // Current count of requests within the window
    private long lastRequestTime; // Time of the last request

    /**
     * Constructs a FixedWindowRateLimiter with the specified rate limit and window size.
     *
     * @param rateLimit               Maximum number of requests allowed within the window.
     * @param windowSizeInMilliSeconds Time window size in milliseconds.
     */
    public FixedWindowRateLimiter(int rateLimit, long windowSizeInMilliSeconds) {
        this.rateLimit = rateLimit;
        this.windowSizeInMilliSeconds = windowSizeInMilliSeconds;
        this.lastRequestTime = System.currentTimeMillis();
        this.requestCount = 0;
        this.lock = new Object();
    }

    /**
     * Determines whether a request is allowed based on the fixed window rate limit.
     *
     * @return true if the request is allowed, false otherwise.
     */
    @Override
    public boolean allowRequest() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRequestTime;
        synchronized (lock) {
            if (elapsedTime > windowSizeInMilliSeconds) {
                requestCount = 0;
                lastRequestTime = System.currentTimeMillis();
            }

            if (requestCount < rateLimit) {
                requestCount++;
                return true;
            }
        }
        return false;
    }

}
