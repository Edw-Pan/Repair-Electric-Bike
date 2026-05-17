package se.kth.iv1350.model;

/**
 * A discount strategy that applies no discount.
 */
public class NoDiscount implements DiscountStrategy {
    @Override
    public Amount calculateDiscount(Amount totalCost) {
        return totalCost;
    }
}
