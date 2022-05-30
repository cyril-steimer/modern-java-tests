package ch.ergon.modern.java.client.shape.kotlin

import ch.ergon.modern.java.api.shape.kotlin.*

private val shapeFactory = ShapeFactory.instance

fun Shape.copy(): Shape = when (this) {
    is Circle -> shapeFactory.createCircle(radius)
    is Square -> shapeFactory.createSquare(side)
    is Rectangle -> shapeFactory.createRectangle(width, height)
}

fun Shape.simplify(): Shape = when (this) {
    // Doesn't seem possible to add further conditions to a when in Kotlin
    is Rectangle -> if (width == height) shapeFactory.createSquare(width) else this
    else -> this
}