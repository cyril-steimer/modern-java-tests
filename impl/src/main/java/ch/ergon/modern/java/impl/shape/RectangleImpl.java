package ch.ergon.modern.java.impl.shape;

import ch.ergon.modern.java.api.shape.Rectangle;

public record RectangleImpl(double width, double height) implements Rectangle {

    @Override
    public double getArea() {
        return width * height;
    }
}
