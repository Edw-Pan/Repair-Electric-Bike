package se.kth.iv1350.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.CustomerDTO;
import se.kth.iv1350.model.RepairOrder;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RepairOrderRegistry class.
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
    }

    /**
     * Tests that a repair order can be saved and then found.
     */
    @Test
    public void testSaveAndFindRepairOrder() {
        CustomerDTO customer = new CustomerDTO("1", "Test Customer");
        RepairOrder order = new RepairOrder("101", customer);
        registry.saveRepairOrder(order);
        
        RepairOrder found = registry.findRepairOrder("101");
        assertNotNull(found, "The saved repair order should be found.");
        assertEquals("101", found.getOrderId(), "The found order ID should match the saved one.");
    }

    /**
     * Tests that searching for a non-existent order returns null.
     */
    @Test
    public void testFindNonExistentOrder() {
        RepairOrder found = registry.findRepairOrder("999");
        assertNull(found, "Searching for a non-existent order should return null.");
    }
}
