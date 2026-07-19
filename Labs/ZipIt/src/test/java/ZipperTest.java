
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ZipperTest {

    private final Zipper zipper = new Zipper();

    @Test
    public void testZipEqualSizeIntegerLists() {
        List<Integer> first = List.of(1, 3, 5, 7);
        List<Integer> second = List.of(2, 4, 6, 8);

        List<Integer> result = zipper.zip(first, second);

        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8), result);
    }

    @Test
    public void testZipFirstListLonger() {
        List<String> first = List.of("Red", "Green", "Blue", "Yellow", "Purple");
        List<String> second = List.of("White", "Black", "Orange");

        List<String> result = zipper.zip(first, second);

        assertEquals(
                List.of("Red", "White", "Green", "Black", "Blue", "Orange", "Yellow", "Purple"),
                result
        );
    }

    @Test
    public void testZipSecondListLonger() {
        List<String> first = List.of("Red", "Green", "Blue");
        List<String> second = List.of("White", "Black", "Orange", "Pink", "Fuschia");

        List<String> result = zipper.zip(first, second);

        assertEquals(
                List.of("Red", "White", "Green", "Black", "Blue", "Orange", "Pink", "Fuschia"),
                result
        );
    }

    @Test
    public void testZipWithEmptyFirstList() {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = List.of(1, 2, 3);

        List<Integer> result = zipper.zip(first, second);

        assertEquals(List.of(1, 2, 3), result);
    }

    @Test
    public void testZipWithEmptySecondList() {
        List<Integer> first = List.of(1, 2, 3);
        List<Integer> second = new ArrayList<>();

        List<Integer> result = zipper.zip(first, second);

        assertEquals(List.of(1, 2, 3), result);
    }

    @Test
    public void testZipWithBothListsEmpty() {
        List<Integer> first = new ArrayList<>();
        List<Integer> second = new ArrayList<>();

        List<Integer> result = zipper.zip(first, second);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testZipWithDoubleType() {
        List<Double> first = List.of(1.1, 2.2, 3.3);
        List<Double> second = List.of(9.9, 8.8, 7.7);

        List<Double> result = zipper.zip(first, second);

        assertEquals(List.of(1.1, 9.9, 2.2, 8.8, 3.3, 7.7), result);
    }

    @Test
    public void testZipWithCustomObjectType() {
        List<Fuzzy> first = List.of(new Fuzzy("red"), new Fuzzy("blue"));
        List<Fuzzy> second = List.of(new Fuzzy("green"), new Fuzzy("gold"));

        List<Fuzzy> result = zipper.zip(first, second);

        assertEquals("red", result.get(0).color);
        assertEquals("green", result.get(1).color);
        assertEquals("blue", result.get(2).color);
        assertEquals("gold", result.get(3).color);
    }

    @Test
    public void testHashmapifyCreatesCorrectMap() {
        List<String> keys = List.of("White", "Black", "Orange", "Pink");
        List<Integer> values = List.of(1, 3, 5, 7);

        HashMap<String, Integer> map = zipper.hashmapify(keys, values);

        assertEquals(4, map.size());
        assertEquals(1, map.get("White"));
        assertEquals(3, map.get("Black"));
        assertEquals(5, map.get("Orange"));
        assertEquals(7, map.get("Pink"));
    }

    @Test
    public void testHashmapifyWithStringValues() {
        List<String> keys = List.of("first", "second");
        List<String> values = List.of("Alice", "Bob");

        HashMap<String, String> map = zipper.hashmapify(keys, values);

        assertEquals("Alice", map.get("first"));
        assertEquals("Bob", map.get("second"));
    }

    @Test
    public void testHashmapifyThrowsWhenSizesDiffer() {
        List<String> keys = List.of("White", "Black", "Orange");
        List<Integer> values = List.of(1, 3);

        assertThrows(IllegalArgumentException.class, () -> zipper.hashmapify(keys, values));
    }

    @Test
    public void testHashmapifyWithEmptyLists() {
        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();

        HashMap<String, Integer> map = zipper.hashmapify(keys, values);

        assertTrue(map.isEmpty());
    }

    // Simple helper class used to prove zip() works with custom object types.
    private static class Fuzzy {
        final String color;

        Fuzzy(String color) {
            this.color = color;
        }
    }
}
