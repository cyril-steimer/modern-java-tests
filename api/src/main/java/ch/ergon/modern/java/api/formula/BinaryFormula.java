package ch.ergon.modern.java.api.formula;


public record BinaryFormula(Formula leftSide, Operator operator, Formula rightSide) implements Formula {

    public enum Operator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE
    }
}
