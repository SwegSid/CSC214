
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Zipper zipper = new Zipper();

        List<Integer> nums1 = List.of(1, 3, 5, 7);
        List<Integer> nums2 = List.of(2, 4, 6, 8);
        List<Integer> mergedNumbers = zipper.zip(nums1, nums2);
        System.out.println(mergedNumbers); // [1, 2, 3, 4, 5, 6, 7, 8]

        List<String> colors1 = List.of("Red", "Green", "Blue");
        List<String> colors2 = List.of("White", "Black", "Orange", "Pink", "Fuschia");
        List<String> mergedWords = zipper.zip(colors1, colors2);
        System.out.println(mergedWords); // [Red, White, Green, Black, Blue, Orange, Pink, Fuschia]

        List<String> keys = List.of("White", "Black", "Orange", "Pink");
        List<Integer> values = List.of(1, 3, 5, 7);
        HashMap<String, Integer> map = zipper.hashmapify(keys, values);
        System.out.println(map); // {White=1, Black=3, Orange=5, Pink=7}
    }
}
