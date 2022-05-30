package ch.ergon.modern.java.client.formula.kotlin

import ch.ergon.modern.java.api.formula.kotlin.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class FormulaEvaluatorTest {

    private val positiveConstant = Constant(10.0)
    private val negativeConstant = Constant(-20.0)

    @Test
    fun testEvaluateSimpleBinaryFormula() {
        val add = positiveConstant + negativeConstant
        assertEquals(-10.0, add.evaluate())
        val subtract = positiveConstant - negativeConstant
        assertEquals(30.0, subtract.evaluate())
        val multiply = positiveConstant * negativeConstant
        assertEquals(-200.0, multiply.evaluate())
        val divide = positiveConstant / negativeConstant
        assertEquals(-0.5, divide.evaluate())
    }


    @Test
    fun testEvaluateSimpleUnaryFormula() {
        val plus = +positiveConstant
        assertEquals(10.0, plus.evaluate())
        val minus = -negativeConstant
        assertEquals(20.0, minus.evaluate())
    }

    @Test
    fun testEvaluateConstant() {
        assertEquals(10.0, positiveConstant.evaluate())
        assertEquals(-20.0, negativeConstant.evaluate())
    }

    @Test
    fun testEvaluateComplexFormula() {
        val negatedPositiveConstant = -positiveConstant
        val negativeAdd = negatedPositiveConstant + negativeConstant
        assertEquals(-30.0, negativeAdd.evaluate())
        val negativeMultiply = negatedPositiveConstant * negativeConstant
        assertEquals(200.0, negativeMultiply.evaluate())
        val complexFormula = negativeAdd - negativeMultiply
        assertEquals(-230.0, complexFormula.evaluate())
    }
}