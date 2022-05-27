package ch.ergon.modern.java.api.shape;

public interface ShapeFactory {
    static ShapeFactory getInstance() {
        try {
            Class<?> cls = Class.forName("ch.ergon.modern.java.impl.shape.ShapeFactoryImpl");
            Object factory = cls.getDeclaredConstructor().newInstance();
            return (ShapeFactory) factory;
        } catch (Exception e) {
            throw new IllegalStateException("Failed to get a ShapeFactory instance", e);
        }
    }

    Circle createCircle(double radius);

    Rectangle createRectangle(double width, double height);

    Square createSquare(double side);
}
