package se.kth.iv1350.model;

/**
 * Represents the different states a repair order can be in.
 */
public enum RepairState {
    /**
     * Newly created (no diagnostic report or repair tasks).
     */
    NEWLY_CREATED,

    /**
     * Ready for approval (a technician has entered diagnostic report and repair tasks).
     */
    READY_FOR_APPROVAL,

    /**
     * Rejected (the customer didn't want to do the proposed repair tasks).
     */
    REJECTED,

    /**
     * Accepted (the customer accepted the proposed repair tasks).
     */
    ACCEPTED,

    /**
     * Completed (the reparation has been done, but the customer hasn't payed yet).
     */
    COMPLETED,

    /**
     * Payed (the customer has payed).
     */
    PAID
}
