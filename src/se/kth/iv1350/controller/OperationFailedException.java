package se.kth.iv1350.controller;

/**
 * Thrown when an operation fails for an unknown reason.
 */
public class OperationFailedException extends Exception {
    /**
     * Creates a new instance with a message and the cause of the failure.
     * 
     * @param message The error message.
     * @param cause The cause of the failure.
     */
    public OperationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
