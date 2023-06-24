package rateLimiter;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * The SlidingWindowRateLimiter class implements the RateLimiter interface and provides a sliding window rate limiting mechanism.
 * It allows a specified number of requests within a specified time window, sliding along with time.
 */
public class SlidingWindowRateLimiter implements RateLimiter {

    private final Object lock; // Object used for synchronization
    private int rateLimit; // Maximum number of requests allowed within the window
    private long windowSizeInMillis; // Time window size in milliseconds
    private Deque<Long> requestQueue; // Queue to track request timestamps within the window

    /**
     * Constructs a SlidingWindowRateLimiter with the specified rate limit and window size.
     *
     * @param rateLimit           Maximum number of requests allowed within the window.
     * @param windowSizeInMillis  Time window size in milliseconds.
     */
    public SlidingWindowRateLimiter(int rateLimit, long windowSizeInMillis) {
        this.rateLimit = rateLimit;
        this.windowSizeInMillis = windowSizeInMillis;
        this.requestQueue = new ArrayDeque<>();
        this.lock = new Object();
    }

    /**
     * Determines whether a request is allowed based on the sliding window rate limit.
     *
     * @return true if the request is allowed, false otherwise.
     */
    @Override
    public boolean allowRequest() {
        long currentTime = System.currentTimeMillis();

        synchronized (lock) {
            while (!requestQueue.isEmpty() && requestQueue.peek() < currentTime - windowSizeInMillis) {
                requestQueue.poll();
            }

            if (requestQueue.size() >= rateLimit) {
                return false;
            }

            requestQueue.add(currentTime);
        }
        return true;
    }
}
