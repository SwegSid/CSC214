
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Generates lists of rainbow fuzzies for searching exercises.
 *
 * The base methods (sortedRainbowFuzzies / randomizedRainbowFuzzies) return
 * ArrayList<Fuzzy> containing rainbow-colored fuzzies plus exactly one gold
 * fuzzy, matching the original acceptance criteria.
 *
 * The Feeling-based methods (sortedFeelings / randomizedFeelings) are for
 * the Cold Pricklies add-on. They return ArrayList<Feeling> containing the
 * same rainbow fuzzies plus one gold fuzzy AND one cold prickly.
 */
public class FuzzyListGenerator {

    private static final String[] RAINBOW_COLORS = {
            "red", "orange", "yellow", "green", "blue", "indigo", "violet"
    };

    private final int iterations;
    private final Random random;

    public FuzzyListGenerator() {
        // 1000 iterations * 7 colors = 7000 fuzzies, plus 1 gold fuzzy
        this(1000);
    }

    public FuzzyListGenerator(int iterations) {
        this.iterations = iterations;
        this.random = new Random();
    }

    // ----- Base acceptance criteria methods (ArrayList<Fuzzy>) -----

    public ArrayList<Fuzzy> sortedRainbowFuzzies() {
        ArrayList<Fuzzy> fuzzies = buildBaseFuzzies();
        fuzzies.sort((a, b) -> a.color.compareTo(b.color));
        return fuzzies;
    }

    public ArrayList<Fuzzy> randomizedRainbowFuzzies() {
        ArrayList<Fuzzy> fuzzies = buildBaseFuzzies();
        Collections.shuffle(fuzzies, random);
        return fuzzies;
    }

    private ArrayList<Fuzzy> buildBaseFuzzies() {
        ArrayList<Fuzzy> fuzzies = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            for (String color : RAINBOW_COLORS) {
                fuzzies.add(new Fuzzy(color));
            }
        }
        fuzzies.add(new Fuzzy("gold"));
        return fuzzies;
    }

    // ----- Cold Pricklies add-on methods (ArrayList<Feeling>) -----

    public ArrayList<Feeling> sortedFeelings() {
        ArrayList<Feeling> feelings = buildFeelings();
        feelings.sort((a, b) -> a.description().compareTo(b.description()));
        return feelings;
    }

    public ArrayList<Feeling> randomizedFeelings() {
        ArrayList<Feeling> feelings = buildFeelings();
        Collections.shuffle(feelings, random);
        return feelings;
    }

    private ArrayList<Feeling> buildFeelings() {
        ArrayList<Feeling> feelings = new ArrayList<>();
        for (int i = 0; i < iterations; i++) {
            for (String color : RAINBOW_COLORS) {
                feelings.add(new Fuzzy(color));
            }
        }
        feelings.add(new Fuzzy("gold"));
        feelings.add(new Prickly());
        return feelings;
    }
}
