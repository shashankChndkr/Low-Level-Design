package rateLimiter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * The TokenBucketRateLimiter class implements the RateLimiter interface and provides a token bucket rate limiting mechanism.
 * It allows a specified number of tokens to be consumed per second, up to a certain capacity.
 */
public class TokenBucketRateLimiter implements RateLimiter {

    private final Object lock; // Object used for synchronization
    private long capacity; // Capacity of the token bucket
    private long refillRatePerSecond; // Rate at which tokens are refilled per second
    private ScheduledExecutorService scheduledExecutorService; // Executor service for token refilling task
    private long currentTokens; // Current number of tokens in the bucket

    /**
     * Constructs a TokenBucketRateLimiter with the specified capacity and refill rate.
     *
     * @param capacity            Capacity of the token bucket.
     * @param refillRatePerSecond Rate at which tokens are refilled per second.
     */
    public TokenBucketRateLimiter(long capacity, long refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
        this.scheduledExecutorService = Executors.newScheduledThreadPool(1);
        this.currentTokens = capacity;
        this.lock = new Object();
        this.startRefillBucketTask();
    }

    /**
     * Starts the token refill task that periodically adds tokens to the bucket.
     */
    public void startRefillBucketTask() {
        scheduledExecutorService.scheduleAtFixedRate(this::refillBucket, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Refills the token bucket by adding tokens up to its capacity.
     */
    public void refillBucket() {
        currentTokens = Math.min(capacity, currentTokens + refillRatePerSecond);
    }

    /**
     * Determines whether a request is allowed based on the token bucket rate limit.
     *
     * @return true if the request is allowed, false otherwise.
     */
    @Override
    public boolean allowRequest() {
        synchronized (lock) {
            if (currentTokens > 0) {
                currentTokens--;
                return true;
            }
        }
        return false;
    }
}
