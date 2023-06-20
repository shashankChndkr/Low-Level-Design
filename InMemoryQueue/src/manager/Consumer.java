/**
 * The Consumer class is responsible for consuming messages from a queue and notifying the listener.
 */
package manager;

import model.Message;
import model.Queue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Consumer implements MessageListener {

    private Queue queue;
    private List<Consumer> dependencies;
    private Set<Message> consumedMessages;
    private String topicName;

    private String consumerName;

    private Integer currentOffset;

    /**
     * Constructs a new Consumer object with the specified queue, topic name, consumer name, and dependencies.
     *
     * @param queue        the queue to consume messages from
     * @param topicName    the name of the topic
     * @param consumerName the name of the consumer
     * @param dependencies the list of dependent consumers
     */
    public Consumer(Queue queue, String topicName, String consumerName, List<Consumer> dependencies) {
        this.queue = queue;
        this.consumerName = consumerName;
        this.currentOffset = 0;
        this.topicName = topicName;
        this.dependencies = dependencies;
        this.consumedMessages = new HashSet<>();
    }

    /**
     * Adds a consumer dependency.
     *
     * @param consumer the dependent consumer to add
     */
    public void addDependency(Consumer consumer) {
        this.dependencies.add(consumer);
    }

    /**
     * Checks if a message has already been consumed by this consumer.
     *
     * @param message the message to check
     * @return true if the message has been consumed, false otherwise
     */
    public boolean isConsumed(Message message) {
        return consumedMessages.contains(message);
    }

    /**
     * Consumes the next available message from the queue.
     * If the message is expired, it is discarded.
     *
     * @throws Exception if an error occurs during consumption
     */
    public void consume() throws Exception {
        if (queue.isEmpty()) return;

        Message message = queue.getMessage(currentOffset);
        if (message.isExpired()) {
            queue.dequeue();
            return;
        }

        for (Consumer consumer : this.dependencies) {
            if (!consumer.isConsumed(message)) {
                return;
            }
        }
        currentOffset++;
        consumedMessages.add(message);
        onMessage(message);
    }

    /**
     * Callback method called when a message is received.
     *
     * @param message the received message
     */
    @Override
    public void onMessage(Message message) {
        System.out.println("Message Received: " + message.getContent() + " for topic - " + topicName + " consumer - " + consumerName);
    }
}