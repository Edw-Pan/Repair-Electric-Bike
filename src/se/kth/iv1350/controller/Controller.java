package se.kth.iv1350.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;
import se.kth.iv1350.util.FileLogger;

/**
 * Coordinates all system operations.
 */
public class Controller {
    private final CustomerRegistry customerReg;
    private final RepairOrderRegistry orderReg;
    private final FileLogger logger = new FileLogger();
    private final List<RepairOrderObserver> observers = new ArrayList<>();

    /**
     * Creates a new instance.
     *
     * @param regCreator The registry creator used to get the registries.
     */
    public Controller(RegistryCreator regCreator) {
        this.customerReg = regCreator.getCustomerRegistry();
        this.orderReg = regCreator.getRepairOrderRegistry();
    }

    /**
     * Adds the specified observer to the list of observers.
     * 
     * @param observer The observer to add.
     */
    public void addRepairOrderObserver(RepairOrderObserver observer) {
        observers.add(observer);
    }

    /**
     * Starts a new repair order for the specified customer.
     *
     * @param customerId The ID of the customer.
     * @param orderId    The ID of the new order.
     * @param description The description of the problem.
     * @return The created repair order.
     * @throws OperationFailedException if there is a database failure.
     * @throws CustomerNotFoundException if the customer was not found.
     */
    public RepairOrderDTO startRepairOrder(String customerId, String orderId, String description) 
            throws OperationFailedException {
        try {
            CustomerDTO customer = customerReg.findCustomer(customerId);
            RepairOrder order = new RepairOrder(orderId, customer, description);
            order.addObservers(observers);
            // Set state to trigger initial notification
            order.setState(RepairState.NEWLY_CREATED);
            orderReg.saveRepairOrder(order.toDTO());
            return order.toDTO();
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            throw new OperationFailedException("Could not start repair order.", e);
        }
    }

    /**
     * Searches for a customer with the specified ID.
     *
     * @param customerId The ID of the customer to search for.
     * @return The customer with the specified ID.
     * @throws CustomerNotFoundException if the customer was not found.
     * @throws OperationFailedException if there is a database failure.
     */
    public CustomerDTO searchCustomer(String customerId) throws OperationFailedException {
        try {
            return customerReg.findCustomer(customerId);
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            throw new OperationFailedException("Could not search for customer.", e);
        }
    }

    /**
     * Adds a diagnostic report to the specified repair order.
     *
     * @param orderId          The ID of the order.
     * @param diagnosticReport The report to add.
     * @return The updated repair order.
     * @throws OperationFailedException if there is a database failure.
     */
    public RepairOrderDTO addDiagnosticReport(String orderId, String diagnosticReport) 
            throws OperationFailedException {
        try {
            RepairOrderDTO orderDto = orderReg.findRepairOrder(orderId);
            if (orderDto != null) {
                RepairOrder order = new RepairOrder(orderDto);
                order.addObservers(observers);
                order.setDiagnosticReport(diagnosticReport);
                orderReg.saveRepairOrder(order.toDTO());
                return order.toDTO();
            }
            return null;
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            throw new OperationFailedException("Could not add diagnostic report.", e);
        }
    }

    /**
     * Adds a service to the specified repair order.
     *
     * @param orderId The ID of the order.
     * @param service The service to add.
     * @return The updated repair order.
     * @throws OperationFailedException if there is a database failure.
     */
    public RepairOrderDTO addServiceToOrder(String orderId, ServiceDTO service) 
            throws OperationFailedException {
        try {
            RepairOrderDTO orderDto = orderReg.findRepairOrder(orderId);
            if (orderDto != null) {
                RepairOrder order = new RepairOrder(orderDto);
                order.addObservers(observers);
                order.addService(service);
                orderReg.saveRepairOrder(order.toDTO());
                return order.toDTO();
            }
            return null;
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            throw new OperationFailedException("Could not add service to order.", e);
        }
    }

    /**
     * Searches for a repair order with the specified ID.
     *
     * @param orderId The ID of the order to search for.
     * @return The order with the specified ID.
     * @throws OperationFailedException if there is a database failure.
     */
    public RepairOrderDTO searchRepairOrder(String orderId) throws OperationFailedException {
        try {
            return orderReg.findRepairOrder(orderId);
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            throw new OperationFailedException("Could not search for repair order.", e);
        }
    }

    /**
     * Registers that the specified order has been accepted.
     *
     * @param orderId The ID of the order.
     * @return The updated repair order.
     * @throws OperationFailedException if there is a database failure.
     */
    public RepairOrderDTO registerAcceptedOrder(String orderId) throws OperationFailedException {
        return updateState(orderId, RepairState.ACCEPTED);
    }

    /**
     * Updates the state of the specified repair order.
     *
     * @param orderId The ID of the order.
     * @param state   The new state.
     * @return The updated repair order.
     * @throws OperationFailedException if there is a database failure.
     */
    public RepairOrderDTO updateState(String orderId, RepairState state) throws OperationFailedException {
        try {
            RepairOrderDTO orderDto = orderReg.findRepairOrder(orderId);
            if (orderDto != null) {
                RepairOrder order = new RepairOrder(orderDto);
                order.addObservers(observers);
                order.setState(state);
                orderReg.saveRepairOrder(order.toDTO());
                return order.toDTO();
            }
            return null;
        } catch (DatabaseFailureException e) {
            logger.logException(e);
            throw new OperationFailedException("Could not update order state.", e);
        }
    }
}
