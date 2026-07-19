import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class DuplicateRemover {

    /**
     * Returns a new ArrayList containing the elements of the given list
     * with any duplicate entries removed. The order of first occurrence
     * is preserved.
     */
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {
        if (list == null) {
            throw new IllegalArgumentException("list cannot be null");
        }

        // LinkedHashSet removes duplicates while preserving insertion order.
        Set<T> seen = new LinkedHashSet<>(list);
        return new ArrayList<>(seen);
    }
}
