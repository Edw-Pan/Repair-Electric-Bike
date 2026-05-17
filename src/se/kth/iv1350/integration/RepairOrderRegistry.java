package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.model.RepairOrderDTO;

/**
 * Handles all database calls regarding repair orders. Since there is no database,
 * this class stores all orders in a list.
 */
public class RepairOrderRegistry {
    private final List<RepairOrderDTO> orders = new ArrayList<>();

    /**
     * Creates a new instance.
     */
    public RepairOrderRegistry() {
    }

    /**
     * Saves the specified repair order.
     *
     * @param order The order to save.
     */
    public void saveRepairOrder(RepairOrderDTO order) {
        // If it already exists, replace it. Otherwise, add it.
        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i).getOrderId().equals(order.getOrderId())) {
                orders.set(i, order);
                return;
            }
        }
        orders.add(order);
    }

    /**
     * Finds a repair order with the specified ID.
     *
     * @param orderId The ID of the order to find.
     * @return The order with the specified ID, or <code>null</code> if no such order exists.
     * @throws DatabaseFailureException if the orderId is "999", simulating a database failure.
     */
    public RepairOrderDTO findRepairOrder(String orderId) throws DatabaseFailureException {
        if (orderId.equals("999")) {
            throw new DatabaseFailureException("Database could not be reached.");
        }
        for (RepairOrderDTO order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}
