

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        FuzzyListGenerator generator = new FuzzyListGenerator();
        FuzzyFinder finder = new FuzzyFinder();

        ArrayList<Fuzzy> sortedFuzzies = generator.sortedRainbowFuzzies();
        ArrayList<Fuzzy> randomFuzzies = generator.randomizedRainbowFuzzies();

        System.out.println("=== Base Trials (searching for the gold fuzzy) ===");

        long start = System.nanoTime();
        int testOne = finder.linearSearch(sortedFuzzies);
        long end = System.nanoTime();
        System.out.println("Test 1 - Linear search on sorted list:   index = " + testOne
                + " (" + (end - start) + " ns)");

        start = System.nanoTime();
        int testTwo = finder.binarySearch(sortedFuzzies);
        end = System.nanoTime();
        System.out.println("Test 2 - Binary search on sorted list:   index = " + testTwo
                + " (" + (end - start) + " ns)");

        start = System.nanoTime();
        int testThree = finder.linearSearch(randomFuzzies);
        end = System.nanoTime();
        System.out.println("Test 3 - Linear search on random list:   index = " + testThree
                + " (" + (end - start) + " ns)");

        start = System.nanoTime();
        int testFour = finder.binarySearch(randomFuzzies);
        end = System.nanoTime();
        System.out.println("Test 4 - Binary search on random list:   index = " + testFour
                + " (" + (end - start) + " ns, UNRELIABLE - list is not sorted)");

        System.out.println();
        System.out.println("=== Cold Pricklies Add-On (searching for the cold prickly) ===");

        ArrayList<Feeling> sortedFeelings = generator.sortedFeelings();
        ArrayList<Feeling> randomFeelings = generator.randomizedFeelings();

        int pricklyLinearSorted = finder.linearSearchPrickly(sortedFeelings);
        int pricklyBinarySorted = finder.binarySearchPrickly(sortedFeelings);
        int pricklyLinearRandom = finder.linearSearchPrickly(randomFeelings);
        int pricklyBinaryRandom = finder.binarySearchPrickly(randomFeelings);

        System.out.println("Linear search for prickly on sorted feelings:   index = " + pricklyLinearSorted);
        System.out.println("Binary search for prickly on sorted feelings:   index = " + pricklyBinarySorted);
        System.out.println("Linear search for prickly on random feelings:   index = " + pricklyLinearRandom);
        System.out.println("Binary search for prickly on random feelings:   index = " + pricklyBinaryRandom
                + " (UNRELIABLE - list is not sorted)");

        System.out.println();
        System.out.println("Description of the found prickly: "
                + sortedFeelings.get(pricklyLinearSorted).description());
    }
}

