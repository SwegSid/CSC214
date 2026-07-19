import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> empty = new ArrayList<>();
        System.out.println(DuplicateRemover.removeDuplicates(empty)); // []

        ArrayList<Integer> single = new ArrayList<>(List.of(1));
        System.out.println(DuplicateRemover.removeDuplicates(single)); // [1]

        ArrayList<Integer> noDupes = new ArrayList<>(List.of(1, 2, 3));
        System.out.println(DuplicateRemover.removeDuplicates(noDupes)); // [1, 2, 3]

        ArrayList<String> letters = new ArrayList<>(List.of("A", "A", "B"));
        System.out.println(DuplicateRemover.removeDuplicates(letters)); // [A, B]

        ArrayList<Integer> numbers = new ArrayList<>(List.of(1, 1, 3, 3, 5, 5));
        System.out.println(DuplicateRemover.removeDuplicates(numbers)); // [1, 3, 5]
    }
}
