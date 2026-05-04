package se.kth.iv1350.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the RepairOrder class.
 */
public class RepairOrderTest {
    private RepairOrder order;
    private CustomerDTO customer;

    @BeforeEach
    public void setUp() {
        customer = new CustomerDTO("1", "Test Customer");
        order = new RepairOrder("101", customer);
    }

    /**
     * Tests that adding a service correctly updates the total cost.
     */
    @Test
    public void testAddServiceUpdatesTotalCost() {
        Amount servicePrice = new Amount(200);
        ServiceDTO service = new ServiceDTO("S1", "Test Service", servicePrice);
        
        order.addService(service);
        
        String expectedCost = "200 SEK";
        String actualCost = order.toString(); // Since toString contains the total
        
        assertTrue(actualCost.contains(expectedCost), "Total cost was not updated correctly after adding service.");
    }

    /**
     * Tests that multiple services are added correctly.
     */
    @Test
    public void testAddMultipleServices() {
        order.addService(new ServiceDTO("S1", "Service 1", new Amount(100)));
        order.addService(new ServiceDTO("S2", "Service 2", new Amount(150)));
        
        assertTrue(order.toString().contains("250 SEK"), "Total cost of multiple services is incorrect.");
    }
}
