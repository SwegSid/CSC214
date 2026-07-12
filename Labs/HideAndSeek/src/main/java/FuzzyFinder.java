
import java.util.ArrayList;

/**
 * Provides search algorithms for locating specific fuzzies (or feelings)
 * within a list.
 */
public class FuzzyFinder {

    /**
     * Scans every element in order until the gold fuzzy is found.
     * Works correctly regardless of whether the list is sorted.
     *
     * @return the index of the gold fuzzy, or -1 if not found
     */
    public int linearSearch(ArrayList<Fuzzy> fuzzies) {
        for (int i = 0; i < fuzzies.size(); i++) {
            if (fuzzies.get(i).color.equals("gold")) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Performs a binary search for the gold fuzzy. Only works
     * correctly if the list is sorted by color, since binary search
     * relies on being able to discard half of the remaining list at
     * each step based on ordering.
     *
     * @return the index of the gold fuzzy, or -1 if not found
     */
    public int binarySearch(ArrayList<Fuzzy> fuzzies) {
        int low = 0;
        int high = fuzzies.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midColor = fuzzies.get(mid).color;
            int comparison = midColor.compareTo("gold");

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // ----- Cold Pricklies add-on search methods -----

    /**
     * Scans every element in order until the cold prickly is found.
     * Works correctly regardless of whether the list is sorted.
     *
     * @return the index of the cold prickly, or -1 if not found
     */
    public int linearSearchPrickly(ArrayList<Feeling> feelings) {
        for (int i = 0; i < feelings.size(); i++) {
            if (feelings.get(i) instanceof Prickly) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Performs a binary search for the cold prickly by comparing
     * description() strings. Only works correctly if the list is
     * sorted by description().
     *
     * @return the index of the cold prickly, or -1 if not found
     */
    public int binarySearchPrickly(ArrayList<Feeling> feelings) {
        int low = 0;
        int high = feelings.size() - 1;
        String target = "Pokey!";

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midDescription = feelings.get(mid).description();
            int comparison = midDescription.compareTo(target);

            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
