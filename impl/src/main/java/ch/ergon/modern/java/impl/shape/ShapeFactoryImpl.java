package ch.ergon.modern.java.impl.shape;

import ch.ergon.modern.java.api.shape.Circle;
import ch.ergon.modern.java.api.shape.Rectangle;
import ch.ergon.modern.java.api.shape.ShapeFactory;
import ch.ergon.modern.java.api.shape.Square;

public class ShapeFactoryImpl implements ShapeFactory {
    @Override
    public Circle createCircle(double radius) {
        return new CircleImpl(radius);
    }

    @Override
    public Rectangle createRectangle(double width, double height) {
        return new RectangleImpl(width, height);
    }

    @Override
    public Square createSquare(double side) {
        return new SquareImpl(side);
    }
}
