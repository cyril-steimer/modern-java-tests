package ch.ergon.modern.java.client.shape.kotlin;

import ch.ergon.modern.java.api.shape.kotlin.Circle;
import ch.ergon.modern.java.api.shape.kotlin.Rectangle;
import ch.ergon.modern.java.api.shape.kotlin.ShapeFactory;
import ch.ergon.modern.java.api.shape.kotlin.Square;
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test;

class ShapeAlgorithmsTest {

    private val factory = ShapeFactory.instance;

    @Test
    fun testCopySquare() {
        val original = factory.createSquare(10.0);
        val copy = assertInstanceOf(Square::class.java, original.copy())

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(10.0, copy.side);
    }

    @Test
    fun testCopyCircle() {
        val original = factory.createCircle(5.0);
        val copy = assertInstanceOf(Circle::class.java, original.copy())

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(5.0, copy.radius);
    }

    @Test
    fun testCopyRectangle() {
        val original = factory.createRectangle(1.0, 2.0);
        val copy = assertInstanceOf(Rectangle::class.java, original.copy())

        assertNotSame(original, copy);
        assertEquals(original, copy);
        assertEquals(1.0, copy.width);
        assertEquals(2.0, copy.height);
    }

    @Test
    fun testSimplifySquare() {
        val original = factory.createSquare(3.0);
        val simplified = assertInstanceOf(Square::class.java, original.simplify())

        assertSame(original, simplified);
    }

    @Test
    fun testSimplifyCircle() {
        val original = factory.createCircle(7.5);
        val simplified = assertInstanceOf(Circle::class.java, original.simplify())

        assertSame(original, simplified);
    }

    @Test
    fun testSimplifySquareRectangle() {
        val original = factory.createRectangle(5.0, 5.0);
        val simplified = assertInstanceOf(Square::class.java, original.simplify())

        assertEquals(5.0, simplified.side);
    }

    @Test
    fun testSimplifyNormalRectangle() {
        val original = factory.createRectangle(5.0, 10.0);
        val simplified = assertInstanceOf(Rectangle::class.java, original.simplify())

        assertSame(original, simplified);
    }
}
