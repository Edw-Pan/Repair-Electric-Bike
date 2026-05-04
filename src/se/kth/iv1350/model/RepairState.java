package se.kth.iv1350.model;

/**
 * Represents the different states a repair order can be in.
 */
public enum RepairState {
    /**
     * The repair has been received and is waiting to be started.
     */
    RECEIVED,

    /**
     * The repair has been started and is currently being worked on.
     */
    STARTED,

    /**
     * The repair is finished and the bike is ready to be picked up.
     */
    FINISHED,

    /**
     * The bike has been picked up by the customer and the repair is completed.
     */
    PICKED_UP
}
