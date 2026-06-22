

public class App {

    public static void main(String[] args) {

        System.out.println("=== Basic Counter Demo ===");
        GroceryCounter counter = new GroceryCounter();
        System.out.println("Initial total: " + counter.total());

        counter.tens();
        counter.tens();
        counter.tenths();
        counter.hundreths();
        System.out.println("After 2x tens, 1x tenths, 1x hundreths: " + counter.total());

        counter.ones();
        System.out.println("After 1x ones: " + counter.total());
        System.out.println("Overflows so far: " + counter.overflows());

        System.out.println();
        System.out.println("=== Overflow Demo ===");
        GroceryCounter overflow = new GroceryCounter();
        for (int i = 0; i < 10; i++) {
            overflow.tens();
        }
        System.out.println("After 10x tens (overflows once): " + overflow.total());
        System.out.println("Overflows: " + overflow.overflows());

        System.out.println();
        System.out.println("=== Clear Demo ===");
        overflow.clear();
        System.out.println("After clear: " + overflow.total());
        System.out.println("Overflows after clear: " + overflow.overflows());

        System.out.println();
        System.out.println("=== Custom Starting Value Demo ===");
        GroceryCounter custom = new GroceryCounter(1234);
        System.out.println("Starting at 1234: " + custom.total());

        System.out.println();
        System.out.println("=== Decrement Demo ===");
        GroceryCounter dec = new GroceryCounter(500);
        System.out.println("Start: " + dec.total());
        dec.decrementOnes();
        System.out.println("After decrementOnes: " + dec.total());

        System.out.println();
        System.out.println("=== Arbitrary Increment Demo ===");
        GroceryCounter arb = new GroceryCounter();
        arb.increment(275);
        System.out.println("After increment(275): " + arb.total());
        arb.increment(9800);
        System.out.println("After increment(9800): " + arb.total());
        System.out.println("Overflows: " + arb.overflows());
    }
}
