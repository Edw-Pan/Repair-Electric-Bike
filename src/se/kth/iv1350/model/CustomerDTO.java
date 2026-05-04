package se.kth.iv1350.model;

/**
 * Data about a customer.
 */
public final class CustomerDTO {
    private final String name;
    private final String id;

    /**
     * Creates a new instance.
     *
     * @param id   The customer's ID.
     * @param name The customer's name.
     */
    public CustomerDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the customer's ID.
     *
     * @return The customer's ID.
     */
    public String getId() { return id; }
    
    /**
     * Returns a string representation of the customer.
     *
     * @return A string representation of the customer.
     */
    @Override
    public String toString() { return "Customer: " + name + " (" + id + ")"; }
}
