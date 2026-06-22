

import java.util.Optional;
import java.util.Scanner;

public class InputValidator {

    private static final Scanner scanner = new Scanner(System.in);

    public int validate(int lower, int upper, String prompt, String errorMessage) {
        System.out.println(prompt);
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= lower && value <= upper) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // -> error
            }
            System.out.println();
            System.out.println(errorMessage);
            System.out.println(prompt);
        }
    }


     //Add-On 1: Default value support:

    public int validateWithDefault(int lower, int upper, String prompt, String errorMessage, int defaultValue) {
        System.out.println(prompt + " Enter \"default\" to use the default value of " + defaultValue + ".");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("default")) {
                return defaultValue;
            }
            try {
                int value = Integer.parseInt(input);
                if (value >= lower && value <= upper) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // fall through to error
            }
            System.out.println();
            System.out.println(errorMessage);
            System.out.println(prompt + " Enter \"default\" to use the default value of " + defaultValue + ".");
        }
    }

     //Add-On 2: Abort support.
     //If the user types "exit", returns an empty Optional.
     //Otherwise returns the valid value wrapped in Optional.

    public Optional<Integer> validateWithAbort(int lower, int upper, String prompt, String errorMessage) {
        System.out.println(prompt + " Enter \"exit\" to leave the menu.");
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                return Optional.empty();
            }
            try {
                int value = Integer.parseInt(input);
                if (value >= lower && value <= upper) {
                    return Optional.of(value);
                }
            } catch (NumberFormatException e) {
                // -> error
            }
            System.out.println();
            System.out.println(errorMessage);
            System.out.println(prompt + " Enter \"exit\" to leave the menu.");
        }
    }
    public boolean isInRange(int value, int lower, int upper) {
        return value >= lower && value <= upper;
    }
}
