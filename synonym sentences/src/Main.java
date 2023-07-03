import controller.SynonymPairGraph;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        SynonymPairGraph synonymPairGraph = new SynonymPairGraph();


        synonymPairGraph.addEdge("hello", "hey");
        synonymPairGraph.addEdge("world", "earth");
        synonymPairGraph.addEdge("planet", "earth");
        synonymPairGraph.addEdge("planet", "planet");


        Map<Integer, Set<String>> allSynonymPairs = synonymPairGraph.getAllSynonyms();

        System.out.println();
        List<String> result = synonymPairGraph.getSentences("hello world");
        System.out.println();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println();
                System.out.println("Thread started");
                synonymPairGraph.getSentences("world earth");
                System.out.println("Thread Ended");
                System.out.println();

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();



        synonymPairGraph.setBlackListedWord("planet");

        System.out.println();
        result = synonymPairGraph.getSentences("world earth");
        System.out.println();


        synonymPairGraph.removeEdge("planet", "earth");

        System.out.println();
        result = synonymPairGraph.getSentences("hello world");
        System.out.println();


    }
}