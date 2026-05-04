package se.kth.iv1350.integration;

/**
 * This class is responsible for creating all registries.
 */
public class RegistryCreator {
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;

    /**
     * Creates a new instance.
     */
    public RegistryCreator() {
        this.customerRegistry = new CustomerRegistry();
        this.repairOrderRegistry = new RepairOrderRegistry();
    }

    /**
     * Returns the customer registry.
     *
     * @return The customer registry.
     */
    public CustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    /**
     * Returns the repair order registry.
     *
     * @return The repair order registry.
     */
    public RepairOrderRegistry getRepairOrderRegistry() {
        return repairOrderRegistry;
    }
}
