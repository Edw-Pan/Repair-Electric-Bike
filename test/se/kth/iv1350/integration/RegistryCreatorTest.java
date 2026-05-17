package se.kth.iv1350.integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RegistryCreator class.
 */
public class RegistryCreatorTest {

    /**
     * Tests that the creator correctly creates and returns the registries.
     */
    @Test
    public void testGetRegistries() {
        RegistryCreator creator = RegistryCreator.getInstance();
        assertNotNull(creator.getCustomerRegistry(), "Customer registry should not be null.");
        assertNotNull(creator.getRepairOrderRegistry(), "Repair order registry should not be null.");
    }
}
