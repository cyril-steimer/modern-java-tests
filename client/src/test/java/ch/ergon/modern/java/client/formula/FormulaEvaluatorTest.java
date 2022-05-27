package ch.ergon.modern.java.client.formula;

import ch.ergon.modern.java.api.formula.BinaryFormula;
import ch.ergon.modern.java.api.formula.Constant;
import ch.ergon.modern.java.api.formula.Formula;
import ch.ergon.modern.java.api.formula.UnaryFormula;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormulaEvaluatorTest {

    private final FormulaEvaluator evaluator = new FormulaEvaluator();

    private final Formula positiveConstant = new Constant(10.0);

    private final Formula negativeConstant = new Constant(-20.0);

    @Test
    public void testEvaluateSimpleBinaryFormula() {
        var add = new BinaryFormula(positiveConstant, BinaryFormula.Operator.ADD, negativeConstant);
        assertEquals(-10.0, evaluator.evaluate(add));

        var subtract = new BinaryFormula(positiveConstant, BinaryFormula.Operator.SUBTRACT, negativeConstant);
        assertEquals(30.0, evaluator.evaluate(subtract));

        var multiply = new BinaryFormula(positiveConstant, BinaryFormula.Operator.MULTIPLY, negativeConstant);
        assertEquals(-200.0, evaluator.evaluate(multiply));

        var divide = new BinaryFormula(positiveConstant, BinaryFormula.Operator.DIVIDE, negativeConstant);
        assertEquals(-0.5, evaluator.evaluate(divide));
    }

    @Test
    public void testEvaluateSimpleUnaryFormula() {
        var plus = new UnaryFormula(positiveConstant, UnaryFormula.Operator.PLUS);
        assertEquals(10.0, evaluator.evaluate(plus));

        var minus = new UnaryFormula(negativeConstant, UnaryFormula.Operator.MINUS);
        assertEquals(20.0, evaluator.evaluate(minus));
    }

    @Test
    public void testEvaluateConstant() {
        assertEquals(10.0, evaluator.evaluate(positiveConstant));
        assertEquals(-20.0, evaluator.evaluate(negativeConstant));
    }

    @Test
    public void testEvaluateComplexFormula() {
        var negatedPositiveConstant = new UnaryFormula(positiveConstant, UnaryFormula.Operator.MINUS);
        var negativeAdd = new BinaryFormula(negatedPositiveConstant, BinaryFormula.Operator.ADD, negativeConstant);
        assertEquals(-30.0, evaluator.evaluate(negativeAdd));

        var negativeMultiply = new BinaryFormula(negatedPositiveConstant, BinaryFormula.Operator.MULTIPLY, negativeConstant);
        assertEquals(200.0, evaluator.evaluate(negativeMultiply));

        var complexFormula = new BinaryFormula(negativeAdd, BinaryFormula.Operator.SUBTRACT, negativeMultiply);
        assertEquals(-230.0, evaluator.evaluate(complexFormula));
    }
}
