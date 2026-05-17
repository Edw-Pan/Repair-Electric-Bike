package se.kth.iv1350.model;

/**
 * A discount strategy that applies a 10% loyalty discount.
 */
public class LoyaltyDiscount implements DiscountStrategy {
    @Override
    public Amount calculateDiscount(Amount totalCost) {
        // Simple 10% discount logic
        int discountAmount = (int) (totalCost.getAmount() * 0.1);
        return new Amount(totalCost.getAmount() - discountAmount);
    }
}
