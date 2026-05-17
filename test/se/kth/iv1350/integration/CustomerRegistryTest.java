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
     * Tests that searching for an existing customer works.
     */
    @Test
    public void testFindExistingCustomer() throws DatabaseFailureException {
        String existingId = "1";
        CustomerDTO result = registry.findCustomer(existingId);
        assertNotNull(result, "Registry should find customer with ID 1.");
        assertEquals(existingId, result.getId(), "Registry should return correct customer ID.");
    }

    /**
     * Tests that searching for a non-existent customer throws CustomerNotFoundException.
     */
    @Test
    public void testFindNonExistentCustomer() {
        String nonExistentId = "888";
        assertThrows(CustomerNotFoundException.class, () -> {
            registry.findCustomer(nonExistentId);
        });
    }
}
