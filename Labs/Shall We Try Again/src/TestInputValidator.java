
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the pure logic of InputValidator.
 *
 * Note: The interactive validate() methods can't be tested here because they
 * block on Scanner input — that requires advanced mocking techniques beyond
 * this course. Instead, we test the bounds-checking logic directly.
 */
public class TestInputValidator {

    InputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new InputValidator();
    }

    // --- isInRange() ---

    @Test
    public void testValueAtLowerBoundIsValid() {
        assertTrue(validator.isInRange(1, 1, 100));
    }

    @Test
    public void testValueAtUpperBoundIsValid() {
        assertTrue(validator.isInRange(100, 1, 100));
    }

    @Test
    public void testValueInMiddleIsValid() {
        assertTrue(validator.isInRange(45, 1, 100));
    }

    @Test
    public void testValueBelowLowerBoundIsInvalid() {
        assertFalse(validator.isInRange(0, 1, 100));
    }

    @Test
    public void testValueAboveUpperBoundIsInvalid() {
        assertFalse(validator.isInRange(101, 1, 100));
    }

    @Test
    public void testNegativeValueInNegativeRangeIsValid() {
        assertTrue(validator.isInRange(-5, -10, -1));
    }

    @Test
    public void testZeroInZeroToZeroRangeIsValid() {
        assertTrue(validator.isInRange(0, 0, 0));
    }

    @Test
    public void testValueBelowNegativeRangeIsInvalid() {
        assertFalse(validator.isInRange(-11, -10, -1));
    }
}
