package ch.ergon.modern.java.impl.shape.kotlin

import ch.ergon.modern.java.api.shape.kotlin.Circle
import ch.ergon.modern.java.api.shape.kotlin.Rectangle
import ch.ergon.modern.java.api.shape.kotlin.ShapeFactory
import ch.ergon.modern.java.api.shape.kotlin.Square
import kotlin.math.pow

data class CircleImpl(override val radius: Double) : Circle {
    override val area: Double = Math.PI * radius.pow(2)
}

data class RectangleImpl(override val width: Double, override val height: Double) : Rectangle {
    override val area: Double = width * height
}

data class SquareImpl(override val side: Double) : Square {
    override val area: Double = side.pow(2)
}

class ShapeFactoryImpl : ShapeFactory {
    override fun createCircle(radius: Double) = CircleImpl(radius)
    override fun createRectangle(width: Double, height: Double) = RectangleImpl(width, height)
    override fun createSquare(side: Double) = SquareImpl(side)
}