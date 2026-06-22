

public class Parallelogram extends Shape implements Polygon {
    protected double base;
    protected double side;
    protected double height;

    public Parallelogram(double base, double side, double height) {
        this.base = base;
        this.side = side;
        this.height = height;
    }

    @Override
    public double getArea() {
        return base * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (base + side);
    }

    @Override
    public int numberOfSides() {
        return 4;
    }
}
