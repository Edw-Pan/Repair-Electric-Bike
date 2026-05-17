package se.kth.iv1350.integration;

/**
 * Thrown when the database cannot be accessed.
 */
public class DatabaseFailureException extends Exception {
    /**
     * Creates a new instance with a message describing the error.
     * 
     * @param message The error message.
     */
    public DatabaseFailureException(String message) {
        super(message);
    }
}
