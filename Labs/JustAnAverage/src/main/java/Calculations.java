import java.util.Arrays;
import java.util.stream.IntStream;

public class Calculations {

    // ----------------------------
    // For Loop Implementations
    // ----------------------------

    public int maximumUsingForLoop(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        return max;
    }

    public int minimumUsingForLoop(int[] nums) {
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        return min;
    }

    public int sumUsingForLoop(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum;
    }

    public int averageUsingForLoop(int[] nums) {
        return sumUsingForLoop(nums) / nums.length;
    }

    // ----------------------------
    // Stream Implementations
    // ----------------------------

    public int maximumUsingStream(int[] nums) {
        return Arrays.stream(nums).max().getAsInt();
    }

    public int minimumUsingStream(int[] nums) {
        return Arrays.stream(nums).min().getAsInt();
    }

    public int sumUsingStream(int[] nums) {
        return Arrays.stream(nums).sum();
    }

    public int averageUsingStream(int[] nums) {
        return (int) Arrays.stream(nums).average().getAsDouble();
    }

    // ----------------------------
    // Extra Credit: Stream Filters/Transforms
    // ----------------------------

    /**
     * Extra Credit: Evens Only
     * Filters out any odd numbers, keeping only even numbers.
     */
    public int[] evensOnly(int[] nums) {
        return Arrays.stream(nums)
                .filter(n -> n % 2 == 0)
                .toArray();
    }

    /**
     * Extra Credit: Odds Only
     * Filters out any even numbers, keeping only odd numbers.
     */
    public int[] oddsOnly(int[] nums) {
        return Arrays.stream(nums)
                .filter(n -> n % 2 != 0)
                .toArray();
    }

    /**
     * Extra Credit: Add Five
     * Returns a new array where every number has been incremented by five.
     */
    public int[] addFive(int[] nums) {
        return Arrays.stream(nums)
                .map(n -> n + 5)
                .toArray();
    }

    /**
     * Extra Credit: Square Numbers
     * Returns a new array where every number has been squared.
     */
    public int[] squareNumbers(int[] nums) {
        return Arrays.stream(nums)
                .map(n -> n * n)
                .toArray();
    }
}
