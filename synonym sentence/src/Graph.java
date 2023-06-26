import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Represents a graph data structure.
 */
public class Graph {

    /**
     * Mapping of nodes to their corresponding component.
     */
    public Map<String, Integer> componentMapping;

    /**
     * Mapping of components to the set of nodes in each component.
     */
    public Map<Integer, Set<String>> connectedComponent;

    private ConcurrentHashMap<String, Set<String>> graph;
    private Set<String> visitedNode;

    /**
     * Constructs an empty graph.
     */
    public Graph() {
        this.graph = new ConcurrentHashMap<>();
    }

    /**
     * Adds an edge between two nodes in the graph.
     *
     * @param a The first node.
     * @param b The second node.
     */
    public void addEdge(String a, String b) {
        if (!graph.containsKey(a)) {
            graph.put(a, Collections.synchronizedSet(new HashSet<>()));
        }
        graph.get(a).add(b);

        if (!graph.containsKey(b)) {
            graph.put(b, Collections.synchronizedSet(new HashSet<>()));
        }
        graph.get(b).add(a);
    }

    /**
     * Calculates the number of connected components in the graph.
     *
     * @return The number of connected components.
     */
    private Integer calculateConnectedComponents() {
        componentMapping = new HashMap<>();
        visitedNode = new HashSet<>();
        Integer count = 0;

        for (String each : graph.keySet()) {
            if (!visitedNode.contains(each)) {
                dfs(each, count);
            }
            count++;
        }

        return count;
    }

    /**
     * Performs a depth-first search (DFS) on the graph to identify connected components.
     *
     * @param each  The current node.
     * @param count The current component count.
     */
    private void dfs(String each, Integer count) {
        visitedNode.add(each);
        componentMapping.put(each, count);
        Set<String> edges = graph.get(each);
        Iterator edge = edges.iterator();
        while (edge.hasNext()) {
            String currentEdge = edge.next().toString();
            if (!visitedNode.contains(currentEdge)) {
                dfs(currentEdge, count);
            }
        }
    }

    /**
     * Retrieves the connected components of the graph.
     *
     * @return A map of component numbers to the set of nodes in each component.
     */
    public synchronized Map<Integer, Set<String>> getConnectedComponent() {
        this.calculateConnectedComponents();
        connectedComponent = new HashMap<>();

        for (String each : componentMapping.keySet()) {
            if (!connectedComponent.containsKey(componentMapping.get(each))) {
                connectedComponent.put(componentMapping.get(each), new HashSet<>());
            }

            connectedComponent.get(componentMapping.get(each)).add(each);
        }

        return connectedComponent;
    }

    /**
     * Removes an edge from the graph.
     *
     * @param edge The edge to be removed.
     */
    public void removeEdge(String edge) {
        if (!graph.containsKey(edge)) {
            return;
        }

        for (String each : graph.keySet()) {
            graph.get(each).remove(edge);
        }

        graph.remove(edge);
    }
}