package ch.ergon.modern.java.client.shape;

import ch.ergon.modern.java.api.shape.*;

public class ShapeAlgorithms {

    private final ShapeFactory shapeFactory = ShapeFactory.getInstance();

    public Shape copy(Shape original) {
        return switch (original) {
            case Circle c -> shapeFactory.createCircle(c.radius());
            case Square s -> shapeFactory.createSquare(s.side());
            case Rectangle r -> shapeFactory.createRectangle(r.width(), r.height());
        };
    }

    public Shape simplify(Shape original) {
        return switch (original) {
            case Rectangle r && r.width() == r.height() -> shapeFactory.createSquare(r.width());
            default -> original;
        };
    }
}
