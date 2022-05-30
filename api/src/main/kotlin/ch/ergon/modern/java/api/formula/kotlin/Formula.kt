package ch.ergon.modern.java.api.formula.kotlin

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

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

data class Variable(val name: String) : Formula

operator fun Formula.plus(other: Formula) = BinaryFormula(this, BinaryOperator.ADD, other)
operator fun Formula.minus(other: Formula) = BinaryFormula(this, BinaryOperator.SUBTRACT, other)
operator fun Formula.times(other: Formula) = BinaryFormula(this, BinaryOperator.MULTIPLY, other)
operator fun Formula.div(other: Formula) = BinaryFormula(this, BinaryOperator.DIVIDE, other)

operator fun Formula.unaryMinus() = UnaryFormula(UnaryOperator.MINUS, this)
operator fun Formula.unaryPlus() = UnaryFormula(UnaryOperator.PLUS, this)

private class VariableDelegate : ReadOnlyProperty<Any?, Variable> {
    override fun getValue(thisRef: Any?, property: KProperty<*>) = Variable(property.name)
}

fun variable(): ReadOnlyProperty<Any?, Variable> = VariableDelegate()