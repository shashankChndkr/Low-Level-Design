/**
 * The Producer class is responsible for producing messages and managing queues.
 */
package manager;

import model.Message;
import model.Queue;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Producer {

    private final int DEFAULT_CAPACITY = 10;
    private Queue queue;
    private Queue errorQueue;

    private String producerName;
    private ScheduledExecutorService executorService;
    private String topicName;

    /**
     * Constructs a new Producer object with the specified topic name and producer name.
     *
     * @param topicName    the name of the topic
     * @param producerName the name of the producer
     */
    public Producer(String topicName, String producerName) {
        this.topicName = topicName;
        this.producerName = producerName;
        this.queue = new Queue(DEFAULT_CAPACITY);
        this.executorService = Executors.newScheduledThreadPool(1);
        this.errorQueue = new Queue(DEFAULT_CAPACITY);
        this.executorService.scheduleAtFixedRate(() -> this.produceErrorQueueMessage(), 1, 1, TimeUnit.SECONDS);
    }

    /**
     * Returns the queue associated with the producer.
     *
     * @return the queue
     */
    public Queue getQueue() {
        return queue;
    }

    /**
     * Returns the topic name associated with the producer.
     *
     * @return the topic name
     */
    public String getTopicName() {
        return topicName;
    }

    /**
     * Produces a message by enqueueing it in the queue.
     * If the message is expired, it is discarded.
     *
     * @param message the message to produce
     */
    public void produce(Message message) {
        if (message.isExpired()) {
            System.out.println("Message is expired");
            return;
        }
        try {
            queue.enqueue(message);
            System.out.println("Producer - " + producerName + " TopicName - " + this.getTopicName() + " Message - " + message.getContent());
        } catch (Exception e) {
            errorQueue.enqueue(message);
        }
    }

    /**
     * Produces messages from the error queue by re-enqueuing them in the main queue.
     * Expired messages in the error queue are discarded.
     */
    public void produceErrorQueueMessage() {
        executorService.submit(() ->
        {
            while (!errorQueue.isEmpty()) {
                Message message = errorQueue.peek();
                if (message.isExpired()) {
                    queue.dequeue();
                    return;
                }
                this.produce(errorQueue.dequeue());
            }
        });
    }
}
