

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ShapesTest {

    // --- Circle ---

    @Test
    public void testCircleArea() {
        Circle c = new Circle(5);
        assertEquals(Math.PI * 25, c.getArea(), 0.0001);
    }

    @Test
    public void testCirclePerimeter() {
        Circle c = new Circle(5);
        assertEquals(2 * Math.PI * 5, c.getPerimeter(), 0.0001);
    }

    @Test
    public void testCircleAreaRadiusOne() {
        Circle c = new Circle(1);
        assertEquals(Math.PI, c.getArea(), 0.0001);
    }

    // --- Rectangle ---

    @Test
    public void testRectangleArea() {
        Rectangle r = new Rectangle(4, 6);
        assertEquals(24.0, r.getArea(), 0.0001);
    }

    @Test
    public void testRectanglePerimeter() {
        Rectangle r = new Rectangle(4, 6);
        assertEquals(20.0, r.getPerimeter(), 0.0001);
    }

    @Test
    public void testRectangleNumberOfSides() {
        Rectangle r = new Rectangle(4, 6);
        assertEquals(4, r.numberOfSides());
    }

    // --- Square ---

    @Test
    public void testSquareArea() {
        Square s = new Square(5);
        assertEquals(25.0, s.getArea(), 0.0001);
    }

    @Test
    public void testSquarePerimeter() {
        Square s = new Square(5);
        assertEquals(20.0, s.getPerimeter(), 0.0001);
    }

    @Test
    public void testSquareNumberOfSides() {
        Square s = new Square(5);
        assertEquals(4, s.numberOfSides());
    }

    @Test
    public void testSquareIsARectangle() {
        Square s = new Square(5);
        assertTrue(s instanceof Rectangle);
    }

    // --- RightTriangle ---

    @Test
    public void testRightTriangleArea() {
        RightTriangle t = new RightTriangle(3, 4);
        assertEquals(6.0, t.getArea(), 0.0001);
    }

    @Test
    public void testRightTrianglePerimeter() {
        // 3 + 4 + 5 = 12
        RightTriangle t = new RightTriangle(3, 4);
        assertEquals(12.0, t.getPerimeter(), 0.0001);
    }

    @Test
    public void testRightTriangleNumberOfSides() {
        RightTriangle t = new RightTriangle(3, 4);
        assertEquals(3, t.numberOfSides());
    }

    // --- IsoscelesRightTriangle ---

    @Test
    public void testIsoscelesRightTriangleArea() {
        // 0.5 * 5 * 5 = 12.5
        IsoscelesRightTriangle t = new IsoscelesRightTriangle(5);
        assertEquals(12.5, t.getArea(), 0.0001);
    }

    @Test
    public void testIsoscelesRightTrianglePerimeter() {
        // 5 + 5 + sqrt(50) = 10 + 7.0711 = 17.0711
        IsoscelesRightTriangle t = new IsoscelesRightTriangle(5);
        double expected = 5 + 5 + Math.sqrt(50);
        assertEquals(expected, t.getPerimeter(), 0.0001);
    }

    @Test
    public void testIsoscelesRightTriangleNumberOfSides() {
        IsoscelesRightTriangle t = new IsoscelesRightTriangle(5);
        assertEquals(3, t.numberOfSides());
    }

    @Test
    public void testIsoscelesRightTriangleIsARightTriangle() {
        IsoscelesRightTriangle t = new IsoscelesRightTriangle(5);
        assertTrue(t instanceof RightTriangle);
    }

    // --- Parallelogram ---

    @Test
    public void testParallelogramArea() {
        // base=6, side=4, height=3
        Parallelogram p = new Parallelogram(6, 4, 3);
        assertEquals(18.0, p.getArea(), 0.0001);
    }

    @Test
    public void testParallelogramPerimeter() {
        // 2 * (6 + 4) = 20
        Parallelogram p = new Parallelogram(6, 4, 3);
        assertEquals(20.0, p.getPerimeter(), 0.0001);
    }

    @Test
    public void testParallelogramNumberOfSides() {
        Parallelogram p = new Parallelogram(6, 4, 3);
        assertEquals(4, p.numberOfSides());
    }

    @Test
    public void testRectangleIsAParallelogram() {
        Rectangle r = new Rectangle(4, 6);
        assertTrue(r instanceof Parallelogram);
    }
}
