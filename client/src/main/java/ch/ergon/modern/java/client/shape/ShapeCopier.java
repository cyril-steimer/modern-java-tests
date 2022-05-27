package ch.ergon.modern.java.client.shape;

import ch.ergon.modern.java.api.shape.*;

public class ShapeCopier {

    private final ShapeFactory shapeFactory = ShapeFactory.getInstance();

    public Shape copy(Shape original) {
        return switch (original) {
            case Circle c -> shapeFactory.createCircle(c.getRadius());
            case Square s -> shapeFactory.createSquare(s.getSide());
            case Rectangle r -> shapeFactory.createRectangle(r.getWidth(), r.getHeight());
        };
    }
}
