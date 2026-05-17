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
        customer = new CustomerDTO("1", "Test Customer", "test@kth.se", "Brand", "Model", "SN1");
        order = new RepairOrder("101", customer, "Problem");
    }

    /**
     * Tests that adding a service correctly updates the total cost.
     */
    @Test
    public void testAddServiceUpdatesTotalCost() {
        Amount servicePrice = new Amount(200);
        ServiceDTO service = new ServiceDTO("S1", "Test Service", servicePrice);
        
        order.addService(service);
        
        assertEquals(200, order.toDTO().getTotalCost().getAmount(), "Total cost was not updated correctly.");
    }

    /**
     * Tests that the diagnostic report is set correctly.
     */
    @Test
    public void testSetDiagnosticReport() {
        String report = "Broken chain";
        order.setDiagnosticReport(report);
        assertEquals(report, order.toDTO().getDiagnosticReport(), "Diagnostic report was not set correctly.");
    }

    /**
     * Tests that toDTO correctly copies all fields.
     */
    @Test
    public void testToDTO() {
        order.setDiagnosticReport("Report");
        order.setState(RepairState.READY_FOR_APPROVAL);
        RepairOrderDTO dto = order.toDTO();
        
        assertEquals("101", dto.getOrderId(), "Order ID not copied to DTO.");
        assertEquals("Problem", dto.getProblemDescription(), "Problem description not copied to DTO.");
        assertEquals("Report", dto.getDiagnosticReport(), "Diagnostic report not copied to DTO.");
        assertEquals(RepairState.READY_FOR_APPROVAL, dto.getState(), "State not copied to DTO.");
    }

    /**
     * Tests that the constructor taking a DTO correctly reconstructs the object.
     */
    @Test
    public void testConstructorFromDTO() {
        order.setDiagnosticReport("Report");
        order.addService(new ServiceDTO("S1", "T", new Amount(100)));
        RepairOrderDTO dto = order.toDTO();
        
        RepairOrder reconstructed = new RepairOrder(dto);
        RepairOrderDTO reconstructedDto = reconstructed.toDTO();
        
        assertEquals(dto.getOrderId(), reconstructedDto.getOrderId(), "Order ID mismatch.");
        assertEquals(dto.getTotalCost().getAmount(), reconstructedDto.getTotalCost().getAmount(), "Total cost mismatch.");
        assertEquals(dto.getDiagnosticReport(), reconstructedDto.getDiagnosticReport(), "Diagnostic report mismatch.");
    }

    /**
     * Tests that the state can be changed.
     */
    @Test
    public void testSetState() {
        order.setState(RepairState.PAID);
        assertEquals(RepairState.PAID, order.toDTO().getState(), "State was not updated.");
    }
}
