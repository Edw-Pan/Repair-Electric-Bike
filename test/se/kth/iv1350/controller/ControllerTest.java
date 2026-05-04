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
        RegistryCreator creator = new RegistryCreator();
        contr = new Controller(creator);
    }

    /**
     * Tests that starting a repair order for a valid customer works.
     */
    @Test
    public void testStartRepairOrderValidCustomer() {
        RepairOrderDTO result = contr.startRepairOrder("1", "101");
        assertNotNull(result, "Repair order DTO should not be null for valid customer.");
        assertTrue(result.toString().contains("101"), "Order ID in created order DTO is incorrect.");
    }

    /**
     * Tests that starting a repair order for an invalid customer returns null.
     */
    @Test
    public void testStartRepairOrderInvalidCustomer() {
        RepairOrderDTO result = contr.startRepairOrder("NON-EXISTENT", "101");
        assertNull(result, "Repair order DTO should be null for invalid customer ID.");
    }

    /**
     * Tests that adding a service through the controller works.
     */
    @Test
    public void testAddServiceToOrder() {
        contr.startRepairOrder("1", "101");
        ServiceDTO service = new ServiceDTO("S1", "Test", new Amount(100));
        
        RepairOrderDTO updatedOrder = contr.addServiceToOrder("101", service);
        assertNotNull(updatedOrder, "Updated order DTO should not be null.");
        assertTrue(updatedOrder.toString().contains("100 SEK"), "Total cost in updated order DTO is incorrect.");
    }
}
