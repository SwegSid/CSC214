
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Zipper {

    /**
     * Merges two lists containing elements of the same type into a single
     * list that alternates between elements of each original list. If one
     * list is longer than the other, the leftover elements are added
     * to the end of the output list in their original order.
     */
    public <T> List<T> zip(List<T> first, List<T> second) {
        List<T> result = new ArrayList<>();

        int maxSize = Math.max(first.size(), second.size());

        for (int i = 0; i < maxSize; i++) {
            if (i < first.size()) {
                result.add(first.get(i));
            }
            if (i < second.size()) {
                result.add(second.get(i));
            }
        }

        return result;
    }

    /**
     * Creates a HashMap by pairing up keys from the first list with values
     * from the second list, based on matching index. Throws an
     * IllegalArgumentException if the two lists do not have the same size.
     */
    public <V> HashMap<String, V> hashmapify(List<String> keys, List<V> values) {
        if (keys.size() != values.size()) {
            throw new IllegalArgumentException(
                    "Keys and values lists must be the same size, but got " +
                            keys.size() + " keys and " + values.size() + " values."
            );
        }

        HashMap<String, V> map = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), values.get(i));
        }

        return map;
    }
}
