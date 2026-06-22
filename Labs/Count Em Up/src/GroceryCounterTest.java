
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GroceryCounterTest {

    GroceryCounter counter;

    @BeforeEach
    void setUp() {
        counter = new GroceryCounter();
    }

    // --- Initial state ---

    @Test
    public void testInitialCountIsZero() {
        assertEquals(0, counter.getCount());
    }

    @Test
    public void testInitialTotalFormatted() {
        assertEquals("$0.00", counter.total());
    }

    @Test
    public void testInitialOverflowsIsZero() {
        assertEquals(0, counter.overflows());
    }

    // --- tens() ---

    @Test
    public void testTensIncrementsBy1000() {
        counter.tens();
        assertEquals(1000, counter.getCount());
    }

    @Test
    public void testTensTwice() {
        counter.tens();
        counter.tens();
        assertEquals(2000, counter.getCount());
    }

    // --- ones() ---

    @Test
    public void testOnesIncrementsBy100() {
        counter.ones();
        assertEquals(100, counter.getCount());
    }

    // --- tenths() ---

    @Test
    public void testTenthsIncrementsBy10() {
        counter.tenths();
        assertEquals(10, counter.getCount());
    }

    // --- hundreths() ---

    @Test
    public void testHundreths() {
        counter.hundreths();
        assertEquals(1, counter.getCount());
    }

    // --- total() formatting ---

    @Test
    public void testTotalFormatsCorrectly() {
        counter.increment(1234);
        assertEquals("$12.34", counter.total());
    }

    @Test
    public void testTotalFormatsLeadingZeroCents() {
        counter.increment(509);
        assertEquals("$5.09", counter.total());
    }

    @Test
    public void testTotalFormatsZeroDollars() {
        counter.increment(5);
        assertEquals("$0.05", counter.total());
    }

    // --- overflow ---

    @Test
    public void testOverflowWrapsToZero() {
        counter.increment(10000);
        assertEquals(0, counter.getCount());
        assertEquals(1, counter.overflows());
    }

    @Test
    public void testOverflowAtExact9999() {
        counter.increment(9999);
        assertEquals(9999, counter.getCount());
        assertEquals(0, counter.overflows());
    }

    @Test
    public void testOverflowBeyond9999() {
        counter.increment(9999);
        counter.hundreths();
        assertEquals(0, counter.getCount());
        assertEquals(1, counter.overflows());
    }

    @Test
    public void testMultipleOverflows() {
        counter.increment(30000);
        assertEquals(3, counter.overflows());
    }

    // --- clear() ---

    @Test
    public void testClearResetsCount() {
        counter.increment(5000);
        counter.clear();
        assertEquals(0, counter.getCount());
    }

    @Test
    public void testClearResetsOverflows() {
        counter.increment(10000);
        counter.clear();
        assertEquals(0, counter.overflows());
    }

    // --- Custom starting value ---

    @Test
    public void testCustomStartValue() {
        GroceryCounter c = new GroceryCounter(1234);
        assertEquals(1234, c.getCount());
    }

    @Test
    public void testCustomStartValueOutOfRangeDefaultsToZero() {
        GroceryCounter c = new GroceryCounter(-1);
        assertEquals(0, c.getCount());
    }

    @Test
    public void testCustomStartValueAtMax() {
        GroceryCounter c = new GroceryCounter(9999);
        assertEquals(9999, c.getCount());
    }

    // --- Custom max ---

    @Test
    public void testCustomMaxOverflow() {
        GroceryCounter c = new GroceryCounter(0, 99);
        c.increment(100);
        assertEquals(0, c.getCount());
        assertEquals(1, c.overflows());
    }

    @Test
    public void testCustomMaxInvalidDefaultsTo9999() {
        GroceryCounter c = new GroceryCounter(0, -5);
        c.increment(9999);
        assertEquals(9999, c.getCount());
    }

    // --- Decrement ---

    @Test
    public void testDecrementOnes() {
        counter.increment(500);
        counter.decrementOnes();
        assertEquals(400, counter.getCount());
    }

    @Test
    public void testDecrementTens() {
        counter.increment(5000);
        counter.decrementTens();
        assertEquals(4000, counter.getCount());
    }

    @Test
    public void testDecrementTenths() {
        counter.increment(50);
        counter.decrementTenths();
        assertEquals(40, counter.getCount());
    }

    @Test
    public void testDecrementHundreths() {
        counter.increment(5);
        counter.decrementHundreths();
        assertEquals(4, counter.getCount());
    }

    @Test
    public void testDecrementUnderflowWraps() {
        counter.decrementHundreths();
        assertEquals(9999, counter.getCount());
        assertEquals(1, counter.overflows());
    }

    // --- Arbitrary increment ---

    @Test
    public void testIncrementArbitraryAmount() {
        counter.increment(275);
        assertEquals(275, counter.getCount());
    }

}
