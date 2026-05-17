package se.kth.iv1350.view;

import se.kth.iv1350.model.RepairOrderDTO;
import se.kth.iv1350.model.RepairOrderObserver;
import se.kth.iv1350.util.FileLogger;

/**
 * A logger that prints information about updated repair orders to a file.
 */
public class RepairOrderLogger implements RepairOrderObserver {
    private final FileLogger logger = new FileLogger();

    @Override
    public void update(RepairOrderDTO order) {
        logger.log("Repair order updated: " + order.getOrderId() + " [State: " + order.getState() + "]");
    }
}
