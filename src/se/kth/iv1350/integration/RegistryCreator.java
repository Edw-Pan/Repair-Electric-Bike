package se.kth.iv1350.integration;

/**
 * This class is responsible for creating all registries.
 * This class is a singleton.
 */
public class RegistryCreator {
    private static final RegistryCreator INSTANCE = new RegistryCreator();
    private final CustomerRegistry customerRegistry;
    private final RepairOrderRegistry repairOrderRegistry;

    /**
     * Creates a new instance.
     */
    private RegistryCreator() {
        this.customerRegistry = new CustomerRegistry();
        this.repairOrderRegistry = new RepairOrderRegistry();
    }

    /**
     * Returns the only instance of this class.
     * 
     * @return The only instance of this class.
     */
    public static RegistryCreator getInstance() {
        return INSTANCE;
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
