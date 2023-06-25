/**
 * The Main class demonstrates the usage of different load balancer implementations
 * (IPHashLoadBalancer, LeastConnectionAlgorithm, RoundRobinLoadBalancer).
 */
import loadBalancer.IPHashLoadBalancer;
import loadBalancer.LeastConnectionAlgorithm;
import loadBalancer.LeastResourceAlgorithm;
import loadBalancer.RoundRobinLoadBalancer;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        IPHashLoadBalancer ipHashLoadBalancer = new IPHashLoadBalancer();
        LeastConnectionAlgorithm leastConnectionAlgorithm = new LeastConnectionAlgorithm();
        RoundRobinLoadBalancer roundRobinLoadBalancer = new RoundRobinLoadBalancer();
        LeastResourceAlgorithm leastResourceAlgorithm = new LeastResourceAlgorithm();

        Random random = new Random();

        // Add servers to the load balancers
        for (int i = 0; i < 20; i++) {
            ipHashLoadBalancer.addServer("Server-" + i);
            leastConnectionAlgorithm.addServer("Server-" + i);
            roundRobinLoadBalancer.addServer("Server-" + i);
            leastResourceAlgorithm.addServer("Server-"+ i);
        }

        // Test LeastConnectionAlgorithm load balancer
        System.out.println("LeastConnectionAlgorithm");
        for (int i = 0; i < 10; i++) {
            System.out.println("Current allocated server is " + leastConnectionAlgorithm.getServer());
        }

        System.out.println("\n\n\n");

        // Test RoundRobinLoadBalancer load balancer
        System.out.println("RoundRobinLoadBalancer");
        for (int i = 0; i < 10; i++) {
            System.out.println("Current allocated server is " + roundRobinLoadBalancer.getServer());
        }

        System.out.println("\n\n\n");

        // Test IPHashLoadBalancer load balancer
        System.out.println("IPHashLoadBalancer");
        for (int i = 0; i < 10; i++) {
            System.out.println("Current allocated server is " + ipHashLoadBalancer.getServer("client-" + random.nextInt()));
        }


        System.out.println("\n\n\n");
        System.out.println("LeastResourceAlgorithm");

        for (int i = 0; i < 10; i++) {
            System.out.println("Current allocated server is " + leastResourceAlgorithm.getServer( random.nextInt(1, 10000)));
        }


    }
}