package ch.ergon.modern.java.impl.shape;

import ch.ergon.modern.java.api.shape.Circle;

public record CircleImpl(double radius) implements Circle {

    public CircleImpl {
        // Compact constructor example (recommended)
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be >= 0 (was " + radius + ")");
        }
    }

    @Override
    public double radius() {
        // It's also possible to define our own accessor (although it's unclear as to why one would do this..)
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}
