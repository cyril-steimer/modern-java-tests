package ch.ergon.modern.java.impl.shape;

import ch.ergon.modern.java.api.shape.Circle;

public record CircleImpl(double radius) implements Circle {

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
