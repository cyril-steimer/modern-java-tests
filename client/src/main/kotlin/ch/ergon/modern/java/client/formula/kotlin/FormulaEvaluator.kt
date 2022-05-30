package ch.ergon.modern.java.client.formula.kotlin

import ch.ergon.modern.java.api.formula.kotlin.*

fun Formula.evaluate(): Double = when (this) {
    is Constant -> value
    is BinaryFormula -> evaluate()
    is UnaryFormula -> evaluate()
}

private fun BinaryFormula.evaluate(): Double = when (operator) {
    BinaryOperator.ADD -> leftSide.evaluate() + rightSide.evaluate()
    BinaryOperator.SUBTRACT -> leftSide.evaluate() - rightSide.evaluate()
    BinaryOperator.MULTIPLY -> leftSide.evaluate() * rightSide.evaluate()
    BinaryOperator.DIVIDE -> leftSide.evaluate() / rightSide.evaluate()
}

private fun UnaryFormula.evaluate(): Double = when (operator) {
    UnaryOperator.PLUS -> formula.evaluate()
    UnaryOperator.MINUS -> -formula.evaluate()
}