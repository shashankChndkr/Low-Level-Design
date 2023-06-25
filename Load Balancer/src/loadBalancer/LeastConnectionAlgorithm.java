/**
 * The LeastConnectionAlgorithm class implements the LoadBalancer interface and represents
 * a load balancer that distributes incoming requests among a group of servers using the least
 * connection algorithm.
 */
package loadBalancer;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class LeastConnectionAlgorithm implements LoadBalancer {


    class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }
    }

    private Set<String> serverMap;
    private PriorityQueue<Pair<Integer, String>> pq;

    /**
     * Constructs a new LeastConnectionAlgorithm object.
     */
    public LeastConnectionAlgorithm() {
        serverMap = new HashSet<>();
        pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
    }

    /**
     * Adds a new server to the load balancer.
     *
     * @param server the server to be added
     */
    @Override
    public void addServer(String server) {
        serverMap.add(server);
        pq.add(new Pair<>(0, server));
    }

    /**
     * Returns a server from the load balancer using the least connection algorithm.
     *
     * @return a server from the load balancer
     */
    @Override
    public String getServer() {
        if (pq.isEmpty()) {
            return null;
        }
        Pair<Integer, String> p = pq.poll();
        p.setKey(p.getKey() + 1);
        pq.add(p);
        return p.getValue();
    }
}