package se.kth.iv1350.model;

/**
 * Defines the contract for discount calculation strategies.
 */
public interface DiscountStrategy {
    /**
     * Calculates the total cost after discount.
     * 
     * @param totalCost The total cost before discount.
     * @return The total cost after discount.
     */
    Amount calculateDiscount(Amount totalCost);
}
