public class Kitty implements Cutie {
    private String name;

    public Kitty(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String makeCuteSound() {
        return "Meow!";
    }
}
