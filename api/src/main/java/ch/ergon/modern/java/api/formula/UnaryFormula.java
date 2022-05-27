package ch.ergon.modern.java.api.formula;

public record UnaryFormula(Formula formula, Operator operator) implements Formula {

    public enum Operator {
        PLUS,
        MINUS;
    }
}
