package se.kth.iv1350.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.controller.OperationFailedException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for exception handling in registries and controller.
 */
public class ExceptionHandlingTest {
    private RegistryCreator regCreator;
    private Controller contr;

    @BeforeEach
    public void setUp() {
        regCreator = RegistryCreator.getInstance();
        contr = new Controller(regCreator);
    }

    /**
     * Tests that CustomerNotFoundException is thrown when customer is missing.
     */
    @Test
    public void testCustomerNotFound() {
        CustomerRegistry registry = regCreator.getCustomerRegistry();
        String invalidId = "non-existent-id";
        
        assertThrows(CustomerNotFoundException.class, () -> {
            registry.findCustomer(invalidId);
        }, "Registry should throw CustomerNotFoundException for invalid ID.");
    }

    /**
     * Tests that DatabaseFailureException is thrown when searching for order ID 999.
     */
    @Test
    public void testDatabaseFailureInRegistry() {
        RepairOrderRegistry registry = regCreator.getRepairOrderRegistry();
        String crashId = "999";

        assertThrows(DatabaseFailureException.class, () -> {
            registry.findRepairOrder(crashId);
        }, "Registry should throw DatabaseFailureException for ID 999.");
    }

    /**
     * Tests that Controller wraps DatabaseFailureException in OperationFailedException.
     */
    @Test
    public void testOperationFailedInController() {
        String crashId = "999";

        assertThrows(OperationFailedException.class, () -> {
            contr.searchRepairOrder(crashId);
        }, "Controller should wrap DatabaseFailureException in OperationFailedException.");
    }
}
