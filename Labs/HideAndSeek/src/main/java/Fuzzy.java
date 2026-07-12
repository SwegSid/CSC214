

/**
 * A simple warm fuzzy object. The color field is publicly accessible
 * so search algorithms can inspect it directly.
 */
public class Fuzzy implements Feeling {

    public String color;

    public Fuzzy(String color) {
        this.color = color;
    }

    /**
     * A warm fuzzy's description is simply its color.
     */
    @Override
    public String description() {
        return color;
    }

    @Override
    public String toString() {
        return "Fuzzy(" + color + ")";
    }
}
