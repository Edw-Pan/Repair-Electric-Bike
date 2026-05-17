package se.kth.iv1350.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.CustomerDTO;
import se.kth.iv1350.model.RepairOrder;
import se.kth.iv1350.model.RepairOrderDTO;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RepairOrderRegistry class.
 */
public class RepairOrderRegistryTest {
    private RepairOrderRegistry registry;
    private RepairOrderDTO sampleOrder;

    @BeforeEach
    public void setUp() {
        registry = new RepairOrderRegistry();
        CustomerDTO customer = new CustomerDTO("1", "Test", "test@kth.se", "Brand", "Model", "SN1");
        RepairOrder order = new RepairOrder("101", customer, "Problem");
        sampleOrder = order.toDTO();
    }

    /**
     * Tests that saving and finding a repair order works.
     */
    @Test
    public void testSaveAndFindRepairOrder() throws DatabaseFailureException {
        registry.saveRepairOrder(sampleOrder);
        RepairOrderDTO found = registry.findRepairOrder("101");
        assertNotNull(found, "Order should be found in registry after saving.");
        assertEquals("101", found.getOrderId(), "Found order has incorrect ID.");
    }

    /**
     * Tests that searching for a non-existent order returns null.
     */
    @Test
    public void testFindNonExistentOrder() throws DatabaseFailureException {
        RepairOrderDTO found = registry.findRepairOrder("888");
        assertNull(found, "Searching for a non-existent order should return null.");
    }
}
