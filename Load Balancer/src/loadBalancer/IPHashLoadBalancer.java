/**
 * The IPHashLoadBalancer class implements the LoadBalancer interface and represents
 * a load balancer that distributes incoming requests among a group of servers based on
 * the client's IP address using a hash function.
 */
package loadBalancer;

import java.util.ArrayList;
import java.util.List;

public class IPHashLoadBalancer implements LoadBalancer {

    private List<String> servers;

    /**
     * Constructs a new IPHashLoadBalancer object.
     */
    public IPHashLoadBalancer() {
        this.servers = new ArrayList<>();
    }

    /**
     * Adds a new server to the load balancer.
     *
     * @param server the server to be added
     */
    @Override
    public void addServer(String server) {
        servers.add(server);
    }

    /**
     * Returns a server from the load balancer based on the client's IP address.
     *
     * @param clientIP the IP address of the client
     * @return a server from the load balancer
     */
    @Override
    public String getServer(String clientIP) {
        int hashCode = clientIP.hashCode();
        int serverIndex = Math.abs(hashCode % servers.size());
        return servers.get(serverIndex);
    }
}