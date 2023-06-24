import rateLimiter.FixedWindowRateLimiter;
import rateLimiter.SlidingWindowRateLimiter;
import rateLimiter.TokenBucketRateLimiter;

import java.util.Random;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Fixed Window Rate Limiter
        System.out.println("Fixed Window Rate Limiter");
        FixedWindowRateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(2, 100);

        Random random = new Random();

        for (int i = 0; i < 40; i++) {
            if (fixedWindowRateLimiter.allowRequest()) {
                System.out.println("Request allowed id-" + i);
            } else {
                System.out.println("Request dropped");
            }
            Thread.sleep(random.nextInt(0, 50));
        }

        // Sliding Window Rate Limiter
        System.out.println("\n\n\n");
        System.out.println("Sliding Window Rate Limiter\n");

        SlidingWindowRateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(2, 100);

        for (int i = 0; i < 40; i++) {
            if (slidingWindowRateLimiter.allowRequest()) {
                System.out.println("Request allowed id-" + i);
            } else {
                System.out.println("Request dropped");
            }
            Thread.sleep(random.nextInt(0, 50));
        }

        // Token Bucket Rate Limiter
        System.out.println("\n\n\nToken bucket Rate Limiter\n");

        TokenBucketRateLimiter tokenBucketRateLimiter = new TokenBucketRateLimiter(2, 1);

        for (int i = 0; i < 40; i++) {
            if (tokenBucketRateLimiter.allowRequest()) {
                System.out.println("Request allowed id-" + i);
            } else {
                System.out.println("Request dropped");
            }
            Thread.sleep(random.nextInt(0, 50));
        }
    }
}
