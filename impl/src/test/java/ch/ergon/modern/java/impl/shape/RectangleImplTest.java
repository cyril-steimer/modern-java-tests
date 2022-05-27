package ch.ergon.modern.java.impl.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RectangleImplTest {

    @Test
    public void testArea() {
        var rectangle = new RectangleImpl(10.0, 5.0);
        assertEquals(50.0, rectangle.getArea());
    }

    @Test
    public void testWidth() {
        var rectangle = new RectangleImpl(10.0, 5.0);
        assertEquals(10.0, rectangle.width());
    }

    @Test
    public void testHeight() {
        var rectangle = new RectangleImpl(10.0, 5.0);
        assertEquals(5.0, rectangle.height());
    }

    @Test
    public void testEquals() {
        var rectangle1 = new RectangleImpl(10.0, 5.0);
        var rectangle2 = new RectangleImpl(10.0, 5.0);
        var rectangle3 = new RectangleImpl(10.0, 10.0);
        var rectangle4 = new RectangleImpl(5.0, 5.0);

        assertEquals(rectangle1, rectangle2);
        assertNotEquals(rectangle1, rectangle3);
        assertNotEquals(rectangle1, rectangle4);
    }
}
