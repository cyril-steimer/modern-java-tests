package ch.ergon.modern.java.impl.shape;

import ch.ergon.modern.java.api.shape.Rectangle;

public record RectangleImpl(double width, double height) implements Rectangle {

    @Override
    public double getWidth() {
        return width;
    }

    @Override
    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}
