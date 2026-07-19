public class Puppy implements Cutie {
    private String name;

    public Puppy(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String makeCuteSound() {
        return "Woof!";
    }
}
