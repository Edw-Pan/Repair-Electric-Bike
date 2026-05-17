package se.kth.iv1350.integration;

/**
 * Thrown when a customer cannot be found in the customer registry.
 */
public class CustomerNotFoundException extends RuntimeException {
    private final String customerId;

    /**
     * Creates a new instance with a message and the ID of the customer that was not found.
     * 
     * @param customerId The ID of the customer that was not found.
     */
    public CustomerNotFoundException(String customerId) {
        super("Customer with ID " + customerId + " was not found.");
        this.customerId = customerId;
    }

    /**
     * Returns the ID of the customer that was not found.
     * 
     * @return The customer ID.
     */
    public String getCustomerId() {
        return customerId;
    }
}
