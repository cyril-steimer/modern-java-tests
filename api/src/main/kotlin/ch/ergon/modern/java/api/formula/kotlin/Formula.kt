package ch.ergon.modern.java.api.formula.kotlin

sealed interface Formula

enum class BinaryOperator {
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE
}

data class BinaryFormula(val leftSide: Formula, val operator: BinaryOperator, val rightSide: Formula) : Formula

enum class UnaryOperator {
    PLUS,
    MINUS
}

data class UnaryFormula(val operator: UnaryOperator, val formula: Formula) : Formula

data class Constant(val value: Double) : Formula