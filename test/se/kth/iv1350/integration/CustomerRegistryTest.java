package se.kth.iv1350.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.CustomerDTO;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CustomerRegistry class.
 */
public class CustomerRegistryTest {
    private CustomerRegistry registry;

    @BeforeEach
    public void setUp() {
        registry = new CustomerRegistry();
    }

    /**
     * Tests that an existing customer can be found.
     */
    @Test
    public void testFindExistingCustomer() {
        String existingId = "1";
        CustomerDTO result = registry.findCustomer(existingId);
        assertNotNull(result, "Registry should find customer with ID '1'.");
        assertEquals(existingId, result.getId(), "Found customer ID does not match search ID.");
    }

    /**
     * Tests that searching for a non-existent customer returns null.
     */
    @Test
    public void testFindNonExistentCustomer() {
        String nonExistentId = "999";
        CustomerDTO result = registry.findCustomer(nonExistentId);
        assertNull(result, "Registry should return null for non-existent ID.");
    }
}
