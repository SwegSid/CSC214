public class Dish {
    private String name;
    private String material;

    public Dish(String name, String material) {
        this.name = name;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public String getMaterial() {
        return material;
    }

    @Override
    public String toString() {
        return name + " (" + material + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Dish)) return false;
        Dish other = (Dish) obj;
        return name.equals(other.name) && material.equals(other.material);
    }

    @Override
    public int hashCode() {
        return name.hashCode() * 31 + material.hashCode();
    }
}
