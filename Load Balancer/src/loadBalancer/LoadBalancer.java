/**
 * The LoadBalancer interface represents a load balancer that distributes incoming requests
 * among a group of servers.
 */
package loadBalancer;

public interface LoadBalancer {

    /**
     * Adds a new server to the load balancer.
     *
     * @param server the server to be added
     */
    public void addServer(String server);

    /**
     * Returns a server from the load balancer using a round-robin algorithm.
     *
     * @return a server from the load balancer
     */
    default public String getServer() {
        return null;
    }

    /**
     * Returns a server from the load balancer based on the client's IP address.
     *
     * @param clientIP the IP address of the client
     * @return a server from the load balancer
     */
    default public String getServer(String clientIP) {
        return null;
    }

    default public String getServer(Integer weight){
        return null;
    }
}