package ch.ergon.modern.java.impl.shape;

import ch.ergon.modern.java.api.shape.Square;

public record SquareImpl(double side) implements Square {

    public SquareImpl(double side) {
        // Canonical constructor example (not recommended)
        if (side <= 0) {
            throw new IllegalArgumentException("Side must be >= 0 (was " + side + ")");
        }
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
}
