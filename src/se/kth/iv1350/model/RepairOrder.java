package se.kth.iv1350.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

/**
 * Represents a repair order.
 */
public class RepairOrder {
    private final String orderId;
    private final CustomerDTO customer;
    private final LocalDateTime creationTime;
    private String problemDescription;
    private String diagnosticReport;
    private RepairState state = RepairState.NEWLY_CREATED;
    private final List<ServiceDTO> services = new ArrayList<>();
    private Amount totalCost = new Amount(0);
    private final List<RepairOrderObserver> observers = new ArrayList<>();
    private DiscountStrategy discountStrategy = new NoDiscount();

    /**
     * Creates a new instance.
     *
     * @param orderId  The order's ID.
     * @param customer The customer who owns the order.
     * @param problemDescription A description of the problem.
     */
    public RepairOrder(String orderId, CustomerDTO customer, String problemDescription) {
        this.orderId = orderId;
        this.customer = customer;
        this.problemDescription = problemDescription;
        this.creationTime = LocalDateTime.now();
    }

    /**
     * Creates a new instance based on the specified DTO.
     *
     * @param dto The DTO to use for initialization.
     */
    public RepairOrder(RepairOrderDTO dto) {
        this.orderId = dto.getOrderId();
        this.customer = dto.getCustomer();
        this.problemDescription = dto.getProblemDescription();
        this.diagnosticReport = dto.getDiagnosticReport();
        this.creationTime = dto.getCreationTime();
        this.state = dto.getState();
        this.services.addAll(dto.getServices());
        this.totalCost = dto.getTotalCost();
    }

    public void setDiagnosticReport(String diagnosticReport) {
        this.diagnosticReport = diagnosticReport;
        notifyObservers();
    }

    public void addService(ServiceDTO service) {
        services.add(service);
        totalCost = totalCost.plus(service.getPrice());
        notifyObservers();
    }

    public void setState(RepairState state) { 
        this.state = state; 
        notifyObservers();
    }

    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    public void addObservers(List<RepairOrderObserver> observers) {
        this.observers.addAll(observers);
    }

    /**
     * Creates a DTO representation of this repair order.
     *
     * @return A DTO representing this order.
     */
    public RepairOrderDTO toDTO() {
        Amount discountedCost = discountStrategy.calculateDiscount(totalCost);
        // Bullet 21: Estimation is 2 hours after creation
        LocalDateTime estimatedCompletion = creationTime.plusHours(2);
        return new RepairOrderDTO(orderId, customer, problemDescription, diagnosticReport, 
                                  state, services, discountedCost, creationTime, estimatedCompletion);
    }

    private void notifyObservers() {
        RepairOrderDTO dto = toDTO();
        for (RepairOrderObserver observer : observers) {
            observer.update(dto);
        }
    }

    public String getOrderId() { return orderId; }

    @Override
    public String toString() {
        return toDTO().toString();
    }
}
