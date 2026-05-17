package se.kth.iv1350.model;

import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A DTO representing a repair order.
 */
public final class RepairOrderDTO {
    private final String orderId;
    private final CustomerDTO customer;
    private final String problemDescription;
    private final String diagnosticReport;
    private final RepairState state;
    private final List<ServiceDTO> services;
    private final Amount totalCost;
    private final LocalDateTime creationTime;
    private final LocalDateTime estimatedCompletionTime;

    /**
     * Creates a new instance.
     */
    public RepairOrderDTO(String orderId, CustomerDTO customer, String problemDescription, 
                          String diagnosticReport, RepairState state, List<ServiceDTO> services, 
                          Amount totalCost, LocalDateTime creationTime, LocalDateTime estimatedCompletionTime) {
        this.orderId = orderId;
        this.customer = customer;
        this.problemDescription = problemDescription;
        this.diagnosticReport = diagnosticReport;
        this.state = state;
        this.services = List.copyOf(services);
        this.totalCost = totalCost;
        this.creationTime = creationTime;
        this.estimatedCompletionTime = estimatedCompletionTime;
    }

    public String getOrderId() { return orderId; }
    public CustomerDTO getCustomer() { return customer; }
    public String getProblemDescription() { return problemDescription; }
    public String getDiagnosticReport() { return diagnosticReport; }
    public RepairState getState() { return state; }
    public List<ServiceDTO> getServices() { return services; }
    public Amount getTotalCost() { return totalCost; }
    public LocalDateTime getCreationTime() { return creationTime; }
    public LocalDateTime getEstimatedCompletionTime() { return estimatedCompletionTime; }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        StringBuilder sb = new StringBuilder();
        sb.append("---------------- REPAIR ORDER ----------------\n");
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("State:    ").append(state).append("\n");
        sb.append("Date:     ").append(creationTime.format(formatter)).append("\n");
        sb.append("Est. Completion: ").append(estimatedCompletionTime.format(formatter)).append("\n");
        sb.append("Customer: ").append(customer.getName()).append(" (").append(customer.getId()).append(")\n");
        sb.append("Bike:     ").append(customer.getBikeBrand()).append(" ").append(customer.getBikeModel()).append("\n");
        sb.append("Problem:  ").append(problemDescription).append("\n");
        sb.append("Report:   ").append(diagnosticReport != null ? diagnosticReport : "Pending").append("\n");
        sb.append("Services:\n");
        if (services.isEmpty()) {
            sb.append("  (No services added yet)\n");
        } else {
            for (ServiceDTO service : services) {
                sb.append("  - ").append(service.getDescription()).append(": ").append(service.getPrice()).append("\n");
            }
        }
        sb.append("TOTAL COST: ").append(totalCost).append("\n");
        sb.append("----------------------------------------------");
        return sb.toString();
    }
}
