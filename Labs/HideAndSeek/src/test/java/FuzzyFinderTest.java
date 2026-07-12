
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class FuzzyFinderTest {

    private final FuzzyFinder finder = new FuzzyFinder();

    // ----- Base linear search tests -----

    @Test
    void linearSearchFindsGoldOnSortedList() {
        FuzzyListGenerator generator = new FuzzyListGenerator(50);
        ArrayList<Fuzzy> sorted = generator.sortedRainbowFuzzies();

        int index = finder.linearSearch(sorted);

        assertTrue(index >= 0, "Gold fuzzy should be found");
        assertEquals("gold", sorted.get(index).color);
    }

    @Test
    void linearSearchFindsGoldOnRandomList() {
        FuzzyListGenerator generator = new FuzzyListGenerator(50);
        ArrayList<Fuzzy> random = generator.randomizedRainbowFuzzies();

        int index = finder.linearSearch(random);

        assertTrue(index >= 0, "Gold fuzzy should be found");
        assertEquals("gold", random.get(index).color);
    }

    @Test
    void linearSearchReturnsNegativeOneWhenGoldMissing() {
        ArrayList<Fuzzy> noGold = new ArrayList<>();
        noGold.add(new Fuzzy("red"));
        noGold.add(new Fuzzy("blue"));

        assertEquals(-1, finder.linearSearch(noGold));
    }

    // ----- Base binary search tests -----

    @Test
    void binarySearchFindsGoldOnSortedList() {
        FuzzyListGenerator generator = new FuzzyListGenerator(50);
        ArrayList<Fuzzy> sorted = generator.sortedRainbowFuzzies();

        int index = finder.binarySearch(sorted);

        assertTrue(index >= 0, "Gold fuzzy should be found");
        assertEquals("gold", sorted.get(index).color);
    }

    @Test
    void binarySearchOnRandomListDoesNotThrow() {
        // Binary search is not guaranteed to find gold on an unsorted list,
        // but it must not crash. We simply verify it returns without error.
        FuzzyListGenerator generator = new FuzzyListGenerator(50);
        ArrayList<Fuzzy> random = generator.randomizedRainbowFuzzies();

        assertDoesNotThrow(() -> finder.binarySearch(random));
    }

    @Test
    void binarySearchReturnsNegativeOneWhenGoldMissing() {
        ArrayList<Fuzzy> noGold = new ArrayList<>();
        noGold.add(new Fuzzy("blue"));
        noGold.add(new Fuzzy("green"));
        noGold.add(new Fuzzy("red"));
        noGold.sort((a, b) -> a.color.compareTo(b.color));

        assertEquals(-1, finder.binarySearch(noGold));
    }

    // ----- Cold Pricklies add-on tests -----

    @Test
    void feelingDescriptionsAreCorrect() {
        Fuzzy fuzzy = new Fuzzy("blue");
        Prickly prickly = new Prickly();

        assertEquals("blue", fuzzy.description());
        assertEquals("Pokey!", prickly.description());
    }

    @Test
    void linearSearchPricklyFindsItOnSortedFeelings() {
        FuzzyListGenerator generator = new FuzzyListGenerator(50);
        ArrayList<Feeling> sorted = generator.sortedFeelings();

        int index = finder.linearSearchPrickly(sorted);

        assertTrue(index >= 0, "Prickly should be found");
        assertTrue(sorted.get(index) instanceof Prickly);
    }

    @Test
    void linearSearchPricklyFindsItOnRandomFeelings() {
        FuzzyListGenerator generator = new FuzzyListGenerator(50);
        ArrayList<Feeling> random = generator.randomizedFeelings();

        int index = finder.linearSearchPrickly(random);

        assertTrue(index >= 0, "Prickly should be found");
        assertTrue(random.get(index) instanceof Prickly);
    }

    @Test
    void binarySearchPricklyFindsItOnSortedFeelings() {
        FuzzyListGenerator generator = new FuzzyListGenerator(50);
        ArrayList<Feeling> sorted = generator.sortedFeelings();

        int index = finder.binarySearchPrickly(sorted);

        assertTrue(index >= 0, "Prickly should be found");
        assertTrue(sorted.get(index) instanceof Prickly);
    }

    @Test
    void binarySearchPricklyReturnsNegativeOneWhenMissing() {
        ArrayList<Feeling> noPrickly = new ArrayList<>();
        noPrickly.add(new Fuzzy("blue"));
        noPrickly.add(new Fuzzy("green"));
        noPrickly.add(new Fuzzy("red"));
        noPrickly.sort((a, b) -> a.description().compareTo(b.description()));

        assertEquals(-1, finder.binarySearchPrickly(noPrickly));
    }

    @Test
    void generatorProducesCorrectListSizes() {
        FuzzyListGenerator generator = new FuzzyListGenerator(1000);
        ArrayList<Fuzzy> sorted = generator.sortedRainbowFuzzies();
        ArrayList<Feeling> feelings = generator.sortedFeelings();

        // 1000 iterations * 7 colors + 1 gold fuzzy = 7001
        assertEquals(7001, sorted.size());
        // Feelings list additionally includes 1 prickly = 7002
        assertEquals(7002, feelings.size());
    }
}

