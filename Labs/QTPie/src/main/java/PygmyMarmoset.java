public class PygmyMarmoset implements Cutie {
    private String name;

    public PygmyMarmoset(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String makeCuteSound() {
        return "Squeak!";
    }
}
