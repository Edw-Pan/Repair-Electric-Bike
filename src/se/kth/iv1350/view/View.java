package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.*;

/**
 * A placeholder for the view. Contains hardcoded calls to the controller.
 */
public class View {
    private final Controller contr;

    /**
     * Creates a new instance.
     *
     * @param contr The controller used for all system operations.
     */
    public View(Controller contr) { this.contr = contr; }

    /**
     * Performs a fake execution by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        System.out.println("Starting repair for Customer-1");
        RepairOrderDTO order = contr.startRepairOrder("1", "1");
        
        System.out.println("Controller returned: " + (order != null ? "RepairOrder ID " + order.getOrderId() : "null"));

        System.out.println("Adding service: Brake Adjustment");
        ServiceDTO brakeService = new ServiceDTO("Service-1", "Brake Adjustment", new Amount(500));
        order = contr.addServiceToOrder("1", brakeService);
        
        System.out.println("Order just finished.");
        order = contr.updateState("1", RepairState.FINISHED);
        
        System.out.println("\nFinal Result:\n" + order);
    }
}
