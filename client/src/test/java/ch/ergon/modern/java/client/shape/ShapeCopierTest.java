package ch.ergon.modern.java.client.shape;

import ch.ergon.modern.java.api.shape.Circle;
import ch.ergon.modern.java.api.shape.Rectangle;
import ch.ergon.modern.java.api.shape.ShapeFactory;
import ch.ergon.modern.java.api.shape.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeCopierTest {

    private final ShapeFactory factory = ShapeFactory.getInstance();

    private final ShapeCopier copier = new ShapeCopier();

    @Test
    public void testCopySquare() {
        var original = factory.createSquare(10.0);
        var copy = assertInstanceOf(Square.class, copier.copy(original));

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(10.0, copy.getSide());
    }

    @Test
    public void testCopyCircle() {
        var original = factory.createCircle(5.0);
        var copy = assertInstanceOf(Circle.class, copier.copy(original));

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(5.0, copy.getRadius());
    }

    @Test
    public void testCopyRectangle() {
        var original = factory.createRectangle(1.0, 2.0);
        var copy = assertInstanceOf(Rectangle.class, copier.copy(original));

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(1.0, copy.getWidth());
        assertEquals(2.0, copy.getHeight());
    }
}
