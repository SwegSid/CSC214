import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DuplicateRemoverTest {

    @Test
    public void testEmptyList() {
        ArrayList<Integer> input = new ArrayList<>();
        ArrayList<Integer> result = DuplicateRemover.removeDuplicates(input);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSingleElement() {
        ArrayList<Integer> input = new ArrayList<>(List.of(1));
        ArrayList<Integer> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(List.of(1), result);
    }

    @Test
    public void testNoDuplicates() {
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 2, 3));
        ArrayList<Integer> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(List.of(1, 2, 3), result);
    }

    @Test
    public void testStringDuplicates() {
        ArrayList<String> input = new ArrayList<>(List.of("A", "A", "B"));
        ArrayList<String> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(List.of("A", "B"), result);
    }

    @Test
    public void testMultipleDuplicates() {
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 1, 3, 3, 5, 5));
        ArrayList<Integer> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(List.of(1, 3, 5), result);
    }

    @Test
    public void testAllDuplicates() {
        ArrayList<String> input = new ArrayList<>(List.of("X", "X", "X", "X"));
        ArrayList<String> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(List.of("X"), result);
    }

    @Test
    public void testPreservesFirstOccurrenceOrder() {
        ArrayList<Integer> input = new ArrayList<>(List.of(3, 1, 2, 1, 3, 2));
        ArrayList<Integer> result = DuplicateRemover.removeDuplicates(input);
        assertEquals(List.of(3, 1, 2), result);
    }

    @Test
    public void testOriginalListUnmodified() {
        ArrayList<Integer> input = new ArrayList<>(List.of(1, 1, 2));
        DuplicateRemover.removeDuplicates(input);
        // The original list passed in should remain untouched.
        assertEquals(List.of(1, 1, 2), input);
    }

    @Test
    public void testNullListThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            DuplicateRemover.removeDuplicates(null);
        });
    }
}
