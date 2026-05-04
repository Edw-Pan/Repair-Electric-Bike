package se.kth.iv1350.model;

/**
 * Contains information about a particular service that can be performed during a repair.
 */
public final class ServiceDTO {
    private final String serviceId;
    private final String description;
    private final Amount price;

    /**
     * Creates a new instance.
     *
     * @param serviceId   The service's unique identification.
     * @param description A description of the service.
     * @param price       The price of the service.
     */
    public ServiceDTO(String serviceId, String description, Amount price) {
        this.serviceId = serviceId;
        this.description = description;
        this.price = price;
    }

    /**
     * Returns the service's ID.
     *
     * @return The service's ID.
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Returns the service's description.
     *
     * @return The service's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the service's price.
     *
     * @return The service's price.
     */
    public Amount getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the service.
     *
     * @return A string representation of the service.
     */
    @Override
    public String toString() {
        return description + " (ID: " + serviceId + "), Price: " + price;
    }
}
