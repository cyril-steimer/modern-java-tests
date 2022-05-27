package ch.ergon.modern.java.impl.shape;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SquareImplTest {

    @Test
    public void testArea() {
        var square = new SquareImpl(10.0);
        assertEquals(100.0, square.getArea());
    }

    @Test
    public void testEquals() {
        var square1 = new SquareImpl(10.0);
        var square2 = new SquareImpl(10.0);
        var square3 = new SquareImpl(20.0);

        assertEquals(square1, square2);
        assertNotEquals(square1, square3);
    }
}
