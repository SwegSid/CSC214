public class Squirrel {

    private final String name;
    private final int age;
    private final String funFact;

    public Squirrel(String name, int age, String funFact) {
        this.name = name;
        this.age = age;
        this.funFact = funFact;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFunFact() {
        return funFact;
    }

    @Override
    public String toString() {
        return name + " (age " + age + ") - " + funFact;
    }
}
