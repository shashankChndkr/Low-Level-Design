roblem: In-Memory Queueing System

Design an efficient in-memory queueing system with low latency requirements. The system should be implemented in Java
and should include a producer and consumer using multithreading. The following constraints should be considered:

Must-Have Tasks:

1. Design an in-memory queue:
    - The queue should be bounded in size and held completely in-memory. The size of the queue should be configurable.
    - The queue should only hold JSON messages.

2. Producer-Consumer Relationship:
    - The queue should allow messages to be received from the producer and sent to the consumer.
    - There should be at least one producer and multiple consumers.
    - Consumers should register callbacks that will be invoked whenever there is a new message.

3. Message Subscription:
    - Consumers should be able to subscribe to messages that match a particular expression.
    - Consumers can have dependency relationships between them, where one consumer cannot consume a message before other
      specified consumers have consumed it.
      For example: Consumer C must process a message only after consumers A and B have processed it (C -> A, B).

4. Concurrency Handling:
    - The system should handle concurrent writes and reads consistently between the producer and consumers.
    - Implement synchronization mechanisms to ensure thread safety.

5. Retry Mechanisms:
    - Implement mechanisms to handle failures in message processing, both during publishing and consumption.
    - Provide retries for failed operations to increase the chances of successful processing.

6. Message Time-to-Live (TTL):
    - Implement a mechanism to handle message expiration based on a defined time-to-live (TTL) value.
    - Messages that have expired should not be delivered to consumers.