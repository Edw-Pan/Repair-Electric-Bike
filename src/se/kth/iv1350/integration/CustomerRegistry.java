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
     * Searches for a customer with the specified ID.
     * 
     * @param customerId The ID of the customer to search for.
     * @return The customer with the specified ID.
     * @throws CustomerNotFoundException if no customer with the specified ID was found.
     * @throws DatabaseFailureException if the customerId is "999", simulating a database failure.
     */
    public CustomerDTO findCustomer(String customerId) throws DatabaseFailureException {
        if (customerId.equals("999")) {
            throw new DatabaseFailureException("Database could not be reached.");
        }
        for (CustomerDTO customer : customers) {
            if (customer.getId().equals(customerId)) {
                return customer;
            }
        }
        throw new CustomerNotFoundException(customerId);
    }

    private void addDummyData() {
        customers.add(new CustomerDTO("1", "Edwin Pang", "edwin@kth.se", "Giant", "Defy", "SN12345"));
        customers.add(new CustomerDTO("2", "Pang Edwin", "pang@kth.se", "Trek", "Domane", "SN23456"));
    }
}
