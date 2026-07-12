

/**
 * A cold prickly. It always describes itself the same way, regardless
 * of any internal state, since cold pricklies are uniformly unpleasant.
 */
public class Prickly implements Feeling {

    @Override
    public String description() {
        return "Pokey!";
    }

    @Override
    public String toString() {
        return "Prickly(Pokey!)";
    }
}
