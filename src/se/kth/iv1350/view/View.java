package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.controller.OperationFailedException;
import se.kth.iv1350.integration.CustomerNotFoundException;
import se.kth.iv1350.model.*;

/**
 * A placeholder for the view. Contains hardcoded calls to the controller.
 */
public class View {
    private final Controller contr;
    private final ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();

    /**
     * Creates a new instance.
     *
     * @param contr The controller used for all system operations.
     */
    public View(Controller contr) { 
        this.contr = contr; 
        contr.addRepairOrderObserver(new RepairOrderView());
        contr.addRepairOrderObserver(new RepairOrderLogger());
    }

    /**
     * Performs a fake execution by calling all system operations in the controller.
     */
    public void runFakeExecution() {
        testBasicFlow();
        testStrategyPattern();
        testExceptionHandling();
    }

    /**
     * Simulation of the Basic Flow as defined in Seminar 1.
     * Demonstrates customer search, order creation, diagnostic reports, and services.
     */
    private void testBasicFlow() {
        System.out.println("--- Basic Flow Simulation (Seminar 1 Scenarios) ---");
        try {
            // Bullet 5: System searches customer registry
            System.out.println("Step 5: Searching for customer...");
            CustomerDTO customer = contr.searchCustomer("1");
            System.out.println("Step 6: Presenting customer details: " + customer.getName());

            // Bullet 11: System creates repair order
            System.out.println("Step 11: Creating repair order...");
            contr.startRepairOrder("1", "1", "Flat tire");

            // Bullet 17: System updates repair order with diagnostic
            System.out.println("Step 17: Adding diagnostic report...");
            contr.addDiagnosticReport("1", "Nail found in rear tire.");
            
            // Bullet 18: Adding proposed repair tasks/services
            System.out.println("Step 18: Adding repair tasks...");
            ServiceDTO tireService = new ServiceDTO("S-01", "Tire Replacement", new Amount(300));
            contr.addServiceToOrder("1", tireService);
            
            // Bullet 20: Receptionist registers that customer accepted repair order
            System.out.println("Step 20: Registering customer acceptance...");
            RepairOrderDTO finalOrder = contr.registerAcceptedOrder("1");
            
            // Bullet 21: System prints repair order (including estimation)
            System.out.println("\nStep 21: Final printed order (includes completion estimation):");
            System.out.println(finalOrder);
            System.out.println("\nBasic flow completed successfully.");

        } catch (Exception e) {
            handleTechnicalError("Failed during basic flow simulation", e);
        }
    }

    private void testStrategyPattern() {
        System.out.println("\n--- Testing Strategy Pattern (Loyalty Discount) ---");
        try {
            CustomerDTO customer = contr.searchCustomer("1");
            RepairOrder loyaltyOrder = new RepairOrder("L-100", customer, "Routine Check");
            loyaltyOrder.setDiscountStrategy(new LoyaltyDiscount());
            loyaltyOrder.addService(new ServiceDTO("S-99", "Premium Service", new Amount(1000)));
            
            System.out.println("Loyalty Order (10% discount on 1000 SEK):");
            System.out.println(loyaltyOrder);
        } catch (Exception e) {
            handleTechnicalError("Failed during strategy test", e);
        }
    }

    private void testExceptionHandling() {
        System.out.println("\n--- Alternative Flow 5a: Customer Not Found ---");
        try {
            contr.searchCustomer("99");
        } catch (CustomerNotFoundException e) {
            errorMsgHandler.showErrorMsg("Could not find customer with ID: " + e.getCustomerId());
        } catch (Exception e) {
            handleTechnicalError("Unexpected error during customer search", e);
        }

        System.out.println("\n--- Testing Exception: Database Failure (Simulated) ---");
        try {
            contr.searchRepairOrder("999");
        } catch (OperationFailedException e) {
            errorMsgHandler.showErrorMsg("System failure: " + e.getMessage());
        } catch (Exception e) {
            handleTechnicalError("Unexpected technical error", e);
        }
    }

    private void handleTechnicalError(String uiMsg, Exception e) {
        errorMsgHandler.showErrorMsg(uiMsg);
    }
}
