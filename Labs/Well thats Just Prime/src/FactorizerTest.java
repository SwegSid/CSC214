

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FactorizerTest {

    Factorizer factorizer;

    @BeforeEach
    void setUp() {
        factorizer = new Factorizer();
    }



    @Test
    public void testPrimeFactorsOfOne() {
        assertTrue(factorizer.primeFactors(1).isEmpty());
    }

    @Test
    public void testPrimeFactorsOfZero() {
        assertTrue(factorizer.primeFactors(0).isEmpty());
    }

    @Test
    public void testPrimeFactorsOfNegativeNumber() {
        assertTrue(factorizer.primeFactors(-5).isEmpty());
    }

    @Test
    public void testPrimeFactorsOfPrime() {
        // 17 = [17]
        assertEquals(List.of(17), factorizer.primeFactors(17));
    }

    @Test
    public void testPrimeFactorsOf100() {
        // 100 = 2 * 2 * 5 * 5
        assertEquals(List.of(2, 2, 5, 5), factorizer.primeFactors(100));
    }

    @Test
    public void testPrimeFactorsOf35() {
        // 35 = 5 * 7
        assertEquals(List.of(5, 7), factorizer.primeFactors(35));
    }

    @Test
    public void testPrimeFactorsOf24() {
        // 24 = 2 * 2 * 2 * 3
        assertEquals(List.of(2, 2, 2, 3), factorizer.primeFactors(24));
    }

    @Test
    public void testPrimeFactorsOf2() {
        assertEquals(List.of(2), factorizer.primeFactors(2));
    }

    // --- isPrime() ---

    @Test
    public void testIsPrimeTrue() {
        assertTrue(factorizer.isPrime(17));
    }

    @Test
    public void testIsPrimeFalseComposite() {
        assertFalse(factorizer.isPrime(100));
    }

    @Test
    public void testIsPrimeFalseOne() {
        assertFalse(factorizer.isPrime(1));
    }

    @Test
    public void testIsPrimeTwo() {
        assertTrue(factorizer.isPrime(2));
    }

    // --- isComposite() ---

    @Test
    public void testIsCompositeTrue() {
        assertTrue(factorizer.isComposite(100));
    }

    @Test
    public void testIsCompositeFalsePrime() {
        assertFalse(factorizer.isComposite(17));
    }

    @Test
    public void testIsCompositeFalseOne() {
        assertFalse(factorizer.isComposite(1));
    }

    @Test
    public void testIsCompositeOf4() {
        assertTrue(factorizer.isComposite(4));
    }

    // --- reduce() ---

    @Test
    public void testReduceSimpleFraction() {
        // 12/26 -> 6/13
        assertEquals("6/13", factorizer.reduce(12, 26));
    }

    @Test
    public void testReduceAlreadyReduced() {
        // 3/7 -> 3/7
        assertEquals("3/7", factorizer.reduce(3, 7));
    }

    @Test
    public void testReduceToWhole() {
        // 6/3 -> 2/1
        assertEquals("2/1", factorizer.reduce(6, 3));
    }

    @Test
    public void testReduceWithCommonFactor() {
        // 100/50 -> 2/1
        assertEquals("2/1", factorizer.reduce(100, 50));
    }

    @Test
    public void testReduceNumeratorZero() {
        assertEquals("0/5", factorizer.reduce(0, 5));
    }
}
