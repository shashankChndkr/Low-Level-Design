import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        // Create a new graph
        Graph graph = new Graph();

        // Add edges to the graph
        graph.addEdge("hey", "hello");
        graph.addEdge("adios", "hello");
        graph.addEdge("howdy", "hello");
        graph.addEdge("hey", "bonjour");

        graph.addEdge("happy", "cheerful");
        graph.addEdge("happy", "happy");
        graph.addEdge("cheerful", "ecstasy");
        graph.addEdge("ecstasy", "high");

        graph.addEdge("sad", "unhappy");
        graph.addEdge("sad", "sober");
        graph.addEdge("depressed", "sober");

        // Get the connected components of the graph
        Map<Integer, Set<String>> result = graph.getConnectedComponent();

        // Print the number of components
        System.out.println("Number of components: " + result.size());

        // Split the sentence into words
        String sentence1 = "hello happy";
        List<String> word = List.of(sentence1.split(" "));

        // Get the synonyms of the words from the connected components
        Set<String> word1Synonyms = result.get(graph.componentMapping.get(word.get(0)));
        Set<String> word2Synonyms = result.get(graph.componentMapping.get(word.get(1)));

        // Print the pairs of synonyms
        for (String each : word1Synonyms) {
            for (String each1 : word2Synonyms) {
                System.out.println(each + " " + each1);
            }
        }

        // Remove the edges associated with the block word from the graph
        String blockWord = "happy";
        for (String each : graph.connectedComponent.get(graph.componentMapping.get(blockWord))) {
            graph.removeEdge(each);
        }

        // Recalculate the connected components
        result = graph.getConnectedComponent();

        // Print the updated number of components
        System.out.println("Number of components: " + result.size());
    }
}