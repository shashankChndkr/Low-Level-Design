/**
 * A listener interface for receiving messages.
 */
package manager;

import model.Message;

public interface MessageListener {

    /**
     * Invoked when a message is received.
     *
     * @param message the received message
     */
    void onMessage(Message message);
}
