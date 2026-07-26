import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculationsTest {

    private final Calculations calc = new Calculations();
    private final int[] nums = {4, 8, 15, 16, 23, 42};
    private final int[] single = {7};
    private final int[] negatives = {-5, -1, -10, -3};

    // ----------------------------
    // For Loop Tests
    // ----------------------------

    @Test
    void testMaximumUsingForLoop() {
        assertEquals(42, calc.maximumUsingForLoop(nums));
        assertEquals(7, calc.maximumUsingForLoop(single));
        assertEquals(-1, calc.maximumUsingForLoop(negatives));
    }

    @Test
    void testMinimumUsingForLoop() {
        assertEquals(4, calc.minimumUsingForLoop(nums));
        assertEquals(7, calc.minimumUsingForLoop(single));
        assertEquals(-10, calc.minimumUsingForLoop(negatives));
    }

    @Test
    void testSumUsingForLoop() {
        assertEquals(108, calc.sumUsingForLoop(nums));
        assertEquals(7, calc.sumUsingForLoop(single));
        assertEquals(-19, calc.sumUsingForLoop(negatives));
    }

    @Test
    void testAverageUsingForLoop() {
        assertEquals(18, calc.averageUsingForLoop(nums));
        assertEquals(7, calc.averageUsingForLoop(single));
        assertEquals(-4, calc.averageUsingForLoop(negatives));
    }

    // ----------------------------
    // Stream Tests
    // ----------------------------

    @Test
    void testMaximumUsingStream() {
        assertEquals(42, calc.maximumUsingStream(nums));
        assertEquals(7, calc.maximumUsingStream(single));
        assertEquals(-1, calc.maximumUsingStream(negatives));
    }

    @Test
    void testMinimumUsingStream() {
        assertEquals(4, calc.minimumUsingStream(nums));
        assertEquals(7, calc.minimumUsingStream(single));
        assertEquals(-10, calc.minimumUsingStream(negatives));
    }

    @Test
    void testSumUsingStream() {
        assertEquals(108, calc.sumUsingStream(nums));
        assertEquals(7, calc.sumUsingStream(single));
        assertEquals(-19, calc.sumUsingStream(negatives));
    }

    @Test
    void testAverageUsingStream() {
        assertEquals(18, calc.averageUsingStream(nums));
        assertEquals(7, calc.averageUsingStream(single));
        assertEquals(-4, calc.averageUsingStream(negatives));
    }

    @Test
    void testForLoopAndStreamAgree() {
        assertEquals(calc.maximumUsingForLoop(nums), calc.maximumUsingStream(nums));
        assertEquals(calc.minimumUsingForLoop(nums), calc.minimumUsingStream(nums));
        assertEquals(calc.sumUsingForLoop(nums), calc.sumUsingStream(nums));
        assertEquals(calc.averageUsingForLoop(nums), calc.averageUsingStream(nums));
    }

    // ----------------------------
    // Extra Credit Tests
    // ----------------------------

    @Test
    void testEvensOnly() {
        assertArrayEquals(new int[]{4, 8, 16, 42}, calc.evensOnly(nums));
        assertArrayEquals(new int[]{-10}, calc.evensOnly(negatives));
        assertArrayEquals(new int[]{}, calc.evensOnly(single));
    }

    @Test
    void testOddsOnly() {
        assertArrayEquals(new int[]{15, 23}, calc.oddsOnly(nums));
        assertArrayEquals(new int[]{-5, -1, -3}, calc.oddsOnly(negatives));
        assertArrayEquals(new int[]{7}, calc.oddsOnly(single));
    }

    @Test
    void testAddFive() {
        assertArrayEquals(new int[]{9, 13, 20, 21, 28, 47}, calc.addFive(nums));
        assertArrayEquals(new int[]{12}, calc.addFive(single));
        assertArrayEquals(new int[]{0, 4, -5, 2}, calc.addFive(negatives));
    }

    @Test
    void testSquareNumbers() {
        assertArrayEquals(new int[]{16, 64, 225, 256, 529, 1764}, calc.squareNumbers(nums));
        assertArrayEquals(new int[]{49}, calc.squareNumbers(single));
        assertArrayEquals(new int[]{25, 1, 100, 9}, calc.squareNumbers(negatives));
    }
}
