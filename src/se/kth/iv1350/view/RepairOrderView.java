package se.kth.iv1350.view;

import se.kth.iv1350.model.RepairOrderDTO;
import se.kth.iv1350.model.RepairOrderObserver;

/**
 * A view that prints information about updated repair orders to the console.
 */
public class RepairOrderView implements RepairOrderObserver {
    @Override
    public void update(RepairOrderDTO order) {
        System.out.println("[Observer] Repair order " + order.getOrderId() + " updated. Status: " + order.getState());
    }
}
