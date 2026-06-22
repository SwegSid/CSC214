

import java.util.ArrayList;
import java.util.List;

public class Factorizer {

    public List<Integer> primeFactors(int n) {
        List<Integer> factors = new ArrayList<>();

        if (n <= 1) {
            return factors;
        }

        // Divide out all factors starting from 2
        for (int divisor = 2; divisor <= n; divisor++) {
            while (n % divisor == 0) {
                factors.add(divisor);
                n /= divisor;
            }
        }

        return factors;
    }


    public boolean isPrime(int n) {
        if (n <= 1) return false;
        List<Integer> factors = primeFactors(n);
        return factors.size() == 1 && factors.get(0) == n;
    }


    public boolean isComposite(int n) {
        if (n <= 1) return false;
        return !isPrime(n);
    }

    public String reduce(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }

        List<Integer> numFactors = new ArrayList<>(primeFactors(Math.abs(numerator)));
        List<Integer> denFactors = new ArrayList<>(primeFactors(Math.abs(denominator)));

        // Cancel common factors
        for (int i = 0; i < numFactors.size(); i++) {
            int factor = numFactors.get(i);
            if (denFactors.remove(Integer.valueOf(factor))) {
                numFactors.remove(i);
                i--;
            }
        }

        // Multiply remaining factors back together
        int reducedNum = 1;
        for (int f : numFactors) reducedNum *= f;

        int reducedDen = 1;
        for (int f : denFactors) reducedDen *= f;

        // Handle signs
        boolean negative = (numerator < 0) ^ (denominator < 0);
        if (negative) reducedNum = -reducedNum;

        // Handle case where numerator or denominator was 0 or 1
        if (numerator == 0) return "0/" + denominator;
        if (reducedDen == 1) return reducedNum + "/1";

        return reducedNum + "/" + reducedDen;
    }
}
