package ch.ergon.modern.java.client.shape;

import ch.ergon.modern.java.api.shape.Circle;
import ch.ergon.modern.java.api.shape.Rectangle;
import ch.ergon.modern.java.api.shape.ShapeFactory;
import ch.ergon.modern.java.api.shape.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeAlgorithmsTest {

    private final ShapeFactory factory = ShapeFactory.getInstance();

    private final ShapeAlgorithms algorithms = new ShapeAlgorithms();

    @Test
    public void testCopySquare() {
        var original = factory.createSquare(10.0);
        var copy = assertInstanceOf(Square.class, algorithms.copy(original));

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(10.0, copy.getSide());
    }

    @Test
    public void testCopyCircle() {
        var original = factory.createCircle(5.0);
        var copy = assertInstanceOf(Circle.class, algorithms.copy(original));

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(5.0, copy.getRadius());
    }

    @Test
    public void testCopyRectangle() {
        var original = factory.createRectangle(1.0, 2.0);
        var copy = assertInstanceOf(Rectangle.class, algorithms.copy(original));

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(1.0, copy.getWidth());
        assertEquals(2.0, copy.getHeight());
    }

    @Test
    public void testSimplifySquare() {
        var original = factory.createSquare(3.0);
        var simplified = assertInstanceOf(Square.class, algorithms.simplify(original));

        assertSame(original, simplified);
    }

    @Test
    public void testSimplifyCircle() {
        var original = factory.createCircle(7.5);
        var simplified = assertInstanceOf(Circle.class, algorithms.simplify(original));

        assertSame(original, simplified);
    }

    @Test
    public void testSimplifySquareRectangle() {
        var original = factory.createRectangle(5.0, 5.0);
        var simplified = assertInstanceOf(Square.class, algorithms.simplify(original));

        assertEquals(5.0, simplified.getSide());
    }

    @Test
    public void testSimplifyNormalRectangle() {
        var original = factory.createRectangle(5.0, 10.0);
        var simplified = assertInstanceOf(Rectangle.class, algorithms.simplify(original));

        assertSame(original, simplified);
    }
}
