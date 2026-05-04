package se.kth.iv1350.controller;

import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

/**
 * Coordinates all system operations.
 */
public class Controller {
    private final CustomerRegistry custReg;
    private final RepairOrderRegistry orderReg;

    /**
     * Creates a new instance.
     *
     * @param creator The registry creator used to get the registries.
     */
    public Controller(RegistryCreator creator) {
        this.custReg = creator.getCustomerRegistry();
        this.orderReg = creator.getRepairOrderRegistry();
    }

    /**
     * Starts a new repair order.
     *
     * @param customerId The ID of the customer who owns the order.
     * @param orderId    The ID of the new order.
     * @return The newly created repair order, or <code>null</code> if the customer was not found.
     */
    public RepairOrderDTO startRepairOrder(String customerId, String orderId) {
        CustomerDTO customer = custReg.findCustomer(customerId);
        if (customer == null) return null;

        RepairOrder order = new RepairOrder(orderId, customer);
        orderReg.saveRepairOrder(order);
        return order.toDTO();
    }

    /**
     * Adds a service to an existing repair order.
     *
     * @param orderId The ID of the order.
     * @param service The service to add.
     * @return The updated repair order, or <code>null</code> if the order was not found.
     */
    public RepairOrderDTO addServiceToOrder(String orderId, ServiceDTO service) {
        RepairOrder order = orderReg.findRepairOrder(orderId);
        if (order != null) {
            order.addService(service);
            return order.toDTO();
        }
        return null;
    }

    /**
     * Updates the state of an existing repair order.
     *
     * @param orderId The ID of the order to update.
     * @param state   The new state.
     * @return The updated repair order, or <code>null</code> if the order was not found.
     */
    public RepairOrderDTO updateState(String orderId, RepairState state) {
        RepairOrder order = orderReg.findRepairOrder(orderId);
        if (order != null) {
            order.setState(state);
            return order.toDTO();
        }
        return null;
    }}
