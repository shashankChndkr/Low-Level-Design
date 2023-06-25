/**
 * The RoundRobinLoadBalancer class implements the LoadBalancer interface and represents
 * a load balancer that distributes incoming requests among a group of servers using the
 * round-robin algorithm.
 */
package loadBalancer;

import java.util.ArrayList;
import java.util.List;

public class RoundRobinLoadBalancer implements LoadBalancer {

    private List<String> servers;
    private Integer currentServer;
    private Object lock;

    /**
     * Constructs a new RoundRobinLoadBalancer object.
     */
    public RoundRobinLoadBalancer() {
        this.servers = new ArrayList<>();
        this.currentServer = 0;
        this.lock = new Object();
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
     * Returns a server from the load balancer using the round-robin algorithm.
     *
     * @return a server from the load balancer
     */
    @Override
    public String getServer() {
        if (servers.isEmpty()) {
            return null;
        }

        int totalServers = servers.size();
        int index;
        synchronized (lock) {
            index = currentServer % totalServers;
            currentServer++;
        }

        return servers.get(index);
    }
}