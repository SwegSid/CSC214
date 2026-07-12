
import java.util.List;

/**
 * Demonstrates the usage of the TriangleNumberCalculator class.
 */
public class Main {

    public static void main(String[] args) {
        TriangleNumberCalculator calculator = new TriangleNumberCalculator();

        System.out.println("T(5) = " + calculator.value(5));
        System.out.println("T(3) + T(4) = " + calculator.add(3, 4));
        System.out.println("T(6) - T(2) = " + calculator.subtract(6, 2));
        System.out.println("T(3) * T(4) = " + calculator.multiply(3, 4));
        System.out.println("T(6) / T(2) = " + calculator.divide(6, 2));

        List<Integer> sequence = calculator.sequence(10);
        System.out.println("Sequence up to T(10): " + sequence);
    }
}
