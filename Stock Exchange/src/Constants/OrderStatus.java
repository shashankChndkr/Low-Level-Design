/**
 * The OrderStatus enum represents the possible status values for an order.
 * These status values indicate the current state of an order.
 */
package Constants;

public enum OrderStatus {

    /**
     * The order has been accepted.
     */
    ACCEPTED,

    /**
     * The order has been rejected.
     */
    REJECTED,

    /**
     * The order has been cancelled.
     */
    CANCELLED,

    /**
     * The order has been executed.
     */
    EXECUTED

}
