/**
 * This enum represents the split types used in the application.
 */
package constants;

public enum SplitType {
    /**
     * Represents an equal split type, where the amount is divided equally among participants.
     */
    EQUAL,

    /**
     * Represents a percentage split type, where the amount is divided based on the specified percentages.
     */
    PERCENTAGE,

    /**
     * Represents an exact split type, where the amount is split exactly as specified without any calculations.
     */
    EXACT

}