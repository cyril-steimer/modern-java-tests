package ch.ergon.modern.java.client.formula;

import ch.ergon.modern.java.api.formula.BinaryFormula;
import ch.ergon.modern.java.api.formula.Constant;
import ch.ergon.modern.java.api.formula.Formula;
import ch.ergon.modern.java.api.formula.UnaryFormula;

public class FormulaEvaluator {

    public double evaluate(Formula formula) {
        return switch (formula) {
            case Constant c -> c.value();
            case BinaryFormula b -> evaluateBinaryFormula(b);
            case UnaryFormula u -> evaluateUnaryFormula(u);
        };
    }

    private double evaluateBinaryFormula(BinaryFormula formula) {
        return switch (formula.operator()) {
            case ADD -> evaluate(formula.leftSide()) + evaluate(formula.rightSide());
            case SUBTRACT -> evaluate(formula.leftSide()) - evaluate(formula.rightSide());
            case MULTIPLY -> evaluate(formula.leftSide()) * evaluate(formula.rightSide());
            case DIVIDE -> evaluate(formula.leftSide()) / evaluate(formula.rightSide());
        };
    }

    private double evaluateUnaryFormula(UnaryFormula formula) {
        return switch (formula.operator()) {
            case PLUS -> evaluate(formula.formula());
            case MINUS -> -evaluate(formula.formula());
        };
    }
}
