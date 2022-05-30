package ch.ergon.modern.java.api.shape.kotlin

sealed interface Shape {
    val area: Double
}

interface Circle : Shape {
    val radius: Double
}

interface Rectangle : Shape {
    val width: Double
    val height: Double
}

interface Square : Shape {
    val side: Double
}

interface ShapeFactory {
    fun createCircle(radius: Double): Circle
    fun createRectangle(width: Double, height: Double): Rectangle
    fun createSquare(side: Double): Square

    companion object {
        val instance: ShapeFactory by lazy {
            val cls = Class.forName("ch.ergon.modern.java.impl.shape.kotlin.ShapeFactoryImpl")
            cls.getConstructor().newInstance() as ShapeFactory
        }
    }
}