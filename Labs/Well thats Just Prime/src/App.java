

import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Factorizer factorizer = new Factorizer();

        System.out.print("Enter an integer to factorize: ");
        int n = scanner.nextInt();

        List<Integer> factors = factorizer.primeFactors(n);

        if (factors.isEmpty()) {
            System.out.println("Prime factors of " + n + ": []");
        } else {
            System.out.println("Prime factors of " + n + ": " + factors);
        }

        System.out.println("Is prime:     " + factorizer.isPrime(n));
        System.out.println("Is composite: " + factorizer.isComposite(n));

        System.out.print("\nEnter a numerator to reduce a fraction: ");
        int num = scanner.nextInt();
        System.out.print("Enter a denominator: ");
        int den = scanner.nextInt();

        System.out.println("Reduced fraction: " + factorizer.reduce(num, den));

        scanner.close();
    }
}
