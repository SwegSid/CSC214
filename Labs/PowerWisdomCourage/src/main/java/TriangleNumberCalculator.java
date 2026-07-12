
import java.util.ArrayList;
import java.util.List;

/**
 * Performs calculations involving triangular numbers using recursion.
 *
 * The nth triangular number is defined as:
 *   T(n) = 1 + 2 + 3 + ... + n
 *
 * All calculations in this class are implemented recursively rather
 * than with iterative loops.
 */
public class TriangleNumberCalculator {

    /**
     * Returns the nth triangular number, calculated recursively.
     *
     * @param n the index of the triangular number to calculate
     * @return the nth triangular number
     * @throws IllegalArgumentException if n is negative
     */
    public int value(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        if (n == 0) {
            return 0;
        }
        return n + value(n - 1);
    }

    /**
     * Adds the nth and mth triangular numbers together.
     *
     * @param n the index of the first triangular number
     * @param m the index of the second triangular number
     * @return the sum of T(n) and T(m)
     */
    public int add(int n, int m) {
        return value(n) + value(m);
    }

    /**
     * Subtracts the mth triangular number from the nth triangular number.
     *
     * @param n the index of the first triangular number
     * @param m the index of the second triangular number
     * @return the result of T(n) - T(m)
     */
    public int subtract(int n, int m) {
        return value(n) - value(m);
    }

    /**
     * Multiplies the nth and mth triangular numbers together.
     *
     * @param n the index of the first triangular number
     * @param m the index of the second triangular number
     * @return the product of T(n) and T(m)
     */
    public int multiply(int n, int m) {
        return value(n) * value(m);
    }

    /**
     * Divides the nth triangular number by the mth triangular number.
     *
     * @param n the index of the first triangular number (numerator)
     * @param m the index of the second triangular number (denominator)
     * @return the result of T(n) / T(m) as a double
     * @throws ArithmeticException if T(m) is zero
     */
    public double divide(int n, int m) {
        int denominator = value(m);
        if (denominator == 0) {
            throw new ArithmeticException("Cannot divide by triangular number of zero");
        }
        return (double) value(n) / denominator;
    }

    /**
     * Returns a list containing the sequence of triangular numbers
     * from T(1) up to T(n), calculated recursively.
     *
     * @param n the final index in the sequence
     * @return a list of triangular numbers, T(1) through T(n)
     * @throws IllegalArgumentException if n is negative
     */
    public List<Integer> sequence(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        List<Integer> result = new ArrayList<>();
        buildSequence(n, result);
        return result;
    }

    /**
     * Recursive helper method that builds the triangular number sequence
     * from 1 up to n by prepending values as the recursion unwinds.
     *
     * @param n      the current index being processed
     * @param result the list being built up with triangular numbers
     */
    private void buildSequence(int n, List<Integer> result) {
        if (n == 0) {
            return;
        }
        buildSequence(n - 1, result);
        result.add(value(n));
    }
}
