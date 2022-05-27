package ch.ergon.modern.java.client.shape;

import ch.ergon.modern.java.api.shape.Shape;
import ch.ergon.modern.java.api.shape.ShapeFactory;

public class ShapeParser {

    private final ShapeFactory shapeFactory = ShapeFactory.getInstance();

    public Shape parseShape(String shapeString) {
        return shapeFactory.createCircle(10.0);
    }
}
