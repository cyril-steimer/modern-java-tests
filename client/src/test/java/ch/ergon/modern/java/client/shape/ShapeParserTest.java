package ch.ergon.modern.java.client.shape;

import ch.ergon.modern.java.api.shape.Circle;
import ch.ergon.modern.java.api.shape.Rectangle;
import ch.ergon.modern.java.api.shape.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ShapeParserTest {

    private final ShapeParser parser = new ShapeParser();

    @Test
    public void testParseCircle() {
        var shape = parser.parseShape("Circle(6.6)");
        var circle = assertInstanceOf(Circle.class, shape);
        assertEquals(6.6, circle.getRadius());
    }

    @Test
    public void testParseRectangle() {
        var shape = parser.parseShape("Rectangle(3.3, 5.2)");
        var rectangle = assertInstanceOf(Rectangle.class, shape);
        assertEquals(3.3, rectangle.getWidth());
        assertEquals(5.2, rectangle.getHeight());
    }

    @Test
    public void testParseSquare() {
        var shape = parser.parseShape("Square(7.5)");
        var square = assertInstanceOf(Square.class, shape);
        assertEquals(7.5, square.getSide());
    }
}
