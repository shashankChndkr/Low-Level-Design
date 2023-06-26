Designing a Synonym Sentence Retrieval System
Problem Statement:

Develop a high-performance system capable of retrieving synonym sentences in parallel. The system should support three
main functionalities: adding synonym word pairs, removing synonym word pairs, and retrieving similar sentences.
Additionally, the system should allow blacklisting of specific words, rendering the corresponding synonym pairs
inactive.

Interfaces:

1. addSynonymPair(word1, word2): Add a synonym pair of words to the system.

        - Input:
            - word1: First word from the synonym pair
            - word2: Second word from the synonym pair
          - Output: None

2. removeSynonymPair(word1, word2): Remove a synonym pair of words from the system.

        - Input:
            - word1: First word from the synonym pair
            - word2: Second word from the synonym pair
          - Output: None

3. getSentences(sentence): Retrieve similar sentences for a given input sentence.

        - Input:
            - sentence: The input sentence for which unique similar sentences need to be returned in lexicographically sorted order.
          - Output:
              - List of similar sentences

Additional Constraints:

- The system should ensure fast response times for synonym sentence retrieval.
- The retrieved sentences should be unique and sorted in lexicographic order.
- Inactive synonym pairs should be considered when generating similar sentences.
- The system should allow the blacklisting of words, which renders all corresponding synonym pairs involving the
  blacklisted word inactive.

Sample Usage:

1. Adding synonym pairs:

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

2. Retrieving similar sentences:

    - "hello happy";
        - Output:
          howdy high
          howdy ecstasy
          howdy happy
          howdy cheerful
          hello high
          hello ecstasy
          hello happy
          hello cheerful
          hey high
          hey ecstasy
          hey happy
          hey cheerful
          bonjour high
          bonjour ecstasy
          bonjour happy
          bonjour cheerful
          adios high
          adios ecstasy
          adios happy
          adios cheerful
          Number of components 2


3. Removing a synonym pair:

4. Retrieving similar sentences after removal:
