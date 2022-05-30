package ch.ergon.modern.java.client.formula.kotlin

import ch.ergon.modern.java.api.formula.kotlin.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FormulaEvaluatorTest {

    private val positiveConstant = Constant(10.0)
    private val negativeConstant = Constant(-20.0)

    @Test
    fun testEvaluateSimpleBinaryFormula() {
        val add = BinaryFormula(positiveConstant, BinaryOperator.ADD, negativeConstant)
        assertEquals(-10.0, add.evaluate())
        val subtract = BinaryFormula(positiveConstant, BinaryOperator.SUBTRACT, negativeConstant)
        assertEquals(30.0, subtract.evaluate())
        val multiply = BinaryFormula(positiveConstant, BinaryOperator.MULTIPLY, negativeConstant)
        assertEquals(-200.0, multiply.evaluate())
        val divide = BinaryFormula(positiveConstant, BinaryOperator.DIVIDE, negativeConstant)
        assertEquals(-0.5, divide.evaluate())
    }


    @Test
    fun testEvaluateSimpleUnaryFormula() {
        val plus = UnaryFormula(UnaryOperator.PLUS, positiveConstant)
        assertEquals(10.0, plus.evaluate())
        val minus = UnaryFormula(UnaryOperator.MINUS, negativeConstant)
        assertEquals(20.0, minus.evaluate())
    }

    @Test
    fun testEvaluateConstant() {
        assertEquals(10.0, positiveConstant.evaluate())
        assertEquals(-20.0, negativeConstant.evaluate())
    }

    @Test
    fun testEvaluateComplexFormula() {
        val negatedPositiveConstant = UnaryFormula(UnaryOperator.MINUS, positiveConstant)
        val negativeAdd = BinaryFormula(
            negatedPositiveConstant,
            BinaryOperator.ADD,
            negativeConstant
        )
        assertEquals(-30.0, negativeAdd.evaluate())
        val negativeMultiply = BinaryFormula(
            negatedPositiveConstant,
            BinaryOperator.MULTIPLY,
            negativeConstant
        )
        assertEquals(200.0, negativeMultiply.evaluate())
        val complexFormula = BinaryFormula(
            negativeAdd,
            BinaryOperator.SUBTRACT,
            negativeMultiply
        )
        assertEquals(-230.0, complexFormula.evaluate())
    }
}