

import java.util.Optional;

public class App {

    public static void main(String[] args) {
        InputValidator validator = new InputValidator();

        System.out.println("=== Basic Validation (1 to 100) ===");
        int basic = validator.validate(1, 100, "Please enter a value", "Your value is invalid");
        System.out.println("\nThe value chosen by the user is " + basic);

        System.out.println("\n=== Default Value Validation (1 to 100, default: 50) ===");
        int withDefault = validator.validateWithDefault(1, 100, "Please enter a value.", "Your value is invalid", 50);
        System.out.println("\nThe value chosen by the user is " + withDefault);

        System.out.println("\n=== Abort Validation (1 to 100) ===");
        Optional<Integer> withAbort = validator.validateWithAbort(1, 100, "Please enter a value.", "Your value is invalid");
    }
}
