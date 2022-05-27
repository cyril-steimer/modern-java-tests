package ch.ergon.modern.java.impl.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CircleImplTest {

    @Test
    public void testArea() {
        var circle = new CircleImpl(10.0);
        assertEquals(314.1592654, circle.getArea(), 0.01);
    }

    @Test
    public void testEquals() {
        var circle1 = new CircleImpl(10.0);
        var circle2 = new CircleImpl(10.0);
        var circle3 = new CircleImpl(20.0);

        assertEquals(circle1, circle2);
        assertNotEquals(circle1, circle3);
    }
}
