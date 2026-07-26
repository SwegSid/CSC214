import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Calculations calc = new Calculations();
        int[] nums = {4, 8, 15, 16, 23, 42};

        System.out.println("Array: " + Arrays.toString(nums));
        System.out.println();

        System.out.println("--- For Loop Results ---");
        System.out.println("Max: " + calc.maximumUsingForLoop(nums));
        System.out.println("Min: " + calc.minimumUsingForLoop(nums));
        System.out.println("Sum: " + calc.sumUsingForLoop(nums));
        System.out.println("Average: " + calc.averageUsingForLoop(nums));

        System.out.println();
        System.out.println("--- Stream Results ---");
        System.out.println("Max: " + calc.maximumUsingStream(nums));
        System.out.println("Min: " + calc.minimumUsingStream(nums));
        System.out.println("Sum: " + calc.sumUsingStream(nums));
        System.out.println("Average: " + calc.averageUsingStream(nums));

        System.out.println();
        System.out.println("--- Extra Credit ---");
        System.out.println("Evens Only: " + Arrays.toString(calc.evensOnly(nums)));
        System.out.println("Odds Only: " + Arrays.toString(calc.oddsOnly(nums)));
        System.out.println("Add Five: " + Arrays.toString(calc.addFive(nums)));
        System.out.println("Square Numbers: " + Arrays.toString(calc.squareNumbers(nums)));
    }
}
