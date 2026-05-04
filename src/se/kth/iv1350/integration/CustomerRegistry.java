package se.kth.iv1350.integration;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.model.CustomerDTO;

/**
 * Handles all database calls regarding customers. 
 */
public class CustomerRegistry {
    private final List<CustomerDTO> customers = new ArrayList<>();

    /**
     * Creates a new instance.
     */
    public CustomerRegistry() {
        addDummyData();
    }

    /**
     * Finds a customer with the specified ID.
     *
     * @param customerId The ID of the customer to find.
     * @return The customer with the specified ID, or <code>null</code> if no such customer exists.
     */
    public CustomerDTO findCustomer(String customerId) {
        for (CustomerDTO customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }

    private void addDummyData() {
        customers.add(new CustomerDTO("1", "Edwin Pang"));
        customers.add(new CustomerDTO("2", "Pang Edwin"));
    }
}
