package se.kth.iv1350.model;

import java.util.List;

/**
 * A DTO representing a repair order.
 */
public final class RepairOrderDTO {
    private final String orderId;
    private final CustomerDTO customer;
    private final RepairState state;
    private final List<ServiceDTO> services;
    private final Amount totalCost;

    /**
     * Creates a new instance.
     *
     * @param orderId   The order's ID.
     * @param customer  The customer who owns the order.
     * @param state     The current state of the order.
     * @param services  The services included in the order.
     * @param totalCost The total cost of the order.
     */
    public RepairOrderDTO(String orderId, CustomerDTO customer, RepairState state, 
                          List<ServiceDTO> services, Amount totalCost) {
        this.orderId = orderId;
        this.customer = customer;
        this.state = state;
        this.services = List.copyOf(services); // Ensure immutability
        this.totalCost = totalCost;
    }

    /**
     * Returns the order's ID.
     * @return The order's ID.
     */
    public String getOrderId() { 
        return orderId; 
    }

    /**
     * Returns the customer.
     * @return The customer.
     */
    public CustomerDTO getCustomer() { 
        return customer; 
    }

    /**
     * Returns the state.
     * @return The state.
     */
    public RepairState getState() { 
        return state; 
    }

    /**
     * Returns the services.
     * @return The services.
     */
    public List<ServiceDTO> getServices() { 
        return services; 
    }

    /**
     * Returns the total cost.
     * @return The total cost.
     */
    public Amount getTotalCost() { 
        return totalCost; 
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ").append(orderId).append(" [").append(state).append("]\n");
        sb.append(" ").append(customer).append("\n");
        sb.append(" Services:\n");
        for (ServiceDTO service : services) {
            sb.append("  - ").append(service).append("\n");
        }
        sb.append(" Total Cost: ").append(totalCost);
        return sb.toString();
    }
}
