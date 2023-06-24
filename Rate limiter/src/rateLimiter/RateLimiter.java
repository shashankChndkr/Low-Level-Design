package rateLimiter;

/**
 * The RateLimiter interface represents a generic rate limiter.
 * Implementing classes provide the logic for allowing or denying requests based on a rate limit.
 */
public interface RateLimiter {

    /**
     * Determines whether a request is allowed based on the rate limit.
     *
     * @return true if the request is allowed, false otherwise.
     */
    boolean allowRequest();
}
