package ch.ergon.modern.java.client.formula.kotlin

import ch.ergon.modern.java.api.formula.kotlin.*

fun Formula.evaluate(variables: Map<String, Double>): Double = when (this) {
    is Constant -> value
    is BinaryFormula -> evaluate()
    is UnaryFormula -> evaluate()
    is Variable -> variables[name] ?: throw IllegalStateException("Undefined value for variable '$name'")
}

fun Formula.evaluate(): Double = evaluate(emptyMap())

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