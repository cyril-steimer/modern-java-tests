package ch.ergon.modern.java.api.shape;

public sealed interface Shape permits Circle, Square, Rectangle {
    double getArea();
}
