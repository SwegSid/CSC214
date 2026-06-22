
public class Rectangle extends Parallelogram {
    protected double length;
    protected double width;

    public Rectangle(double length, double width) {

        super(length, width, width);
        this.length = length;
        this.width = width;
    }

    @Override
    public double getArea() {
        return length * width;
    }

    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    @Override
    public int numberOfSides() {
        return 4;
    }
}
