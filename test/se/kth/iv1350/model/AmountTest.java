package se.kth.iv1350.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Amount class.
 */
public class AmountTest {

    /**
     * Tests that the plus method correctly adds two amounts.
     */
    @Test
    public void testPlus() {
        Amount amount1 = new Amount(100);
        Amount amount2 = new Amount(200);
        Amount result = amount1.plus(amount2);
        
        assertEquals("300 SEK", result.toString(), "The sum of 100 and 200 should be 300 SEK.");
    }

    /**
     * Tests that the toString method returns the expected format.
     */
    @Test
    public void testToString() {
        Amount amount = new Amount(500);
        assertEquals("500 SEK", amount.toString(), "The toString method should return '500 SEK'.");
    }
}
