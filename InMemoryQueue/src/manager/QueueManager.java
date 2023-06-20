/**
 * The QueueManager class is responsible for managing producers, consumers, and message queues.
 */
package manager;

import model.Queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class QueueManager {

    private HashMap<String, Producer> producers;
    private HashMap<String, List<Consumer>> consumers;

    private ScheduledExecutorService scheduledExecutorService;

    /**
     * Constructs a new QueueManager object.
     */
    public QueueManager() {
        producers = new HashMap<>();
        consumers = new HashMap<>();
        scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Adds a producer with the specified topic name and producer name.
     *
     * @param topicName    the name of the topic
     * @param producerName the name of the producer
     */
    public void addProducer(String topicName, String producerName) {
        if (producers.containsKey(topicName)) {
            return;
        }
        Producer producer = new Producer(topicName, producerName);
        producers.put(topicName, producer);
    }

    /**
     * Adds a consumer with the specified topic name and consumer name.
     *
     * @param topicName    the name of the topic
     * @param consumerName the name of the consumer
     */
    public void addConsumer(String topicName, String consumerName) {
        Queue queue = producers.get(topicName).getQueue();
        Consumer consumer = new Consumer(queue, topicName, consumerName, new ArrayList<>());
        if (!consumers.containsKey(topicName)) {
            consumers.putIfAbsent(topicName, new ArrayList<>());
        }
        consumers.get(topicName).add(consumer);
    }

    /**
     * Starts consuming messages from the consumers.
     */
    public void startConsumers() {
        System.out.println("Starting Consumers");
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            for (String topic : consumers.keySet()) {
                List<Consumer> consumers1 = consumers.get(topic);
                for (Consumer consumer : consumers1) {
                    try {
                        consumer.consume();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * Returns the map of producers.
     *
     * @return the map of producers
     */
    public HashMap<String, Producer> getProducers() {
        return producers;
    }

    /**
     * Returns the map of consumers.
     *
     * @return the map of consumers
     */
    public HashMap<String, List<Consumer>> getConsumers() {
        return consumers;
    }

    /**
     * Shuts down the consumers and stops consuming messages.
     */
    public void shutDownConsumers() {
        System.out.println("Consumers shutting down");
        scheduledExecutorService.shutdownNow();
    }
}