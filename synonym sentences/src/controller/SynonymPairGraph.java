package controller;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class SynonymPairGraph implements Graph {

    private final Map<String, Integer> synonymToComponentMap;
    private final ConcurrentHashMap<String, Set<String>> synonymGraph;
    private final Set<String> blackListedWord;
    private final ReentrantLock lock;
    private Map<Integer, Set<String>> componentToSynonymMap;
    private Set<String> visitedWord;
    private Integer uniqueSynonyms;
    private boolean synonymComponentInitialised;


    public SynonymPairGraph() {
        this.blackListedWord = new HashSet<>();
        this.componentToSynonymMap = new HashMap<>();
        this.synonymToComponentMap = new HashMap<>();
        this.synonymGraph = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
        this.synonymComponentInitialised = false;
    }

    private static void checkEdgesNotEmpty(String edge1, String edge2) {
        if (edge2.isEmpty() || edge1.isEmpty()) {
            throw new NullPointerException();
        }
    }

    @Override
    public void addEdge(String edge1, String edge2) {
        checkEdgesNotEmpty(edge1, edge2);

        if (edge1.equals(edge2)) {
            return;
        }
        lock.lock();
        try {
            if (!synonymGraph.containsKey(edge1)) {
                synonymGraph.put(edge1, new HashSet<>());
            }

            if (!synonymGraph.containsKey(edge2)) {
                synonymGraph.put(edge2, new HashSet<>());
            }

            synonymGraph.get(edge1).add(edge2);
            synonymGraph.get(edge2).add(edge1);
            this.getAllSynonyms();
        } finally {
            lock.unlock();
        }
        synonymComponentInitialised = true;
    }

    @Override
    public boolean removeEdge(String edge1, String edge2) {
        checkEdgesNotEmpty(edge1, edge2);
        lock.lock();
        try {
            synonymGraph.get(edge1).remove(edge2);
            synonymGraph.get(edge2).remove(edge1);
            this.getAllSynonyms();
        } finally {
            lock.unlock();
        }
        return true;
    }

    private void findAllSynonyms() {
        lock.lock();
        try {
            componentToSynonymMap = new HashMap<>();
            visitedWord = new HashSet<>();
            uniqueSynonyms = 0;

            for (String each : synonymGraph.keySet()) {
                if (!visitedWord.contains(each)) {
                    dfs(each, uniqueSynonyms);
                    uniqueSynonyms++;
                }
            }
        } finally {
            lock.unlock();
        }

    }

    private void dfs(String word, Integer uniqueSynonyms) {
        lock.lock();
        try {
            visitedWord.add(word);
            synonymToComponentMap.put(word, uniqueSynonyms);
            Set<String> edges = synonymGraph.get(word);
            if (edges.isEmpty()) {
                return;
            }
            for (String edge : edges) {
                if (!visitedWord.contains(edge)) {
                    dfs(edge, uniqueSynonyms);
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public Map<Integer, Set<String>> getAllSynonyms() {
        lock.lock();
        try {
            this.findAllSynonyms();
            componentToSynonymMap = new HashMap<>();

            for (String each : synonymToComponentMap.keySet()) {
                Integer currentComponent = synonymToComponentMap.get(each);
                if (!componentToSynonymMap.containsKey(currentComponent)) {
                    componentToSynonymMap.put(currentComponent, new HashSet<>());
                }
                componentToSynonymMap.get(currentComponent).add(each);
            }
        } finally {
            lock.unlock();
        }
        return componentToSynonymMap;
    }

    public List<String> getSentences(String sentence) {

        List<String> words = List.of(sentence.split(" "));
        List<String> allSentences = generateAllSentence(words, 0);

        allSentences.removeIf(each -> each.equals(sentence));

        if (!blackListedWord.isEmpty()) {
            for (String each : blackListedWord) {
                allSentences.removeIf(each1 -> each1.contains(each));
            }
        }


        Collections.sort(allSentences);

        allSentences.forEach(s -> System.out.println(s));

        return allSentences;
    }

    private List<String> generateAllSentence(List<String> words, Integer i) {

        if (!synonymComponentInitialised) {
            return new ArrayList<>();
        }

        if (words.size() == i) {
            List<String> result = new ArrayList<>();
            result.add("");
            return result;
        }

        List<String> result = new ArrayList<>();
        Integer componentNumber = synonymToComponentMap.get(words.get(i));
        Set<String> allSynonym = componentToSynonymMap.get(componentNumber);
        List<String> nextWord = generateAllSentence(words, i + 1);

        for (String word1 : allSynonym) {
            for (String word2 : nextWord) {
                if (word2.isEmpty()) {
                    result.add(word1);
                } else {
                    result.add(word1 + " " + word2);
                }

            }
        }
        return result;
    }

    public void setBlackListedWord(String word) {
        blackListedWord.add(word);
    }


}
