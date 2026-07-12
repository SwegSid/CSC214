

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the TriangleNumberCalculator class.
 */
public class TriangleNumberCalculatorTest {

    private final TriangleNumberCalculator calculator = new TriangleNumberCalculator();

    // ---- value(int n) ----

    @Test
    void testValueOfZero() {
        assertEquals(0, calculator.value(0));
    }

    @Test
    void testValueOfOne() {
        assertEquals(1, calculator.value(1));
    }

    @Test
    void testValueOfTwo() {
        assertEquals(3, calculator.value(2));
    }

    @Test
    void testValueOfThree() {
        assertEquals(6, calculator.value(3));
    }

    @Test
    void testValueOfFive() {
        assertEquals(15, calculator.value(5));
    }

    @Test
    void testValueOfTen() {
        assertEquals(55, calculator.value(10));
    }

    @Test
    void testValueThrowsOnNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> calculator.value(-1));
    }

    // ---- add(int n, int m) ----

    @Test
    void testAddTwoPositiveIndices() {
        // T(3) = 6, T(4) = 10, sum = 16
        assertEquals(16, calculator.add(3, 4));
    }

    @Test
    void testAddWithZero() {
        // T(0) = 0, T(5) = 15, sum = 15
        assertEquals(15, calculator.add(0, 5));
    }

    @Test
    void testAddSameIndexTwice() {
        // T(4) = 10, T(4) = 10, sum = 20
        assertEquals(20, calculator.add(4, 4));
    }

    // ---- subtract(int n, int m) ----

    @Test
    void testSubtractLargerMinusSmaller() {
        // T(6) = 21, T(2) = 3, difference = 18
        assertEquals(18, calculator.subtract(6, 2));
    }

    @Test
    void testSubtractSmallerMinusLarger() {
        // T(2) = 3, T(6) = 21, difference = -18
        assertEquals(-18, calculator.subtract(2, 6));
    }

    @Test
    void testSubtractSameIndex() {
        assertEquals(0, calculator.subtract(5, 5));
    }

    // ---- multiply(int n, int m) ----

    @Test
    void testMultiplyTwoPositiveIndices() {
        // T(3) = 6, T(4) = 10, product = 60
        assertEquals(60, calculator.multiply(3, 4));
    }

    @Test
    void testMultiplyWithZero() {
        // T(0) = 0, T(5) = 15, product = 0
        assertEquals(0, calculator.multiply(0, 5));
    }

    @Test
    void testMultiplyByOne() {
        // T(1) = 1, T(6) = 21, product = 21
        assertEquals(21, calculator.multiply(1, 6));
    }

    // ---- divide(int n, int m) ----

    @Test
    void testDivideEvenly() {
        // T(6) = 21, T(2) = 3, quotient = 7.0
        assertEquals(7.0, calculator.divide(6, 2), 0.0001);
    }

    @Test
    void testDivideWithRemainder() {
        // T(5) = 15, T(3) = 6, quotient = 2.5
        assertEquals(2.5, calculator.divide(5, 3), 0.0001);
    }

    @Test
    void testDivideByZeroTriangularNumberThrows() {
        // T(0) = 0, dividing by it should throw
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }

    // ---- sequence(int n) ----

    @Test
    void testSequenceUpToFive() {
        List<Integer> expected = Arrays.asList(1, 3, 6, 10, 15);
        assertEquals(expected, calculator.sequence(5));
    }

    @Test
    void testSequenceUpToOne() {
        List<Integer> expected = Arrays.asList(1);
        assertEquals(expected, calculator.sequence(1));
    }

    @Test
    void testSequenceOfZeroReturnsEmptyList() {
        assertTrue(calculator.sequence(0).isEmpty());
    }

    @Test
    void testSequenceThrowsOnNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> calculator.sequence(-1));
    }

    @Test
    void testSequenceUpToTen() {
        List<Integer> expected = Arrays.asList(1, 3, 6, 10, 15, 21, 28, 36, 45, 55);
        assertEquals(expected, calculator.sequence(10));
    }
}
