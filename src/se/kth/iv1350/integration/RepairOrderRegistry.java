package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.model.RepairOrder;

/**
 * Handles all database calls regarding repair orders. Since there is no database,
 * this class stores all orders in a list.
 */
public class RepairOrderRegistry {
    private final List<RepairOrder> orders = new ArrayList<>();

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
    public void saveRepairOrder(RepairOrder order) {
        orders.add(order);
    }

    /**
     * Finds a repair order with the specified ID.
     *
     * @param orderId The ID of the order to find.
     * @return The order with the specified ID, or <code>null</code> if no such order exists.
     */
    public RepairOrder findRepairOrder(String orderId) {
        for (RepairOrder order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}
