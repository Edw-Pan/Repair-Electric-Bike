package se.kth.iv1350.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.integration.RegistryCreator;
import se.kth.iv1350.model.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Controller class.
 */
public class ControllerTest {
    private Controller contr;

    @BeforeEach
    public void setUp() {
        RegistryCreator creator = RegistryCreator.getInstance();
        contr = new Controller(creator);
    }

    /**
     * Tests that starting a repair order for a valid customer works.
     */
    @Test
    public void testStartRepairOrderValidCustomer() throws OperationFailedException {
        RepairOrderDTO result = contr.startRepairOrder("1", "101", "Problem");
        assertNotNull(result, "Repair order DTO should not be null for valid customer.");
        assertTrue(result.getOrderId().equals("101"), "Order ID in created order DTO is incorrect.");
    }

    /**
     * Tests that starting a repair order for an invalid customer throws CustomerNotFoundException.
     */
    @Test
    public void testStartRepairOrderInvalidCustomer() {
        assertThrows(se.kth.iv1350.integration.CustomerNotFoundException.class, () -> {
            contr.startRepairOrder("NON-EXISTENT", "101", "Problem");
        });
    }

    /**
     * Tests that searching for a customer works.
     */
    @Test
    public void testSearchCustomer() throws OperationFailedException {
        CustomerDTO result = contr.searchCustomer("1");
        assertNotNull(result, "Customer should be found.");
        assertEquals("1", result.getId(), "Correct customer ID should be returned.");
    }

    /**
     * Tests that adding a diagnostic report works.
     */
    @Test
    public void testAddDiagnosticReport() throws OperationFailedException {
        contr.startRepairOrder("1", "101", "Problem");
        RepairOrderDTO result = contr.addDiagnosticReport("101", "Found issue");
        assertNotNull(result, "Updated order should not be null.");
        assertEquals("Found issue", result.getDiagnosticReport(), "Diagnostic report was not updated.");
    }

    /**
     * Tests that adding a service through the controller works.
     */
    @Test
    public void testAddServiceToOrder() throws OperationFailedException {
        contr.startRepairOrder("1", "101", "Problem");
        ServiceDTO service = new ServiceDTO("S1", "Test", new Amount(100));
        
        RepairOrderDTO updatedOrder = contr.addServiceToOrder("101", service);
        assertNotNull(updatedOrder, "Updated order DTO should not be null.");
        assertEquals(100, updatedOrder.getTotalCost().getAmount(), "Total cost should be 100.");
    }

    /**
     * Tests that searching for a repair order works.
     */
    @Test
    public void testSearchRepairOrder() throws OperationFailedException {
        contr.startRepairOrder("1", "101", "Problem");
        RepairOrderDTO result = contr.searchRepairOrder("101");
        assertNotNull(result, "Order should be found.");
        assertEquals("101", result.getOrderId(), "Correct order ID should be returned.");
    }

    /**
     * Tests that registering an accepted order works.
     */
    @Test
    public void testRegisterAcceptedOrder() throws OperationFailedException {
        contr.startRepairOrder("1", "101", "Problem");
        RepairOrderDTO result = contr.registerAcceptedOrder("101");
        assertNotNull(result, "Accepted order should not be null.");
        assertEquals(RepairState.ACCEPTED, result.getState(), "State should be ACCEPTED.");
    }

    /**
     * Tests that updating state works.
     */
    @Test
    public void testUpdateState() throws OperationFailedException {
        contr.startRepairOrder("1", "101", "Problem");
        RepairOrderDTO result = contr.updateState("101", RepairState.COMPLETED);
        assertNotNull(result, "Updated order should not be null.");
        assertEquals(RepairState.COMPLETED, result.getState(), "State should be COMPLETED.");
    }

    /**
     * Tests that operations on non-existent orders return null.
     */
    @Test
    public void testOperationsOnNonExistentOrder() throws OperationFailedException {
        assertNull(contr.searchRepairOrder("888"), "Search should return null.");
        assertNull(contr.addDiagnosticReport("888", "Report"), "Add diagnostic should return null.");
        assertNull(contr.addServiceToOrder("888", new ServiceDTO("S1", "T", new Amount(0))), "Add service should return null.");
        assertNull(contr.registerAcceptedOrder("888"), "Register accepted should return null.");
        assertNull(contr.updateState("888", RepairState.ACCEPTED), "Update state should return null.");
    }
}
