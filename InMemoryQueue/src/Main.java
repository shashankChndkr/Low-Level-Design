import manager.Producer;
import manager.QueueManager;
import model.Message;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        QueueManager queueManager = new QueueManager();

        // Add producers
        for (int i = 0; i < 20; i++) {
            queueManager.addProducer("topic-" + i, "producer-" + i);
        }

        // Add consumers
        for (int i = 0; i < 20; i++) {
            queueManager.addConsumer("topic-" + i, "consumerName-" + 11111);
            queueManager.addConsumer("topic-" + i, "consumerName-" + 22222);
            queueManager.addConsumer("topic-" + i, "consumerName-" + 33333);
        }

        // Start consuming messages
        queueManager.startConsumers();

        // Create and run producer threads
        Runnable producerRunnable = new Runnable() {
            @Override
            public void run() {
                HashMap<String, Producer> producers = queueManager.getProducers();
                for (String topicName : producers.keySet()) {
                    Producer producer = producers.get(topicName);
                    Message message = new Message("content: " + LocalDateTime.now().getNano(), 1);
                    producer.produce(message);
                }
            }
        };

        Thread producerThread = new Thread(producerRunnable);
        producerThread.start();
    }
}