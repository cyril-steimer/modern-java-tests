package ch.ergon.modern.java.impl.shape;

import ch.ergon.modern.java.api.shape.Square;

public record SquareImpl(double side) implements Square {

    @Override
    public double getSide() {
        return side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
}
