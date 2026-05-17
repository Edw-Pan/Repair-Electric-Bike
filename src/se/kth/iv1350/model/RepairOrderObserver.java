package se.kth.iv1350.model;

/**
 * A listener interface for receiving notifications about changes to a repair order.
 */
public interface RepairOrderObserver {
    /**
     * Invoked when a repair order has been updated.
     * 
     * @param order The updated repair order.
     */
    void update(RepairOrderDTO order);
}
