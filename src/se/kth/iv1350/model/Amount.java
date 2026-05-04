package se.kth.iv1350.model;

/**
 * Represents an amount of money.
 */
public final class Amount {
    private final int amount;

    /**
     * Creates a new instance.
     *
     * @param amount The amount of money.
     */
    public Amount(int amount) { this.amount = amount; }

    /**
     * Adds the specified amount to this amount and returns a new instance with the result.
     *
     * @param other The amount to add.
     * @return The sum of this amount and the other amount.
     */
    public Amount plus(Amount other) { return new Amount(this.amount + other.amount); }
    
    /**
     * Returns a string representation of the amount.
     *
     * @return A string representation of the amount.
     */
    @Override
    public String toString() { return amount + " SEK"; }
}
