package se.kth.iv1350.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a repair order.
 */
public class RepairOrder {
    private final String orderId;
    private final CustomerDTO customer;
    private RepairState state = RepairState.RECEIVED;
    private final List<ServiceDTO> services = new ArrayList<>();
    private Amount totalCost = new Amount(0);

    /**
     * Creates a new instance.
     *
     * @param orderId  The order's ID.
     * @param customer The customer who owns the order.
     */
    public RepairOrder(String orderId, CustomerDTO customer) {
        this.orderId = orderId;
        this.customer = customer;
    }

    /**
     * Adds a service to this repair order.
     *
     * @param service The service to add.
     */
    public void addService(ServiceDTO service) {
        services.add(service);
        totalCost = totalCost.plus(service.getPrice());
    }

    /**
     * Sets the state of this repair order.
     *
     * @param state The new state.
     */
    public void setState(RepairState state) { this.state = state; }

    /**
     * Creates a DTO representation of this repair order.
     *
     * @return A DTO representing this order.
     */
    public RepairOrderDTO toDTO() {
        return new RepairOrderDTO(orderId, customer, state, services, totalCost);
    }

    /**
     * Returns the order's ID.
     *
     * @return The order's ID.
     */
    public String getOrderId() { 
        return orderId; 
    }

    /**
     * Returns a string representation of the repair order.
     *
     * @return A string representation of the repair order.
     */
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
